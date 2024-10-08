package edu.java.studentorder.validator;

import edu.java.studentorder.domain.AnswerWedding;
import edu.java.studentorder.domain.StudentOrder;

public class WeddingValidator {
    public AnswerWedding checkWedding(StudentOrder so) {
        System.out.println("Wedding is running");
        return new AnswerWedding();
    }
}
