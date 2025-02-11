abstract class UNITATE{
    public abstract void ranire(int valoare);
    public abstract void loveste(UNITATE tip);
    public abstract boolean esteVie();
}

abstract class DateComune extends UNITATE{//comun pt arcasi si calareti
    protected int viata_initiala;
    protected int valoare;

    public DateComune(int viata_initiala,int valoare) {
        this.valoare = valoare;
        this.viata_initiala = viata_initiala;
    }

    public boolean esteVie() {
        if(this.viata_initiala<=0)
            return false;
        else
            return true;
    }

    public void ranire(int valoare) {
        if(this.esteVie()==true)
            this.viata_initiala-=valoare;
    }

    public void loveste(UNITATE unitate) {
        if(this.esteVie()==true)
            unitate.ranire(this.valoare);
        else
            unitate.ranire(0);
    }

}

class ARCAS extends DateComune{
    private static final int viata_arcas=100;
    private static final int valoare_arcas=10;

    public ARCAS(){
        super(viata_arcas, valoare_arcas);
    }
}

class CALARET extends DateComune{
    private static final int viata_calaret=200;
    private static final int valoare_calaret=15;
    private static int nr_cai=0;

    public CALARET() {
        super(viata_calaret, valoare_calaret);
    }

    public void ranire(int valoare){
        if(this.esteVie()==true)
        {
            this.viata_initiala-=valoare;
            if(this.esteVie()==false)
                nr_cai++;
        }
    }

    public static int getNr_cai(){
        return nr_cai;
    }
}

class PLUTON extends UNITATE{
    public UNITATE[] v=new UNITATE[12];
    private int cnt=0;

    public boolean adauga(UNITATE u){
        if(cnt==10)
        {
            UNITATE[] aux=new UNITATE[cnt*2];
            for(int i=0;i<cnt;i++)
                aux[i]=v[i];
            v=aux;
            v[cnt]=u;
            cnt++;
            return true;
        }
        else
            if(u.esteVie()==false)
                return false;
            else
                if(this.esteVie()==false)
                    return false;
                else
                {
                    v[cnt]=u;
                    cnt++;
                    return true;
                }
    }

    public void ranire(int valoare){
        for(int i=0;i<cnt;i++)
            if(v[i].esteVie()==true)
            {
                v[i].ranire(valoare);
                break;
            }
    }

    public boolean esteVie(){
        if(cnt==0)
            return true;
        else
        {
            for(int i=0;i<cnt;i++)
                if(v[i].esteVie()==true)
                    return true;
            return false;
        }
    }

    public void loveste(UNITATE tip){
        for(int i=0;i<cnt;i++)
            v[i].loveste(tip);
    }

}

class Main{
    public static void main(String[] args) {
        UNITATE u1 = new ARCAS();
        UNITATE u2 = new ARCAS();
        UNITATE u3 = new ARCAS();
        UNITATE u4 = new ARCAS();
        UNITATE pluton1 = new PLUTON();
        ((PLUTON) pluton1).adauga(u1);
        ((PLUTON) pluton1).adauga(u2);
        ((PLUTON) pluton1).adauga(u3);
        ((PLUTON) pluton1).adauga(u4);


        UNITATE aux1 = new ARCAS();
        UNITATE aux2 = new ARCAS();
        UNITATE u6 = new PLUTON();
        ((PLUTON) u6).adauga(aux1);
        ((PLUTON) u6).adauga(aux2);

        UNITATE pluton2 = new PLUTON();
        UNITATE u5 = new CALARET();
        ((PLUTON) pluton2).adauga(u5);
        ((PLUTON) pluton2).adauga(u6);


        for(int i=0;i<20;i++) {
            pluton1.loveste(pluton2);
            pluton2.loveste(pluton1);
        }
        System.out.println(pluton1.esteVie());
        System.out.println(pluton2.esteVie());
        System.out.println("Cai decedati: " + CALARET.getNr_cai());
    }
}