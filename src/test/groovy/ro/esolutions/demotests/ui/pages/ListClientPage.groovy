package ro.esolutions.demotests.ui.pages

import com.codeborne.selenide.Condition

import static com.codeborne.selenide.Selenide.$
import static com.codeborne.selenide.Selenide.open

class ListClientPage {

    static ListClientPage open() {
        ListClientPage clientListPage = open("/client/view", ListClientPage.class)
        clientListPage.assertIsOnPage()
        clientListPage
    }

    def assertIsOnPage() {
        $(".qa-client-view-page").should(Condition.exist)
    }
}
