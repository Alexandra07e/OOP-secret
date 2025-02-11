abstract class GREUTATI{
    public abstract int capacitate();
}

// toate metodele din clasa abstracta trebuie implementate in toate subclasele ei

class SIMPLE extends GREUTATI{
    private int c;
    public SIMPLE(int c){
        this.c=c;
    }

    public int capacitate(){
        return c;
    }
}

class DUBLE extends GREUTATI{
    GREUTATI g1,g2;
     public DUBLE(GREUTATI g1, GREUTATI g2){
         this.g1=g1;
         this.g2=g2;
     }

     public void setGreutate1(GREUTATI g){
         this.g1=g;
     }
     public void setGreutate2(GREUTATI g){
         this.g2=g;
     }

     public int capacitate(){
         return g1.capacitate()+g2.capacitate();
     }
}

class MULTIPLE extends GREUTATI{
    public GREUTATI[] v; //nu facem cu new pt ca trebuie sa folosim v.length intr un for !!

    public MULTIPLE(GREUTATI[] v){  //se ia automat length ul pe care il trimit in vectorul dat ca parametru
        this.v=v;
    }

    public int capacitate(){
        int suma=0;
        for(int i=0;i<v.length;i++)
            suma+=v[i].capacitate();
        return suma;
    }
}

class ColectieGreutati{  //daca n am nevoie de ce se afla in cls abstracta NU DAU EXTEND!!!!
    public GREUTATI[] v;
    private int cnt=0;
    private int cMax=0;

    public ColectieGreutati(int cMax){
        this.cMax=cMax;
        v=new GREUTATI[cMax];
    }

    public void adauga(GREUTATI g){
        if(cnt>=cMax)
            System.out.println("nu mai e loc!");
        else {
            v[cnt]=g;
            cnt++;
        }
    }

    public double medie(){
        double m=0;
        for(int i=0;i<cnt;i++)
            m+=v[i].capacitate();
        return m/cnt;
    }
}

class ClientJava{
    public static void main(String[] args){
        GREUTATI g1=new SIMPLE(10);
        GREUTATI g2=new SIMPLE(20);
        GREUTATI g3=new DUBLE(g1,g2);
        GREUTATI[] aux=new GREUTATI[3];
        aux[0]=g1;
        aux[1]=g2;
        aux[2]=g3;
        GREUTATI g4=new MULTIPLE(aux);

        ColectieGreutati colectie=new ColectieGreutati(10);
        colectie.adauga(g1);
        colectie.adauga(g2);
        colectie.adauga(g3);
        colectie.adauga(g4);

        System.out.println("Media capacitatilor este "+colectie.medie());

    }
}