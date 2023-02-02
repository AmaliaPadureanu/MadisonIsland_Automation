package Tests.ObjectModels;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EditAccountInformationModel {

    private String firstname;
    private String middlename;
    private String lastname;
    private String email;
    private String firstnameError;
    private String lastnameError;
    private String emailError;
    private String emailErrorPopup;

    public EditAccountInformationModel() {

    }

    public EditAccountInformationModel(String firstname, String middlename, String lastname, String email, String firstnameError,
                                       String lastnameError, String emailError, String emailErrorPopup) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.email = email;
        this.firstnameError = firstnameError;
        this.lastnameError = lastnameError;
        this.emailError = emailError;
        this.emailErrorPopup = emailErrorPopup;
    }

    @Override
    public String toString() {
        return "EditAccountInformationModel{" +
                "firstname='" + firstname + '\'' +
                ", middlename='" + middlename + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", firstnameError='" + firstnameError + '\'' +
                ", lastnameError='" + lastnameError + '\'' +
                ", emailError='" + emailError + '\'' +
                ", emailErrorPopup='" + emailErrorPopup + '\'' +
                '}';
    }
}
