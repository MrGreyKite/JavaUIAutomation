package gb.lesson5.dybr;

import gb.lesson5.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class OperationsTests extends BaseTest {

    @BeforeEach
    void setupDriver() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        driver.get(URL);
        localStorage = ((ChromeDriver)driver).getLocalStorage();
        localStorage.setItem("token",
                "\"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiI5NjI2OCIsImV4cCI6MTY1MTA3MDMwNCwic3ViIjoiMTU4ODAifQ.OIMaqXrk-raMYdJh1_uJJ98OZXysSca4y0Y3DZ3jpHw\"");

    }

    @Test
    void postingPost() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='новая запись']")));
        WebElement newPostButton = driver.findElement(By.xpath("//a[@title='новая запись']"));
        newPostButton.click();

        String postHeader = "Test" + new Random().nextInt(100);

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-testid=\"new-entry-topic\"]")));
        WebElement head = driver.findElement(By.xpath("//*[@data-testid=\"new-entry-topic\"]"));
        head.sendKeys(postHeader);
        driver.findElement(By.xpath("//div[contains(@class, 'fr-view')]")).sendKeys("Test text");

        List<WebElement> dropdowns = driver.findElements(By.xpath("//div[contains(@class, 'blog-select__control')]"));
        dropdowns.get(1).click();
        driver.findElement(By.id("react-select-2-option-1")).click();

        WebElement publish = driver.findElement(By.xpath("//button[contains(text(), 'опубликовать')]"));
        publish.click();

        webDriverWait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//div[@data-testid=\"blog-feed-control\"]")));
        List<WebElement> posts = driver.findElements(By.xpath("//a[@data-testid='blog-entry-title-link']"));

        Assertions.assertTrue(posts.stream().anyMatch(p -> p.getText().equals(postHeader)));
    }

    @Test
    void goingToProfile() {
        driver.navigate().refresh();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-testid='user-menu-button']/img")));
        WebElement menuButton = driver.findElement(By.xpath("//*[@data-testid='user-menu-button']"));
        menuButton.click();

        WebElement profileLink = driver.findElement(By.xpath("//a[@data-testid='sidebar-my-active-profile-link']"));

        actions.moveToElement(profileLink).perform();
        Assertions.assertEquals("rgba(222, 65, 58, 1)", profileLink.getCssValue("color"));

        profileLink.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='avatar']")));

        WebElement profileName = driver.findElement(By.xpath("//*[@class='nickname']"));
        WebElement blogName = driver.findElement(By.className("blog-title"));

        Assertions.assertEquals(userName, profileName.getText());
        Assertions.assertEquals(diaryName, blogName.getText());

    }

    @AfterEach
    void clearStorage() {
        localStorage.clear();
    }

}
