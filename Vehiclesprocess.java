package flexTransport;

public abstract class Vehiclesprocess extends Vehicles{  //an abstract class for implementation of methods that are accessed through polymorphyism for all three vehicles
    abstract void  addVehicle();
    abstract void deleteVehicle();
    abstract void updateVehicle();


    //getting and setting  the times available
    public  String getMorningtime() {
        return morningtime;
    }

    public  void setMorningtime(String morningtime) {
        this.morningtime=morningtime;
    }

    public  String getAfternoontime() {
        return afternoontime;

    }
    public  void setAfternoontime(String afternoontime) {
        this.afternoontime=afternoontime;

    }

    public  String getEveningtime() {
        return eveningtime;

    }
    public  void setEveningtime(String eveningtime) {
        this.eveningtime=eveningtime;
    }


}


