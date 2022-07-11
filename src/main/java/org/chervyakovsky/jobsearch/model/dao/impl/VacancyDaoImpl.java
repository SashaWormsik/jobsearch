package org.chervyakovsky.jobsearch.model.dao.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.dao.VacancyDao;
import org.chervyakovsky.jobsearch.model.entity.Location;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.entity.Vacancy;
import org.chervyakovsky.jobsearch.model.mapper.ColumnName;
import org.chervyakovsky.jobsearch.model.mapper.MapperFromDbToEntity;
import org.chervyakovsky.jobsearch.model.mapper.impl.LocationMapperFromDbToEntity;
import org.chervyakovsky.jobsearch.model.mapper.impl.UserInfoMapperFromDbToEntity;
import org.chervyakovsky.jobsearch.model.mapper.impl.VacancyMapperFromDbToEntity;
import org.chervyakovsky.jobsearch.model.pool.ConnectionPool;
import org.chervyakovsky.jobsearch.util.Pageable;

import java.sql.Date;
import java.sql.*;
import java.util.*;

/**
 * The type VacancyDaoImpl class executes requests to the DB. Singleton.
 */
public class VacancyDaoImpl implements VacancyDao {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String FOR_LIKE_ALL = "%%";
    private static final String FOR_LIKE_ANY = "%";

    private static final String SELECT_VACANCY_BY_ID =
            "SELECT * FROM vacancy WHERE v_vacancy_id = ?";

    private static final String SELECT_ALL_VACANCIES_FOR_COMPANY =
            "SELECT *, count(*) OVER() AS total_count " +
                    "FROM vacancy " +
                    "JOIN location ON v_location_id = l_location_id " +
                    "WHERE v_company_id = ? " +
                    "ORDER BY v_create_date DESC " +
                    "LIMIT ? OFFSET ?";
    ;
    private static final String SELECT_VACANCY_BY_ID_WITH_LOCATION_AND_COMPANY =
            "SELECT * FROM vacancy " +
                    "JOIN user_info ON v_company_id = u_user_info_id " +
                    "JOIN location ON v_location_id = l_location_id " +
                    "WHERE v_vacancy_id = ?";
    private static final String INSERT_NEW_VACANCY =
            "INSERT INTO vacancy (v_create_date, v_job_title, v_company_id, v_location_id, v_salary, v_currency, " +
                    "v_work_experience, v_responsibilities, v_requirement, v_working_conditions, v_vacancy_status) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?::work_experience_enum, ?, ?, ?, ?)";
    private static final String INSERT_NEW_LOCATION_FOR_NEW_OR_UPDATE_VACANCY =
            "INSERT INTO location (l_country, l_city) " +
                    "VALUES (?, ?)";
    private static final String UPDATE_VACANCY_BY_ID =
            "UPDATE vacancy " +
                    "SET v_create_date = ?, v_job_title = ?, v_company_id = ?, v_location_id = ?, v_salary = ?, " +
                    "v_currency = ?, v_work_experience = ?::work_experience_enum, v_responsibilities = ?, " +
                    "v_requirement = ?, v_working_conditions = ?, v_vacancy_status = ? " +
                    "WHERE v_vacancy_id = ?";
    private static final String SELECT_VACANCY_BY_CRITERIA =
            "SELECT *, count(*) OVER() AS total_count " +
                    "FROM vacancy " +
                    "JOIN user_info ON v_company_id = u_user_info_id " +
                    "JOIN location ON v_location_id = l_location_id " +
                    "WHERE l_country ILIKE ? AND " +
                    "l_city ILIKE ? AND " +
                    "v_job_title ILIKE ? AND " +
                    "v_work_experience::text ILIKE ? AND " +
                    "v_vacancy_status = true " +
                    "ORDER BY v_create_date DESC " +
                    "LIMIT ? OFFSET ?;";

    private static VacancyDaoImpl instance;

    private VacancyDaoImpl() {
    }

    public static VacancyDaoImpl getInstance() {
        if (instance == null) {
            instance = new VacancyDaoImpl();
        }
        return instance;
    }

