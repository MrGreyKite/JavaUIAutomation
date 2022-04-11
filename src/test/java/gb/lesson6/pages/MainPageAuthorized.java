package gb.lesson6.pages;

import io.qameta.allure.Step;
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

    @FindBy(xpath = "//*[@data-testid='user-menu-button']")
    protected WebElement menuButton;

    @Step("Проверить приветственное сообщение после авторизации")
    public void checkSalutationSectionHeader(){
        webDriverWait.until(ExpectedConditions.visibilityOf(salutationSection));
        Assertions.assertEquals("привет!", this.getSalutationSectionHeader().getText());

    }

    @Step("Нажать кнопку нового поста")
    public NewPostPage newPostClick() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(newPostButton));
        newPostButton.click();
        return new NewPostPage(driver);
    }

    @Step("Раскрыть блок меню")
    public MenuBlock openMenu() {
        webDriverWait.until(ExpectedConditions.visibilityOf(menuButton));
        menuButton.click();
        return new MenuBlock(driver);
    }

}
