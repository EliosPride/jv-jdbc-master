package mate.jdbc.factory;

import mate.jdbc.service.CarService;
import mate.jdbc.service.impl.CarServiceImpl;

public class FactorySingleton {
    private static CarService carService;

    public static CarService getCarService() {
        if (carService == null) {
            return new CarServiceImpl();
        }
        return carService;
    }
}
