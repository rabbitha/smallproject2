import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class sauceDemo extends BeforeAfter {
    @Test
    public void sauceKetchup() throws InterruptedException{
        driver.get("https://saucedemo.com"); //1-2
        driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
        Thread.sleep(1000);
        System.out.println("--------------------START--------------------------");
        String Tittle = driver.getTitle(); //3 getTittle Web
        System.out.println(Tittle); //prin
        // t Tittle
        Thread.sleep(1000);
        Assert.assertEquals(Tittle, "Swag Labs"); //verify
        System.out.println("Verify Tittle is done!");
        System.out.println("Tittle Length is: "+Tittle.length()); //panjang Tittle
        System.out.println("----------------------------------------------------------");
        String URL = "https://www.saucedemo.com/inventory.html"; //expected URL
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL is: "+currentUrl);
        Assert.assertEquals(currentUrl,URL);
        System.out.println("Verify the Current URL is done!");
        Thread.sleep(1000);
        System.out.println("----------------------------------------------------------");
        String Page = driver.getPageSource();
        System.out.println("The Page Source is: "+Page);
        System.out.println("and the length of the Page Source: "+Page.length());
        Thread.sleep(2000);
    }
}
