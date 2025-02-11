import java.util.Random;
import java.io.*;

/*Un obiect telefon este caracterizat de numele proprietarului (String) ce se setează la crearea obiectului respectiv. Un telefon mai
conține un tablou (eventual și un contor dacă se consideră necesar) ce memorează nume de proprietari de telefoane (String-uri) care au
apelat telefonul respectiv. Pentru simplitate, acest tablou va conține maxim 100 de elemente.
Cu un telefon se pot realiza următoarele operații:
    • apeleaza: această metodă primește ca parametru o referință la obiectul telefon apelat de telefonul curent. În continuare se adaugă
numele proprietarului telefonului pe care s-a apelat metoda, în lista de nume a telefonului dat ca parametru la aceasta metodă. Dacă nu mai este
loc în tabloul respectiv, metoda apelare întoarce valoarea false (adică apelul nu a reușit). Altfel, metoda întoarce valoarea true.
    • numarDeApeluri: această metodă primește ca parametru un String reprezentând numele unui proprietar de telefon și întoarce o referintă
la un obiect Integer ce conține numărul de apeluri efectuate de respectiva persoană la telefonul curent.
    • toString: returnează un șir de caractere conținând numele proprietarului și numele tuturor persoanelor care au apelat telefonul respectiv.

  Pentru exemplificare, creați o clasă separată care conține o metodă main în care:
    • Se citește de la intrarea standard un număr întreg. Acest număr reprezintă câte telefoane trebuie create. Se vor crea apoi aceste
telefoane și se vor memora într-un tablou referințe la ele. Numele proprietarilor vor fi citite tot de la intrarea standard.
    • Se citește de la intrarea standard un număr A reprezentând un număr de apeluri de efectuat. Apoi se generează aleator A perechi de
numere întregi (x,y) semnificând faptul ca telefonul x din tabloul de mai sus apelează telefonul y din același tablou. Pentru generarea de
numere aleatoare vezi documentația de aici sau de aici (în al doilea caz trebuind să aveți un import java.util.Random; la începutul fișierului).
    • Se citește de la intrarea standard un nume de proprietar. Pentru fiecare telefon se va afișa la ieșirea standard reprezentarea sa sub
formă de șir de caractere și de căte ori persoana cu numele dat a apelat acel telefon.*/



class DEVICE{
    private String nume;
    private String[] t=new String[100];
    private int cnt=0;

    public DEVICE(String nume)
    {
        this.nume=nume;
    }

    public boolean apeleaza(DEVICE telefon)
    {
        if(telefon.cnt==100)
            return false;
        else
        {
            telefon.t[telefon.cnt] = this.nume;
            telefon.cnt++;
            return true;
        }
    }

    public Integer numarDeApeluri(String nume)
    {
        Integer nr=0;
        int i;
        for(i=0;i<this.cnt;i++)
            if(t[i].equals(nume))
                nr++;
        return nr;
    }

    public String toString()
    {
        String s="NUME PROPRIETAR: "+this.nume+"  ->  NUME APELANTI: ";
        for(int i=0;i<this.cnt;i++)
            s+=t[i]+" ";
        return s;
    }

    public String getNume()
    {
        return this.nume;
    }

    public int frecventa(String nume)
    {
        int nr=0;
        for(int i=0;i<this.cnt;i++)
            if(nume.equals(t[i]))
                nr++;
        return nr;
    }
}

class ClientJava{
    public static void main(String[] args) {
        try{
            BufferedReader inputReader=new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Dati nr de telefoane: ");
            int n;
            n=Integer.parseInt(inputReader.readLine());

            System.out.println();
            DEVICE[] telefoane=new DEVICE[100];
            int i;
            for(i=0;i<n;i++)
            {
                System.out.print("Dati numele proprietarului: ");
                String nume=inputReader.readLine();
                telefoane[i]=new DEVICE(nume);
                System.out.println();
            }

            for(i=0;i<n;i++)
                System.out.println("Telefonul "+i+" cu proprietarul "+telefoane[i].getNume());

            System.out.println();
            int A;
            Random random=new Random();
            System.out.print("Dati un nr de apeluri: ");
            A=Integer.parseInt(inputReader.readLine());
            for(i=0;i<A;i++)
            {
                int x=random.nextInt(5);
                int y=random.nextInt(5);
                System.out.println(x+" "+y);
                telefoane[x].apeleaza(telefoane[y]);
            }

            System.out.println();
            System.out.print("Dati un nume de proprietar random: ");
            String nume=inputReader.readLine();
            System.out.println();
            for(i=0;i<n;i++)
            {
                System.out.print(telefoane[i].toString());
                int nr=telefoane[i].frecventa(nume);
                System.out.println("         "+nume+" a apelat telefonul de "+nr+" ori.");
            }

        }catch(IOException e){
            System.out.println("Eroare la operatiile de intrare-iesire!");
            System.exit(1);
        }
    }
}