//problema cu STATISTICA STRING URI

/*import java.util.*;
abstract class STATISTICA{
    protected String jurnal;

    public STATISTICA(){
        jurnal="";
    }
    public abstract void calculeaza(ArrayList<String>secventa);

    public String toString(){
        return jurnal;
    }
}

class StatisticaNrAparitii extends STATISTICA{
    private ArrayList<String> cautate;

    public StatisticaNrAparitii(ArrayList<String> secv){
        cautate=secv;
    }

    public void calculeaza(ArrayList<String>secventa){
        int x=0;
        for(String s:secventa){
            for(String p:cautate)
                if(s.equals(p))
                    x++;
        }
        jurnal="In secventa { ";
        Iterator<String> it=secventa.iterator();
        while(it.hasNext())
            jurnal+= it.next()+" ";
        jurnal+="} apar "+x+" siruri din secventa { ";
        Iterator<String> it2= cautate.iterator();
        while(it2.hasNext())
            jurnal+=it2.next()+" ";
        jurnal+="}.\n";
    }
}

class StatisticaNrNonReale extends STATISTICA{

    public void calculeaza(ArrayList<String> secventa) {
        int y=0;
        jurnal="In secventa { ";
        for(String s:secventa){
            try{
                Double.parseDouble(s);
            }catch(NumberFormatException e){
                y++;
            }
        }
        Iterator<String> it= secventa.iterator();
        while(it.hasNext())
            jurnal+=it.next()+" ";
        jurnal+="} avem "+y+" siruri ce nu sunt numerale reale.\n";
    }
}

class Executor{
    private ArrayList<STATISTICA> lista;

    public Executor(ArrayList<STATISTICA> lista){
        this.lista=lista;
    }

    public void executa(ArrayList<String> secventa){
        for(STATISTICA s:lista)
        {
            s.calculeaza(secventa);
        }
        String aux="";
        for(STATISTICA s:lista)
            aux+=s.toString();
        System.out.println(aux);
    }
}

class Main{
    public static void main(String[] args){
        ArrayList<String> l1=new ArrayList<String>(); //pt aparitii;
        l1.add("mere");
        l1.add("pere");
        l1.add("banane");
        StatisticaNrAparitii stat1=new StatisticaNrAparitii(l1);
        StatisticaNrNonReale stat2=new StatisticaNrNonReale();

        ArrayList<STATISTICA> lista=new ArrayList<STATISTICA>(); //pt executor
        lista.add(stat1);
        lista.add(stat2);
        Executor e=new Executor(lista);

        //pt secvente
        ArrayList<String> secv1=new ArrayList<String>();
        secv1.add("Ana");
        secv1.add("are");
        secv1.add("mere");
        e.executa(secv1);

        ArrayList<String> secv2=new ArrayList<String>();
        secv2.add("Maria");
        secv2.add("1");
        secv2.add("pere");
        e.executa(secv2);
    }
}*/

//problema cu Utilizator

/*import java.util.*;

abstract class DESTINATAR{
    protected String nume;

    public DESTINATAR(String nume){
        this.nume=nume;
    }
    public abstract void receptioneaza(UTILIZATOR expeditor,String mesaj);

    public boolean equals(Object obj){
        if(obj instanceof DESTINATAR){
            DESTINATAR aux=(DESTINATAR)obj;
            return aux.nume.equals(this.nume);
        }
        else return false;
    }
}

class UTILIZATOR extends DESTINATAR{
    private String jurnal;
    public UTILIZATOR(String nume){
        super(nume);
        jurnal="";
    }

    public void receptioneaza(UTILIZATOR expeditor, String mesaj) {
        expeditor.jurnal+="Primit de la "+expeditor.nume+" mesajul: "+mesaj+".\n";
    }

    void trimite(DESTINATAR d, String mesaj){
        jurnal+="Trimis catre "+d.nume+" mesajul: "+mesaj+"\n";
        d.receptioneaza(this,mesaj);
    }

    public String toString(){
        return this.nume+":\n"+jurnal+"\n";
    }
}

class DestinatarDuplicat extends Exception{
    public DestinatarDuplicat(){
        super("Destinatar duplicat!");
    }
}

class GRUP extends DESTINATAR{

    public GRUP(String nume){
        super(nume);
    }

    protected ArrayList<DESTINATAR> secventa=new ArrayList<DESTINATAR>();

    public void inscrie(DESTINATAR d) throws DestinatarDuplicat{
        int ok=1;
        Iterator<DESTINATAR> it= secventa.iterator();
        while(it.hasNext()){
            if(it.next().equals(d))
            {
                ok=0;
                throw new DestinatarDuplicat();
            }
        }
        if(ok==1)
            secventa.add(d);
    }

    public void receptioneaza(UTILIZATOR expeditor, String mesaj) {
        for(DESTINATAR d:secventa)
            if(expeditor.equals(d)==false)
                d.receptioneaza(expeditor,mesaj);
    }
}

class Main{
    public static void main(String[] args){
        UTILIZATOR u1=new UTILIZATOR("Dan");
        UTILIZATOR u2=new UTILIZATOR("Marius");
        UTILIZATOR u3=new UTILIZATOR("Alex");

        GRUP g=new GRUP("Carnivorii");
        try {
            ((GRUP) g).inscrie(u1);
        } catch (DestinatarDuplicat e) {
            System.out.println("Destinatar duplicat!");
        }
        try{
            ((GRUP) g).inscrie(u2);
        }catch (DestinatarDuplicat e) {
            System.out.println("Destinatar duplicat!");
        }
        try{
            ((GRUP) g).inscrie(u3);
        }catch (DestinatarDuplicat e) {
            System.out.println("Destinatar duplicat!");
        }

        //
        try{
            ((GRUP) g).inscrie(u1);
        } catch (DestinatarDuplicat e) {
            System.out.println("Destinatar duplicat!");
        }

        u3.trimite(g,"Am deschis magazinul");
        u2.trimite(g,"Vin acuma");

        for(DESTINATAR d:g.secventa)
            System.out.println(d.toString());
        /*System.out.println(u1.toString());
        System.out.println(u2.toString());
        System.out.println(u3.toString());

    }
}*/

