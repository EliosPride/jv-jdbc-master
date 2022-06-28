package mate.jdbc.factory;

import mate.jdbc.service.DriverService;
import mate.jdbc.service.impl.DriverServiceImpl;

public class DriverServiceFactory {
    private static DriverService driverService;

    public static DriverService getInstance() {
        if (driverService == null) {
            driverService = new DriverServiceImpl();
        }
        return driverService;
    }
}
