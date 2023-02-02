package Tests.ObjectModels;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ContactInformationModel {

    private String firstName;
    private String middleName;
    private String lastName;
    private String company;
    private String telephone;
    private String fax;

    @Override
    public String toString() {
        return "ContactInformationModel{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", company='" + company + '\'' +
                ", telephone='" + telephone + '\'' +
                ", fax='" + fax + '\'' +
                '}';
    }
}
