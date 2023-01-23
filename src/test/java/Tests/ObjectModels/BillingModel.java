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

    public BillingModel() {

    }

    public BillingModel(String firstname, String lastname, String email, String address, String city, String state,
                        String zipcode, String country, String telephone){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.country = country;
        this.telephone = telephone;
    }
}
