import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.Objects;

import static org.junit.Assert.assertEquals;


public class cobaBooking extends BeforeAfter {
    @Test
    public void loginAccount() throws InterruptedException {

        // Scenario User melakukan booking kamar dengan tahapan :
        // 1. login tanpa mengisi alamat
        // 2. melakukan pencarian hotel
        // 3. memilih kamar yang tersedia
        // 4. mengisi alamat
        // 5. Melakukan pembayaran dan mendapat invoice

        driver.get("http://qa.cilsy.id:8080/en/");         //User membuka website booking hotel

         //verify halaman awal
            //Tulisan "Hotel Dominic Parks" muncul atau tidak
            if (driver.findElement(By.xpath("//*[@id=\"header\"]/div[4]/div/div/div/div[1]/div/div/h1"))!=null){
                System.out.println("---- Success masuk website booking hotel ----");
            } else {
                System.out.println("---- Website belum terbuka ----");
            }
           //Tulisan "Sign In" muncul atau tidak
            if (driver.getPageSource().contains("Sign in")){
                System.out.println("---- Element Sign In muncul ----");
            } else {
                System.out.println("---- Element Sign In tidak muncul ----");
            }

        //user masuk ke akun website booking hotel
        driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[7]/ul/li/a")).click();

            //verify halaman login, tulisan "ALREADY REGISTERED?" muncul atau tidak
            if (driver.getPageSource().contains("Already registered?")){
                System.out.println("---- User sudah berada di halaman login ----");
            } else {
                System.out.println("---- Gagal memuat halaman login ----");
            }

        //user mengisi form login
        driver.findElement(By.id("email")).sendKeys("lala@testing.com");
        driver.findElement(By.id("passwd")).sendKeys("123456");
        driver.findElement(By.id("SubmitLogin")).click();


            //verify halaman success login account

           //verify URL login sesuai dengan expected atau tidak
            String expected_login = "http://qa.cilsy.id:8080/en/my-account";
            String actual_login = driver.getCurrentUrl();
            assertEquals(actual_login, expected_login);
            System.out.println("Sekarang berada di URL: " + actual_login);
            System.out.println("---- URL sudah susuai dengan expected result ----");


            if (driver.getPageSource().contains("My account")){
                System.out.println("---- User Sukses Login -----");
            } else {
                System.out.println("---- User Gagal Login -----");
            }


            if (driver.getPageSource().contains("Sign in")){
                System.out.println("---- Seharusnya Nama User ----");
            } else {
                System.out.println("---- Nama user sudah login ----");
            }

        driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/a")).click();   //button home kembali ke halaman awal

        //user melakukan pencarian hotel
        driver.findElement(By.id("hotel_location")).sendKeys("Jakarta");
        driver.findElement(By.id("id_hotel_button")).click();
        driver.findElement(By.xpath("//*[@id=\"search_hotel_block_form\"]/div[2]/div/ul")).click();
        driver.findElement(By.id("check_in_time")).click();
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[4]/td[4]/a")).click();
        driver.findElement(By.id("check_out_time")).click();
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[4]/td[5]/a")).click();
        driver.findElement(By.id("search_room_submit")).click();

            //verif pilihan kamar yang muncul
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

        //user memilih ruangan "Luxury Room"
        driver.findElement(By.xpath("//*[@id=\"category_data_cont\"]/div[4]/div/div[1]/a/img")).click();
        Thread.sleep(3000);

            //verif pilihan kamar yang muncul
            String expected_room = "luxury Rooms - The Hotel Prime";
            String actual_room = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/div/div[1]/div[1]/div[1]/div[1]/span")).getText();
            System.out.println("Kamar yang dipilih :" +actual_room);
            if (Objects.equals(actual_room, expected_room)){
                System.out.println("Pilihan kamar sudah sesuai");
            } else {
                System.out.println("Salah memilih kamar");
            }

            //verif pilihan kamar yang muncul
            if (driver.getPageSource().contains("Hotel Features")){
                System.out.println("---- Cek Fasilitas kamar -----");
            } else {
                System.out.println("---- Anda belum memilih kamar -----");
            }

        driver.findElement(By.id("add_to_cart")).click();
        Thread.sleep(3000);

            //verify popup halaman success add to cart
            if (driver.getPageSource().contains("Room successfully added to your cart")){
                System.out.println("---- Success memasukkan kamar pada cart -----");
            } else {
                System.out.println("---- Tidak ada kamar di cart -----");
            }

        driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")).click();
        System.out.println("---- User success melakukan pemilihan kamar -----");

            //verify halaman pengisian alamat
            if (driver.getPageSource().contains("Your addresses")){
                System.out.println("---- User harus mengisi alamat -----");
            } else {
                System.out.println("---- Gagal memuat halaman -----");
            }

        //user mengisi data alamat
        driver.findElement(By.id("firstname")).sendKeys("Lala");
        driver.findElement(By.id("lastname")).sendKeys("Teletabis");
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

            //verif setelah mengisi alamat
            String expected_checkout = "Rooms & Price Summary";
            String actual_checkout = driver.findElement(By.xpath("//*[@id=\"shopping-cart-summary-head\"]/h5/span")).getText();
            System.out.println("Halaman :" +actual_checkout);
            if (Objects.equals(actual_checkout, expected_checkout)){
                System.out.println("Halaman Details Pembayaran Sudah Sesuai");
            } else {
                System.out.println("Halaman Belum Termuat");
            }

        System.out.println("---- User Sukses Mengisi Alamat -----");

        //User mengkonfirmasi pemesanan
        driver.findElement(By.xpath("//*[@id=\"collapse-shopping-cart\"]/div/div[2]/div[2]/div/a")).click();


        driver.findElement(By.xpath("//*[@id=\"collapse-guest-info\"]/div/div[4]/div/a")).click();
        driver.findElement(By.id("cgv")).click();
        Thread.sleep(3000);
        System.out.println("---- CEK 4 -----");
//
//        //User memilih pembayaran
//        driver.findElement(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[1]/div/p/a")).click();
//        driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button")).click();
//        System.out.println("---- CEK 5 -----");
//
//        //User melihat invoice pembayaran & order history
//        driver.findElement(By.xpath("//*[@id=\"center_column\"]/p/a")).click();
//        driver.findElement(By.className("footable-toggle")).click();
//        System.out.println("---- CEK 6 -----");

    }
}

