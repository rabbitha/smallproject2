import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Objects;

public class RegisterAccount extends BeforeAfter{
    @Test
    public void RegisterUser()throws InterruptedException{
        driver.get("http://qa.cilsy.id:8080/en/");

        driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[7]/ul/li/a")).click();
        driver.findElement(By.id("email_create")).sendKeys("a1@testing.com");
        driver.findElement(By.id("SubmitCreate")).click();
        Thread.sleep(3000);

        WebElement gender_mr = driver.findElement(By.id("id_gender1"));
        WebElement gender_mrs = driver.findElement(By.id("id_gender2"));
        gender_mr.click();
        Thread.sleep(3000);
        System.out.println("gender laki-laki :" +gender_mr.isSelected());
        gender_mrs.click();
        System.out.println("gender perempuan :" +gender_mrs.isSelected());

        //user mengisi data diri
        driver.findElement(By.id("customer_firstname")).sendKeys("Lala");
        driver.findElement(By.id("customer_lastname")).sendKeys("Teletubies");
        driver.findElement(By.id("email")).clear();                                        //menghapus isi yang sudah ada masukkan
        driver.findElement(By.id("email")).sendKeys("traa@testing.com");
        driver.findElement(By.id("passwd")).sendKeys("upackanhalo");

        //memilih dropdown tanggal lahir
        driver.findElement(By.id("uniform-days")).click();
        WebElement days = driver.findElement(By.id("days"));
        Select tanggal = new Select(days);
        tanggal.selectByValue("3");                                       //isinya tanggal 3
        Thread.sleep(1000);
        System.out.println("---- CEK TANGGAL ----");

        //memilih dropdown bulan
        driver.findElement(By.id("uniform-months")).click();
        WebElement months = driver.findElement(By.id("months"));
        Select bulan = new Select(months);
        bulan.selectByValue("3");                                       //isinya bulan Maret uniform-years
        Thread.sleep(1000);
        System.out.println("---- CEK BULAN ----");

        //memilih dropdown tahun
        driver.findElement(By.id("uniform-years")).click();
        WebElement years = driver.findElement(By.id("years"));
        Select tahun = new Select(years);
        tahun.selectByValue("1997");                                       //isinya Tahun 1997
        Thread.sleep(1000);
        System.out.println("---- CEK TAHUN ----");

        driver.findElement(By.id("newsletter")).click();

        driver.findElement(By.id("submitAccount")).click();
        Thread.sleep(3000);
        System.out.println("---- CEK ----");

        //verify halaman DONE register
        boolean user_enable = driver.findElement(By.id("user_info_acc")).isEnabled();
        if (user_enable){
            System.out.println("----  User Success Membuat Akun -----");
        } else {
            System.out.println("---- User belum berada di halaman akun -----");
        }

        String expected_success_create = "Your account has been created.";
        String actual_success_create = driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[1]")).getText();
        if (Objects.equals(actual_success_create, expected_success_create)){
            System.out.println(("---- User Success Membuat Akun ----"));
        } else {
            System.out.println("---- User belum berada di halaman akun -----");;
        }
    }
}
