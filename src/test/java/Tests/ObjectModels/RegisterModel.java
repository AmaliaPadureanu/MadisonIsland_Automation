package Tests.ObjectModels;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterModel {

    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
    private Boolean signUpForNewsletter;
    private String firstNameError;
    private String lastNameError;
    private String emailError;
    private String passwordError;
    private String confirmPasswordError;
    private String emailErrorPopup;

    public RegisterModel() {

    }

    public RegisterModel(String firstName, String middleName, String lastName, String email, String password,
                         String confirmPassword, Boolean signUpForNewsletter, String firstNameError, String lastNameError, String emailError,
                         String passwordError, String confirmPasswordError, String emailErrorPopup) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.signUpForNewsletter = signUpForNewsletter;
        this.firstNameError = firstNameError;
        this.lastNameError = lastNameError;
        this.emailError = emailError;
        this.passwordError = passwordError;
        this.confirmPasswordError = confirmPasswordError;
        this.emailErrorPopup = emailErrorPopup;
    }
}
