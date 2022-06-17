package mate.jdbc.dao;

import mate.jdbc.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarDao {
    public List<Car> getAll();
    public Car create(Car car);
    public Optional<Car> get(Long id);
    public Car update(Car car);
    public boolean delete(Long id);
}
