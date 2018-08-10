package ro.esolutions.demotests.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientModel {

    private Long id;

    @NotEmpty(message = "Please enter first name")
    private String firstName;

    @NotEmpty(message = "Please enter last name")
    private String lastName;

    @NotEmpty(message = "Please enter address")
    private String address;

    @NotEmpty(message = "Please enter email")
    private String emailAddress;

    @NotEmpty(message = "Please enter phone")
    private String phoneNumber;

    private boolean active;

    public String getName() {
        return this.getFirstName() + " " + this.getLastName();
    }
}
