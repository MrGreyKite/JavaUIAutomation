package gb.lesson6.pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MenuBlock extends BasePage{
    public MenuBlock(WebDriver driver) {
        super(driver);
    }

    public WebElement getProfileLink() {
        return profileLink;
    }

    @FindBy(xpath = "//a[@data-testid='sidebar-my-active-profile-link']")
    WebElement profileLink;

    @FindBy(xpath = "//*[@data-testid='sidebar-logout-link']")
    WebElement logoutLink;

    @Step("Навести курсор на ссылку")
    public MenuBlock hoverOnLink(WebElement element){
        actions.moveToElement(element).perform();
        return this;
    }

    @Step("Проверить цвет ссылки")
    public void checkColor() {
        Assertions.assertEquals("rgba(222, 65, 58, 1)", profileLink.getCssValue("color"));
    }

    @Step("Нажать на ссылку на профиль пользователя")
    public ProfilePage clickOnProfileLink(){
        profileLink.click();
        return new ProfilePage(driver);
    }

    @Step("Нажать на ссылку выхода из профиля")
    public MainPageWithoutAuthorization logout(){
        logoutLink.click();
        return new MainPageWithoutAuthorization(driver);
    }
}
