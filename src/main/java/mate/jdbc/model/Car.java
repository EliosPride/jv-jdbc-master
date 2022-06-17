//package mate.jdbc.model;
//
//import java.util.List;
//import java.util.Objects;
//
//public class Car {
//    private Long id;
//    private String model;
//    private Integer year;
//
//    public Car(String model, Integer year, List<Driver> drivers, Long id) {
//        this.model = model;
//        this.year = year;
//        this.drivers = drivers;
//        this.id = id;
//    }
//
//    public Car() {
//    }
//
//    public Car(String model, Integer year) {
//        this.model = model;
//        this.year = year;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getModel() {
//        return model;
//    }
//
//    public void setModel(String model) {
//        this.model = model;
//    }
//
//    public Integer getYear() {
//        return year;
//    }
//
//    public void setYear(Integer year) {
//        this.year = year;
//    }
//
//    public List<Driver> getDrivers() {
//        return drivers;
//    }
//
//    public void setDrivers(List<Driver> drivers) {
//        this.drivers = drivers;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Car car = (Car) o;
//        return Objects.equals(id, car.id) && Objects.equals(model, car.model) && Objects.equals(year, car.year) && Objects.equals(drivers, car.drivers);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, model, year, drivers);
//    }
//
//    @Override
//    public String toString() {
//        return "Car{" +
//                "id=" + id +
//                ", model='" + model + '\'' +
//                ", year=" + year +
//                ", drivers=" + drivers +
//                '}';
//    }
//}
