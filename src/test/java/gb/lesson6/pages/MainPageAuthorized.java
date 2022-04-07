package gb.lesson6.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPageAuthorized extends BasePage{
    public MainPageAuthorized(WebDriver driver) {
        super(driver);
    }

    public WebElement getSalutationSectionHeader() {
        return salutationSectionHeader;
    }

    @FindBy(xpath = "//*[@data-testid='salutation-section']")
    protected WebElement salutationSection;

    @FindBy(xpath = "//*[@data-testid='salutation-section']//h3")
    protected WebElement salutationSectionHeader;

    @FindBy(xpath = "//a[@title='новая запись']")
    protected WebElement newPostButton;

    public void checkSalutationSectionHeader(){
        webDriverWait.until(ExpectedConditions.visibilityOf(salutationSection));
        Assertions.assertEquals("привет!", this.getSalutationSectionHeader().getText());

    }

}
