package mate.jdbc.service.impl;

import mate.jdbc.Factory.FactorySingleton;
import mate.jdbc.dao.CarDao;
import mate.jdbc.dao.impl.CarDaoImpl;
import mate.jdbc.model.Car;
import mate.jdbc.service.CarService;

import java.util.List;
import java.util.Optional;

public class CarServiceImpl implements CarService {
    CarDao carDao = new CarDaoImpl();

    @Override
    public List<Car> getAll() {
        return carDao.getAll();
    }

    @Override
    public Car create(Car car) {
        return carDao.create(car);
    }

    @Override
    public Optional<Car> get(Long id) {
        return carDao.get(id);
    }

    @Override
    public Car update(Car car) {
        return carDao.update(car);
    }

    @Override
    public boolean delete(Long id) {
        return carDao.delete(id);
    }
}
