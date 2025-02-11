import java.io.*;
import java.util.*;

/* Intr-un program de gestiune a notelor studentilor unei facultati avem mai multe entitati. Fiecare curs este modelat printr-un obiect
caracterizat de denumirea cursului (String), un id, un cod de disciplina si un numar de credite (int-uri) si sunt initializate la
crearea obiectului. Doua cursuri sunt considerate egale daca au acelasi cod de disciplina.
    Un obiect curs mai pune la dispozitie metode prin care un client poate afla numarul de credite si id-ul cursului si o metoda
ce intoarce reprezentarea sir de caractere a unui curs sub forma "id denumire credite".
    Curicula unei facultati este modelata tot printr-un obiect. Aceasta tine minte intr-un tablou toate cursurile facultatii.
Obiectul pune la dispozitie o metoda void add(Curs c) ce adauga un curs in curicula facultatii (se pot adauga oricate cursuri
la o curicula). Un obiect curicula mai stie sa intoarca o reprezentare ca sir de caractere a obiectului compusa din reprezentarea sub forma de sir
a fiecarui curs continut. In fine, un astfel de obiect mai pune la dispozitie o metoda cauta(int id) ce intoarce apelantului o referinta la cursul
din curicula a carui id este cel dat ca argument. Se va scrie si o metoda load(String fisier) care creaza un obiect curicula si ii adauga toate cursurile
descrise in fisierul text dat ca argument. Fiecare curs are 4 linii consecutive: id-ul, denumirea, codul unic si numarul de credite.

Sistemul mai modeleaza studentii printr-un obiect corespunzator. Aceasta tine minte numele studentului si toate cursurile la care studentul a
obtinut nota de trecere + nota obtinuta. In acest sens, exista un serviciu inregistrareNota ce primeste o referinta la un Curs si nota obtinuta. Acesta
verifica
    i) daca nota e de trecere (si e nota) si
    2) daca studentul nu a mai trecut deja acea disciplina.
Daca nota nu e de trecere nu se face nimic iar daca disciplina a fost deja trecuta se actualizeaza nota. Altfel se adauga cursul si nota la
secventa de cursuri / note trecute. Se pot adauga oricate astfel de cursuri / note. Un obiect student mai stie sa calculeze numarul total de
credite obtinute (printr-un serviciu adecvat), media notelor (tot printr-un serviciu adecvat iar media e 0 daca nu exista cursuri trecute)
precum si o metoda pt. tiparirea datelor studentului sub forma  "nume / cursuri trecute si nota pt. fiecare / Credite / Medie".
Pt. exemplificarea functionalitatii incarcati dintr-un fisier o curicula, creati 2-3 studenti si inregistrati
la ei niste cursuri existente + note pt. ele. Tipariti pe ecran datele studentilor*/


class CURS{
    private String nume;
    private int id,cod,nr_credite;

    public CURS(String nume,int id,int cod,int nr_credite)
    {
        this.nume=nume;
        this.id=id;
        this.cod=cod;
        this.nr_credite=nr_credite;
    }

    public void egale(CURS c)
    {
        if(c.cod==this.cod)
            System.out.println("Cursurile sunt egale");
        else
            System.out.println("Cursurile nu sunt egale");
    }

    public int getNr_credite()
    {
        return this.nr_credite;
    }

    public int getId()
    {
        return this.id;
    }

    public String getNume()
    {
        return this.nume;
    }

    public String toString()
    {
        return "ID: "+this.id+"  DENUMIRE: "+this.nume+"  COD: "+this.cod+"  CREDITE: "+this.nr_credite;
    }
}

class CURICULA{
    public CURS[] cursuri=new CURS[10];
    private int cnt=0;

    public void add(CURS c)
    {
        if(cnt==cursuri.length)
        {
            CURS[] aux=new CURS[2*cnt];
            for(int i=0;i<cnt;i++)
                aux[i]=cursuri[i];
            cursuri=aux;
        }
        cursuri[cnt]=c;
        cnt++;
    }

    public String toString()
    {
        String s="CURICULA: ";
        for(int i=0;i<cnt;i++)
            s+=cursuri[i]+" ,";
        return s;
    }

    public CURS cauta(int id)
    {
        for(int i=0;i<cnt;i++)
            if(cursuri[i].getId()==id)
                return cursuri[i];
        return null;
    }

    public static CURICULA load()
    {
        CURICULA c=new CURICULA();
        try{
            FileInputStream fileInputStream= new FileInputStream("input.txt");
            BufferedReader inputReader=new BufferedReader(new InputStreamReader(fileInputStream));
            String line;
            int id,cod,nr_credite;
            String nume;
            while((line=inputReader.readLine())!=null)
            {
                id = Integer.parseInt(line);
                line = inputReader.readLine();
                nume = line;
                line = inputReader.readLine();
                cod = Integer.parseInt(line);
                line = inputReader.readLine();
                nr_credite = Integer.parseInt(line);
                c.add(new CURS(nume, id, cod, nr_credite));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return c;
    }
}

class STUDENT{
    private String nume;
    public CURS[] discipline=new CURS[10];
    public int[] note=new int[10];
    private int cnt=0;

    public STUDENT(String nume)
    {
        this.nume=nume;
    }

    public void inregistrareNota(CURS c,int nota_obt)
    {
        int i;
        if(nota_obt>=5 && nota_obt<=10)
        {
            for(i=0;i<cnt;i++)
                if(c.equals(discipline[i]))
                {
                    if (note[i] < nota_obt)
                        note[i] = nota_obt;
                    return;
                }
            if(cnt== discipline.length)
            {
                CURS[] aux1=new CURS[2*cnt];
                int[] aux2=new int[2*cnt];
                for(i=0;i<cnt;i++)
                {
                    aux1[i]=discipline[i];
                    aux2[i]=note[i];
                }
                discipline=aux1;
                note=aux2;
            }
            discipline[cnt]=c;
            note[cnt]=nota_obt;
            cnt++;
        }
    }

    public int nrCredite()
    {
        int s=0;
        for(int i=0;i<cnt;i++)
            s+=discipline[i].getNr_credite();
        return s;
    }

    public double medie()
    {
        double m=0;
        for(int i=0;i<cnt;i++)
            m+=note[i];
        if(m==0)
            return m;
        else
            m/=cnt;
        return m;
    }

    public void tiparire()
    {
        System.out.print("NUME: "+this.nume+" -> CURSURI TRECUTE: ");
        for(int i=0;i<cnt;i++)
            System.out.print("curs "+i+" ,"+discipline[i].getNume()+" cu nota "+note[i]+"  ");
        System.out.print(" -> NR CREDITE: "+this.nrCredite()+" -> MEDIA: "+this.medie());
        System.out.println();
    }
}


class ClientJava{
    public static void main(String[] args){
        CURICULA curicula=new CURICULA();
        curicula=CURICULA.load();
        System.out.println(curicula);

        System.out.println();
        STUDENT s1=new STUDENT("Alexandra");
        s1.inregistrareNota(curicula.cauta(1),10);
        s1.inregistrareNota(curicula.cauta(2),4);
        s1.tiparire();
        s1.inregistrareNota(curicula.cauta(2),7);
        s1.tiparire();

        STUDENT s2=new STUDENT("Iulia");
        s2.inregistrareNota(curicula.cauta(1),7);
        s2.inregistrareNota(curicula.cauta(2),8);
        s2.tiparire();
        s2.inregistrareNota(curicula.cauta(2),10);
        s2.tiparire();
    }
}