package gb.lesson6.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MenuBlock extends BasePage{
    public MenuBlock(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@data-testid='sidebar-my-active-profile-link']")
    WebElement profileLink;

    public MenuBlock hoverOnProfileLink(){
        actions.moveToElement(profileLink).perform();
        return this;
    }

    public void checkColor() {
        Assertions.assertEquals("rgba(222, 65, 58, 1)", profileLink.getCssValue("color"));
    }

    public ProfilePage clickOnProfileLink(){
        profileLink.click();
        return new ProfilePage(driver);
    }
}
