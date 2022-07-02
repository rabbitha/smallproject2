import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class beforeafter {
    WebDriver driver;
    @Before
    public void setUp() throws Exception{
        System.setProperty("webdriver.chrome.driver", "Resources/chromedriver.exe");
        driver = new ChromeDriver();
    }
    @After
    public void tearDown() throws Exception{
        driver.quit();
    }
}
