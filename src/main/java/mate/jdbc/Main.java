package mate.jdbc;

import mate.jdbc.dao.CarDao;
import mate.jdbc.lib.Injector;

public class Main {
    private static final Injector injector = Injector.getInstance("mate.jdbc");

    public static void main(String[] args) {
        CarDao carDao = (CarDao) injector.getInstance(CarDao.class);

        System.out.println(carDao.getAll());
    }
}
