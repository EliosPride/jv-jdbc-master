package mate.jdbc;

import mate.jdbc.dao.impl.CarDaoImpl;
import mate.jdbc.lib.Injector;
import mate.jdbc.model.Car;

public class Main {
    private static final Injector injector = Injector.getInstance("mate.jdbc");

    public static void main(String[] args) {
        CarDaoImpl dao = new CarDaoImpl();
        Car car = new Car();
        car.setId(5L);
        car.setModel("ASTON MARTIN");
        car.setYear(2022);

        System.out.println(dao.getAll());
        System.out.println(dao.get(3L));
        System.out.println(dao.delete(6L));
        System.out.println(dao.getAll());
    }
}
