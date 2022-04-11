package gb.lesson6.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getButtonContinueRegistration() {
        return buttonContinueRegistration;
    }

    @FindBy(xpath = "//button[@data-testid=\"continue-registration-button\"]")
    WebElement buttonContinueRegistration;
}
