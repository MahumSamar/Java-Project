package flexTransport;

public class Vans  extends Vehicles implements VehiclesStatus
{  //a subclass of Buses that is extending Vehicles abstarct class

    public  String vanNo ;   //A vehicle Number(VehNo)

    //Getter and Setters for all credentials
    public String getVanNo() {
        return vanNo;

    }
    public void setVanNo(String vanNo) {
        this.vanNo=vanNo;
    }

    //adding and deleting vehicles
    void addVehicle() {
        int vans=getVans();
        vans++;
        setVans(vans);
    }

    void deleteVehicle() {
        int vans=getVans();
        vans--;
        setVans( vans);
    }

    void  updateVehicle() {

    }

    //overriding an instance mathod for status of vehicle
    @Override
    public String InsuranceStatus() {
        return null;
    }

}





