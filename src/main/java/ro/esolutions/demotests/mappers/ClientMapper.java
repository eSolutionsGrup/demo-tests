package ro.esolutions.demotests.mappers;

import ro.esolutions.demotests.entities.Client;
import ro.esolutions.demotests.models.ClientModel;

public final class ClientMapper {

    private ClientMapper() {}

    public static ClientModel toModel(final Client entity) {
        return ClientModel.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .address(entity.getAddress())
                .emailAddress(entity.getEmailAddress())
                .phoneNumber(entity.getPhoneNumber())
                .active(entity.isActive())
                .build();
    }

    public static Client toEntity(final ClientModel model) {
        return Client.builder()
                .id(model.getId())
                .firstName(model.getFirstName())
                .lastName(model.getLastName())
                .address(model.getAddress())
                .emailAddress(model.getEmailAddress())
                .phoneNumber(model.getPhoneNumber())
                .active(model.isActive())
                .build();
    }

}
