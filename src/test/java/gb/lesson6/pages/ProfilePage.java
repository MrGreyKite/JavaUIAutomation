package gb.lesson6.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;

public class ProfilePage extends BasePage{
    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//img[@alt='avatar']")
    WebElement avatar;

    @FindBy(className = "nickname")
    WebElement profileName;

    @FindBy(className = "blog-title")
    WebElement blogName;

    public void checkNamesOfProfileAndBlog(String userName, String diaryName){
        webDriverWait.until(ExpectedConditions.visibilityOf(avatar));
        Assertions.assertAll(
                () -> Assertions.assertEquals(userName, profileName.getText()),
                () -> assertThat(blogName, hasText(diaryName))
        );

    }
}
