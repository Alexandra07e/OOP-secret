/* //AVIOANE LAB7
abstract class AVION{
    private String planeID;
    private int totalEnginePower;

    public AVION(String id,int t){
        planeID=id;
        totalEnginePower=t;
    }

    public String getPlaneID(){
        return planeID;
    }
    public int getTotalEnginePower(){
        return totalEnginePower;
    }

    public void takeOff(){
        System.out.println(this.planeID+"- Initiating takeoff procedure- Starting engines- Accelerating down the runway- Taking off - Retracting gear- Takeoff complete");
    }

    public void fly(){
        System.out.println(this.planeID+"- Flying");
    }

    public void land(){
        System.out.println(this.planeID+"- Initiating landing procedure- Enabling airbrakes - Lowering gear- Contacting runway- Decelerating- Stopping engines- Landing complete");
    }
}

abstract class CALATORI extends AVION{
    private int maxPassengers;

    public CALATORI(String id,int t,int p){
        super(id, t);
        maxPassengers=p;
    }

    public int getMaxPassengers(){
        return maxPassengers;
    }
}

abstract class LUPTA extends AVION{
    public LUPTA(String id,int t){
        super(id, t);
    }

    public void launchMissile(){
        System.out.println(getPlaneID()+"- Initiating missile launch procedure- Acquiring target- Launching missile- Breaking away- Missile launch complete");
    }
}

class BOEING extends CALATORI{
    public BOEING(String id,int t,int p) {
        super(id, t, p);
    }
}

class CONCORDE extends CALATORI{
    public CONCORDE(String id,int t,int p){
        super(id, t, p);
    }
    public void goSuperSonic(){
        System.out.println(getPlaneID()+" - Supersonic mode activated");
    }

    public void goSubSonic(){
        System.out.println(getPlaneID()+" - Supersonic mode deactivated");
    }
}

class MIG extends LUPTA{
    public MIG(String id,int t){
        super(id, t);
    }
    public void highSpeedGeometry(){
        System.out.println(getPlaneID()+"- High speed geometry selected");
    }

    public void normalGeometry(){
        System.out.println(getPlaneID()+"- Normal geometry selected");
    }
}

class TOMCAT extends LUPTA {
    public TOMCAT(String id, int t) {
        super(id, t);
    }

    public void refuel() {
        System.out.println(getPlaneID() + "- Initiating refueling procedure- Locating refueller- Catching up - Refueling- Refueling complete");
    }
}

class Main{
    public static void main(String[] args){
        AVION a1=new CONCORDE("C123",1,20);
        AVION a2=new BOEING("B234",2,30);
        AVION a3=new MIG("M456",3);
        AVION a4=new TOMCAT("T678",4);

        System.out.println("Avion 1: "+a1.getPlaneID()+" "+a1.getTotalEnginePower()+" "+((CONCORDE)a1).getMaxPassengers());
        a1.fly();
        a2.takeOff();
        a3.land();
        a4.takeOff();

        ((CONCORDE)a1).goSuperSonic();
        ((MIG)a3).launchMissile();
        ((TOMCAT)a4).refuel();
    }
}*/

//ARCASI LAB8

