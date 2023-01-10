package Tests.ObjectModels;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddressModel {

    private String streetAddress1;
    private String streetAddress2;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}
