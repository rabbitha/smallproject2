import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class cobaBooking extends BeforeAfter {
    @Test
    public void isiAlamat() throws InterruptedException {

        driver.get("http://qa.cilsy.id:8080/en/");         //User membuka website booking hotel

        //user masuk ke akun website booking hotel
        driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[7]/ul/li/a")).click();
        driver.findElement(By.id("email")).sendKeys("testing@test.com");
        driver.findElement(By.id("passwd")).sendKeys("123456");
        driver.findElement(By.id("SubmitLogin")).click();
        System.out.println("---- User Sukses Login -----");

        driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/a")).click();   //button home kembali ke halaman awal

        //user melakukan pencarian hotel
        driver.findElement(By.id("hotel_location")).sendKeys("Jakarta");
        driver.findElement(By.id("id_hotel_button")).click();
        driver.findElement(By.xpath("//*[@id=\"search_hotel_block_form\"]/div[2]/div/ul")).click();
        driver.findElement(By.id("check_in_time")).click();
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[2]/td[7]")).click();
        driver.findElement(By.id("check_out_time")).click();
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[3]/td[1]")).click();
        driver.findElement(By.id("search_room_submit")).click();
        System.out.println("---- User success melakukan pencarian hotel -----");

        //user memilih ruangan "Luxury Room"
        driver.findElement(By.xpath("//*[@id=\"category_data_cont\"]/div[4]/div/div[1]/a")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("add_to_cart")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")).click();
        System.out.println("---- User success melakukan pemilihan kamar -----");

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
        Thread.sleep(3000);

        driver.findElement(By.id("id_country")).click();
        driver.findElement(By.id("phone_mobile")).sendKeys("085233449876");
        driver.findElement(By.id("alias")).sendKeys("Alamat rumah");
        driver.findElement(By.id("submitAddress")).click();
        System.out.println("---- User Sukses Mengisi Alamat -----");

        //User mengkonfirmasi pemesanan
        driver.findElement(By.xpath("//*[@id=\"collapse-shopping-cart\"]/div/div[2]/div[2]/div/a")).click();
        driver.findElement(By.xpath("//*[@id=\"collapse-guest-info\"]/div/div[4]/div/a")).click();
        driver.findElement(By.id("cgv")).click();
        Thread.sleep(3000);
        System.out.println("---- CEK 4 -----");

        //User memilih pembayaran
        driver.findElement(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[1]/div/p/a")).click();
        driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button")).click();
        System.out.println("---- CEK 5 -----");

        //User melihat invoice pembayaran & order history
        driver.findElement(By.xpath("//*[@id=\"center_column\"]/p/a")).click();
        driver.findElement(By.className("footable-toggle")).click();
        System.out.println("---- CEK 6 -----");




    }
}

