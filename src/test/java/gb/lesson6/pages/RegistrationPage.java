package gb.lesson6.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.Assertions.assertThat;

public class RegistrationPage extends BasePage {
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getButtonContinueRegistration() {
        return buttonContinueRegistration;
    }

    @FindBy(xpath = "//button[@data-testid='continue-registration-button']")
    WebElement buttonContinueRegistration;

    public void checkTextAboutRegistration() {
        webDriverWait.until(ExpectedConditions.visibilityOf(buttonContinueRegistration));
        assertThat(driver.findElement(By.xpath("//h2")).getText()).isEqualTo("Создание аккаунта");
    }
}