/*abstract class UNITATE{
    public abstract void ranire(int valoare);
    public abstract void loveste(UNITATE u);
    public abstract boolean esteVie();
}

abstract class DATE_COMUNE extends UNITATE{  //pt Arcasi si Calareti
    protected int viata;
    private int valoare;

    public DATE_COMUNE(int viata,int valoare){
        this.viata=viata;
        this.valoare=valoare;
    }

    public boolean esteVie(){
        if(viata<=0)
            return false;
        else return true;
    }

    public void ranire(int valoare){
        if(this.esteVie()==true)
            viata-=valoare;
    }

    public void loveste(UNITATE u){
        if(this.esteVie()==true)
            u.ranire(valoare);
        else u.ranire(0);
    }
}

class ARCAS extends DATE_COMUNE{
    private static final int viata_arcas=100;
    private static final int valoare_arcas=10;

    public ARCAS(){
        super(viata_arcas,valoare_arcas);
    }
}

class CALARET extends DATE_COMUNE{
    private static final int viata_calaret=200;
    private static final int valoare_calaret=15;

    private static int nr_cai=0;

    public CALARET(){
        super(viata_calaret,valoare_calaret);
    }

    public void ranire(int valoare){
        if(this.esteVie()==true) {
            viata -= valoare;
            if (this.esteVie() == false)
                nr_cai++;
        }
    }

    public static int getNr_cai(){
        return nr_cai;
    }
}

class PLUTON extends UNITATE{

    public UNITATE[] v=new UNITATE[10];
    private int cnt=0;

    public void ranire(int valoare) {
        for(int i=0;i<cnt;i++)
            if(v[i].esteVie()==true){
                {
                    v[i].ranire(valoare);
                    break;
                }
            }
    }

    public void loveste(UNITATE u){
        for(int i=0;i<cnt;i++)
            v[i].loveste(u);
    }

    public boolean esteVie() {
        if(cnt==0)
            return true;
        for(int i=0;i<cnt;i++)
        {
            if(v[i].esteVie()==true)
                return true;
        }
        return false;
    }

    public boolean adauga(UNITATE u){
        if(u.esteVie()==false || this.esteVie()==false)
            return false;
        if(cnt==v.length){
            UNITATE[] aux=new UNITATE[2* v.length];
            for(int i=0;i<cnt;i++)
                aux[i]=v[i];
            v=aux;
        }
        v[cnt]=u;
        cnt++;
        return true;
    }

}

class Main{
    public static void main(String[] args){
        PLUTON p1=new PLUTON();
        p1.adauga(new ARCAS());
        p1.adauga(new ARCAS());
        p1.adauga(new ARCAS());
        p1.adauga(new ARCAS());

        PLUTON aux=new PLUTON();
        aux.adauga(new ARCAS());
        aux.adauga(new ARCAS());

        PLUTON p2=new PLUTON();
        p2.adauga(new CALARET());
        p2.adauga(aux);

        for(int i=0;i<5;i++)
        {
            p1.loveste(p2);
            p2.loveste(p1);
        }
        System.out.println(p1.esteVie());
        System.out.println(p2.esteVie());
        System.out.println(CALARET.getNr_cai());
    }
}*/


//TREN lab8

/*abstract class VAGON{
    protected int pasageri;
    protected int colete;

    public VAGON(int p,int c){
        pasageri=p;
        colete=c;
    }

    public void deschidere(){
        System.out.println("Usi deschise!");
    }
    public void inchidere(){
        System.out.println("Usi inchise");
    }
}

class CalatoriA extends VAGON{

    public CalatoriA(){
        super(40,300);
    }
}

class CalatoriB extends VAGON{
    public CalatoriB(){
        super(50,400);
    }

    public void blocare(){
        System.out.println("Geamuri blocate!");
    }
}

class Marfa extends VAGON{
    public Marfa(){
        super(0,400);
    }

    public void deschidere() {
        System.out.println("Usi deschise automat!");
    }

    public void inchidere() {
        System.out.println("Usi inchise automat!");
    }
}

class TREN{
    public VAGON[] v=new VAGON[15];
    private int cnt;

    public TREN(VAGON[] aux,int cnt){
        v=aux;
        this.cnt=cnt;
    }

    public String toString(){
        String s="";
        for(int i=0;i<cnt;i++)
            s+="Vagon "+i+" cu "+v[i].pasageri+" pasageri si "+v[i].colete+" colete.\n";
        return s;
    }

    public void egale(TREN t2){
        int nr1=0,nr2=0;
        for(int i=0;i<this.cnt;i++)
            nr1+=this.v[i].colete;
        for(int i=0;i<t2.cnt;i++)
            nr2+=t2.v[i].colete;
        if(nr1==nr2)
            System.out.println("egale");
        else System.out.println("nu sunt egale");
    }

}

class Main{
    public static void main(String[] args){
        VAGON[] aux1=new VAGON[3];
        aux1[0]=new CalatoriA();
        aux1[1]=new CalatoriB();
        aux1[2]=new Marfa();

        VAGON[] aux2=new VAGON[3];
        aux2[0]=new Marfa();
        aux2[1]=new CalatoriB();
        aux2[2]=new Marfa();

        TREN t1=new TREN(aux1,3);
        TREN t2=new TREN(aux2,3);

        System.out.println("TREN 1:  "+t1.toString());
        System.out.println("TREN 2:  "+t2.toString());

        t1.egale(t2);

        aux1[0].inchidere();
        aux1[1].deschidere();
        ((CalatoriB)aux1[1]).blocare();
        ((Marfa)aux1[2]).deschidere();
    }
}*/

