//import io.cucumber.java.Before;
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.junit.Assert;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//public class LoginStepdefs {
//    public WebDriver driver;
//    @Before
//    public void startBrowser(){
//        System.setProperty("webdriver.chrome.driver", "Resources/chromedriver.exe");
//        driver = new ChromeDriver();
//    }
//    @Given("user open url saucedemo.com")
//    public void userOpenUrlSaucedemoCom() {
//        driver.get("https://www.saucedemo.com/");
//    }
//
//    @When("user input username field with {string}")
//    public void userInputUsernameFieldWith(String username) {
//        driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys(username);
//    }
//
//    @And("user input password field with {string}")
//    public void userInputPasswordFieldWith(String password) {
//        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);
//    }
//
//    @And("user click Login")
//    public void userClickLogin() {
//        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
//    }
//
//    @Then("user see result that direct to homepage")
//    public void userSeeResultThatDirectToHomepage() {
//        String urlredirect = driver.getCurrentUrl();
//        String urlExpected = "https://www.saucedemo.com/inventory.html";
//
//        Assert.assertEquals(urlredirect,urlExpected);
//    }
//
////    @Then("user see result that consist validation message {string}")
////    public void userSeeResultThatConsistValidationMessage(String arg0) {
////    }
////
////    @And("redirect to homepage should not occured")
////    public void redirectToHomepageShouldNotOccured() {
////    }
//}
