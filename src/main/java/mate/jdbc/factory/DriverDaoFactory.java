package mate.jdbc.factory;

import mate.jdbc.dao.DriverDao;
import mate.jdbc.dao.impl.DriverDaoImpl;

public class DriverDaoFactory {
    private static DriverDao driverDao;

    public static DriverDao getInstance() {
        if (driverDao == null) {
            driverDao = new DriverDaoImpl();
        }
        return driverDao;
    }
}
