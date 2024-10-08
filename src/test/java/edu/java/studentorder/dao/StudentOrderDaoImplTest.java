package edu.java.studentorder.dao;

import edu.java.studentorder.domain.*;
import edu.java.studentorder.exception.DaoException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class StudentOrderDaoImplTest {

    @BeforeClass
    public static void startUp() throws URISyntaxException, IOException {
        DBInit.startUp();
    }

    @Test
    public void saveStudentOrder() throws DaoException {
        StudentOrder so = buildStudentOrder(10);
        Long id = new StudentOrderDaoImpl().saveStudentOrder(so);
    }

    @Test(expected = DaoException.class)
    public void saveStudentOrderError() throws DaoException {
        StudentOrder so = buildStudentOrder(10);
        so.getHusband().setSurName(null);
        Long id = new StudentOrderDaoImpl().saveStudentOrder(so);
    }

    @Test
    public void getStudentOrders() throws DaoException {
        List<StudentOrder> list = new StudentOrderDaoImpl().getStudentOrders();
    }

    static StudentOrder buildStudentOrder(long id){
        Street street = new Street(1L, "First Street");

        StudentOrder so = new StudentOrder();
        so.setStudentOrderId(id);
        so.setMarriageCertificateId("" + (123456000 + id));
        so.setMarriageDate(LocalDate.of(2016,7,4));

        RegisterOffice ro = new RegisterOffice(1L, "","");
        so.setMarriageOffice(ro);

        Address address = new Address("195000", street, "10", "2", "121");


        RegisterOffice registerOffice = new RegisterOffice(2L, "LLV", "LKX");


        //Husband
        Adult husband = new Adult("Veluchko", "Pavlo", "Mukolayovuch", LocalDate.of(1995,3,18));
        husband.setPassportSeries("" + (1000 + id));
        husband.setPassportNumber("" + (100000 + id));
        husband.setIssueDate(LocalDate.of(2017,9,15));
        PassportOffice passportOffice_h = new PassportOffice(1L, "", "");
        husband.setIssueDepartment(passportOffice_h);
        husband.setStudentId("" + (100000 + id));
        husband.setAddress(address);
        husband.setUniversity(new University(2L, ""));
        husband.setStudentId("HH12345");
        //Wife
        Adult wife = new Adult("Veluchko", "Iruna", "Petrivna", LocalDate.of(1997,8,21));
        wife.setPassportSeries("" + (2000 + id));
        wife.setPassportNumber("" + (200000 + id));
        wife.setIssueDate(LocalDate.of(2018,4,5));
        PassportOffice passportOffice_w = new PassportOffice(2L, "", "");
        wife.setIssueDepartment(passportOffice_w);
        wife.setStudentId("" + (200000 + id));
        wife.setAddress(address);
        wife.setUniversity(new University(1L, ""));
        wife.setStudentId("WW12345");
        //Child
        Child child1 = new Child("Veluchko", "Evgenia", "Pavlivna", LocalDate.of(2016,1,11));
        child1.setCertificateNumber("" + (300000 + id));
        child1.setIssueDate(LocalDate.of(2018,6,11));
        child1.setIssueDepartment(registerOffice);
        child1.setAddress(address);
        //Child
        Child child2 = new Child("Veluchko", "Olexandr", "Pavlovuch", LocalDate.of(2018,10,24));
        child2.setCertificateNumber("" + (400000 + id));
        child2.setIssueDate(LocalDate.of(2018,7,19));
        child2.setIssueDepartment(registerOffice);
        child2.setAddress(address);

        so.setHusband(husband);
        so.setWife(wife);
        so.addChild(child1);
        so.addChild(child2);
        return so;
    }
}