//GREUTATI lab8

/*abstract class GREUTATE{
    public abstract int capacitate();
}

class SIMPLA extends GREUTATE{
    private int c;
    public SIMPLA(int c){
        this.c=c;
    }

    public int capacitate(){
        return c;
    }
}

 class DUBLA extends GREUTATE{
    private GREUTATE g1,g2;

    public DUBLA(GREUTATE g1,GREUTATE g2){
        this.g1=g1;
        this.g2=g2;
    }

    //etc..cele 2 metode

     public int capacitate(){
        return g1.capacitate()+g2.capacitate();
     }
 }

 class MULTIPLA extends GREUTATE{
    private GREUTATE[] v=new GREUTATE[10];
    private int cnt=0;

    public void adaugare(GREUTATE g){
        if(cnt<v.length)
        {
            v[cnt]=g;
            cnt++;
        }
    }

    public int capacitate(){
        int s=0;
        for(int i=0;i<cnt;i++)
            s+=v[i].capacitate();
        return s;
    }
 }

 class COLECTIE{
    private GREUTATE[] colectie;
    private int cMax;
    private int cnt=0;

    public COLECTIE(int cMax){
        this.cMax=cMax;
        colectie=new GREUTATE[cMax];
    }
    public void adauga(GREUTATE g){
        if(cnt<cMax){
            colectie[cnt]=g;
            cnt++;
        }
    }

    public double medie(){
        double m=0;
        for(int i=0;i<cnt;i++)
            m+=colectie[i].capacitate();
        return m/cnt;
    }
 }

 class Main{
    public static void main(String[] args){
        GREUTATE g1=new SIMPLA(1);
        GREUTATE g2=new SIMPLA(2);
        GREUTATE g3=new DUBLA(g1,g2);
        GREUTATE g4=new MULTIPLA();
        ((MULTIPLA)g4).adaugare(g1);
        ((MULTIPLA)g4).adaugare(g2);
        ((MULTIPLA)g4).adaugare(g3);

        COLECTIE c=new COLECTIE(4);
        c.adauga(g1);
        c.adauga(g2);
        c.adauga(g3);
        c.adauga(g4);
        System.out.println(c.medie());
    }
 }*/

//PROBLEMA DOBANZI lab9

