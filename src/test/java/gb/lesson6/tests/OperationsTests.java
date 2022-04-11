package gb.lesson6.tests;

import gb.lesson6.pages.MainPageAuthorized;
import gb.lesson6.pages.MenuBlock;
import gb.lesson6.pages.NewPostPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

@Epic("Различные операции при авторизованном состоянии")
public class OperationsTests extends BaseTests{
    @BeforeEach
    void open(){
        driver = new ChromeDriver();
        driver.get(URL);
        localStorage = ((ChromeDriver)driver).getLocalStorage();
        localStorage.setItem("token",
                "\"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiI5NjI2OCIsImV4cCI6MTY1MTA3MDMwNCwic3ViIjoiMTU4ODAifQ.OIMaqXrk-raMYdJh1_uJJ98OZXysSca4y0Y3DZ3jpHw\"");
        driver.navigate().refresh();
    }

    @Test
    @Feature("Публикация поста")
    @DisplayName("Опубликовать пост - позитивный кейс - без тэгов")
    void publishAPost(){
        new MainPageAuthorized(driver).
                newPostClick().
                setPostHeader(postHeader).
                setPostText("Some Text").
                setPrivacyLevel("1").
                publish().
                checkExistenceOfPost(postHeader);
    }

    @Test
    @Feature("Публикация поста")
    @DisplayName("Проверка невозможности опубликовать пост без текста")
    void cannotPublishPostWithoutText(){
        NewPostPage np = new MainPageAuthorized(driver).
                newPostClick().
                setPostHeader("Text empty");
        np.buttonIsDisabled(np.getPublishButton());
    }

    @Test
    @Feature("Профиль пользователя")
    @DisplayName("Проверка цвета при наведении курсора на профиль")
    void hoverOnProfile(){
        MenuBlock mb = new MainPageAuthorized(driver).
                openMenu();
        mb.hoverOnLink(mb.getProfileLink()).
                checkColor();
    }

    @Test
    @Feature("Профиль пользователя")
    @DisplayName("Проверка корректности открытия профиля")
    void openProfile() {
        new MainPageAuthorized(driver).
                openMenu().
                clickOnProfileLink().
                checkNamesOfProfileAndBlog(userName, diaryName);
    }

    @Test
    @Feature("Действия в меню")
    @DisplayName("Проверка корректности разлогина")
    void logoutFromMenu(){
        new MainPageAuthorized(driver).
                openMenu().
                logout().
                checkIntroText();
    }
}
