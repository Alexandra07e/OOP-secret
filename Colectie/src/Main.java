import java.util.*;

abstract class TIP{
    public String getTip(){
        return "Tip: ";
    }
    //valoarea atributului incapsulat de clasele derivate=>
    public abstract String toString();
}

class INTREG extends TIP{
    private int atribut;

    public INTREG(int nr){
        this.atribut=nr;
    }

    public String getTip(){
        return super.getTip()+"Intreg";
    }

    public String toString(){  //valoarea atributului incapsulat de clasele derivate
        return ""+atribut;
    }
}

class SIR extends TIP{
    private String atribut;

    public SIR(String sir){
        this.atribut=sir;
    }

    public String getTip(){
        return super.getTip()+"Sir";
    }

    public String toString(){
        return atribut;
    }
}

class COLECTIE extends TIP{
    private ArrayList<TIP> collection=new ArrayList<TIP>();

    public String getTip(){
        return super.getTip()+"Colectie";
    }

    public String toString(){
        String s=" ";
        Iterator<TIP> it= collection.iterator();
        while(it.hasNext()) {
            s += it.next() + " ";
        }
        return "("+s+")";
    }

    public boolean equals(Object obj){
        if(obj instanceof COLECTIE){
            COLECTIE aux=(COLECTIE)obj;
            return aux.collection.equals(this.collection);
        }
        else
            return false;
    }

    public void adauga(TIP a){
        collection.add(a);
    }
}

class Main{
    public static void main(String[] args){
        TIP nr1=new INTREG(10);
        TIP nr2=new INTREG(20);
        TIP nr3=new INTREG(30);

        System.out.print(nr1.getTip()+"   ");
        System.out.println(nr1.toString());
        System.out.print(nr2.getTip()+"   ");
        System.out.println(nr2.toString());
        System.out.print(nr3.getTip()+"   ");
        System.out.println(nr3.toString());

        TIP sir1=new SIR("Alexandra");
        TIP sir2=new SIR("Nicoleta");

        System.out.print(sir1.getTip()+"   ");
        System.out.println(sir1.toString());
        System.out.print(sir2.getTip()+"   ");
        System.out.println(sir2.toString());

        COLECTIE colectie1=new COLECTIE();
        colectie1.adauga(nr1);
        colectie1.adauga(sir1);
        colectie1.adauga(nr2);
        colectie1.adauga(sir2);
        colectie1.adauga(nr3);

        System.out.print(colectie1.getTip()+"   ");
        System.out.println(colectie1.toString());

        COLECTIE aux=new COLECTIE();
        aux.adauga(new INTREG(40));
        aux.adauga(new SIR("Levi"));

        colectie1.adauga(aux);
        System.out.println(colectie1.toString());

        COLECTIE colectie2=new COLECTIE();
        colectie2.adauga(nr1);
        colectie2.adauga(sir1);
        colectie2.adauga(nr2);
        colectie2.adauga(sir2);
        colectie2.adauga(nr3);

        System.out.println("Colectia 1: "+colectie1.toString());
        System.out.println("Colectia 2: "+colectie2.toString());
        System.out.println("Egalitate: "+colectie1.equals(colectie2));

    }
}