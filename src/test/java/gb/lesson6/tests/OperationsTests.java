package gb.lesson6.tests;

import org.junit.jupiter.api.BeforeEach;
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
}
