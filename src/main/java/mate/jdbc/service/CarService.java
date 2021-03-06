package mate.jdbc.service;

import mate.jdbc.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    List<Car> getAll();
    Car create(Car car);
    Optional<Car> get(Long id);
    Car update(Car car);
    boolean delete(Long id);
}