/*interface SumaTotala{
    public abstract double getSumaTotala();
}

abstract class ContBancar implements SumaTotala{
    private String numarCont;
    protected double suma;

    public ContBancar(String nr,double s){
        numarCont=nr;
        suma=s;
    }

    public String toString(){
        return numarCont+" ,"+suma+"  ";
    }
}

class EURO extends ContBancar{
    private double dobanda;

    public EURO(String nr,double s){
        super(nr,s);
    }

    public double getDobanda() {
        if (this.suma > 500)
            dobanda = 0.3;
        else
            dobanda = 0;
        return dobanda;
    }

    public double getSumaTotala() {
        return this.suma*36000;
    }
}

class LEI extends ContBancar{

    public LEI(String nr,double s){
        super(nr,s);
    }
    public void transfer(ContBancar contDestinatie,double suma){
        contDestinatie.suma-=suma;
        this.suma+=suma;
    }

    public double getSumaTotala() {
        return this.suma;
    }
}

class Client{
    protected String nume;
    protected String adresa;
    protected ContBancar[] c=new ContBancar[3];

    public Client(String nume,String adresa,ContBancar[] c){
        this.nume=nume;
        this.adresa=adresa;
        this.c=c;
    }

    public String toString(){
        String s=nume+" ,"+adresa+" ,conturi: ";
        for(int i=0;i<c.length;i++)
            s+=c[i].toString();
        return s;
    }
}

class Banca{
    private String codBanca;
    private Client[] v=new Client[5];

    public Banca(String cod){
        codBanca=cod;
    }

    private int cnt=0;

    public void add(Client c){
        for(int i=0;i<cnt;i++)
            if(c.nume.equals(v[i].nume))
                return;
        if(cnt<v.length)
        {
            v[cnt]=c;
            cnt++;
        }
    }

    public void afisareClient(Client cl){
        for(int i=0;i<cnt;i++)
            if(cl.nume.equals(v[i].nume)){
                System.out.print(cl.adresa+"  conturi: ");
                for(int j=0;j<cl.c.length;j++)
                    System.out.print(cl.c[j].getSumaTotala()+" ,");
            }
        System.out.print("\n");
    }
}

class Main {
    public static void main(String[] args) {
        ContBancar c1 = new EURO("1234", 700);
        ContBancar c2 = new LEI("5678", 100);
        ContBancar c3 = new LEI("9012", 200);
        System.out.println(((EURO) c1).getDobanda());
        //System.out.println(c2.toString());

        ((LEI)c2).transfer(c3,50);
        System.out.println(c2.toString());
        System.out.println(c3.toString());


        ContBancar[] aux = new ContBancar[3];
        aux[0] = c1;
        aux[1] = c2;
        aux[2] = c3;

        Client client1 = new Client("Nicoleta", "Timisoara", aux);

        ContBancar c4 = new EURO("alex", 700);
        ContBancar c5 = new LEI("ana", 100);
        ContBancar c6 = new LEI("maria", 200);

        ContBancar[] aux1 = new ContBancar[3];
        aux1[0] = c4;
        aux1[1] = c5;
        aux1[2] = c6;
        Client  client2=new Client("Alexandra","Craiova",aux1);

        Banca bank=new Banca("ROBRD567287");
        bank.add(client1);
        bank.add(client2);

        bank.afisareClient(client1);
        bank.afisareClient(client2);
    }
}*/

//INVESTMENT COMPANY lab9

