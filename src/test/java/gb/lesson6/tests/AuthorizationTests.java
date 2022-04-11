package gb.lesson6.tests;

import gb.lesson6.pages.MainPageAuthorized;
import gb.lesson6.pages.MainPageWithoutAuthorization;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

@Story("Тесты без предварительной авторизации")
public class AuthorizationTests extends BaseTests {

    @BeforeEach
    void open(){
        driver = new ChromeDriver();
        driver.get(URL);
    }

    @Feature("Авторизация")
    @Test
    @DisplayName("Авторизация с корректным логином и паролем")
    void authorizeWithCorrectLoginAndPassword() {
                new MainPageWithoutAuthorization(driver).
                        enterEmail(email).
                        enterPassword(password).
                        login().
                        checkSalutationSectionHeader();
    }

    @Feature("Авторизация")
    @Test
    @DisplayName("Авторизация без пароля")
    void authorizeWithoutPassword(){
        new MainPageWithoutAuthorization(driver).enterEmail(email).login();
        Assertions.assertEquals("не заполнено",
                driver.findElement(By.xpath("//*[@id=\"password\"]/following-sibling::span/span")).getText());

    }
}