//refacem statisticile

/*import java.util.*;

abstract class STATISTICA{
    public abstract void calculeaza(ArrayList<String> secventa);
    protected String jurnal;

    public String toString(){
        return jurnal;
    }
}

class StatisticaNrAparitii extends STATISTICA{
    private ArrayList<String> cautate;

    public StatisticaNrAparitii(ArrayList<String> secv){
        cautate=secv;
        jurnal="";
    }

    public void calculeaza(ArrayList<String> secventa){
        int x=0;
        for(String s:secventa)
            for(String t:cautate)
                if(t.equals(s))
                    x++;
        jurnal="In secventa { ";
        for(String s:secventa)
            jurnal+=s+" ";
        jurnal+="} apar "+x+" siruri din secventa { ";
        for(String s:cautate)
            jurnal+=s+" ";
        jurnal+="}.\n";
    }
}


class StatisticaNrNonReale extends STATISTICA{

    public StatisticaNrNonReale(){
        jurnal="";
    }

    public void calculeaza(ArrayList<String> secventa) {
        int y=0;
        for(String s:secventa)
            try{
                Double.parseDouble(s);
            } catch (NumberFormatException e) {
                y++;
            }
        jurnal="In secventa { ";
        for(String s:secventa)
            jurnal+=s+" ";
        jurnal+="} avem "+y+" siruri ce nu sunt nr reale.\n";
    }
}

class Executor{
    private ArrayList<STATISTICA> lista;

    public Executor(ArrayList<STATISTICA> secventa){
        lista=secventa;
    }

    public void executa(ArrayList<String> secventa){
        for(STATISTICA s:lista)
        {
            s.calculeaza(secventa);
            System.out.println(s.toString());
        }
    }
}

class Main{
    public static void main(String[] args){
        ArrayList<String> l1=new ArrayList<String>();
        l1.add("mere");
        l1.add("pere");
        l1.add("banane");
        StatisticaNrAparitii s1=new StatisticaNrAparitii(l1);

        StatisticaNrNonReale s2=new StatisticaNrNonReale();

        ArrayList<STATISTICA>l2=new ArrayList<STATISTICA>();
        l2.add(s1);
        l2.add(s2);
        Executor e=new Executor(l2);

        ArrayList<String> secv1=new ArrayList<String>();
        secv1.add("Ana");
        secv1.add("are");
        secv1.add("mere");
        e.executa(secv1);

        ArrayList<String> secv2=new ArrayList<String>();
        secv2.add("Maria");
        secv2.add("are");
        secv2.add("2");
        e.executa(secv2);
    }
}*/

