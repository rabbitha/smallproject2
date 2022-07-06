import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

import static org.junit.Assert.assertEquals;


public class LoginAccount extends BeforeAfter {
    @Test
    public void login() throws InterruptedException {
        //User membuka website booking hotel
        driver.get("http://qa.cilsy.id:8080/en/");


        driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[7]/ul/li/a")).click();
        driver.findElement(By.id("email")).sendKeys("lala@testing.com");
        driver.findElement(By.id("passwd")).sendKeys("123456");
        driver.findElement(By.id("SubmitLogin")).click();
        System.out.println("------------------------------------");
        System.out.println("        User Sukses Login           ");
        System.out.println("------------------------------------");
        Thread.sleep(3000);


    }
}