/*class MEMBER{
    private int varsta;
    private String nume;

    public MEMBER(int v,String n){
        varsta=v;
        nume=n;
    }
}

interface Risky{
    public abstract double getRisk();
}

abstract class PROJECT implements Risky{
    protected String titlu;
    private String obiectiv;
    protected long fonduri;
    private MEMBER manager;
    public MEMBER[] part=new MEMBER[15];
    protected int cnt=0;

    public PROJECT(MEMBER manager,String titlu,String obiectiv,long fonduri){
        this.manager=manager;
        this.titlu=titlu;
        this.obiectiv=obiectiv;
        this.fonduri=fonduri;
    }
    public abstract void addMember(MEMBER m);
}

class COMERCIALE extends PROJECT{
    private String deadlinne;
    private long fonduri_marketing;
    private int nr_echipe;

    public COMERCIALE(MEMBER manager,String titlu,String obiectiv,long fonduri,String deadline,int nr_echipe){
        super(manager, titlu, obiectiv, fonduri);
        this.deadlinne=deadline;
        fonduri_marketing=fonduri/2;
        this.nr_echipe=nr_echipe;
    }

    public void addMember(MEMBER m){
        if(cnt< part.length)
        {
            part[cnt]=m;
            cnt++;
        }
    }

    public double getRisk() {
       return nr_echipe*3/cnt/(fonduri-fonduri_marketing);
    }
}

class MILITARE extends PROJECT{
    private String deadlinne;
    private String parola;

    public MILITARE(MEMBER manager,String titlu,String obiectiv,long fonduri,String deadline,String parola){
        super(manager, titlu, obiectiv, fonduri);
        this.deadlinne=deadline;
        this.parola=parola;
    }

    public void addMember(MEMBER m){
        if(cnt< part.length)
        {
            part[cnt]=m;
            cnt++;
        }
    }

    public double getRisk() {
        return cnt/parola.length()/fonduri;
    }
}

class OPEN_SOURCE extends PROJECT{
    private String mailinglist;

    public OPEN_SOURCE(MEMBER manager,String titlu,String obiectiv,long fonduri,String mailinglist){
        super(manager, titlu, obiectiv, fonduri);
        this.mailinglist=mailinglist;
    }

    public void addMember(MEMBER m){
        if(cnt== part.length)
        {
            MEMBER[] aux=new MEMBER[2*cnt];
            for(int i=0;i<cnt;i++)
                aux[i]=part[i];
            part=aux;
        }
        part[cnt]=m;
        cnt++;
    }

    public double getRisk() {
        return cnt/fonduri;
    }
}

class Main{
    private PROJECT[] proiecte=new PROJECT[10];
    private int cnt=0;

    public void addProject(PROJECT p){
        if(cnt== proiecte.length)
        {
            PROJECT[] aux=new PROJECT[2*cnt];
            for(int i=0;i<cnt;i++)
                aux[i]=proiecte[i];
            proiecte=aux;
        }
        proiecte[cnt]=p;
        cnt++;
    }

    public PROJECT getBestInvestment(){
        PROJECT p=proiecte[0];
        double min=proiecte[0].getRisk();
        for(int i=1;i<cnt;i++)
        {
            double risk=proiecte[i].getRisk();
            if(risk<min)
            {
                min=risk;
                p=proiecte[i];
            }
        }
        return p;
    }

    public static void main(String[] args){
        MEMBER manager=new MEMBER(20,"Alexandra");
        PROJECT p1=new COMERCIALE(manager,"C123","none",10,"1210",3);
        PROJECT p2=new MILITARE(manager,"M234","none",20,"1234","ale");
        PROJECT p3=new OPEN_SOURCE(manager,"O789","none",3,"mail");

        MEMBER part1=new MEMBER(20,"Nicoleta");
        MEMBER part2=new MEMBER(20,"Mihnea");
        MEMBER part3=new MEMBER(19,"Levi");

        ((COMERCIALE)p1).addMember(part1);
        ((COMERCIALE)p1).addMember(part2);
        ((COMERCIALE)p1).addMember(part3);
        ((MILITARE)p2).addMember(part1);
        ((MILITARE)p2).addMember(part1);
        ((MILITARE)p2).addMember(part1);
        ((OPEN_SOURCE)p3).addMember(part1);
        ((OPEN_SOURCE)p3).addMember(part1);
        ((OPEN_SOURCE)p3).addMember(part1);

        Main proiect=new Main();
        proiect.addProject(p1);
        proiect.addProject(p2);
        proiect.addProject(p3);

        PROJECT best= proiect.getBestInvestment();
        System.out.println("Proiectul cu cel mai mic risc: "+best.titlu);
    }
}*/

//MECI FOTBAL lab10

/*import java.util.Random;
import java.util.Date;
class CoordinateGenerator {
    private Random randomGenerator;
    public CoordinateGenerator() {
        Date now = new Date();
        long sec = now.getTime();
        randomGenerator = new Random(sec);
    }
    public int generateX() {
        int x = randomGenerator.nextInt(101);
        if(x < 5) {
            x = 0;
        } else if(x > 95) {
            x = 100;
        } else {
            x = randomGenerator.nextInt(99) + 1;
        }
        return x;
    }
    public int generateY() {
        int y = randomGenerator.nextInt(101);
        if(y < 5) {
            y = 0;
        } else if(y > 95) {
            y = 50;
        } else {
            y = randomGenerator.nextInt(49) + 1;
        }
        return y;
    }
}

class OUT extends Exception{
    public OUT(){
        super("Este out!");
    }
}

class GOL extends Exception{
    public GOL(){
       super("Este GOLLLL!");
    }
}

class CORNER extends Exception{
    public CORNER(){
        super("Este corner!");
    }
}

class MINGE{
    protected int x,y;
    private static CoordinateGenerator generate=new CoordinateGenerator();

    public MINGE(int x,int y){
        this.x=x;
        this.y=y;
    }

    public void suteaza() throws OUT,GOL,CORNER{
        x= generate.generateX();
        y= generate.generateY();
        if(y==0 || y==50)
            throw new OUT();
        if((x==0 || x==100) && (y>=20 && y<=30))
            throw new GOL();
        if((x==0 || x==100) && ((y>0 && y<20) || (y>30 && y<50)))
            throw new CORNER();
    }
}

class JOC{
    private String nume1,nume2;
    private int goluri1=0,goluri2=0,cornere=0,outuri=0;

    public JOC(String nume1,String nume2){
        this.nume1=nume1;
        this.nume2=nume2;
    }

    public String toString(){
        return nume1+" vs "+nume2+" - "+goluri1+":"+goluri2+" ,cornere:"+cornere+" ,outuri:"+outuri+"\n";
    }

    public void simuleaza(){
        MINGE balon=new MINGE(1,1);
        for(int i=0;i<1000;i++)
            try{
                System.out.println(nume1+" - "+nume2+" : Mingea se afla la coordonatele ("+ balon.x+","+balon.y+")");
                balon.suteaza();
            }catch(OUT e){
                outuri++;
                balon=new MINGE(balon.x,balon.y);
            }catch(GOL e){
                if(balon.x==100)
                    goluri1++;
                else if(balon.x==0)
                    goluri2++;
                balon=new MINGE(50,25);
            }catch(CORNER e){
                cornere++;
                if(balon.x==0 && balon.y<20)
                    balon=new MINGE(0,0);
                else if(balon.x==0 && (balon.y>30 && balon.y<50))
                    balon=new MINGE(0,50);
                else if(balon.x==100 && balon.y<20)
                    balon=new MINGE(100,0);
                else if(balon.x==100 && (balon.y>30 && balon.y<50))
                    balon=new MINGE(100,50);
            }
    }
}

class Main{
    public static void main(String[] args){
        JOC joc1=new JOC("Manchester City","Manchester United");
        joc1.simuleaza();
        System.out.println(joc1.toString());
        System.out.println("\n");
        JOC joc2=new JOC("Farul Constanta","FCSB");
        joc2.simuleaza();
        System.out.println(joc2.toString());
    }
}*/

