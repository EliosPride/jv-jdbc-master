package mate.jdbc.factory;

import mate.jdbc.service.CarService;
import mate.jdbc.service.impl.CarServiceImpl;

public class CarServiceFactory {
    private static CarService carService;

    public static CarService getInstance() {
        if (carService == null) {
            carService = new CarServiceImpl();
        }
        return carService;
    }
}
