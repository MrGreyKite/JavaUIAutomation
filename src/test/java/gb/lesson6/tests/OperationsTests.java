package gb.lesson6.tests;

import gb.lesson6.pages.MainPageAuthorized;
import gb.lesson6.pages.MenuBlock;
import gb.lesson6.pages.NewPostPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

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
        NewPostPage np = new MainPageAuthorized(driver).
                newPostClick().
                setPostHeader("Text empty");
        np.buttonIsDisabled(np.getPublishButton());
    }

    @Test
    void hoverOnProfile(){
        MenuBlock mb = new MainPageAuthorized(driver).
                openMenu();
        mb.hoverOnLink(mb.getProfileLink()).
                checkColor();
    }

    @Test
    void openProfile() {
        new MainPageAuthorized(driver).
                openMenu().
                clickOnProfileLink().
                checkNamesOfProfileAndBlog(userName, diaryName);
    }
}
