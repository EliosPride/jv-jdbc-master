package mate.jdbc;

import mate.jdbc.dao.impl.DriverDaoImpl;
import mate.jdbc.lib.Injector;
import mate.jdbc.model.Driver;

public class Main {
    private static final Injector injector = Injector.getInstance("mate.jdbc");

    public static void main(String[] args) {
        DriverDaoImpl dao = new DriverDaoImpl();
        Driver driver = new Driver();
        driver.setFirstName("Anton");
        driver.setLastName("Babak");
        driver.setCarId(3L);

        System.out.println(dao.getAll());
        System.out.println(dao.create(driver));
        System.out.println(driver.getId());
        System.out.println(dao.get(3L));
        driver.setFirstName("Bohdan");
        System.out.println(dao.update(driver));
        System.out.println(dao.delete(4L));
        System.out.println(dao.getByCarId(3L));
        System.out.println(dao.getAll());
    }
}
