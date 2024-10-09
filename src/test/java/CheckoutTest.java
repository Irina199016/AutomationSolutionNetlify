import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

import static java.time.Duration.ofMillis;
import static org.testng.Assert.*;

// This annotation is used to specify that the ExtentTestNGITestListener class should be used to listen to the test events.
// The listener will generate reports based on the test results using the ExtentReports library.
@Listeners(ExtentTestNGITestListener.class)

// Declaring the CheckoutTest class, which extends the Hooks class.
// By extending Hooks, CheckoutTest inherits the setup and teardown methods for WebDriver.
public class CheckoutTest extends Hooks {

    // Declaring a public variable of type CheckoutPage named 'checkoutPage'.
    // This will be used to interact with the CheckoutPage object during the tests.
    public CheckoutPage checkoutPage;
    public LoginPage loginPage;

    // Declaring a public variable of type WebDriverWait named 'wait'.
    // WebDriverWait is used to explicitly wait for certain conditions or elements during test execution.
    public WebDriverWait wait;
    public SoftAssert softAssert;

    // Method annotated with @BeforeMethod, indicating that it will run before each test method.
    // This method is used to set up the page objects and other necessary components before each test.
    @BeforeMethod
    public void SetupPageObject() {

        // Initializing the checkoutPage object with the current WebDriver instance.
        // This allows the test methods to interact with elements on the checkout page.
        checkoutPage = new CheckoutPage(driver);
        loginPage = new LoginPage(driver);

        // Initializing the WebDriverWait object with the current WebDriver instance and a timeout of 30 seconds.
        // This wait will be used to pause the execution until certain conditions are met or elements are found.
        wait = new WebDriverWait(driver, 30);
        softAssert = new SoftAssert();
    }


    @Test(description = "Tests the search functionality by searching for the keyword 'mouse'")
    public void searchTest()  {
        checkoutPage.setSearchBar("Awesome");
        checkoutPage.clickSearchButton();
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The search engine is looking up for the keyword 'mouse'");
    List<String> expectedProducts = new ArrayList<>();
    expectedProducts.add("Awesome Granite Chips");
    expectedProducts.add("Awesome Metal Chair");
    expectedProducts.add("Awesome Soft Shirt");

    List <WebElement> productElements = loginPage.getProductElements();
    List<String> actualProducts = new ArrayList<>();
    for (WebElement productElement : productElements){
        actualProducts.add(productElement.getText());
    }
    for (String expectedProduct : expectedProducts){
        softAssert.assertTrue(actualProducts.contains(expectedProduct),"Expected product" + expectedProduct + " not found in the search results");
    }
    for (String actualProduct : actualProducts){
        if (!actualProduct.contains("Awesome")){
        softAssert.fail("Unexpected product found: " + actualProduct);
        }
    }
    softAssert.assertAll();
    }

    @Test(description = "Purchasing a simple product from a guest user")
    public void checkoutTest() {
        checkoutPage.clickAwesomeGraniteChipsLink();
        checkoutPage.clickCartIcon();
        checkoutPage.clickShoppingCartIcon();
        checkoutPage.clickCheckooutButton();
        checkoutPage.setFirstNameField();
        checkoutPage.setLastNameField();
        checkoutPage.setAddressField();
        checkoutPage.clickContinueCheckout();
        checkoutPage.clickCompleteYourOrder();
        assertEquals(checkoutPage.getSuccessMessage().getText(), "Order complete");

    }

    @Test(description = "Adding  a product to wishlist")
    public void wishlistTest() {
        checkoutPage.addProductToWishlist();
        if(checkoutPage.getShoppingCartBadge().getText().equals("1")) {
            ExtentTestNGITestListener.getTest().log(Status.PASS, "Shopping Cart Badge was updated with success");
        } else{
            softAssert.fail("Shopping Cart Badge was not updated correctly");
        }
        checkoutPage.clickShoppingCartBadge();
        assertEquals(checkoutPage.getAwesomeChipsProduct().getText(), "Awesome Granite Chips");
        ExtentTestNGITestListener.getTest().log(Status.PASS, "Awesome Granite Chips product was found in the Wishlist");
        softAssert.assertAll();
    }
    @Test(description = "Removing  a product to wishlist")
    public void removeItemFromWishlist() {
        checkoutPage.addProductToWishlist();
        checkoutPage.clickShoppingCartBadge();
        checkoutPage.clickBrokenHeartIcon();
        try {
            // driver.findElement(By.linkText("Awesome Granite Chips"));
            if (checkoutPage.getAwesomeChipsProduct().isDisplayed()) {
                Assert.fail("Element is still present.");
            }
        }catch (NoSuchElementException e){
            ExtentTestNGITestListener.getTest().log(Status.PASS, "Awesome Granite Chips product was present");
            Assert.assertTrue(true, "Element is not present as expected.");
        }

    }

    @Test(description = "Increase the amount of a product")
    public void increasedAmountTest() throws InterruptedException {
        Thread.sleep(5000);

        ExtentTestNGITestListener.getTest().log(Status.INFO, "The price of the product is:+" + checkoutPage.productPrice());
        double expectedTotal = checkoutPage.productPrice() * 2;
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The price of the product is:+" + expectedTotal);
        checkoutPage.clickPlusOne();
        assertEquals(checkoutPage.productPrice(), expectedTotal);
        ExtentTestNGITestListener.getTest().log(Status.PASS, "The price of the product matches the expected total" + checkoutPage.productPrice() + "=" + expectedTotal);
    }
    @Test(description = "Calculate the total price for a product")
    public void totalPriceForAProduct(){
        checkoutPage.addProductToCart();
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The price of product is"+checkoutPage.productPrice());
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The tax price  is"+checkoutPage.taxPrice());
        double expectedTotal = checkoutPage.totalPrice()+checkoutPage.taxPrice();
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The actual price  is"+checkoutPage.totalPrice());
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The expected price  is"+expectedTotal);
        assertEquals(checkoutPage.totalPrice(),expectedTotal);

    }

    @Test(description = "Tests the deleting button")
    public void deleteProduct()  {
        checkoutPage.clickAwesomeMetalChair();
        checkoutPage.clickChartIconForMetalChair();
        checkoutPage.clickCounterShoppingCartBadge();
        checkoutPage.clickDeleteOneProduct();
        assertEquals(checkoutPage.getDeleteMessage().getText(), "How about adding some products in your cart?");
    }

    @Test(description = "Tests the login")
    public void loginAction() {
        checkoutPage.clickLoginButton();
        checkoutPage.setUserNameField();
        checkoutPage.setPasswordField();
        checkoutPage.clickBlueLoginButton();
    }
    @Test(description = "Click on wishlist icon")
    public void wishlistbutton(){
        checkoutPage.clickOnWishlistIcon();
        assertEquals(checkoutPage.getWishlistMessage().getText(),"Wishlist");
    }
    @Test(description = "Click on help button")
    public void helpButton() {
        checkoutPage.clickOnQuestionSign();
        assertEquals(checkoutPage.getQuestionMessage(). getText(),"Help");
    }

}


