import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.time.Duration;
import static org.junit.Assert.assertTrue;

public class SeleniumTest extends beforeafter{
    @Test
    public void firstTest() throws InterruptedException {
       // Webdriver
        //System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        //WebDriver driver = new ChromeDriver();

        // Navigate webapp
        driver.get("https://www.saucedemo.com/");

        // Ensure state
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement elementUN = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("user-name")));
        WebElement elementPS = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));

        // Perform Action
        elementUN.sendKeys("standard_user");
        elementPS.sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        //assertTrue(elementUN.isDisplayed());

        Thread.sleep(3000);
        //driver.quit();
    }
}

