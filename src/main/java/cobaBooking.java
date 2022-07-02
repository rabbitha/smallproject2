import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class cobaBooking extends BeforeAfter {
    @Test
    public void login() throws InterruptedException {
        //User membuka website booking hotel
        driver.get("http://qa.cilsy.id:8080/en/");

        driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[7]/ul/li/a")).click();
        driver.findElement(By.id("email")).sendKeys("testing@test.com");
        driver.findElement(By.id("passwd")).sendKeys("123456");
        driver.findElement(By.id("SubmitLogin")).click();
        System.out.println("---- User Sukses Login -----");
        Thread.sleep(1500);

        driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/a")).click();   //button home kembali ke halaman awal
        Thread.sleep(1500);

        //user melakukan pencarian hotel
        driver.findElement(By.id("hotel_location")).sendKeys("Jakarta");
        Thread.sleep(1500);
        driver.findElement(By.id("id_hotel_button")).click();
        driver.findElement(By.xpath("//*[@id=\"search_hotel_block_form\"]/div[2]/div/ul")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("check_in_time")).click();
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[2]/td[7]")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("check_out_time")).click();
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[3]/td[1]")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("search_room_submit")).click();
        Thread.sleep(1500);

        //user memilih ruangan "Luxury Room"
        driver.findElement(By.xpath("//*[@id=\"category_data_cont\"]/div[4]/div/div[1]/a")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("add_to_cart")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")).click();

        //user mengisi data alamat
        driver.findElement(By.id("firstname")).click();
        driver.findElement(By.id("lastname")).click();
        driver.findElement(By.id("address1")).sendKeys("Bandung");
        driver.findElement(By.id("postcode")).sendKeys("55861");
        driver.findElement(By.id("city")).sendKeys("Bandung");

        driver.findElement(By.id("uniform-id_state")).click();          //pilih state/kota
        WebElement element = driver.findElement(By.id("id_state"));
        Select sel = new Select(element);
        sel.selectByValue("259");                                       //isinya west java

        driver.findElement(By.id("id_country")).click();
        driver.findElement(By.id("phone_mobile")).sendKeys("085233449876");
        driver.findElement(By.id("alias")).sendKeys("Alamat rumah");
        driver.findElement(By.id("submitAddress")).click();
        Thread.sleep(3000);
        System.out.println("---- User Sukses Mengisi Alamat -----");
    }
}

