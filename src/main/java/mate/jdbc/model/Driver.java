package mate.jdbc.model;

import java.util.Objects;

public class Driver {
    private Long id;
    private String firstName;
    private String lastName;
    private Long carId;

    public Driver(Long id, String firstName, String lastName, Long carId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.carId = carId;
    }

    public Driver() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(id, driver.id) && Objects.equals(firstName, driver.firstName) && Objects.equals(lastName, driver.lastName) && Objects.equals(carId, driver.carId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, carId);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", carId=" + carId +
                '}';
    }
}
