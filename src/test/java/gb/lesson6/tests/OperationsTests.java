package gb.lesson6.tests;

import gb.lesson6.pages.MainPageAuthorized;
import gb.lesson6.pages.NewPostPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
    void cannotPublishPostWithoutText(){
        driver.navigate().refresh();
        NewPostPage np = new MainPageAuthorized(driver).
                newPostClick().
                setPostHeader("Text empty");
        np.buttonIsDisabled(np.getPublishButton());
    }
}
