package flexTransport;

public abstract class Vehicles implements VehiclesStatus {  //an abstract class for all three vehicles
    private int cars;
    private int buses;
    private int shuttles;
    private int vans;
    //all fields:
    public  String color;
    public  String model;
    public  String manufacturer;
    public  int sittingCapacity;
    public String morningtime;
    public  String eveningtime;
    public  String afternoontime;

    //Getter and Setters for all vehicles
    public int getCars() {
        return cars;
    }
    public void setCars(int cars) {
        this.cars=cars;
    }

    public int getShuttles() {
        return shuttles;
    }
    public void setShuttles(int shuttles) {
        this.shuttles=shuttles;
    }

    public int getBuses() {
        return buses;
    }
    public void setBuses(int  buses) {
        this.buses=buses;
    }

    public int getVans() {
        return vans;
    }
    public void setVans (int vans) {
        this.vans=vans;
    }

    //Getter and setters for vehicle info
    public int  getSittingCapacity() {
        return sittingCapacity;
    }
    public  void setSittingCapacity(int sittingCapacity) {
        this.sittingCapacity=sittingCapacity;

    }


    public String getColor() {
        return color;
    }
    public  void setColor(String color) {
        this.color=color;
    }

    public  String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model=model;
    }

    public String getManufacturer() {
        return manufacturer;
    }
    public void setManufacturer(String manufacturer) {
        this.manufacturer=manufacturer;
    }


}



