package gb.lesson6.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class BlogPage extends BasePage{
    public BlogPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@data-testid='blog-entry-title-link']")
    List<WebElement> posts;

    @FindBy(xpath = "//div[@data-testid=\"blog-feed-control\"]")
    WebElement controlPanel;

    public void checkExistenceOfPost(String title){
        webDriverWait.until(ExpectedConditions.
                visibilityOf(controlPanel));
        Assertions.assertTrue(posts.stream().anyMatch(p -> p.getText().equals(title)));
    }

    public PostViewPage openPost(String title) {
        WebElement post = posts.stream().filter(p -> p.getText().equals(title)).findFirst().orElse(null);
        if (post == null) throw new AssertionError();
        post.click();
        return new PostViewPage(driver);
    }

}
