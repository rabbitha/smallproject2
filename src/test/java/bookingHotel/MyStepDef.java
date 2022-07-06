package bookingHotel;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

public class MyStepDef {
    public WebDriver driver;

    @Before
    public void startBrowser(){
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

// Scenario ke 1
    @Given("User access website")
    public void userAccessWebsite() {
        driver.get("http://qa.cilsy.id:8080/en/");
        driver.manage().window().maximize();
            if (driver.getPageSource().contains("Sign in")){
                System.out.println("---- Element Sign In muncul ----");
            } else {
                System.out.println("---- Element Sign In tidak muncul ----");
            }
    }

    @When("User log in using the account that has been created")
    public void userLogInUsingTheAccountThatHasBeenCreated() {
        driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[7]/ul/li/a")).click();
            if (driver.getPageSource().contains("Already registered?")){
                System.out.println("---- User sudah berada di halaman login ----");
            } else {
                System.out.println("---- Gagal memuat halaman login ----");
            }

        driver.findElement(By.id("email")).sendKeys("lala@testing.com");
        driver.findElement(By.id("passwd")).sendKeys("123456");
        driver.findElement(By.id("SubmitLogin")).click();

            //verify succes login
            String expected_login = "http://qa.cilsy.id:8080/en/my-account";
            String actual_login = driver.getCurrentUrl();
            if (Objects.equals(actual_login, expected_login)){
                System.out.println("---- URL sudah susuai dengan expected result ----");
            } else {
                System.out.println("---- User belum berada pada halaman akun ----");
            }

            boolean user_enable = driver.findElement(By.id("user_info_acc")).isEnabled();
            if (user_enable){
                System.out.println("----  User berada pada halaman akun -----");
            } else {
                System.out.println("---- User belum berada pada halaman akun -----");
            }
    }

    @And("User choose hotel")
    public void userChooseHotel() {
        driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/a")).click();

            String expected_page_booking = "http://qa.cilsy.id:8080/en/";
            String actual_page_booking = driver.getCurrentUrl();
            assertEquals(actual_page_booking, expected_page_booking);
            System.out.println("---- URL sudah susuai dengan expected result ----");


        driver.findElement(By.id("hotel_location")).sendKeys("Jakarta");
        driver.findElement(By.id("id_hotel_button")).click();
        driver.findElement(By.xpath("//*[@id=\"search_hotel_block_form\"]/div[2]/div/ul")).click();

        driver.findElement(By.id("check_in_time")).click();
        int in = 1;                 //buat ganti bulans
        while (in<20) {
            driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[2]")).click();
            in++;
        }
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[1]/td[6]/a")).click();

        driver.findElement(By.id("check_out_time")).click();
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[1]/td[7]/a")).click();

        driver.findElement(By.id("search_room_submit")).click();

        WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"category_data_cont\"]/div[4]/div/div[1]/a/img")));

            boolean displayed_generalRooms = driver.findElement(By.xpath("//*[@id=\"category_data_cont\"]/div[1]/div/div[2]/p[1]")).isDisplayed();
            boolean displayed_deluxRooms = driver.findElement(By.xpath("//*[@id=\"category_data_cont\"]/div[1]/div/div[2]/p[1]")).isDisplayed();
            boolean displayed_executiveRooms = driver.findElement(By.xpath("//*[@id=\"category_data_cont\"]/div[1]/div/div[2]/p[1]")).isDisplayed();
            boolean displayed_luxuryRooms = driver.findElement(By.xpath("//*[@id=\"category_data_cont\"]/div[1]/div/div[2]/p[1]")).isDisplayed();

            System.out.println("CEK General Rooms : " +displayed_generalRooms);
            System.out.println("CEK Delux Rooms : " +displayed_deluxRooms);
            System.out.println("CEK Executive Rooms : " +displayed_executiveRooms);
            System.out.println("CEK Luxury Rooms : " +displayed_luxuryRooms);
    }

    @And("User choose hotel room")
    public void userChooseHotelRoom() {
        driver.findElement(By.xpath("//*[@id=\"category_data_cont\"]/div[4]/div/div[1]/a/img")).click();

        WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"center_column\"]/div/div/div[1]/div[1]/div[1]/div[1]/span")));

            String expected_room = "luxury Rooms - The Hotel Prime";
            String actual_room = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/div/div[1]/div[1]/div[1]/div[1]/span")).getText();
            if (Objects.equals(actual_room, expected_room)){
                System.out.println("---- Pilihan kamar sudah sesuai ----");
            } else {
                System.out.println("---- Salah memilih kamar ----");
            }

        driver.findElement(By.id("add_to_cart")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")));

            //verify popup halaman success add to cart
            String expected_room_cart = "Room successfully added to your cart";
            String actual_room_cart = driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[1]/h2")).getText();
            if (Objects.equals(actual_room_cart,expected_room_cart)){
                System.out.println("---- Success memasukkan kamar pada cart -----");
            } else {
                System.out.println("---- Tidak ada kamar di cart -----");
            }

        driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")).click();
        System.out.println("---- User success melakukan pemilihan kamar -----");
    }

    @And("User fill in address")
    public void userFillInAddress() {
            //verify halaman alamat
            String expected_page_fill_address = "http://qa.cilsy.id:8080/en/address?back=order.php%3Fstep%3D1";
            String actual_page_fill_address = driver.getCurrentUrl();
            if (Objects.equals(actual_page_fill_address, expected_page_fill_address)){
                System.out.println("---- URL sudah susuai dengan expected result ----");
            } else {
                System.out.println("---- Halaman Belum Termuat ----");
            }

        driver.findElement(By.id("firstname")).clear();                         //menghaopus isi yang sudah terinput
        driver.findElement(By.id("firstname")).sendKeys("Lala");
        driver.findElement(By.id("lastname")).clear();
        driver.findElement(By.id("lastname")).sendKeys("Teletabis");
        driver.findElement(By.id("address1")).sendKeys("Bandung");
        driver.findElement(By.id("postcode")).sendKeys("55861");
        driver.findElement(By.id("city")).sendKeys("Bandung");

        driver.findElement(By.id("uniform-id_state")).click();          //pilih state/kota
        WebElement element = driver.findElement(By.id("id_state"));
        Select state = new Select(element);
        state.selectByValue("259");                                       //isinya west java

        WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"uniform-id_state\"]/span")));

        driver.findElement(By.id("id_country")).click();
        driver.findElement(By.id("phone_mobile")).sendKeys("085233449876");
        driver.findElement(By.id("alias")).sendKeys("Alamat rumah");
        driver.findElement(By.id("submitAddress")).click();
        System.out.println("---- User Sukses Mengisi Alamat -----");
    }

    @And("User confirm order")
    public void userConfirmOrder() throws InterruptedException{
            //verif halaman confirm order
            String expected_page_checkout = "http://qa.cilsy.id:8080/en/quick-order";
            String actual_page_checkout = driver.getCurrentUrl();
            if (Objects.equals(actual_page_checkout, expected_page_checkout)){
                System.out.println("---- URL sudah susuai dengan expected result ----");
            } else {
                System.out.println("---- Halaman Belum Termuat ----");
            }

            String expected_checkout = "Rooms & Price Summary";
            String actual_checkout = driver.findElement(By.xpath("//*[@id=\"shopping-cart-summary-head\"]/h5/span")).getText();
            if (Objects.equals(actual_checkout, expected_checkout)){
                System.out.println("---- Berada pada halaman Checkout Information ----");
            } else {
                System.out.println("---- Halaman Belum Termuat ----");
            }

            //verify ROOM INFORMATION
            String expected_room_order = "luxury Rooms";
            String actual_room_order = driver.findElement(By.xpath("//*[@id=\"collapse-shopping-cart\"]/div/div[2]/div[1]/div[2]/div[1]/div[2]/p[1]/a[1]")).getText();
            if (Objects.equals(actual_room_order, expected_room_order)){
                System.out.println("---- Type Kamar yang dipilih sudah sesuai ----");
            } else {
                System.out.println("---- Type Kamar yang dipilih tidak sesuai ----");
            }

        driver.findElement(By.xpath("//*[@id=\"collapse-shopping-cart\"]/div/div[2]/div[2]/div/a")).click();
            //verify GUEST INFORMATION
            String expected_guest_information = "Guest Information";
            String actual_guest_information = driver.findElement(By.xpath("//*[@id=\"guest-info-head\"]/h5/span")).getText();
            if (Objects.equals(actual_guest_information, expected_guest_information)){
                System.out.println("---- Berada pada halaman Guest Information ----");
            } else {
                System.out.println("---- Halaman Belum Termuat ----");
            }

        driver.findElement(By.xpath("//*[@id=\"collapse-guest-info\"]/div/div[4]/div/a")).click();
             //verify PAYMENT INFORMATION
            String expected_payment_information = "Payment Information";
            String actual_payment_information = driver.findElement(By.xpath("//*[@id=\"order-payment-head\"]/h5/span")).getText();
            if (Objects.equals(actual_payment_information, expected_payment_information)){
                System.out.println("---- Berada pada halaman Payment Information ----");
            } else {
                System.out.println("---- Halaman Belum Termuat ----");
            }

        driver.findElement(By.id("cgv")).click();
        WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[1]/div/p/a")));

            boolean payment_bankwire = driver.findElement(By.className("bankwire")).isEnabled();
            if (payment_bankwire){
                System.out.println("---- Methode Pembayaran Bank Wire muncul -----");
            } else {
                System.out.println("---- Methode Pembayaran Bank Wire tidak muncul-----");
            }

        driver.findElement(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[1]/div/p/a")).click();
        driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button")).click();

    }

    @Then("User get invoice and user view order history")
    public void userGetInvoiceAndUserViewOrderHistory() {
            //verify Halaman Invoice
            if (driver.getPageSource().contains("Your order on Small Project QA13 is complete.")){
                System.out.println("---- Booking Hotel Succes ----");
            } else {
                System.out.println("---- Booking Hotel Failed ----");
            }

            String expected_order_status = "Confirmed";
            String expected_confirm_room_order = "luxury Rooms";
            String expected_confirm_hotel_order = "The Hotel Prime";
            String actual_order_status = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/p[3]/span")).getText();
            String actual_confirm_room_order = driver.findElement(By.xpath("//*[@id=\"order-detail-content\"]/table/tbody/tr/td[2]/p/a")).getText();
            String actual_confirm_hotel_order = driver.findElement(By.xpath("//*[@id=\"order-detail-content\"]/table/tbody/tr/td[3]")).getText();
            assertEquals("order status confirmed",actual_order_status,expected_order_status);
            assertEquals("room sesuai", actual_confirm_room_order,expected_confirm_room_order);
            assertEquals("hotel sesuai",actual_confirm_hotel_order,expected_confirm_hotel_order);

        driver.findElement(By.xpath("//*[@id=\"center_column\"]/p/a")).click();
            if (driver.getPageSource().contains("Awaiting bank wire payment")){
                System.out.println("---- Menunggu pembayaran bank wire ----");
            } else {
                System.out.println("---- Belum ada invoice pembayaran ----");
            }

    }


//-------------------------------------------------------------------------------------------------------------------------------------------------------//

// Scenario ke 2
    @Given("User Access website")
    public void UserAccessWebsite() {
        driver.get("http://qa.cilsy.id:8080/en/");
        driver.manage().window().maximize();
        if (driver.getPageSource().contains("Sign in")){
            System.out.println("---- Element Sign In muncul ----");
        } else {
            System.out.println("---- Element Sign In tidak muncul ----");
        }
    }

    @When("User Choose Hotel")
    public void UserChooseHotel() {
        driver.findElement(By.id("hotel_location")).sendKeys("Surabaya");
        driver.findElement(By.id("id_hotel_button")).click();
        driver.findElement(By.xpath("//*[@id=\"search_hotel_block_form\"]/div[2]/div/ul")).click();

        driver.findElement(By.id("check_in_time")).click();
        int in = 1;                                                         //buat ganti bulan
        while (in<15) {
            driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[2]")).click();
            in++;
        }
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[2]/td[7]/a")).click();

        driver.findElement(By.id("check_out_time")).click();
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[3]/td[1]/a")).click();

        driver.findElement(By.id("search_room_submit")).click();
        WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"category_data_cont\"]/div[2]/div/div[2]/p[1]")));

        boolean displayed_generalRooms = driver.findElement(By.xpath("//*[@id=\"category_data_cont\"]/div[1]/div/div[2]/p[1]")).isDisplayed();
        boolean displayed_deluxRooms = driver.findElement(By.xpath("//*[@id=\"category_data_cont\"]/div[1]/div/div[2]/p[1]")).isDisplayed();
        boolean displayed_executiveRooms = driver.findElement(By.xpath("//*[@id=\"category_data_cont\"]/div[1]/div/div[2]/p[1]")).isDisplayed();
        boolean displayed_luxuryRooms = driver.findElement(By.xpath("//*[@id=\"category_data_cont\"]/div[1]/div/div[2]/p[1]")).isDisplayed();

        System.out.println("CEK General Rooms : " +displayed_generalRooms);
        System.out.println("CEK Delux Rooms : " +displayed_deluxRooms);
        System.out.println("CEK Executive Rooms : " +displayed_executiveRooms);
        System.out.println("CEK Luxury Rooms : " +displayed_luxuryRooms);
    }

    @And("User Choose Hotel Room and add more rooms")
    public void userChooseHotelRoomAndAddMoreRooms() {
        driver.findElement(By.xpath("//*[@id=\"category_data_cont\"]/div[2]/div/div[1]/a/img")).click();

        WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"center_column\"]/div/div/div[1]/div[1]/div[1]/div[1]/span")));

        String expected_room = "Delux Rooms - The Hotel Prime";
        String actual_room = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/div/div[1]/div[1]/div[1]/div[1]/span")).getText();
        if (Objects.equals(actual_room, expected_room)){
            System.out.println("---- Pilihan kamar sudah sesuai ----");
        } else {
            System.out.println("---- Salah memilih kamar ----");
        }

        //menghapus dan menambah quantity
        WebElement quantity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"quantity_wanted\"]")));
        quantity.sendKeys(Keys.BACK_SPACE);
        quantity.sendKeys("2");

        driver.findElement(By.id("add_to_cart")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a/span")));

        //verify popup halaman success add to cart
        String expected_room_cart = "Room successfully added to your cart";
        String actual_room_cart = driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[1]/h2")).getText();
        if (Objects.equals(actual_room_cart,expected_room_cart)){
            System.out.println("---- Success memasukkan kamar pada cart -----");
        } else {
            System.out.println("---- Tidak ada kamar di cart -----");
        }

        driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a/span")).click();
        System.out.println("---- User success melakukan pemilihan kamar -----");
    }

    @And("User Confirm order")
    public void UserConfirmOrder() {
        //verif halaman confirm order
        String expected_page_checkout = "http://qa.cilsy.id:8080/en/quick-order";
        String actual_page_checkout = driver.getCurrentUrl();
        if (Objects.equals(actual_page_checkout, expected_page_checkout)){
            System.out.println("---- URL sudah susuai dengan expected result ----");
        } else {
            System.out.println("---- Halaman Belum Termuat ----");
        }

        String expected_checkout = "Rooms & Price Summary";
        String actual_checkout = driver.findElement(By.xpath("//*[@id=\"shopping-cart-summary-head\"]/h5/span")).getText();
        if (Objects.equals(actual_checkout, expected_checkout)){
            System.out.println("---- Berada pada halaman Checkout Information ----");
        } else {
            System.out.println("---- Halaman Belum Termuat ----");
        }

        //verify ROOM INFORMATION
        String expected_room_order = "Delux Rooms";
        String actual_room_order = driver.findElement(By.xpath("//*[@id=\"collapse-shopping-cart\"]/div/div[2]/div[1]/div[2]/div[1]/div[2]/p[1]/a[1]")).getText();
        if (Objects.equals(actual_room_order, expected_room_order)){
            System.out.println("---- Type Kamar yang dipilih sudah sesuai ----");
        } else {
            System.out.println("---- Type Kamar yang dipilih tidak sesuai ----");
        }

        String expected_quantity_room_order = "02";
        String actual_quantity_room_order = driver.findElement(By.xpath("//*[@id=\"collapse-shopping-cart\"]/div/div[2]/div[1]/div[2]/div[3]/div[3]/p[2]")).getText();
        if (Objects.equals(actual_quantity_room_order, expected_quantity_room_order)){
            System.out.println("---- Jumlah Kamar Sudah Sesuai ----");
        } else {
            System.out.println("---- Jumlah Kamar tidak sesuai dengan pemesanan ----");
        }

        //jumlah angka yg tertampil di keranjang belanja
        String expected_cart_room_order = "2";
        String actual_cart_room_order = driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[4]/div/a/span[1]")).getText();
        if (Objects.equals(actual_cart_room_order, expected_cart_room_order)){
            System.out.println("---- Jumlah Kamar di cart Sudah Sesuai ----");
        } else {
            System.out.println("---- Jumlah Kamar di cart tidak sesuai dengan pemesanan ----");
        }

        driver.findElement(By.xpath("//*[@id=\"collapse-shopping-cart\"]/div/div[2]/div[2]/div/a")).click();

        driver.findElement(By.id("id_gender")).click();                //pilih gender
        WebElement element = driver.findElement(By.id("id_gender"));
        Select state = new Select(element);
        state.selectByValue("1");                                       //isinya Mr

        driver.findElement(By.id("customer_firstname")).sendKeys("Tingky");
        driver.findElement(By.id("customer_lastname")).sendKeys("Wingky");
        driver.findElement(By.id("email")).sendKeys("anakpertama@testing.com");
        driver.findElement(By.id("passwd")).sendKeys("ucapkanhalo");
        driver.findElement(By.id("phone_mobile")).sendKeys("089111911911");
        driver.findElement(By.id("submitAccount")).click();

        WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"collapse-guest-info\"]/div/div[1]/div[2]")));

        String expected_nameGuest = "Tingky Wingky";
        String actual_nameGuest = driver.findElement(By.xpath("//*[@id=\"collapse-guest-info\"]/div/div[1]/div[2]")).getText();
        if (Objects.equals(actual_nameGuest, expected_nameGuest)){
            System.out.println("---- Nama sudah sesuai ----");
        } else {
            System.out.println("---- Nama belum sesuai ----");
        }
        driver.findElement(By.xpath("//*[@id=\"collapse-guest-info\"]/div/div[4]/div/a")).click();
        driver.findElement(By.id("cgv")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[2]/div/p/a")));

        boolean payment_bankwire = driver.findElement(By.className("cheque")).isEnabled();
        if (payment_bankwire){
            System.out.println("---- Methode Pembayaran Check muncul -----");
        } else {
            System.out.println("---- Methode Pembayaran Check tidak muncul-----");
        }

        driver.findElement(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[2]/div/p/a")).click();
        driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button")).click();
    }

    @Then("User get invoice and User view order history")
    public void UserGetInvoiceAndUserViewOrderHistory() {
        //verify Halaman Invoice
        if (driver.getPageSource().contains("Your order on Small Project QA13 is complete.")){
            System.out.println("---- Booking Hotel Succes ----");
        } else {
            System.out.println("---- Booking Hotel Failed ----");
        }

        String expected_order_status = "Confirmed";
        String expected_confirm_room_order = "Delux Rooms";
        String expected_confirm_hotel_order = "The Hotel Prime";
        String actual_order_status = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/p[2]/span")).getText();
        String actual_confirm_room_order = driver.findElement(By.xpath("//*[@id=\"order-detail-content\"]/table/tbody/tr/td[2]/p/a")).getText();
        String actual_confirm_hotel_order = driver.findElement(By.xpath("//*[@id=\"order-detail-content\"]/table/tbody/tr/td[3]")).getText();
        assertEquals("order status confirmed",actual_order_status,expected_order_status);
        assertEquals("room sesuai", actual_confirm_room_order,expected_confirm_room_order);
        assertEquals("hotel sesuai",actual_confirm_hotel_order,expected_confirm_hotel_order);

        driver.findElement(By.xpath("//*[@id=\"center_column\"]/p/a")).click();
        if (driver.getPageSource().contains("Awaiting check payment")){
            System.out.println("---- Menunggu pembayaran check ----");
        } else {
            System.out.println("---- Belum ada invoice pembayaran ----");
        }
    }


    //------------------------------------------------------------------------------------------------------------------------------------//

// Scenario ke 3

    @Given("User access website for order")
    public void userAccessWebsiteForOrder() {
        driver.get("http://qa.cilsy.id:8080/en/");
        driver.manage().window().maximize();
        if (driver.getPageSource().contains("Sign in")){
            System.out.println("---- Element Sign In muncul ----");
        } else {
            System.out.println("---- Element Sign In tidak muncul ----");
        }
    }

    @When("User login using the account that has been created")
    public void UserLoginUsingTheAccountThatHasBeenCreated() {
        driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[7]/ul/li/a")).click();
        if (driver.getPageSource().contains("Already registered?")){
            System.out.println("---- User sudah berada di halaman login ----");
        } else {
            System.out.println("---- Gagal memuat halaman login ----");
        }
        driver.findElement(By.id("email")).sendKeys("po@testing.com");
        driver.findElement(By.id("passwd")).sendKeys("ucapkanhalo");
        driver.findElement(By.id("SubmitLogin")).click();

        //verify succes login
        String expected_login = "http://qa.cilsy.id:8080/en/my-account";
        String actual_login = driver.getCurrentUrl();
        if (Objects.equals(actual_login, expected_login)){
            System.out.println("---- URL sudah susuai dengan expected result ----");
        } else {
            System.out.println("---- User belum berada pada halaman akun ----");
        }

        boolean user_enable = driver.findElement(By.id("user_info_acc")).isEnabled();
        if (user_enable){
            System.out.println("----  User berada pada halaman akun -----");
        } else {
            System.out.println("---- User belum berada pada halaman akun -----");
        }
    }

    @And("User Choose hotel for stay")
    public void userChooseHotelForStay() {
        driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/a")).click();

        String expected_page_booking = "http://qa.cilsy.id:8080/en/";
        String actual_page_booking = driver.getCurrentUrl();
        assertEquals(actual_page_booking, expected_page_booking);
        System.out.println("---- URL sudah susuai dengan expected result ----");


        driver.findElement(By.id("hotel_location")).sendKeys("Jakarta");
        driver.findElement(By.id("id_hotel_button")).click();
        driver.findElement(By.xpath("//*[@id=\"search_hotel_block_form\"]/div[2]/div/ul")).click();

        driver.findElement(By.id("check_in_time")).click();
        int in = 1;                 //buat ganti bulans
        while (in<16) {
            driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[2]")).click();
            in++;
        }
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[1]/td[7]/a")).click();

        driver.findElement(By.id("check_out_time")).click();
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[2]/td[1]/a")).click();

        driver.findElement(By.id("search_room_submit")).click();

        WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"category_data_cont\"]/div[4]/div/div[1]/a/img")));

        boolean displayed_generalRooms = driver.findElement(By.xpath("//*[@id=\"category_data_cont\"]/div[1]/div/div[2]/p[1]")).isDisplayed();
        boolean displayed_deluxRooms = driver.findElement(By.xpath("//*[@id=\"category_data_cont\"]/div[1]/div/div[2]/p[1]")).isDisplayed();
        boolean displayed_executiveRooms = driver.findElement(By.xpath("//*[@id=\"category_data_cont\"]/div[1]/div/div[2]/p[1]")).isDisplayed();
        boolean displayed_luxuryRooms = driver.findElement(By.xpath("//*[@id=\"category_data_cont\"]/div[1]/div/div[2]/p[1]")).isDisplayed();

        System.out.println("CEK General Rooms : " +displayed_generalRooms);
        System.out.println("CEK Delux Rooms : " +displayed_deluxRooms);
        System.out.println("CEK Executive Rooms : " +displayed_executiveRooms);
        System.out.println("CEK Luxury Rooms : " +displayed_luxuryRooms);
    }

    @And("User Choose hotel Room more than one type room")
    public void userChooseHotelRoomMoreThanOneTypeRoom() {
        driver.findElement(By.xpath("//*[@id=\"category_data_cont\"]/div[3]/div/div[2]/a")).click();//executive room

        WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")));

        //verify popup halaman success add to cart room 1
        String expected_room1_cart = "Room successfully added to your cart";
        String actual_room1_cart = driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[1]/h2")).getText();
        if (Objects.equals(actual_room1_cart,expected_room1_cart)){
            System.out.println("---- Success memasukkan kamar pada cart -----");
        } else {
            System.out.println("---- Tidak ada kamar di cart -----");
        }

        driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/span")).click();
        driver.findElement(By.xpath("//*[@id=\"category_data_cont\"]/div[2]/div/div[2]/a")).click();   //delux room

        //verify popup halaman success add to cart room 2
        String expected_room2_cart = "Room successfully added to your cart";
        String actual_room2_cart = driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[1]/h2")).getText();
        if (Objects.equals(actual_room2_cart,expected_room2_cart)){
            System.out.println("---- Success memasukkan kamar pada cart -----");
        } else {
            System.out.println("---- Tidak ada kamar di cart -----");
        }

        driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")).click();
        System.out.println("---- User success melakukan pemilihan kamar -----");
    }

    @And("User confirm order hotel room")
    public void userConfirmOrderHotelRoom() {
        //verify type kamar order
        String expected_room1_order = "Executive Rooms";
        String actual_room1_order = driver.findElement(By.xpath("//*[@id=\"collapse-shopping-cart\"]/div/div[2]/div[1]/div[2]/div[1]/div[2]/p[1]/a[1]")).getText();
        if (Objects.equals(actual_room1_order, expected_room1_order)){
            System.out.println("---- Type Kamar 1 yang dipilih sudah sesuai ----");
        } else {
            System.out.println("---- Type Kamar 1 yang dipilih tidak sesuai ----");
        }

        String expected_room2_order = "Delux Rooms";
        String actual_room2_order = driver.findElement(By.xpath("//*[@id=\"collapse-shopping-cart\"]/div/div[2]/div[2]/div[2]/div[1]/div[2]/p[1]/a[1]")).getText();
        if (Objects.equals(actual_room2_order, expected_room2_order)){
            System.out.println("---- Type Kamar 2 yang dipilih sudah sesuai ----");
        } else {
            System.out.println("---- Type Kamar 2 yang dipilih tidak sesuai ----");
        }

        driver.findElement(By.xpath("//*[@id=\"collapse-shopping-cart\"]/div/div[2]/div[3]/div/a")).click();       //proceed
        //verify GUEST INFORMATION
        String expected_guest_information = "Guest Information";
        String actual_guest_information = driver.findElement(By.xpath("//*[@id=\"guest-info-head\"]/h5/span")).getText();
        if (Objects.equals(actual_guest_information, expected_guest_information)){
            System.out.println("---- Berada pada halaman Guest Information ----");
        } else {
            System.out.println("---- Halaman Belum Termuat ----");
        }

        driver.findElement(By.xpath("//*[@id=\"collapse-guest-info\"]/div/div[4]/div/a")).click();

        //verify PAYMENT INFORMATION
        String expected_payment_information = "Payment Information";
        String actual_payment_information = driver.findElement(By.xpath("//*[@id=\"order-payment-head\"]/h5/span")).getText();
        if (Objects.equals(actual_payment_information, expected_payment_information)){
            System.out.println("---- Berada pada halaman Payment Information ----");
        } else {
            System.out.println("---- Halaman Belum Termuat ----");
        }

        driver.findElement(By.id("cgv")).click();
        WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[3]/div/p")));

        boolean payment_paypall = driver.findElement(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[3]/div/p/a")).isEnabled();
        if (payment_paypall){
            System.out.println("---- Methode Pembayaran Paypall muncul -----");
        } else {
            System.out.println("---- Methode Pembayaran Paypall tidak muncul -----");
        }

        driver.findElement(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[3]/div/p")).click();
    }

    @Then("User get invoice order and user view order history")
    public void userGetInvoiceOrderAndUserViewOrderHistory() {
        //verify Halaman Invoice
        if (driver.getPageSource().contains("Error occurred")){
            System.out.println("---- Pembayaran menggunakan paypal Error ----");
        } else {
            System.out.println("---- Pembayaran Paypall success ----");
        }

        //jumlah angka yg tertampil di keranjang belanja
        String expected_cart_room_order = "2";
        String actual_cart_room_order = driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[4]/div/a/span[1]")).getText();
        if (Objects.equals(actual_cart_room_order, expected_cart_room_order)){
            System.out.println("---- Booking kamar berada di keranjang belanja ----");
        } else {
            System.out.println("---- Tidak ada kamar di keranjang belanja ----");
        }

    }

//----------------------------------------------------------------------------------------------------------------------------------//
// Scenario ke 4

    @Given("User Access Website For Create Account")
    public void userAccessWebsiteForCreateAccount() {
        driver.get("http://qa.cilsy.id:8080/en/");
        driver.manage().window().maximize();
            if (driver.getPageSource().contains("Sign in")){
                System.out.println("---- Element Sign In muncul ----");
            } else {
                System.out.println("---- Element Sign In tidak muncul ----");
            }
    }

    @When("User Create Account")
    public void userCreateAccount() {
        driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[7]/ul/li/a")).click();
        driver.findElement(By.id("email_create")).sendKeys("check@testing.com");
        driver.findElement(By.id("SubmitCreate")).click();

        WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"submitAccount\"]")));

        WebElement gender_mrs = driver.findElement(By.id("id_gender2"));
        gender_mrs.click();
        System.out.println("gender wanita :" +gender_mrs.isSelected());

        //user mengisi data diri
        driver.findElement(By.id("customer_firstname")).sendKeys("Lala");
        driver.findElement(By.id("customer_lastname")).sendKeys("Teletubies");
        driver.findElement(By.id("email")).clear();                                        //menghapus isi yang sudah ada masukkan
        driver.findElement(By.id("email")).sendKeys("pipii@testing.com");
        driver.findElement(By.id("passwd")).sendKeys("upackanhalo");

        //memilih dropdown tanggal lahir
        driver.findElement(By.id("uniform-days")).click();
        WebElement days = driver.findElement(By.id("days"));
        Select tanggal = new Select(days);
        tanggal.selectByValue("3");                                      //isinya tanggal 3

        driver.findElement(By.id("uniform-months")).click();
        WebElement months = driver.findElement(By.id("months"));
        Select bulan = new Select(months);
        bulan.selectByValue("3");                                       //isinya bulan Maret

        driver.findElement(By.id("uniform-years")).click();
        WebElement years = driver.findElement(By.id("years"));
        Select tahun = new Select(years);
        tahun.selectByValue("1997");                                      //isinya Tahun 1997

        driver.findElement(By.id("newsletter")).click();
        driver.findElement(By.id("submitAccount")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_info_acc")));

            //verify halaman success create akun
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

    @And("User input addreess")
    public void userInputAddreess() {

        WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("///*[@id=\"center_column\"]/div/div/ul/li[1]/a")));

        driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/div/ul/li[1]/a")).click();
    }

    @Then("User have account")
    public void userHaveAccount() {

    }



}
