package gb.lesson6.tests;

import gb.lesson6.pages.MainPageAuthorized;
import gb.lesson6.pages.MainPageWithoutAuthorization;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AuthorizationTests extends BaseTests {

    @BeforeEach
    void open(){
        driver = new ChromeDriver();
        driver.get(URL);
    }

    @Test
    void authorizeWithCorrectLoginAndPassword() {
                new MainPageWithoutAuthorization(driver).
                        enterEmail(email).
                        enterPassword(password).
                        login().
                        checkSalutationSectionHeader();
    }

    @Test
    void authorizeWithoutPassword(){
        new MainPageWithoutAuthorization(driver).enterEmail(email).login();
        Assertions.assertEquals("не заполнено",
                driver.findElement(By.xpath("//*[@id=\"password\"]/following-sibling::span/span")).getText());

    }
}
