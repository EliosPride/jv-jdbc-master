package mate.jdbc.service.impl;

import mate.jdbc.dao.DriverDao;
import mate.jdbc.factory.DriverDaoFactory;
import mate.jdbc.model.Driver;
import mate.jdbc.service.DriverService;
import mate.jdbc.util.InjectorUtils;

import java.util.List;
import java.util.Optional;

public class DriverServiceImpl implements DriverService {
    private final DriverDao driverDao = DriverDaoFactory.getInstance();

    @Override
    public List<Driver> getAll() {
        return driverDao.getAll();
    }

    @Override
    public Driver create(Driver driver) {
        return driverDao.create(driver);
    }

    @Override
    public Optional<Driver> get(Long id) {
        return driverDao.get(id);
    }

    @Override
    public Driver update(Driver driver) {
        return driverDao.update(driver);
    }

    @Override
    public boolean delete(Long id) {
        return driverDao.delete(id);
    }

    @Override
    public List<Driver> getByCarId(Long carId) {
        return driverDao.getByCarId(carId);
    }

    @Override
    public Optional<Driver> getByLogin(String login) {
        return driverDao.getByLogin(login);
    }
}
