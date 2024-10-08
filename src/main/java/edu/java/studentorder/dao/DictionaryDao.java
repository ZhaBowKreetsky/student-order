package edu.java.studentorder.dao;

import edu.java.studentorder.domain.CountryArea;
import edu.java.studentorder.domain.PassportOffice;
import edu.java.studentorder.domain.RegisterOffice;
import edu.java.studentorder.domain.Street;
import edu.java.studentorder.exception.DaoException;

import java.util.List;

public interface DictionaryDao {
    List<Street> findStreets(String pattern) throws DaoException;
    List<PassportOffice> findPassportOffices(String pattern) throws DaoException;
    List<RegisterOffice> findRegisterOffices(String pattern) throws DaoException;
    List<CountryArea> findAreas(String areaId) throws DaoException;

}
