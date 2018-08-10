package ro.esolutions.demotests.ui.specs

import ro.esolutions.demotests.ui.pages.CreateClientPage
import ro.esolutions.demotests.ui.pages.ListClientPage
import spock.lang.Specification
import spock.lang.Unroll

class ClientSpecUI extends Specification {

    def "Open client list page"() {
        when:
        def listPage = ListClientPage.open()

        then:
        listPage.assertIsOnPage()
    }

    @Unroll
    def "Create a new client"() {
        given:
        def createClientPage = CreateClientPage.open()

        when:
        createClientPage.fillFirstName(firstName)
        createClientPage.fillLastName(lastName)
        createClientPage.fillAddress(address)
        createClientPage.fillEmail(emailAddress)
        createClientPage.fillPhone(phoneNumber)

        and:
        createClientPage.clickSaveButton()

        then:
        createClientPage.firstNameValidationErrorIsDisplayed() == firstNameErrorDisplayed
        createClientPage.lastNameValidationErrorIsDisplayed() == lastNameErrorDisplayed

        where:
        firstName  | lastName | address     | emailAddress     | phoneNumber | firstNameErrorDisplayed | lastNameErrorDisplayed
        ""         | ""       | "Bucuresti" | "tests@mail.com" | "07445234"  | true                    | true
        "masinuta" | ""       | "Bucuresti" | "tests@mail.com" | "07445234"  | true                    | true
    }


}