/*import java.util.*;
abstract class DESTINATAR{
    protected String nume;

    public DESTINATAR(String nume){
        this.nume=nume;
    }

    public abstract void receptioneaza(UTILIZATOR u,String mesaj);
}

class UTILIZATOR extends DESTINATAR{
    private String jurnal;

    public UTILIZATOR(String nume){
        super(nume);
        jurnal="";
    }

    public void trimite(DESTINATAR d,String msj){
        jurnal+="Trimis catre "+d.nume+" mesajul "+msj+"\n";
        d.receptioneaza(this,msj);
    }

    public void receptioneaza(UTILIZATOR u,String msj){
        jurnal+="Primit de la "+u.nume+" mesajul "+msj+"\n";
    }

    public String toString(){
        return this.nume+":\n"+jurnal;
    }
}

class DestinatarDuplicat extends Exception{
    public DestinatarDuplicat(String msj){
        super("Exceptie cu mesajul "+msj);
    }
}

class GRUP extends DESTINATAR{
    protected ArrayList<DESTINATAR> v=new ArrayList<DESTINATAR>();

    public GRUP(String nume){
        super(nume);
    }
    public void inscrie(DESTINATAR d) throws DestinatarDuplicat{
        int ok=1;
        for(DESTINATAR dest:v){
            if(dest.nume.equals(d.nume)){
                ok=0;
                throw new DestinatarDuplicat("MAI ESTE!");
            }
        }
        if(ok==1)
            v.add(d);
    }

    public void receptioneaza(UTILIZATOR u, String mesaj) {
        for(DESTINATAR d:v){
            if(!d.nume.equals(u.nume))
                d.receptioneaza(u,mesaj);
        }
    }
}

class Main{
    public static void main(String[] args){
        UTILIZATOR u1=new UTILIZATOR("Dan");
        UTILIZATOR u2=new UTILIZATOR("Marius");
        UTILIZATOR u3=new UTILIZATOR("Alex");
        GRUP grup=new GRUP("Carnivorii");

        try{
            grup.inscrie(u1);
            grup.inscrie(u2);
            grup.inscrie(u3);
            grup.inscrie(u3);
        }catch(DestinatarDuplicat e){
           // e.printStackTrace();
            System.out.println("MAI ESTEEE!");
        }

        u3.trimite(grup,"Am deschis magazinul");
        u2.trimite(grup,"Vin acuma");
        u1.trimite(grup,"VOCEA INTERLOPILOR");

        for(DESTINATAR d:grup.v)
            System.out.println(d.toString());
    }
}*/

//teste software

/*import java.util.*;

interface TESTE{

    public abstract int getNumarTeste();
    public abstract String toString();
}

class WrongQualityIndicatorException extends RuntimeException{
    public WrongQualityIndicatorException(String msj){
        super("Exceptie cu mesajul "+msj);
    }
}

abstract class DATE_COMUNE implements TESTE{
    protected String nume;
    protected int calitate;

    public DATE_COMUNE(String nume,int ind){
        this.nume=nume;
        if(ind<1 || ind>10)
            throw new WrongQualityIndicatorException("NU E BUN!");
        else calitate=ind;
    }
}

class IntegrationTest extends DATE_COMUNE{

    public IntegrationTest(String nume,int ind){
        super(nume, ind);
    }

    public int getNumarTeste() {
        return 1;
    }

    public String toString(){
        return "IntegrationTest ("+nume+", "+calitate+")";
    }
}

class WrongComponentComplexityException extends Exception{
    public WrongComponentComplexityException(String msj){
        super("Exceptie cu mesajul "+msj);
    }
}

class ComponentTest extends DATE_COMUNE{
    private int complexitate;

    public ComponentTest (String nume,int ind,int c)throws WrongComponentComplexityException{
        super(nume, ind);
        if(c>0)
            complexitate=c;
        else throw new WrongComponentComplexityException("NU E BUNNN!");
    }

    public int getNumarTeste() {
        return 1;
    }

    public String toString(){
        return "ComponentTest ("+nume+", "+calitate+", "+complexitate+")";
    }
}

class TestSuite implements TESTE {
    private ArrayList<TESTE> v;

    public TestSuite() {
        v=new ArrayList<TESTE>();
    }

    public int getNumarTeste() {
        int nr = 0;
        for (TESTE t : v)
            nr += t.getNumarTeste();
        return nr;
    }

    public boolean addNewIntegrationTest(String name, int ind) {
        try {
            IntegrationTest t = new IntegrationTest(name, ind);
            v.add(t);
        } catch (WrongQualityIndicatorException e) {
            return false;
        }
        return true;
    }

    public boolean addNewComponentTest(String nume, int ind, int c) {
        try {
            ComponentTest t = new ComponentTest(nume, ind, c);
            v.add(t);
        } catch (WrongQualityIndicatorException e) {
            return false;
        } catch (WrongComponentComplexityException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public String toString(){
        String s="TestSuite:\n";
        for(TESTE t:v)
            s+=t.toString()+"\n";
        return s;
    }
}

class Main{
    public static void main(String[] args){
        TestSuite suita=new TestSuite();
        suita.addNewIntegrationTest("Ion",9);
        suita.addNewIntegrationTest("Marcela",7);
        suita.addNewComponentTest("Lorena",5,9);
        suita.addNewComponentTest("Ale",3,7);
        System.out.println(suita.toString());
        System.out.println(suita.getNumarTeste());
    }
}*/

