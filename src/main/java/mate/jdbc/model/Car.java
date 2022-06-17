package mate.jdbc.model;

import java.util.List;

public class Car {
    private String model;
    private int year;
    private List<Driver> drivers;

    public Car(String model, int year, List<Driver> drivers) {
        this.model = model;
        this.year = year;
        this.drivers = drivers;
    }

    public Car() {
    }

    public Car(String model, int year) {
        this.model = model;
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    @Override
    public String toString() {
        return "Car{"
                + "model='" + model + '\''
                + ", year=" + year + '\''
                + ", drivers=" + drivers + '\n'
                + '}';
    }
}
