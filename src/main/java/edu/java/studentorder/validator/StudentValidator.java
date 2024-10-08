package edu.java.studentorder.validator;

import edu.java.studentorder.domain.AnswerStudent;
import edu.java.studentorder.domain.StudentOrder;

public class StudentValidator {
    public AnswerStudent checkStudent(StudentOrder so) {
        System.out.println("Student check is running");
        return new AnswerStudent();
    }
}
