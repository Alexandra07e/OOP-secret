abstract class VAGON{
    protected int pasageri;
    protected int colete;
    public VAGON(int pasageri, int colete){
        this.pasageri= pasageri;
        this.colete=colete;
    }

    public void deschidere(){
        System.out.println("Usa deschisa!");
    }
    public void inchidere(){
        System.out.println("Usa inchisa!");
    }

    public String Tip(){
        return "Vagon";
    }
}

class CalatoriA extends VAGON{

    public CalatoriA(int pasageri,int colete) {
        super(pasageri, colete);
    }

    public String Tip(){
        return "CalatoriA";
    }
}

class CalatoriB extends VAGON{
    public CalatoriB(int pasageri,int colete){
        super(pasageri,colete);
    }

    public void blocare(){
        System.out.println("Geamuri blocate!");
    }

    public String Tip(){
        return "CalatoriB";
    }
}

class Marfa extends VAGON{

    public Marfa(int pasageri,int colete){
        super(0,colete);
    }
    public String Tip(){
        return "Marfa";
    }
}

class TREN{
    public VAGON[] v=new VAGON[15];
    private int cnt=0;

    public void adauga(VAGON vagon){
        if(cnt>=15)
            System.out.println("nu mai e loc!");
        else {
            v[cnt]=vagon;
            cnt++;
        }
    }

    public void egalitate(TREN t2){
        int nr1=0,nr2=0;
        for(int i=0;i<this.cnt;i++)
            nr1+=v[i].colete;
        for(int i=0;i<t2.cnt;i++)
            nr2+=t2.v[i].colete;
        if(nr1==nr2)
            System.out.println("Trenurile sunt egale");
        else
            System.out.println("Trenurile nu sunt egale");
    }

    public String toString(){
        String s="";
        for(int i=0;i<cnt;i++)
            s+="Vagon de tip "+v[i].Tip()+" cu "+v[i].colete+" colete si "+v[i].pasageri+" pasageri  ";
        return s;
    }

}

class ClientJava{
    public static void main(String[] args){
        VAGON v1=new CalatoriA(120,40);
        VAGON v2=new CalatoriB(60,100);
        VAGON v3=new Marfa(0,500);
        System.out.print("Vagon 1: ");
        v1.deschidere();
        System.out.print("Vagon 1: ");
        v1.inchidere();
        System.out.print("Vagon 2: ");
        ((CalatoriB)v2).blocare();
        TREN t1=new TREN();
        t1.adauga(v1);
        t1.adauga(v2);
        t1.adauga(v3);
        System.out.println("TREN 1: "+t1.toString());

        VAGON v4=new CalatoriA(12,100);
        VAGON v5=new CalatoriB(23,500);
        VAGON v6=new Marfa(0,40);
        TREN t2=new TREN();
        t2.adauga(v4);
        t2.adauga(v5);
        t2.adauga(v6);
        System.out.println("TREN 2: "+t2.toString());

        t1.egalitate(t2);
    }
}