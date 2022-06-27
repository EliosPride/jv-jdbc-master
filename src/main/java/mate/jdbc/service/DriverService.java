package mate.jdbc.service;

import mate.jdbc.model.Driver;

import java.util.List;
import java.util.Optional;

public interface DriverService {
    List<Driver> getAll();
    Driver create(Driver driver);
    Optional<Driver> get(Long id);
    Driver update(Driver driver);
    boolean delete(Long id);
    List<Driver> getByCarId(Long carId);
    Optional<Driver> getByLogin(String login);
}
