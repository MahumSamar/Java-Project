package flexTransport;
public class Shuttles extends Vehicles implements VehiclesStatus
{  //a subclass of Buses that is extending Vehicles abstarct class

    public  String shuttleNo ;    //A vehicle Number(VehNo)

    //Getter and Setters for all credentials
    public String getShuttleNo() {
        return shuttleNo;

    }
    public void setShuttleNo(String shuttleNo) {
        this.shuttleNo=shuttleNo;
    }

    //adding and deleting vehicles
    void addVehicle() {
        int shuttles=getShuttles();
        shuttles++;
        setShuttles( shuttles);
    }

    void deleteVehicle() {
        int shuttles=getShuttles();
        shuttles--;
        setShuttles( shuttles);
    }

    void  updateVehicle() {

    }

    //overriding an instance mathod for status of vehicle

    @Override
    public String InsuranceStatus() {

        return null;
    }

}

