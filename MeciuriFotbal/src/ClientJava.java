import java.util.Random;
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
        if (x < 5) {
            x = 0;
        } else if (x > 95) {
            x = 100;
        }
        else {
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
    public OUT(String mesaj){
        super("Exceptie cu mesajul: "+mesaj);
    }
}

class CORNER extends Exception {
    public CORNER(String mesaj) {
        super("Exceptie cu mesajul: " + mesaj);
    }
}

class GOL extends Exception {
    public GOL(String mesaj) {
        super("Exceptie cu mesajul: " + mesaj);
    }
}


class MINGE{
    protected int x,y;
    private static CoordinateGenerator generator=new CoordinateGenerator();

    public MINGE(int x,int y){
        this.x=x;
        this.y=y;
    }

    public void setX(int x){
        this.x=x;
    }
    public void setY(int y){
        this.y=y;
    }

    public void suteaza() throws OUT,CORNER,GOL{
        x= generator.generateX();
        y= generator.generateY();
        if(y==0 || y==50)
            throw new OUT("Este out!");
        if((x==0 || x==100) && (y>=20 && y<=30))
            throw new GOL("Este GOOOOOOL!");
        if((x==0 || x==100) && ((y>0 && y<20) || (y>30 && y<50)))
            throw new CORNER("Este corner!");
    }
}


class JOC {
    private String nume1, nume2;
    private int goluri1=0, goluri2=0;
    private int outuri=0, cornere=0;

    public JOC(String echipa1, String echipa2) {
        this.nume1 = echipa1;
        this.nume2 = echipa2;
    }

    public String toString() {
        return nume1 + " vs " + nume2 + "  " + goluri1 + ":" + goluri2 + " ," + cornere + " cornere si " + outuri + " outuri.\n";
    }

    public void simuleaza() {
        MINGE balon=new MINGE(1,1);
        for(int i=0;i<1000;i++){
            try{
                System.out.println("Mingea se afla la coordonatele ("+balon.x+","+balon.y+")");
                balon.suteaza();
            }catch(OUT e){
                outuri++;
                balon=new MINGE(balon.x,balon.y);
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
            }catch(GOL e){
                if(balon.x==100)
                    goluri1++;
                else if(balon.x==0)
                    goluri2++;
                balon=new MINGE(50,25);
            }
        }
    }
}

class ClientJava{
    public static void main(String[] args){
        JOC meci1=new JOC("Real Madrid","Barcelona");
        meci1.simuleaza();
        System.out.println(meci1.toString());

        JOC meci2=new JOC("Universitatea Craiova","FCSB");
        meci2.simuleaza();
        System.out.println(meci2.toString());
    }
}