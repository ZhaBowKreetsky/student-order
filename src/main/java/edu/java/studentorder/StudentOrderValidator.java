package edu.java.studentorder;

import edu.java.studentorder.dao.StudentOrderDaoImpl;
import edu.java.studentorder.domain.*;
import edu.java.studentorder.exception.DaoException;
import edu.java.studentorder.mail.MailSender;
import edu.java.studentorder.validator.ChildrenValidator;
import edu.java.studentorder.validator.CityRegisterValidator;
import edu.java.studentorder.validator.StudentValidator;
import edu.java.studentorder.validator.WeddingValidator;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class StudentOrderValidator {

    private CityRegisterValidator cityRegisterVal;
    private WeddingValidator weddingVal;
    private ChildrenValidator childrenVal;
    private StudentValidator studentVal;
    private MailSender mailSender;

    public StudentOrderValidator(){
        cityRegisterVal = new CityRegisterValidator();
        weddingVal = new WeddingValidator();
        childrenVal = new ChildrenValidator();
        studentVal = new StudentValidator();
        mailSender = new MailSender();
    }

    public static void main(String[] args) {
        StudentOrderValidator sov = new StudentOrderValidator();
        sov.checkAll();
    }

    public void checkAll(){
        try {
            List<StudentOrder> soList = readStudentOrders();
            for (StudentOrder so : soList) {
                System.out.println();
                checkOneOrder(so);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<StudentOrder> readStudentOrders() throws DaoException {
        return new StudentOrderDaoImpl().getStudentOrders();
    }

    public void checkOneOrder(StudentOrder so){
        AnswerCityRegister cityAnswer = checkCityRegister(so);
//        AnswerWedding wedAnswer = checkWedding(so);
//        AnswerChildren childAnswer = checkChildren(so);
//        AnswerStudent studentAnswer = checkStudent(so);
//
//        sendMail(so);
    }

    public AnswerCityRegister checkCityRegister (StudentOrder so) {
        return cityRegisterVal.checkCityRegister(so);
    }

    public AnswerWedding checkWedding(StudentOrder so) {
        return weddingVal.checkWedding(so);
    }

    public AnswerChildren checkChildren(StudentOrder so) {
        return childrenVal.checkChildren(so);
    }

    public AnswerStudent checkStudent(StudentOrder so) {
        return studentVal.checkStudent(so);
    }

    public void sendMail(StudentOrder so){
        mailSender.sendMail(so);
    }
}
