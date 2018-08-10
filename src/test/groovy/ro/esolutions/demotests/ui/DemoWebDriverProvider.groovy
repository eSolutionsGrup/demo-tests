package ro.esolutions.demotests.ui

import com.codeborne.selenide.WebDriverProvider
import org.openqa.selenium.WebDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver
import org.openqa.selenium.remote.DesiredCapabilities

class DemoWebDriverProvider implements WebDriverProvider {

    @Override
    WebDriver createDriver(DesiredCapabilities capabilities) {
        capabilities.setCapability("loadImages", false)
        capabilities.setCapability("localToRemoteUrlAccessEnabled", true)
        capabilities.setCapability("phantomjs.cli.args", ["--load-images=no",
                                                          "--local-to-remote-url-access=true",
                                                          "--web-security=false"])

        return new PhantomJSDriver(capabilities)
    }
}