//COLECTII lab11

import java.util.*;

abstract class TIP{
    public String getTip(){
        return "Tip: ";
    }
    public abstract String toString();
}

class Intreg extends TIP{
    private int atribut;

    public Intreg(int nr){
        atribut=nr;
    }

    public String getTip(){
        return super.getTip()+"Intreg";
    }

    public String toString(){
        return ""+atribut;
    }

    public boolean equals(Object obj){
        if(obj instanceof Intreg)
        {
            Intreg aux=(Intreg)obj;
            return aux.atribut==this.atribut;
        }
        else return false;
    }
}

class Sir extends TIP{
    private String atribut;

    public Sir(String s){
        atribut=s;
    }

    public String getTip(){
        return super.getTip()+"Sir";
    }
    public String toString(){
        return atribut;
    }

    public boolean equals(Object obj){
        if(obj instanceof Sir){
            Sir aux=(Sir)obj;
            return aux.atribut.equals(this.atribut);
        }else return false;
    }
}

class COLECTIE extends TIP{
    private ArrayList<TIP> collection=new ArrayList<TIP>();

    public void adaugare(TIP a){
        collection.add(a);
    }

    public String getTip(){
        return super.getTip()+"Colectie";
    }

    public String toString(){
        String s=" ";
        Iterator<TIP> it= collection.iterator();
        while(it.hasNext()){
            s+=it.next()+" ";
        }
        s="("+s+")";
        return s;
    }

    public boolean equals(Object obj){
        if(obj instanceof COLECTIE){
            COLECTIE aux=(COLECTIE)obj;
            return aux.collection.equals(this.collection);
        }
        else return false;
    }
}

class Main{
    public static void main(String[] args){
        COLECTIE c1=new COLECTIE();
        c1.adaugare(new Intreg(7));
        c1.adaugare(new Intreg(4));
        c1.adaugare(new Sir("Eu"));
        c1.adaugare(new Intreg(12));

        System.out.println(c1.toString());

        COLECTIE c2=new COLECTIE();
        c2.adaugare(new Intreg(7));
        c2.adaugare(new Intreg(4));
        c2.adaugare(new Sir("Eu"));
        c2.adaugare(new Intreg(12));
        /*COLECTIE aux=new COLECTIE();
        aux.adaugare(new Intreg(2));
        aux.adaugare(new Intreg(8));
        c2.adaugare(aux);*/

        System.out.println(c2.toString());
        System.out.println(c2.equals(c1));
    }
}