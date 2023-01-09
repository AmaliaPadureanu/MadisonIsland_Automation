package Tests.ObjectModels;

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

    public void setAccount(AccountModel account) {
        this.account = account;
    }

    public AccountModel getAccount() {
        return account;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setInvalidUserOrPasswordError(String invalidUserOrPasswordError) {
        this.invalidUserOrPasswordError = invalidUserOrPasswordError;
    }

    public String getInvalidUserOrPasswordError() {
        return invalidUserOrPasswordError;
    }

    public void setInvalidUserOrPasswordErrorPopup(String invalidUserOrPasswordErrorPopup) {
        this.invalidUserOrPasswordErrorPopup = invalidUserOrPasswordErrorPopup;
    }

    public String getInvalidUserOrPasswordErrorPopup() {
        return invalidUserOrPasswordErrorPopup;
    }

}
