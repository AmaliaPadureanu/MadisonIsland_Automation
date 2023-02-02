package Tests.ObjectModels;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BillingModel {

    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private String city;
    private String state;
    private String zipcode;
    private String country;
    private String telephone;
    private String firstnameError;
    private String lastnameError;
    private String emailError;
    private String addressError;
    private String cityError;
    private String zipcodeError;
    private String countryError;
    private String telephoneError;

    public BillingModel() {

    }

    public BillingModel(String firstname, String lastname, String email, String address, String city, String state,
                        String zipcode, String country, String telephone, String firstnameError, String lastnameError,
                        String emailError, String addressError, String cityError, String zipcodeError, String countryError,
                        String telephoneError){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.country = country;
        this.telephone = telephone;
        this.firstnameError = firstnameError;
        this.lastnameError = lastnameError;
        this.emailError = emailError;
        this.addressError = addressError;
        this.cityError = cityError;
        this.zipcodeError = zipcodeError;
        this.countryError = countryError;
        this.telephoneError = telephoneError;
    }

    @Override
    public String toString() {
        return "BillingModel{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", country='" + country + '\'' +
                ", telephone='" + telephone + '\'' +
                ", firstnameError='" + firstnameError + '\'' +
                ", lastnameError='" + lastnameError + '\'' +
                ", emailError='" + emailError + '\'' +
                ", addressError='" + addressError + '\'' +
                ", cityError='" + cityError + '\'' +
                ", zipcodeError='" + zipcodeError + '\'' +
                ", countryError='" + countryError + '\'' +
                ", telephoneError='" + telephoneError + '\'' +
                '}';
    }
}