//DRIVE!!!!!!

//twitch

/*import java.util.*;

interface UTILIZATOR{
    public abstract double calculeazaVenit(int minute);
    public abstract String toString();
}

class SUBSCRIBER implements UTILIZATOR{
    private String nume;
    private int nivel;

    public SUBSCRIBER(String nume,int n){
        this.nume=nume;
        this.nivel=n;
    }

    public double calculeazaVenit(int minute){
        return minute*1.5*nivel;
    }

    public String toString(){
        return nume+" - "+nivel;
    }
}

class CREATOR implements UTILIZATOR{
    private String nume;
    private SUBSCRIBER[] v=new SUBSCRIBER[10];
    private int cnt=0;

    public CREATOR(String nume){
        this.nume=nume;
    }

    public void adaugaSubscriber(SUBSCRIBER s){
        if(cnt==v.length)
        {
            SUBSCRIBER[] aux=new SUBSCRIBER[2*v.length];
            for(int i=0;i<cnt;i++)
                aux[i]=v[i];
            v=aux;
        }
        v[cnt]=s;
        cnt++;
    }

    public double calculeazaVenit(int minute) {
        double s=0;
        for(int i=0;i<cnt;i++)
            s+=v[i].calculeazaVenit(minute);
        return s;
    }

    public String toString(){
        String s=nume+": ";
        for(int i=0;i<cnt;i++)
            s+=v[i].toString()+"  ";
        return s;
    }
}

class PLATFORMA{
    private ArrayList<UTILIZATOR> tablou=new ArrayList<UTILIZATOR>(1000);
    private static int cnt=0;

    public boolean adaugaUtilizator(UTILIZATOR u){
        if(cnt==1000)
            return false;
        else {
            tablou.add(u);
            cnt++;
            return true;
        }
    }

    public UTILIZATOR determinaVIP(int minute){
        double venit=0;
        UTILIZATOR u=null;

        for(UTILIZATOR aux:tablou) {
            double s = aux.calculeazaVenit(minute);
            if (s > venit) {
                venit = s;
                u = aux;
            }
        }
        return u;
    }
}

class Main{
    public static void main(String[] args){
        PLATFORMA p=new PLATFORMA();
        CREATOR c1=new CREATOR("ZAPPYTV");
        c1.adaugaSubscriber(new SUBSCRIBER("Ale",1));
        c1.adaugaSubscriber(new SUBSCRIBER("Lore",2));
        CREATOR c2=new CREATOR("IMOGEN");
        c2.adaugaSubscriber(new SUBSCRIBER("Alex",3));
        c2.adaugaSubscriber((new SUBSCRIBER("Costi",4)));

        p.adaugaUtilizator(c1);
        p.adaugaUtilizator(c2);
        p.adaugaUtilizator(new SUBSCRIBER("Nico",2));

        System.out.println(c1.toString());
        System.out.println(c1.calculeazaVenit(30)+"\n");
        System.out.println(c2.toString());
        System.out.println(c2.calculeazaVenit(30)+"\n");

        System.out.println("VIP: "+p.determinaVIP(30));

    }
}*/

//whiskey

 interface WHISKEY{
    public abstract double getNrCalorii(double ml);
    public abstract double getConcentratieAlcool();
    public abstract String toString();
}

class ClassicWhiskey implements WHISKEY{
     private String nume;
     private double conc;

     public ClassicWhiskey(String n,double c){
         nume=n;
         conc=c;
     }

    public double getNrCalorii(double ml) {
        return conc*ml*5;
    }

    public double getConcentratieAlcool() {
        return conc;
    }

    public String toString(){
         return nume+", concentratie "+conc+", calorii "+getNrCalorii(100);
    }
}

class JackAndHoney implements WHISKEY{
     private String nume;
     private double conc;
     private int indulcitor;

     public JackAndHoney(String n,double c,int i){
         nume=n;
         conc=c;
         indulcitor=i;
     }

    public double getNrCalorii(double ml) {
        return conc*ml*5+indulcitor*ml*15;
    }

    public double getConcentratieAlcool() {
        return conc;
    }

    public String toString(){
         return nume+", concentratie "+conc+", calorii "+getNrCalorii(100);
    }
}












