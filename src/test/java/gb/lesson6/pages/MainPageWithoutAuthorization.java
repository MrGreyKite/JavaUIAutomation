package gb.lesson6.pages;

import io.qameta.allure.Step;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;


public class MainPageWithoutAuthorization extends BasePage{
    public MainPageWithoutAuthorization(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//form[@data-testid=\"login-form\"]")
    protected WebElement loginForm;

    @FindBy(id = "username")
    protected WebElement usernameAuthorizationField;

    @FindBy(id = "password")
    protected WebElement passwordAuthorizationField;

    @FindBy(xpath = "//button[@data-testid=\"login-button\"]")
    protected WebElement loginButton;

    @FindBy(xpath = "//button[@data-testid=\"create-account-button\"]")
    protected WebElement createAccButton;

    @FindBy(xpath = "//h3[text()='Вход на Дыбр']")
    protected WebElement introHeader;

    public MainPageAuthorized authorize(String email, String password){
        usernameAuthorizationField.sendKeys(email);
        passwordAuthorizationField.sendKeys(password);
        loginButton.click();
        return new MainPageAuthorized(driver);
    }

    @Step("Ввести email")
    public MainPageWithoutAuthorization enterEmail(String email){
        usernameAuthorizationField.sendKeys(email);
        return this;
    }

    @Step("Ввести пароль")
    public MainPageWithoutAuthorization enterPassword(String password){
        passwordAuthorizationField.sendKeys(password);
        return this;
    }

    @Step("Нажать кнопку входа")
    public MainPageAuthorized login(){
        loginButton.click();
        return new MainPageAuthorized(driver);
    }

    @Step("Нажать кнопку регистрации")
    public RegistrationPage toRegister(){
        createAccButton.click();
        return new RegistrationPage(driver);
    }

    @Step("Проверка наличия интро текста над формой логина")
    public void checkIntroText(){
        webDriverWait.until(ExpectedConditions.visibilityOf(loginForm));
        Assertions.assertAll(
                () -> MatcherAssert.assertThat(introHeader, isDisplayed()),
                () -> MatcherAssert.assertThat(loginButton, isDisplayed())
        );
    }

}
