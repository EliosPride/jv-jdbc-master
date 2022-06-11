package mate.jdbc;

import mate.jdbc.dao.ManufacturerDaoImpl;
import mate.jdbc.lib.Injector;
import mate.jdbc.model.Manufacturer;

public class Main {
    private static final Injector injector = new Injector("manufacturers");

    public static void main(String[] args) {
        ManufacturerDaoImpl manufacturerDao = (ManufacturerDaoImpl) injector.getInstance(ManufacturerDaoImpl.class);
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("Kostik");
        manufacturer.setCountry("Trinidad");
        manufacturer.setId(7L);

        System.out.println(manufacturerDao.getAll());

        System.out.println(manufacturerDao.create(manufacturer));

        System.out.println(manufacturerDao.delete(6L));

        System.out.println(manufacturerDao.update(manufacturer));

        System.out.println(manufacturerDao.get(7L));
    }
}
