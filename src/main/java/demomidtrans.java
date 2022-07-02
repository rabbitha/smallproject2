import org.junit.Test;
import org.openqa.selenium.By;

public class demomidtrans extends beforeafter {
    @Test
    public void firstTest() throws InterruptedException {
        driver.get("https://demo.midtrans.com");
        driver.findElement(By.xpath("//*[@id=\"container\"]/div/div/div[1]/div[2]/div/div/a")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"container\"]/div/div/div[2]/div[2]/div[1]")).click();
        Thread.sleep(3000);
        driver.switchTo().frame(0); //pindah ke iframe
        driver.findElement(By.xpath("//*[@id=\"application\"]/div/a[1]/div[1]/div[2]")).click();
        Thread.sleep(3000);
        //driver.findElement(By.xpath(inputCardNumber)).sendKeys("11111");

    }
}
