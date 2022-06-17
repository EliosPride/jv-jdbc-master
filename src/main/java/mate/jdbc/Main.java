package mate.jdbc;

import mate.jdbc.dao.impl.CarDaoImpl;
import mate.jdbc.lib.Injector;
import mate.jdbc.model.Car;

public class Main {
    private static final Injector injector = Injector.getInstance("mate.jdbc");

    public static void main(String[] args) {
//        ManufacturerDao manufacturerDao = (ManufacturerDao) injector.getInstance(ManufacturerDao.class);
//        Manufacturer manufacturer = new Manufacturer();
//        manufacturer.setName("Kostik");
//        manufacturer.setCountry("Trinidad");
//        manufacturer.setId(7L);
//
//        System.out.println(manufacturerDao.getAll());
//
//        System.out.println(manufacturerDao.create(manufacturer));
//
//        System.out.println(manufacturerDao.delete(6L));
//
//        System.out.println(manufacturerDao.update(manufacturer));
//
//        System.out.println(manufacturerDao.get(7L));

        CarDaoImpl carDao = new CarDaoImpl();
        System.out.println(carDao.get(1L));
    }
}
