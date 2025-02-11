class MEMBER{
    private int varsta;
    protected String nume;

    public MEMBER(int varsta,String nume){
        this.varsta=varsta;
        this.nume=nume;
    }
}

interface Risky{
    public double getRisk();
}

abstract class PROJECT implements Risky{
    protected MEMBER manager;
    public MEMBER[] participanti = new MEMBER[15];
    private int cnt = 0;

    protected String titlu;
    protected String obiectiv;
    protected long fonduri;

    public PROJECT(MEMBER manager,String titlu,String obiectiv,long fonduri){
        this.titlu=titlu;
        this.obiectiv=obiectiv;
        this.fonduri=fonduri;
        this.manager=manager;
    }

    public abstract void addMember(MEMBER m);
}

class COMERCIALE extends PROJECT{
    private String deadline;
    private long fonduri_marketing;
    private int nr_echipe;


    public COMERCIALE(MEMBER manager,String titlu,String obiectiv,long fonduri,String deadline,int nr_echipe){
        super(manager,titlu,obiectiv,fonduri);
        this.deadline=deadline;
        this.fonduri_marketing=fonduri/2;
        this.nr_echipe=nr_echipe;
    }

    private int cnt=0;
    public void addMember(MEMBER m){
        if(cnt< participanti.length){
            participanti[cnt]=m;
            cnt++;
        }
    }

    public double getRisk(){
        return nr_echipe*3/cnt/(fonduri-fonduri_marketing);
    }
}

class MILITARE extends PROJECT{
    private String deadline;
    private String parola;

    public MILITARE(MEMBER manager,String titlu,String obiectiv,long fonduri,String deadline,String parola){
        super(manager, titlu, obiectiv, fonduri);
        this.deadline=deadline;
        this.parola=parola;
    }

    private int cnt=0;
    public void addMember(MEMBER m){
        if(cnt< participanti.length){
            participanti[cnt]=m;
            cnt++;
        }
    }

    public double getRisk(){
        return cnt/parola.length()/fonduri;
    }
}

class OPEN_SOURCE extends PROJECT{
    private String mailinglist;

    public OPEN_SOURCE(MEMBER manager,String titlu,String obiectiv,long fonduri,String mailinglist){
        super(manager, titlu, obiectiv, fonduri);
        this.mailinglist=mailinglist;
    }

    private int cnt=0;
    public void addMember(MEMBER m){
        if(cnt>=participanti.length){
            MEMBER []aux=new MEMBER[2* participanti.length];
            for(int i=0;i< participanti.length;i++)
                aux[i]=participanti[i];
            participanti=aux;
        }
        participanti[cnt]=m;
        cnt++;
    }

    public double getRisk(){
        return cnt/fonduri;
    }
}

class ClientJava{
    public PROJECT[] proiecte=new PROJECT[15];
    private int cnt=0;

    public void addProject(PROJECT p){
        if(cnt>=proiecte.length)
        {
            PROJECT[] aux=new PROJECT[2* proiecte.length];
            for(int i=0;i< proiecte.length;i++)
                aux[i]=proiecte[i];
            proiecte=aux;
        }
        proiecte[cnt]=p;
        cnt++;
    }

    public PROJECT getBestInvestment(){
        double min=proiecte[0].getRisk();
        PROJECT p=proiecte[0];
        for(int i=1;i<cnt;i++)
        {
            double r=proiecte[i].getRisk();
            if(r<min)
            {
                min=r;
                p=proiecte[i];
            }
        }
        return p;
    }

    public String toString(){
        String s="";
        for(int i=0;i<cnt;i++)
        {
            s+="PROIECT "+i+":  Manager: "+proiecte[i].manager.nume+", Titlu: "+proiecte[i].titlu+" ,Obiectiv:"+proiecte[i].obiectiv+" ,Fonduri:"+proiecte[i].fonduri+"\n";
        }
        return s;
    }

    public static void main(String[] args){
        MEMBER manager=new MEMBER(20,"Alexandra");
        PROJECT p1=new COMERCIALE(manager,"C123","none",10,"1210",10);
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

        ClientJava proiect=new ClientJava();
        proiect.addProject(p1);
        proiect.addProject(p2);
        proiect.addProject(p3);
        System.out.println(proiect.toString());

        PROJECT best= proiect.getBestInvestment();
        System.out.println("Proiectul cu cel mai mic risc: "+best.titlu);
    }
}