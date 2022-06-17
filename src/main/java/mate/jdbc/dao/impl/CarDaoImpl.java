package mate.jdbc.dao.impl;

import mate.jdbc.dao.CarDao;
import mate.jdbc.exception.DataProcessingException;
import mate.jdbc.model.Car;
import mate.jdbc.model.Driver;
import mate.jdbc.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDaoImpl implements CarDao {
    String query;

    @Override
    public List<Car> getAll() {
        List<Car> cars = new ArrayList<>();
        List<Driver> driversList = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM taxi_service.driver_list");
            while (rs.next()) {
                Driver driver = new Driver();
                driver.setFirstName(rs.getString("first_name"));
                driver.setLastName(rs.getString("last_name"));
                driver.setCarId(rs.getObject("car_list_id", Integer.class));
                driversList.add(driver);
            }
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM taxi_service.car_list INNER JOIN taxi_service.driver_list" +
                            " ON taxi_service.car_list.id = taxi_service.driver_list.car_list_id");
            while (resultSet.next()) {
                List<Driver> drivers = new ArrayList<>();
                Integer id = resultSet.getObject("car_list.id", Integer.class);
                String model = resultSet.getString("car_model");
                Integer year = resultSet.getObject("car_year", Integer.class);
                Car car = new Car();
                for (Driver driver : driversList) {
                    if (id.equals(driver.getCarId())) {
                        drivers.add(driver);
                    }
                }
                car.setModel(model);
                car.setYear(year);
                car.setDrivers(drivers);
                cars.add(car);
            }
        } catch (SQLException e) {
            throw  new DataProcessingException("can't get all cars from database", e);
        }
        return cars;
    }

    @Override
    public Car create(Car car) {
        query = "INSERT INTO car_list(car_model, car_year) values(?, ?);";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, car.getModel());
            preparedStatement.setInt(2, car.getYear());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("can't add car into database", e);
        }
        return car;
    }

    @Override
    public Optional<Car> get(Long id) {
        Car car = new Car();
        query = "SELECT * FROM taxi_service.car_list RIGHT JOIN taxi_service.driver_list" +
                " ON taxi_service.car_list.id = taxi_service.driver_list.car_list_id";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Driver> drivers = new ArrayList<>();
            Driver driver = new Driver();
            while (resultSet.next()) {
                driver.setFirstName(resultSet.getString("first_name"));
                driver.setLastName(resultSet.getString("last_name"));
                driver.setCarId(resultSet.getObject("car_list_id", Integer.class));
                drivers.add(driver);
            }
            if (resultSet.next()) {
                car.setModel(resultSet.getString("car_model"));
                car.setYear(resultSet.getInt("car_year"));
                car.setDrivers(drivers);
            }

        } catch (SQLException e) {
            throw new DataProcessingException("Can't get car by id", e);
        }
        return Optional.of(car);
    }

    @Override
    public Car update(Car car) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

//    private List<Driver> getDriversByCar(ResultSet resultSet) {
//        List<Driver> driversList = new ArrayList<>();
//        try (Connection connection = ConnectionUtil.getConnection();
//             Statement statement = connection.createStatement()) {
//            resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                Driver driver = new Driver();
//                driver.setFirstName(resultSet.getString("first_name"));
//                driver.setLastName(resultSet.getString("last_name"));
//                driver.setCarId(resultSet.getObject("car_list_id", Integer.class));
//                driversList.add(driver);
//            }
//        } catch (SQLException e) {
//            throw new DataProcessingException("can't get driver list", e);
//        }
//        return driversList;
//    }
}
