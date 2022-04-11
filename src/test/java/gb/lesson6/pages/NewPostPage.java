package gb.lesson6.pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewPostPage extends BasePage{
    public NewPostPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@data-testid=\"new-entry-topic\"]")
    WebElement postTitleField;

    @FindBy(xpath = "//div[contains(@class, 'fr-view')]")
    WebElement postTextArea;

    @FindBy(className = "preview-entry-btn")
    WebElement previewButton;

    public WebElement getPreviewButton() {
        return previewButton;
    }

    public WebElement getPublishButton() {
        return publishButton;
    }

    @FindBy(xpath = "//button[contains(text(), 'опубликовать')]")
    protected WebElement publishButton;

    @FindBy(xpath = "//*[@class='privacy-settings']//div[contains(@class, 'blog-select__control')]")
    WebElement privacySelector;

    @Step("Ввести заголовок поста")
    public NewPostPage setPostHeader(String text) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(postTitleField));
        postTitleField.sendKeys(text);
        return this;
    }

    @Step("Ввести текст поста")
    public NewPostPage setPostText(String text) {
        postTextArea.sendKeys(text);
        return this;
    }

    @Step("Выбрать уровень приватности")
    public NewPostPage setPrivacyLevel(String level) {
        privacySelector.click();
        driver.findElement(By.id("react-select-2-option-" + level)).click();
        return this;
    }

    @Step("Опубликовать пост")
    public BlogPage publish() {
        publishButton.click();
        return new BlogPage(driver);
    }

    @Step("Проверить, что кнопка задисейблена")
    public void buttonIsDisabled(WebElement element){
        Assertions.assertFalse(element.isEnabled());
    }


}
