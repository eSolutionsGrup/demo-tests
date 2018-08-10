package ro.esolutions.demotests.ui.specs

import ro.esolutions.demotests.ui.pages.ListClientPage
import spock.lang.Specification

class ClientSpecUI extends Specification {

    def "Open client list page"() {
        when:
        def listPage = ListClientPage.open()

        then:
        listPage.assertIsOnPage()
    }



}