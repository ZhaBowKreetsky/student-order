package edu.java.studentorder.validator;

//import com.sun.jdi.connect.Transport;
import edu.java.studentorder.domain.*;
import edu.java.studentorder.exception.CityRegisterException;
import java.util.List;

public class CityRegisterValidator {

    public static final String IN_CODE = "NO_SYSTEM";
    public String hostName;
    protected int port;
    private String login;
    String password;

    private CityRegisterChecker personChecker;

    public CityRegisterValidator() {
        personChecker = new RealCityRegisterChecker();
    }

    public AnswerCityRegister checkCityRegister(StudentOrder so) {
        AnswerCityRegister ans = new AnswerCityRegister();

            ans.addItem(checkPerson(so.getHusband()));
            ans.addItem(checkPerson(so.getWife()));

            List<Child> children = so.getChildren();

            for(Child child: children) {
                ans.addItem(checkPerson(child));
            }

        return ans;
    }

    private AnswerCityRegisterItem checkPerson(Person person){
        AnswerCityRegisterItem.CityStatus status = null;
        AnswerCityRegisterItem.CityError error = null;
        try {
            CityRegisterResponse tmp = personChecker.checkPerson(person);
            status = tmp.isRegistered() ?
                    AnswerCityRegisterItem.CityStatus.YES :
                    AnswerCityRegisterItem.CityStatus.NO;
        } catch (CityRegisterException ex){
            ex.printStackTrace();
            status = AnswerCityRegisterItem.CityStatus.ERROR;
            error = new AnswerCityRegisterItem.CityError(ex.getCode(), ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            status = AnswerCityRegisterItem.CityStatus.ERROR;
            error = new AnswerCityRegisterItem.CityError(IN_CODE, ex.getMessage());
        }

        AnswerCityRegisterItem ans = new AnswerCityRegisterItem(person, status, error);

        return ans;
    }
}
