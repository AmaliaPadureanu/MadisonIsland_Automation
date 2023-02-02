package Tests.ObjectModels;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginModel {

    private AccountModel account;
    private String emailError;
    private String passwordError;
    private String invalidUserOrPasswordError;
    private String invalidUserOrPasswordErrorPopup;

    public LoginModel() {

    }

    public LoginModel(String email, String password, String emailError, String passwordError,
                      String invalidUserOrPasswordError, String invalidUserOrPasswordErrorPopup) {
        AccountModel accountModel = new AccountModel();
        accountModel.setEmail(email);
        accountModel.setPassword(password);

        this.account = accountModel;
        this.emailError = emailError;
        this.passwordError = passwordError;
        this.invalidUserOrPasswordError = invalidUserOrPasswordError;
        this.invalidUserOrPasswordErrorPopup = invalidUserOrPasswordErrorPopup;
    }

    @Override
    public String toString() {
        return "LoginModel{" +
                "account=" + account +
                ", emailError='" + emailError + '\'' +
                ", passwordError='" + passwordError + '\'' +
                ", invalidUserOrPasswordError='" + invalidUserOrPasswordError + '\'' +
                ", invalidUserOrPasswordErrorPopup='" + invalidUserOrPasswordErrorPopup + '\'' +
                '}';
    }
}
