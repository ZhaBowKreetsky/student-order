package edu.java.studentorder.dao;

import edu.java.studentorder.config.Config;
import edu.java.studentorder.domain.CountryArea;
import edu.java.studentorder.domain.PassportOffice;
import edu.java.studentorder.domain.RegisterOffice;
import edu.java.studentorder.domain.Street;
import edu.java.studentorder.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DictionaryDaoImpl implements DictionaryDao {

    private static final Logger logger = LoggerFactory.getLogger(DictionaryDaoImpl.class);
    private static final String GET_STREET = "SELECT street_code, street_name FROM ps_street WHERE UPPER(street_name) LIKE UPPER(?)";
    private static final String GET_PASSPORT = "SELECT * FROM ps_passport_office WHERE p_office_area_id = ?";
    private static final String GET_REGISTER = "SELECT * FROM ps_register_office WHERE r_office_area_id = ?";
    private static final String GET_AREA = "SELECT * FROM ps_country_struct WHERE area_id like ? and area_id <> ?";

    private Connection getConnection() throws SQLException {
        return ConnectionBuilder.getConnection();
    }

    public List<Street> findStreets(String pattern) throws DaoException{
        List<Street> result = new LinkedList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_STREET)) {

            stmt.setString(1, "%" + pattern + "%");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Street str = new Street(rs.getLong("street_code"), rs.getString("street_name"));
                result.add(str);
            }
        } catch (SQLException ex){
            logger.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }
        return result;
    }

    @Override
    public List<PassportOffice> findPassportOffices(String areaId) throws DaoException {
        List<PassportOffice> result = new LinkedList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_PASSPORT)) {

            stmt.setString(1, areaId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                PassportOffice po = new PassportOffice(rs.getLong("p_office_id"), rs.getString("p_office_area_id"), rs.getString("p_office_name"));
                result.add(po);
            }
        } catch (SQLException ex){
            logger.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }
        return result;
    }

    @Override
    public List<RegisterOffice> findRegisterOffices(String areaId) throws DaoException {
        List<RegisterOffice> result = new LinkedList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_REGISTER)) {

            stmt.setString(1, areaId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                RegisterOffice ro = new RegisterOffice(rs.getLong("r_office_id"), rs.getString("r_office_area_id"), rs.getString("r_office_name"));
                result.add(ro);
            }
        } catch (SQLException ex){
            logger.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }
        return result;
    }

    @Override
    public List<CountryArea> findAreas(String areaId) throws DaoException {
        List<CountryArea> result = new LinkedList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_AREA)) {

            String param1 = buildParam(areaId);
            String param2 = areaId;


            stmt.setString(1, param1);
            stmt.setString(2, param2);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                CountryArea ca = new CountryArea(rs.getString("area_id"), rs.getString("area_name"));
                result.add(ca);
            }
        } catch (SQLException ex){
            logger.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }
        return result;
    }

    private String buildParam(String areaId) throws SQLException {
        if (areaId == null || areaId.trim().isEmpty()){
            return "__0000000000";
        } else if (areaId.endsWith("0000000000")) {
            return areaId.substring(0,2) + "___0000000";
        } else if (areaId.endsWith("0000000")) {
            return areaId.substring(0,5) + "___0000";
        } else if (areaId.endsWith("0000")) {
            return areaId.substring(0,8) + "____";
        }
        throw new SQLException("Invalid parameter 'areaId':" + areaId);
    }

}
