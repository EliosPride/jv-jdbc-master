package mate.jdbc.service.impl;

import mate.jdbc.dao.CarDao;
import mate.jdbc.factory.CarDaoFactory;
import mate.jdbc.model.Car;
import mate.jdbc.service.CarService;
import mate.jdbc.util.InjectorUtils;

import java.util.List;
import java.util.Optional;

public class CarServiceImpl implements CarService {
    private final CarDao carDao = CarDaoFactory.getInstance();

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
