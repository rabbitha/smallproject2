import org.junit.Test;
import org.openqa.selenium.By;


import static org.junit.Assert.assertEquals;


public class LoginAccount extends BeforeAfter {
    @Test
    public void login() throws InterruptedException {
        //User membuka website booking hotel
        driver.get("http://qa.cilsy.id:8080/en/");

        driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[7]/ul/li/a")).click();
        driver.findElement(By.id("email")).sendKeys("testing@test.com");
        driver.findElement(By.id("passwd")).sendKeys("123456");
        driver.findElement(By.id("SubmitLogin")).click();
        System.out.println("------------------------------------");
        System.out.println("        User Sukses Login           ");
        System.out.println("------------------------------------");
        Thread.sleep(3000);
    }
}

