/* Un obiect tir este format dintr-un număr de maxim 5 obiecte remorcă. Un tir memorează într un tablou referințe la obiectele
remorcă pe care le conține la un moment dat. Fiecare remorcă este caracterizată de un întreg care reprezintă cantitatea de
cutii de marfă pe care le poate transporta și de un număr de înmatriculare (String). Pentru o remorcă vom avea doi constructori.
Unul va seta ambele caracteristici așa cum sunt date prin parametrii constructorului. Al doilea, primește ca valoare doar numărul
de înmatriculare și va seta numărul de cutii ce pot fi transportate ca fiind 1 + numărul de cutii a ultimului obiect remorcă creat anterior
celui curent. Dacă nu s-a mai creat un astfel de obiect până acum, numărul de cutii va fi setat pe 10. Evident, o remorcă poate conține
și alte elemente de care considerați că este nevoie.
  Asupra unui tir se pot realiza următoarele operații:
    • adaugaRemorca: ce primește ca parametru numărul de cutii de marfă și un număr de înmatriculare. Dacă mai există poziții libere
în tabloul de remorci, se crează un nou obiect remorcă ce este adăugat tirului iar metoda de adăugare întoarce true. Altfel,
metoda întoarce false.
    • adaugaRemorca: ce primește ca parametru o referință la o remorcă pe care o adaugă în tabloul de remorci a tirului pe următoarea
poziție liberă. Dacă operația nu se poate efectua (nu mai este loc) metoda returnează false altfel true.
    • stergeRemorca: ce primește ca parametru un număr de înmatriculare pentru o remorcă. Dacă în tir există o remorcă cu acel număr de
înmatriculare, atunci ea va fi înlăturată din tir și metoda întoarce o referință la obiectul remorcă eliminat. Altfel, metoda întoarce null.
    • o operație ce ne spune dacă două tiruri sunt egale, situație adevărată atunci când două tiruri pot transporta în total aceeași
cantitate de cutii de marfă.
    • o operație ce întoarce reprezentarea obiectului tir sub formă de șir de caractere. Acesta va avea forma: “T -> R1 -> … -> Rn” unde
Ri sunt reprezentarea sub formă de șir de caractere a fiecărei remorci (sub forma “R(nr_inmatriculare, nr_cutii)” conținute de tir.
  Se va implementa și o metodă main (într-o clasă separată) în care se vor crea cel puțin două tiruri distincte și se va exemplifica
funcționarea fiecărei operații cu fiecare comportament distinct observabil de către client.*/

class REMORCA{
    private int cutii;
    private String nr_inmat;
    private static int anterior=0;

    public REMORCA(int cutii,String nr_inmat)
    {
        this.cutii=cutii;
        this.nr_inmat=nr_inmat;
        anterior=this.cutii;
    }

    public REMORCA(String nr_inmat)
    {
        this.nr_inmat=nr_inmat;
        if(anterior==0)
            this.cutii=10;
        else
            this.cutii=anterior+1;
        anterior=this.cutii;
    }

    public int getCutii()
    {
        return this.cutii;
    }

    public String getNr_inmat()
    {
        return this.nr_inmat;
    }

}

class TIR{
    public REMORCA[] r=new REMORCA[5];
    private int cnt=0;

    public boolean adaugaRemorca(int cutii, String nr_inmat)
    {
        if(cnt==5)
            return false;
        else
        {
            r[cnt]=new REMORCA(cutii,nr_inmat);
            //r[cnt]=new REMORCA(nr_inmat);
            cnt++;
            return true;
        }
    }

    public boolean adaugaRemorca(REMORCA rem)
    {
        if(cnt==5)
            return false;
        else
        {
            r[cnt]=rem;
            cnt++;
            return true;
        }
    }

    public REMORCA stergeRemorca(String nr_inmat)
    {
        int i,j;
        REMORCA rem=null;
        for(i=0;i<cnt-1;i++)
        {
            String nr=r[i].getNr_inmat();
            if(nr_inmat.equals(nr))
            {
                rem=r[i];
                cnt--;
                for(j=i;j<cnt;j++)
                    r[j]=r[j+1];
            }
        }
        return rem;
    }

    public int suma_marfa()
    {
        int i,s=0;
        for(i=0;i<cnt;i++)
            s=s+r[i].getCutii();
        return s;
    }

    public void egalitate(TIR t)
    {
        int s1=this.suma_marfa();
        int s2=t.suma_marfa();
        if(s1==s2)
            System.out.println("Tirurile sunt egale");
        else
            System.out.println("Tirurile nu sunt egale");
    }

    public String toString()
    {
        int i;
        String s="T -> ";
        for(i=0;i<cnt;i++)
            s=s+"R("+r[i].getCutii()+" "+r[i].getNr_inmat()+")  ";
        return s;
    }

}

class Main {
    public static void main(String[] args){
        TIR t1=new TIR();
        t1.adaugaRemorca(100,"ADR");
        t1.adaugaRemorca(200,"DRU");
        t1.adaugaRemorca(300,"ALE");
        t1.adaugaRemorca(400,"DRA");
        System.out.println("PRIMUL TIR: "+t1.toString());
        //t1.stergeRemorca("DRU");
        //System.out.println("PRIMUL TIR: "+t1.toString());

        TIR t2=new TIR();
        REMORCA r1=new REMORCA(400,"ASD");
        REMORCA r2=new REMORCA("ALF");
        REMORCA r3=new REMORCA("FRE");
        REMORCA r4=new REMORCA("FRA");
        t2.adaugaRemorca(r1);
        t2.adaugaRemorca(r2);
        t2.adaugaRemorca(r3);
        t2.adaugaRemorca(r4);
        System.out.println("AL DOILEA TIR: "+t2.toString());
        //t2.stergeRemorca("ALO");
        //System.out.println("AL DOILEA TIR: "+t2.toString());

        t1.egalitate(t2);
    }
}
