package ro.esolutions.demotests.ui.pages

import com.codeborne.selenide.Condition

import static com.codeborne.selenide.Selenide.$
import static com.codeborne.selenide.Selenide.open

class CreateClientPage {

    static CreateClientPage open() {
        CreateClientPage createClientPage = open("/client/add", CreateClientPage.class)
        createClientPage.assertIsOnPage()
        createClientPage
    }

    def assertIsOnPage() {
        $(".qa-client-edit-page").should(Condition.exist)
    }

    def fillFirstName(firstName) {
        $("#firstName").val(firstName)
    }

    def fillLastName(lastName) {
        $("#lastName").val(lastName)
    }

    def fillAddress(address) {
        $("#address").val(address)
    }

    def fillEmail(emailAddress) {
        $("#emailAddress").val(emailAddress)
    }

    def fillPhone(phoneNumber) {
        $("#phoneNumber").val(phoneNumber)
    }

    def clickSaveButton() {
        $("#saveButtoon").click()
    }

    def firstNameValidationErrorIsDisplayed() {
        $(".qa-firstName-error").isDisplayed()
    }

    def lastNameValidationErrorIsDisplayed() {
        $(".qa-lastName-error").isDisplayed()
    }
}
