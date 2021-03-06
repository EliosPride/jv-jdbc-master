package mate.jdbc.dao.impl;

import mate.jdbc.dao.DriverDao;
import mate.jdbc.exception.DataProcessingException;
import mate.jdbc.lib.Dao;
import mate.jdbc.model.Driver;
import mate.jdbc.model.Role;
import mate.jdbc.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Dao
public class DriverDaoImpl implements DriverDao {
    private static final String GET_ALL_QUERY = "SELECT * FROM driver WHERE is_deleted = false";
    private static final String CREATE_QUERY =
            "INSERT INTO driver(first_name, last_name, car_id, login, password, role) values(?, ?, ?, ?, ?, ?)";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM driver WHERE id = ? AND is_deleted = false";
    private static final String UPDATE_QUERY =
            "UPDATE driver SET first_name = ?, last_name = ?, car_id = ?, login = ?, password = ?, role = ? WHERE id = ?";
    private static final String DELETE_QUERY = "UPDATE driver SET is_deleted = true WHERE id = ?";
    private static final String GET_BY_CAR_ID_QUERY = "SELECT * FROM driver WHERE car_id = ? AND is_deleted = false";
    private static final String GET_BY_LOGIN_QUERY = "SELECT * FROM driver WHERE login = ? AND is_deleted = false";

    @Override
    public List<Driver> getAll() {
        List<Driver> drivers = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_QUERY);
            while (resultSet.next()) {
                drivers.add(parseDriver(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("can't get all drivers from db", e);
        }
        return drivers;
    }

    @Override
    public Driver create(Driver driver) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, driver.getFirstName());
            preparedStatement.setString(2, driver.getLastName());
            preparedStatement.setObject(3, driver.getCarId());
            preparedStatement.setString(4, driver.getLogin());
            preparedStatement.setString(5, driver.getPassword());
            preparedStatement.setString(6, String.valueOf(driver.getRole()));
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                driver.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("can't create driver in db", e);
        }
        return driver;
    }

    @Override
    public Optional<Driver> get(Long id) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(parseDriver(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("can't get driver from db by id", e);
        }
        return Optional.empty();
    }

    @Override
    public Driver update(Driver driver) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, driver.getFirstName());
            preparedStatement.setString(2, driver.getLastName());
            preparedStatement.setLong(3, driver.getCarId());
            preparedStatement.setString(4, driver.getLogin());
            preparedStatement.setString(5, driver.getPassword());
            preparedStatement.setObject(6, driver.getRole());
            preparedStatement.setLong(7, driver.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("can't update driver in db", e);
        }
        return driver;
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("can't delete driver from db", e);
        }
    }

    @Override
    public List<Driver> getByCarId(Long carId) {
        List<Driver> drivers = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_CAR_ID_QUERY)) {
            preparedStatement.setLong(1, carId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                drivers.add(parseDriver(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("can't get driver from db by carId", e);
        }
        return drivers;
    }

    @Override
    public Optional<Driver> getByLogin(String login) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_LOGIN_QUERY)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(parseDriver(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("can't get driver from db by login", e);
        }
        return Optional.empty();
    }

    private Driver parseDriver(ResultSet resultSet) throws SQLException {
        Driver driver = new Driver();
        driver.setId(resultSet.getLong("id"));
        driver.setFirstName(resultSet.getString("first_name"));
        driver.setLastName(resultSet.getString("last_name"));
        driver.setCarId(resultSet.getLong("car_id"));
        driver.setLogin(resultSet.getString("login"));
        driver.setPassword(resultSet.getString("password"));
        driver.setRole(Role.valueOf(resultSet.getString("role").toUpperCase(Locale.ROOT)));
        return driver;
    }
}