    @Override
    public Optional<Vacancy> findById(long id) throws DaoException {
        Optional<Vacancy> optionalVacancy = Optional.empty();
        MapperFromDbToEntity<Vacancy> vacancyMapper = new VacancyMapperFromDbToEntity();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_VACANCY_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    optionalVacancy = vacancyMapper.map(resultSet);
                }
            }
        } catch (SQLException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new DaoException(exception);
        }
        return optionalVacancy;
    }

    @Override
    public Map<Vacancy, Location> findVacancyForCompany(long companyId, Pageable pageable) throws DaoException {
        Map<Vacancy, Location> result = new LinkedHashMap<>();
        MapperFromDbToEntity<Vacancy> vacancyMapper = new VacancyMapperFromDbToEntity();
        MapperFromDbToEntity<Location> locationMapper = new LocationMapperFromDbToEntity();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_VACANCIES_FOR_COMPANY)) {
            statement.setLong(1, companyId);
            statement.setInt(2, pageable.getSize());
            statement.setInt(3, pageable.getOffset());
            try (ResultSet resultSet = statement.executeQuery()) {
                boolean flag = true;
                while (resultSet.next()) {
                    if (flag) {
                        pageable.calculatePageCount(resultSet.getInt(ColumnName.COUNT_ROWS));
                        flag = false;
                    }
                    Vacancy vacancyFromDb = vacancyMapper.map(resultSet).orElse(null);
                    Location locationFromDb = locationMapper.map(resultSet).orElse(null);
                    result.put(vacancyFromDb, locationFromDb);
                }
            }
        } catch (SQLException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new DaoException(exception);
        }
        return result;
    }

    @Override
    public Map<Vacancy, Map.Entry<Location, UserInfo>> findVacancyById(long vacancyId) throws DaoException {
        HashMap<Vacancy, Map.Entry<Location, UserInfo>> result = new LinkedHashMap<>();
        MapperFromDbToEntity<Vacancy> vacancyMapper = new VacancyMapperFromDbToEntity();
        MapperFromDbToEntity<Location> locationMapper = new LocationMapperFromDbToEntity();
        MapperFromDbToEntity<UserInfo> userMapper = new UserInfoMapperFromDbToEntity();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_VACANCY_BY_ID_WITH_LOCATION_AND_COMPANY)) {
            statement.setLong(1, vacancyId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Vacancy vacancyFromDb = vacancyMapper.map(resultSet).orElse(null);
                    Location locationFromDb = locationMapper.map(resultSet).orElse(null);
                    UserInfo userFromDb = userMapper.map(resultSet).orElse(null);
                    Map.Entry<Location, UserInfo> tempEntry = new AbstractMap.SimpleEntry<>(locationFromDb, userFromDb);
                    result.put(vacancyFromDb, tempEntry);
                }
            }
        } catch (SQLException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new DaoException(exception);
        }
        return result;
    }

    @Override
    public boolean update(Vacancy vacancy) throws DaoException {
        return runUpdateOrInsertQueryForVacancy(vacancy, UPDATE_VACANCY_BY_ID);
    }

    @Override
    public boolean updateVacancyWithCreateNewLocation(Vacancy vacancy, Location location) throws DaoException {
        return runUpdateOrInsertQueryForVacancyWithLocation(vacancy, location, UPDATE_VACANCY_BY_ID);
    }

    @Override
    public boolean insert(Vacancy vacancy) throws DaoException {
        return runUpdateOrInsertQueryForVacancy(vacancy, INSERT_NEW_VACANCY);
    }

    @Override
    public boolean insertVacancyWithCreateNewLocation(Vacancy vacancy, Location location) throws DaoException {
        return runUpdateOrInsertQueryForVacancyWithLocation(vacancy, location, INSERT_NEW_VACANCY);
    }

    @Override
    public Map<Vacancy, Map.Entry<Location, UserInfo>> findByCriteria(Vacancy vacancy, Location location, Pageable pageable) throws DaoException {
        HashMap<Vacancy, Map.Entry<Location, UserInfo>> result = new LinkedHashMap<>();
        MapperFromDbToEntity<Vacancy> vacancyMapper = new VacancyMapperFromDbToEntity();
        MapperFromDbToEntity<Location> locationMapper = new LocationMapperFromDbToEntity();
        MapperFromDbToEntity<UserInfo> userMapper = new UserInfoMapperFromDbToEntity();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_VACANCY_BY_CRITERIA)) {
            String country = location.getCountry().trim().isEmpty() ? FOR_LIKE_ALL : location.getCountry();
            String city = location.getCity().trim().isEmpty() ? FOR_LIKE_ALL : location.getCity();
            String profession = vacancy.getJobTitle().trim().isEmpty() ? FOR_LIKE_ALL : FOR_LIKE_ANY + vacancy.getJobTitle() + FOR_LIKE_ANY;
            String workExperienceStatus = vacancy.getWorkExperienceStatus() == null ? FOR_LIKE_ALL : vacancy.getWorkExperienceStatus().name();
            statement.setString(1, country);
            statement.setString(2, city);
            statement.setString(3, profession);
            statement.setString(4, workExperienceStatus);
            statement.setInt(5, pageable.getSize());
            statement.setInt(6, pageable.getOffset());
            try (ResultSet resultSet = statement.executeQuery()) {
                boolean flag = true;
                while (resultSet.next()) {
                    if (flag) {
                        pageable.calculatePageCount(resultSet.getInt(ColumnName.COUNT_ROWS));
                        flag = false;
                    }
                    pageable.calculatePageCount(resultSet.getInt(ColumnName.COUNT_ROWS));
                    Vacancy vacancyFromDb = vacancyMapper.map(resultSet).orElse(new Vacancy());
                    Location locationFromDb = locationMapper.map(resultSet).orElse(new Location());
                    UserInfo userFromDb = userMapper.map(resultSet).orElse(new UserInfo());
                    Map.Entry<Location, UserInfo> tempEntry = new AbstractMap.SimpleEntry<>(locationFromDb, userFromDb);
                    result.put(vacancyFromDb, tempEntry);
                }
            }
        } catch (SQLException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new DaoException(exception);
        }
        return result;
    }

    private int insertVacancyStatement(Vacancy vacancy, PreparedStatement statement) throws SQLException {
        statement.setDate(1, new Date(vacancy.getCreateDate().getTime()));
        statement.setString(2, vacancy.getJobTitle());
        statement.setLong(3, vacancy.getCompanyId());
        statement.setLong(4, vacancy.getLocationId());
        statement.setBigDecimal(5, vacancy.getSalary());
        statement.setString(6, vacancy.getCurrency());
        statement.setObject(7, vacancy.getWorkExperienceStatus(), Types.OTHER);
        statement.setString(8, vacancy.getResponsibilities());
        statement.setString(9, vacancy.getRequirement());
        statement.setString(10, vacancy.getWorkingConditions());
        statement.setBoolean(11, vacancy.getVacancyStatus());
        if (vacancy.getId() != 0) {
            statement.setLong(12, vacancy.getId());
        }
        return statement.executeUpdate();
    }

    private int insertLocationStatement(Location location, PreparedStatement locationStatement) throws SQLException {
        locationStatement.setString(1, location.getCountry());
        locationStatement.setString(2, location.getCity());
        return locationStatement.executeUpdate();
    }

    private boolean runUpdateOrInsertQueryForVacancy(Vacancy vacancy, String query) throws DaoException {
        boolean result = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            int row = insertVacancyStatement(vacancy, statement);
            if (row != 0) {
                result = true;
            }
        } catch (SQLException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new DaoException(exception);
        }
        return result;
    }

    private boolean runUpdateOrInsertQueryForVacancyWithLocation(Vacancy vacancy, Location location, String query) throws DaoException {
        boolean result = false;
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement vacancyStatement = connection.prepareStatement(query);
             PreparedStatement locationStatement = connection.prepareStatement(INSERT_NEW_LOCATION_FOR_NEW_OR_UPDATE_VACANCY, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            int rowLocation = insertLocationStatement(location, locationStatement);
            try (ResultSet resultSet = locationStatement.getGeneratedKeys()) {
                resultSet.next();
                long locationId = resultSet.getLong(1);
                vacancy.setLocationId(locationId);
            }
            int rowVacancy = insertVacancyStatement(vacancy, vacancyStatement);
            if (rowVacancy != 0 && rowLocation != 0) {
                connection.commit();
                result = true;
            } else {
                connection.rollback();
            }
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException sqlException) {
                LOGGER.log(Level.ERROR, sqlException);
                throw new DaoException(sqlException);
            }
            LOGGER.log(Level.ERROR, exception);
            throw new DaoException(exception);
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException exception) {
                LOGGER.log(Level.ERROR, exception);
                throw new DaoException(exception);
            }
        }
        return result;
    }
}
