package gb.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;


public class DybrTests {

    public static void authorization(String url, String email, String password) throws InterruptedException {

        /*
        Тест-кейс 1. Авторизация.
        Шаги:
        1.Перейти на сайт https://dybr.ru/
        2.Ввести существующий логин (почту)
        3.Ввести пароль от логина с предыдущего шага
        4.Нажать кнопку "Войти"
        Ожидаемый результат: На странице содержится приветственный текст "Привет!".
         */
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get(url);

        driver.findElement(By.id("username")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.xpath("//button[@data-testid=\"login-button\"]")).click();

        Thread.sleep(1000);

        WebElement banner = driver.findElement(By.xpath("//*[text() = 'Привет!']"));
//        System.out.println("Detected");
        Actions actions = new Actions(driver);
        actions.doubleClick(banner).perform();
//        System.out.println("Double click operation performed");

        Thread.sleep(5000);
        driver.quit();

    }

    public static void posting(String url) throws InterruptedException {

        /*
        Тест-кейс 2. Новая запись в дневнике.
        Предусловие: Авторизоваться на https://dybr.ru/ под выданными логином и паролем
        1.Нажать на кнопку создание нового поста
        2.Ввести тему записи
        3.Ввести текст записи
        4.Выбрать область видимости записи
        5.Опубликовать запись
        Ожидаемый результат: Запись опубликована с темой и текстом, введенными на предыдущих шагах.
         */

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get(url);

        LocalStorage localStorage =  ((ChromeDriver)driver).getLocalStorage();
        localStorage.setItem("token","\"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiI5NjI2OCIsImV4cCI6MTY1MTA3MDMwNCwic3ViIjoiMTU4ODAifQ.OIMaqXrk-raMYdJh1_uJJ98OZXysSca4y0Y3DZ3jpHw\"");
        driver.get(url); //обновить страницу, чтобы обновление хранилища вступило в силу

        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='новая запись']")));

        WebElement newPostButton = driver.findElement(By.xpath("//a[@title='новая запись']"));
        newPostButton.click();
        Thread.sleep(1000);

        String postHeader = "Test" + new Random().nextInt(100);

        driver.findElement(By.xpath("//*[@data-testid=\"new-entry-topic\"]")).sendKeys(postHeader);
        driver.findElement(By.xpath("//div[contains(@class, 'fr-view')]")).sendKeys("Test text");

        List<WebElement> dropdowns = driver.findElements(By.xpath("//div[contains(@class, 'blog-select__control')]"));
        dropdowns.get(1).click();
        driver.findElement(By.id("react-select-2-option-1")).click();

        WebElement publish = driver.findElement(By.xpath("//button[contains(text(), 'опубликовать')]"));
        publish.click();

        webDriverWait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//a[@data-testid='blog-entry-title-link']")));

        List<WebElement> posts = driver.findElements(By.xpath("//a[@data-testid='blog-entry-title-link']"));
        posts.stream().filter(p -> p.getText().contains(postHeader)).findFirst().get().click();

        Thread.sleep(5000);
        driver.quit();
    }

    public static void main(String[] args) throws InterruptedException {
        String url = "https://dybr.ru/";
        String email = "tipikov.tip@yandex.ru";
        String password = "Fortests222";

        authorization(url, email, password);

        posting(url);
    }
}
