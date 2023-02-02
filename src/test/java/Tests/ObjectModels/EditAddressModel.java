package Tests.ObjectModels;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EditAddressModel {

    private ContactInformationModel contactInformation;
    private AddressModel address;
    private String firstNameError;
    private String lastNameError;
    private String telephoneError;
    private String streetAddress1Error;
    private String cityError;
    private String zipCodeError;
    private String countryError;

    public EditAddressModel() {

    }

    public EditAddressModel(String firstName, String middleName, String lastName, String company,
                            String telephone, String fax, String streetAddress1, String streetAddress2, String city,
                            String state, String zipCode, String country, String firstNameError, String lastNameError,
                            String telephoneError, String streetAddress1Error, String cityError, String zipCodeError, String countryError) {

        ContactInformationModel contactInformationModel = new ContactInformationModel();
        contactInformationModel.setFirstName(firstName);
        contactInformationModel.setMiddleName(middleName);
        contactInformationModel.setLastName(lastName);
        contactInformationModel.setCompany(company);
        contactInformationModel.setTelephone(telephone);
        contactInformationModel.setFax(fax);

        AddressModel addressModel = new AddressModel();
        addressModel.setStreetAddress1(streetAddress1);
        addressModel.setStreetAddress2(streetAddress2);
        addressModel.setCity(city);
        addressModel.setState(state);
        addressModel.setZipCode(zipCode);
        addressModel.setCountry(country);

        this.contactInformation = contactInformationModel;
        this.address = addressModel;
        this.firstNameError = firstNameError;
        this.lastNameError = lastNameError;
        this.telephoneError = telephoneError;
        this.streetAddress1Error = streetAddress1Error;
        this.cityError = cityError;
        this.zipCodeError = zipCodeError;
        this.countryError = countryError;
    }

    @Override
    public String toString() {
        return "EditAddressModel{" +
                "contactInformation=" + contactInformation +
                ", address=" + address +
                ", firstNameError='" + firstNameError + '\'' +
                ", lastNameError='" + lastNameError + '\'' +
                ", telephoneError='" + telephoneError + '\'' +
                ", streetAddress1Error='" + streetAddress1Error + '\'' +
                ", cityError='" + cityError + '\'' +
                ", zipCodeError='" + zipCodeError + '\'' +
                ", countryError='" + countryError + '\'' +
                '}';
    }
}
