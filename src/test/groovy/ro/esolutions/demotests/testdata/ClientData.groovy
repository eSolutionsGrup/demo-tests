package ro.esolutions.demotests.testdata

import ro.esolutions.demotests.entities.Client
import ro.esolutions.demotests.models.ClientModel

class ClientData {

    static aClient(Map overrides = [:]) {
        Map values = [id: 1L, firstName: "Gigi", lastName: "Masinuta", address: "Bucuresti", emailAddress: "gigi@email.com",
                      phoneNumber: "07669504", active: true]
        values << overrides
        return Client.newInstance(values)
    }

    static aClientModel(Map overrides = [:]) {
        Map values = [id: 1L, firstName: "Gigi", lastName: "Masinuta", address: "Bucuresti", emailAddress: "gigi@email.com",
        phoneNumber: "07669504", active: true]
        values << overrides
        return ClientModel.newInstance(values)
    }
}
