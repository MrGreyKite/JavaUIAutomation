package gb.lesson6.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTests {
    protected final static String URL = "https://dybr.ru/";
    protected final static String email = "tipikov.tip@yandex.ru";
    protected final static String password = "Fortests222";
    protected final static String diaryName = "Типа";
    protected final static String userName = "tipikov";

    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    protected Actions actions;
    protected LocalStorage localStorage;


    @BeforeAll
    static void createDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
