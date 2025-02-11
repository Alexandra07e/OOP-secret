/*Versiuni de fișiere

 Într-un sistem de versionare de fișiere text (un sistem care ține minte conținutul fișierului dar și  conținutul fișierului înainte de
 fiecare modificare a sa), un fișier este reprezentat ca și obiect. Un astfel de obiect este caracterizat de numele său (String),
 conținut (String) și un id unic (int) al obiectului. Conținutul și numele fișierului se setează la crearea obiectului folosind un
 constructor. Identificatorul trebuie și el setat la crearea obiectului folosind un mecanism implementat de voi și trebuie să fie
 transparent pentru clientul unui obiect fișier (adică, de exemplu,  valoare id-ului nu trebuie să fie dat de client prin constructor, etc.).
 Fiecare fișier mai conține în starea sa o referință către un alt obiect fișier care reprezintă versiunea anterioară a obietului fișier
 curent. La crearea unui obiect fișier, acesta nu are versiuni anterioare.Pe un fișier putem realiza în continuare următoarele operații:
    - salveazaVersiune: această metodă crează un nou obiect fișier a cărui nume e dat de numele fișierului pe care s-a apelat metoda,
 la care se adaugă șirul “bak”. Conținutul noului fisier va fi identic cu al fișierului pe care am apelat operația. În continuare, noul
 fișier este înregistrat ca versiune anterioară a fișierului curent folosind câmpul descris anterior și dedicat acestui lucru.
Evident, versiunea anterioară a fisierului pe care am apelat metoda este inregistrată ca și versiune anterioară a noului obiect fișier creat.
    - concateneaza: care primește ca parametru o referință către un alt obiect fișier și concatenează conținutul său la conținutul obiectului
 pe care am apelat metoda. Înainte de a face aceste operații, se salveaza o nouă versiune pentru fișierul pe care am apelat metoda
    - toString: care întoarece un șir de caractere de forma: “id nume_fisier [ continut ] <sirul de caractere similar corespunzător
 versiunii anterioare (daca exista) a fișierului curent>
    - numărConcatenari: care intoarce numărul de concatenări care au fost efectuate pe un fișier
Implementați clasa Fisier iar într-o clasă separată implementați un main în care să se evidențieze toate funcționalitățile descrise anterior.*/


class FISIER{
    private String nume;
    private String continut;
    private int id_unic;
    private static int idGenerator=1;
    private FISIER referinta;
    private int cnt=0;

    public FISIER(String nume, String continut)
    {
        this.nume=nume;
        this.continut=continut;
        this.referinta=null;
        this.id_unic=idGenerator;
        idGenerator++;
    }

    public FISIER salveazaVersiune()
    {
        String nume=this.nume+".bak";
        FISIER f=new FISIER(nume,this.continut);
        f.referinta=this;
        return f;
    }

    public FISIER concateneaza(FISIER f1)
    {
        FISIER f=salveazaVersiune();
        f.continut+=" "+f1.continut;
        f.cnt++;
        return f;
    }

    public String toString()
    {
        if(this.referinta==null)
            return "ID: "+this.id_unic+" - NUME: "+this.nume+" - CONTINUT: "+this.continut+" - NU ARE VERSIUNE ANTERIOARA";
        else
            return "ID: "+this.id_unic+" - NUME: "+this.nume+" - CONTINUT: "+this.continut+" - VERSIUNE ANTERIOARA: "+this.referinta.nume+" "+this.referinta.continut;
    }

    public int frecventa()
    {
        return this.cnt;
    }
}

class ClientJava{
    public static void main(String[] args) {
        FISIER f = new FISIER("Alina", "nota 5");
        System.out.println(f.toString());

        FISIER f1 = f.salveazaVersiune();
        System.out.println(f1.toString());
        FISIER f2=f1.salveazaVersiune();
        System.out.println(f2.toString());

        System.out.println("\n");
        FISIER g=new FISIER("Nicoleta","nota 10");
        System.out.println(g.toString());
        f=f.concateneaza(g);
        System.out.println(f.toString());


        FISIER k=new FISIER("Alexandra","nota 11");
        System.out.println(k.toString());
        g=g.concateneaza(k);
        System.out.println(g.toString());

        System.out.println("Frecventa concatenarilor pe fisierul f este: "+f.frecventa());
        System.out.println("Frecventa concatenarilor pe fisierul g este: "+g.frecventa());
        System.out.println("Frecventa concatenarilor pe fisierul k este: "+k.frecventa());
    }
}