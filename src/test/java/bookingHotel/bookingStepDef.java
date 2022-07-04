package bookingHotel;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Objects;

public class bookingStepDef {

    public WebDriver driver;

    @Before
    public void startBrowser(){
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Given("User open website")
    public void userOpenWebsite() {
        driver.get("http://qa.cilsy.id:8080/en/");
    }


    @When("Maximize browser")
    public void maximizeBrowser() {
        driver.manage().window().maximize();
    }

    @And("User klik Element Sign In")
    public void userKlikElementSignIn() {
        driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[7]/ul/li/a")).click();
     }

    @And("User input email")
    public void userInputEmail(){
        driver.findElement(By.id("email")).sendKeys("lala@testing.com");
    }

    @And("User input password")
    public void userInputPassword() {
        driver.findElement(By.id("passwd")).sendKeys("123456");
    }

    @And("User click button Sign In")
    public void userClickButtonSignIn() {
        driver.findElement(By.id("SubmitLogin")).click();
    }

    @Then("User masuk kehalaman akun")
    public void userMasukKehalamanAkun() {
        if (driver.getPageSource().contains("My account")){
            System.out.println("---- User Sukses Login -----");
        } else {
            System.out.println("---- User Gagal Login -----");
        }

        String expected_success_create = "Your account has been created.";
        String actual_success_create = driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[1]")).getText();
        if (Objects.equals(actual_success_create, expected_success_create)){
            System.out.println(("---- User Success Login ----"));
        } else {
            System.out.println("---- User belum berada di halaman akun -----");
        }
      }

    @When("User kembali kehalaman awal")
    public void userKembaliKehalamanAwal() {
        driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/a")).click();
    }

    @And("User mengisi Location Hotel")
    public void userMengisiLocationHotel() {
        driver.findElement(By.id("id_hotel_button")).click();
    }

    @And("User memilih Select Hotel")
    public void userMemilihSelectHotel() {
        driver.findElement(By.xpath("//*[@id=\"search_hotel_block_form\"]/div[2]/div/ul")).click();
    }

    @And("User memilih Date Check In")
    public void userMemilihDateCheckIn() {
        driver.findElement(By.id("check_in_time")).click();
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[1]/td[6]/a")).click();
    }

    @And("User memilih Date Check Out")
    public void userMemilihDateCheckOut(){
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[1]/td[7]/a")).click();
    }

    @And("User meng-klik button Search Now")
    public void userMengKlikButtonSearchNow() {
        driver.findElement(By.id("search_room_submit")).click();
    }

    @Then("Muncul list pilihan kamar")
    public void munculListPilihanKamar() {
        if (driver.getPageSource().contains("General Rooms")){
            System.out.println("---- Pilihan : General Rooms -----");
        } else {
            System.out.println("---- Belum masuk halaman pilih kamar -----");
        }

        if (driver.getPageSource().contains("Delux Rooms")){
            System.out.println("---- Pilihan : Delux Rooms -----");
        } else {
            System.out.println("---- Belum masuk halaman pilih kamar -----");
        }
        if (driver.getPageSource().contains("Executive Rooms")){
            System.out.println("---- Pilahan : Executive Rooms -----");
        } else {
            System.out.println("---- Belum masuk halaman pilih kamar -----");
        }

        if (driver.getPageSource().contains("luxury Rooms")){
            System.out.println("---- Pilihan : luxury Rooms -----");
        } else {
            System.out.println("---- Belum masuk halaman pilih kamar -----");
        }
    }

    @When("User memilih kamar Luxury Room")
    public void userMemilihKamarLuxuryRoom() throws InterruptedException{
        driver.findElement(By.xpath("//*[@id=\"category_data_cont\"]/div[4]/div/div[1]/a/img")).click();
        Thread.sleep(3000);
    }

    @And("Menampilkan details kamar")
    public void menampilkanDetailsKamar() {
        if (driver.getPageSource().contains("Hotel Features")){
            System.out.println("---- Cek Fasilitas kamar -----");
        } else {
            System.out.println("---- Anda belum memilih kamar -----");
        }
    }

    @And("User melakukan pemesanan kamar")
    public void userMelakukanPemesananKamar() throws InterruptedException{
        driver.findElement(By.id("add_to_cart")).click();
        Thread.sleep(3000);
    }


    @Then("Muncul halaman popup pesanan masuk cart")
    public void munculHalamanPopupPesananMasukCart() {
        if (driver.getPageSource().contains("Room successfully added to your cart")){
            System.out.println("---- Success memasukkan kamar pada cart -----");
        } else {
            System.out.println("---- Tidak ada kamar di cart -----");
        }
    }

    @When("User klik button Proceed to checkout")
    public void userKlikButtonProceedToCheckout() {
        driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")).click();
    }

    @Then("Menampilkan halaman pengisian alamat")
    public void menampilkanHalamanPengisianAlamat() {
        if (driver.getPageSource().contains("Your addresses")){
            System.out.println("---- User harus mengisi alamat -----");
        } else {
            System.out.println("---- Gagal memuat halaman -----");
        }
    }

    @When("User mengisi First Name")
    public void userMengisiFirstName() {
        driver.findElement(By.id("firstname")).clear();
        driver.findElement(By.id("firstname")).sendKeys("Lala");
    }

    @And("User mengisi Last Name")
    public void userMengisiLastName() {
        driver.findElement(By.id("lastname")).clear();
        driver.findElement(By.id("lastname")).sendKeys("Teletabis");
    }

    @And("User mengisi Address")
    public void userMengisiAddress() {
        driver.findElement(By.id("address1")).sendKeys("Bandung");
    }

    @And("User mengisi Postal Code")
    public void userMengisiPostalCode() {
        driver.findElement(By.id("postcode")).sendKeys("55861");

    }

    @And("User mengisi City")
    public void userMengisiCity() {
        driver.findElement(By.id("city")).sendKeys("Bandung");
    }

    @And("User mengisi State")
    public void userMengisiState() throws InterruptedException{
        driver.findElement(By.id("uniform-id_state")).click();          //pilih state/kota
        WebElement element = driver.findElement(By.id("id_state"));
        Select state = new Select(element);
        state.selectByValue("259");                                       //isinya west java
        Thread.sleep(3000);
    }

    @And("User mengisi Country")
    public void userMengisiCountry() {
        driver.findElement(By.id("id_country")).click();
    }

    @And("User mengisi Mobile Phone")
    public void userMengisiMobilePhone() {
        driver.findElement(By.id("phone_mobile")).sendKeys("085233449876");
    }

    @And("User mengisi Alias Address")
    public void userMengisiAliasAddress() {
        driver.findElement(By.id("alias")).sendKeys("Alamat rumah");
    }

    @And("User klik button Save")
    public void userKlikButtonSave() {
        driver.findElement(By.id("submitAddress")).click();
    }
}
