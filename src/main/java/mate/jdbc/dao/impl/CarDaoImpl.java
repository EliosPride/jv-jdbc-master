package mate.jdbc.dao.impl;

import mate.jdbc.dao.CarDao;
import mate.jdbc.exception.DataProcessingException;
import mate.jdbc.model.Car;
import mate.jdbc.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDaoImpl implements CarDao {
    private static final String GET_ALL_QUERY = "SELECT * FROM car WHERE is_deleted = false";
    private static final String CREATE_QUERY = "INSERT INTO car(model, year) values(?, ?)";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM car WHERE id = ? AND is_deleted = false";
    private static final String UPDATE_QUERY = "UPDATE car SET model = ?, year = ? WHERE id = ?";
    private static final String DELETE_QUERY = "UPDATE car SET is_deleted = true WHERE id = ?";

    @Override
    public List<Car> getAll() {
        List<Car> cars = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_QUERY);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String model = resultSet.getString("model");
                Integer year = resultSet.getInt("year");
                cars.add(new Car(id, model, year));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("can't get all cars from db", e);
        }
        return cars;
    }

    @Override
    public Car create(Car car) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, car.getModel());
            ps.setInt(2, car.getYear());
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                Long id = resultSet.getLong(1);
                car.setId(id);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("can't create car in db", e);
        }
        return car;
    }

    @Override
    public Optional<Car> get(Long id) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_BY_ID_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return Optional.of(parseCar(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("can't get car from db", e);
        }
        return Optional.empty();
    }

    @Override
    public Car update(Car car) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, car.getModel());
            ps.setInt(2, car.getYear());
            ps.setLong(3, car.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("can't update car in db", e);
        }
        return car;
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("can't update car in db", e);
        }
    }

    private Car parseCar(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getLong("id"));
        car.setModel(resultSet.getString("model"));
        car.setYear(resultSet.getInt("year"));
        return car;
    }
}
