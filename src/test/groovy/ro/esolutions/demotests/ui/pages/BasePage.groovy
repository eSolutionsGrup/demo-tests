package ro.esolutions.demotests.ui.pages

import com.codeborne.selenide.WebDriverRunner
import org.openqa.selenium.JavascriptExecutor

class BasePage {

    def executeScript(final String script, final Object... args) {
        final JavascriptExecutor executor = (JavascriptExecutor) WebDriverRunner.getWebDriver()
        executor.executeScript(script, args)
    }
}
