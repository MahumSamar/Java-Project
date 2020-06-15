package flexTransport;

public abstract class Customers extends Users
{
    // Private Variables
    private static int drivers;
    private static int students;
    private static int faculty;
    private String department;
    private String vehicleAllotted;
    private String address;
    private String area;
    private double duesPerMonth;
    private double salary;

    // Constructor
    protected Customers() {
    }

    // Abstract Method
    abstract void incrementCustomers();
    abstract void decrementCustomers();


    // Method to Calculate Total customers
    public int totalCustomers()
    {
        return (drivers + students + faculty);
    }

    // getters and setters for variables
    public double getDuesPerMonth()
    {
        return duesPerMonth;
    }

    public void setDuesPerMonth(double duesPerMonth)
    {
        this.duesPerMonth = duesPerMonth;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getDrivers() {
        return drivers;
    }

    public void setDrivers(int drivers) {
        this.drivers = drivers;
    }

    public int getStudents() {
        return students;
    }

    public void setStudents(int students) {
        this.students = students;
    }

    public int getFaculty() {
        return faculty;
    }

    public void setFaculty(int faculty) {
        this.faculty = faculty;
    }

    public String getDepartment()
    {
        return department;
    }
    public void setDepartment(String department)
    {
        this.department = department;
    }

    public String getVehicleAllotted()
    {
        return vehicleAllotted;
    }
    public void setVehicleAllotted(String vehicleAllotted)
    {
        this.vehicleAllotted = vehicleAllotted;
    }

    public String getAddress()
    {
        return address;
    }
    public void setAddress()
    {
        this.address = address;
    }


}
