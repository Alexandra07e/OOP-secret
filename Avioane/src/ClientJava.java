abstract class AVION{  //abstract pt ca nu vrem sa folosim new AVION() , le tratam pe restu claselor la care dam extend ca fiind avioane
    private String planeID;
    private int totalEnginePower;

    public AVION(String planeID,int totalEnginePower){
        this.planeID=planeID;
        this.totalEnginePower=totalEnginePower;
    }

    public String getPlaneID(){
        return this.planeID;
    }

    public int getTotalEnginePower(){
        return this.totalEnginePower;
    }

    public void takeOff(){
        System.out.println(this.planeID+"- Initiating takeoff procedure- Starting engines- Accelerating down the runway- Taking off - Retracting gear- Takeoff complete");
    }

    public void fly(){
        System.out.println(this.planeID+"- Flying");
    }

    public void land(){
        System.out.println(this.planeID+"- Initiating landing procedure- Enabling airbrakes - Lowering gear- Contacting runway- Decelerating- Stopping engines- Landing complete");
    }
}

abstract class CALATORI extends AVION{
    private int maxPassengers;

    public CALATORI(String planeID,int totalEnginePower,int maxPassengers){
        super(planeID, totalEnginePower);
        this.maxPassengers=maxPassengers;
    }

    public int getMaxPassengers(){
        return this.maxPassengers;
    }
}

class BOEING extends CALATORI{

    public BOEING(String planeID,int totalEnginePower,int maxPassengers){
        super(planeID,totalEnginePower,maxPassengers);
    }

}

class CONCORDE extends CALATORI{

    public CONCORDE(String planeID,int totalEnginePower,int maxPassengers){
        super(planeID, totalEnginePower, maxPassengers);
    }

    public void goSuperSonic(){
        System.out.println(getPlaneID()+" - Supersonic mode activated");
    }

    public void goSubSonic(){
        System.out.println(getPlaneID()+" - Supersonic mode deactivated");
    }

}

abstract class LUPTA extends AVION{
    public LUPTA(String planeID,int totalEnginePower){
        super(planeID, totalEnginePower);
    }
    public void highSpeedGeometry(){
        System.out.println(getPlaneID()+"- High speed geometry selected");
    }

    public void normalGeometry(){
        System.out.println(getPlaneID()+"- Normal geometry selected");
    }
    public void launchMissile(){
        System.out.println(getPlaneID()+"- Initiating missile launch procedure- Acquiring target- Launching missile- Breaking away- Missile launch complete");
    }
}

class MIG extends LUPTA{
    public MIG(String planeID, int totalEnginePower){
        super(planeID, totalEnginePower);
    }


}

class TOMCAT extends LUPTA{
    public TOMCAT(String planeID,int totalEnginePower){
        super(planeID, totalEnginePower);
    }

    public void refuel(){
        System.out.println(getPlaneID()+"- Initiating refueling procedure- Locating refueller- Catching up - Refueling- Refueling complete");
    }
}

class ClientJava{
    public static void main(String[] args){
        AVION a1=new BOEING("A1234",123,34);
        AVION a2=new CONCORDE("B234",234,40);
        AVION a3=new MIG("M345",567);
        AVION a4=new TOMCAT("T789",897);

        a1.fly();
        a2.land();
        a3.takeOff();
        a4.fly();

        ((CONCORDE)a2).goSuperSonic();
        ((MIG)a3).normalGeometry();
        ((TOMCAT)a4).refuel();

        System.out.println("Avionul "+a1.getPlaneID()+" are "+((BOEING)a1).getMaxPassengers()+" pasageri!");
        System.out.println("Avionul "+a2.getPlaneID()+" are "+ ((CONCORDE) a2).getMaxPassengers()+" pasageri!");
    }
}