package edu.java.studentorder.domain;

public enum StudentOrderStatus {
    START, CHECKED;

    public static StudentOrderStatus fromValue(int val){
        for (StudentOrderStatus sos : StudentOrderStatus.values()){
            if(sos.ordinal() == val){
                return sos;
            }
        }
        throw new RuntimeException("Unknown value:" + val);
    }
}
