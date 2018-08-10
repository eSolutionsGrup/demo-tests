package ro.esolutions.demotests.ui.specs

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide
import org.openqa.selenium.By
import spock.lang.Specification

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan
import static com.codeborne.selenide.Condition.text
import static com.codeborne.selenide.Selenide.*

class GoogleSearchSpecUI extends Specification {

    def "Search in Google by a text should get the appropriate results"() {
        given: "google search page is open"
        def url = "https://google.ro"
        Selenide.open(url)
        def serachField = $(By.name("q")).shouldBe(Condition.visible)

        when: "serach for esolutions"
        serachField.val("esolutions").pressEnter()

        then:"eSolutions web page is found in results"
        $("#ires .g").shouldBe(Condition.visible).shouldHave(
                text("eSolutions: Dezvoltare Java, training, cursuri, programare mobile"),
                text("esolutions.ro"))
    }

    def "Test Google 2"() {
        when:
        open("https://google.com/ncr")

        then:
        $(By.name("q")).val("selenide").pressEnter()
        $$("#ires .g").shouldHave(sizeGreaterThan(1))
    }

}