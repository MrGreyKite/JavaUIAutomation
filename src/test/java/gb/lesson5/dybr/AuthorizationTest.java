package gb.lesson5.dybr;

import gb.lesson5.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthorizationTest extends BaseTest {
    @BeforeEach
    void setupDriver() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get(URL);
    }

    @Test
    void authorizeWithLoginAndPassword() {
        driver.findElement(By.id("username")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.xpath("//button[@data-testid=\"login-button\"]")).click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//*[@data-testid='salutation-section']"))));
        WebElement salutHeader = driver.findElement(By.xpath("//*[@data-testid='salutation-section']//h3"));
        Assertions.assertEquals("привет!", salutHeader.getText());

    }


}
