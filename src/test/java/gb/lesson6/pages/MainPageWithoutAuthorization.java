package gb.lesson6.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class MainPageWithoutAuthorization extends BasePage{
    public MainPageWithoutAuthorization(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "username")
    protected WebElement usernameAuthorizationField;

    @FindBy(id = "password")
    protected WebElement passwordAuthorizationField;

    @FindBy(xpath = "//button[@data-testid=\"login-button\"]")
    protected WebElement loginButton;

    @FindBy(xpath = "//button[@data-testid=\"create-account-button\"]")
    protected WebElement createAccButton;

    public MainPageAuthorized authorize(String email, String password){
        usernameAuthorizationField.sendKeys(email);
        passwordAuthorizationField.sendKeys(password);
        loginButton.click();
        return new MainPageAuthorized(driver);
    }

    public MainPageWithoutAuthorization enterEmail(String email){
        usernameAuthorizationField.sendKeys(email);
        return this;
    }

    public MainPageWithoutAuthorization enterPassword(String password){
        passwordAuthorizationField.sendKeys(password);
        return this;
    }

    public MainPageAuthorized login(){
        loginButton.click();
        return new MainPageAuthorized(driver);
    }

    public RegistrationPage toRegister(){
        createAccButton.click();
        return new RegistrationPage(driver);
    }

}
