package edu.java.studentorder.validator;

import edu.java.studentorder.domain.CityRegisterResponse;
import edu.java.studentorder.domain.Person;
import edu.java.studentorder.exception.CityRegisterException;

public interface CityRegisterChecker {

    CityRegisterResponse checkPerson(Person person) throws CityRegisterException;


}
