package mate.jdbc.model;

import java.util.Objects;

public class Driver {
    private Long id;
    private String firstName;
    private String lastName;
    private Long carId;
    private String login;
    private String password;
    private Role role;

    public Driver(String firstName, String lastName, String login, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public Driver(Long id, String firstName, String lastName, Long carId, String login, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.carId = carId;
        this.login = login;
        this.password = password;
    }

    public Driver(String firstName, String lastName, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
    }

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(id, driver.id) && Objects.equals(firstName, driver.firstName) && Objects.equals(lastName, driver.lastName) && Objects.equals(carId, driver.carId) && Objects.equals(login, driver.login) && Objects.equals(password, driver.password) && role == driver.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, carId, login, password, role);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", carId=" + carId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
