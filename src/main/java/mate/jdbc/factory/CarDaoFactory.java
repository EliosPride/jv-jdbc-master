package mate.jdbc.factory;

import mate.jdbc.dao.CarDao;
import mate.jdbc.dao.impl.CarDaoImpl;

public class CarDaoFactory {
    private static CarDao carDao;

    public static CarDao getInstance() {
        if (carDao == null) {
            carDao = new CarDaoImpl();
        }
        return carDao;
    }
}
