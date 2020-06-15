package flexTransport;

public class Buses extends Vehicles implements VehiclesStatus
{ //a subclass of Buses that is extending Vehicles abstarct class
    public  String busNo ;  //A vehicle Number(VehNo)
    //Getter and Setters for all credentials
    public String getBusNo() {
        return busNo;

    }
    public void setBusNo(String busNo) {
        this.busNo=busNo;
    }

    //adding and deleting vehicles
    void addVehicle() {
        int buses=getBuses();
        buses++;
        setBuses( buses);
    }

    void deleteVehicle() {
        int buses=getBuses();
        buses--;
        setBuses( buses);
    }

    void  updateVehicle() {

    }

    //overriding an instance mathod for status of vehicle
    @Override
    public String InsuranceStatus() {
        // TODO Auto-generated method stub
        return null;
    }
}

