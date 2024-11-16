import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.*;

import static org.testng.Assert.assertEquals;

// This annotation is used to specify that the ExtentTestNGITestListener class should be used to listen to the test events.
// The listener will generate reports based on the test results using the ExtentReports library.
@Listeners(ExtentTestNGITestListener.class)

// Declaring the CheckoutTest class, which extends the Hooks class.
// By extending Hooks, CheckoutTest inherits the setup and teardown methods for WebDriver.
public class LoginTest extends Hooks {

    // Declaring a public variable of type CheckoutPage named 'checkoutPage'.
    // This will be used to interact with the CheckoutPage object during the tests.
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
        loginPage = new LoginPage(driver);

        // Initializing the WebDriverWait object with the current WebDriver instance and a timeout of 30 seconds.
        // This wait will be used to pause the execution until certain conditions are met or elements are found.
        wait = new WebDriverWait(driver, 30);
        softAssert = new SoftAssert();
    }
    @Test(description = "Login with a user")
    public void loginTest() {
        loginPage.clickLoginButton();
        loginPage.setUserNameField("dino");
        loginPage.setPasswordField("choochoo");
        loginPage.clickLoginBtn();
        assertEquals(loginPage.getUserLoggedIn().getText(), "dino");
        ExtentTestNGITestListener.getTest().log(Status.PASS, "The user 'dino' is logged in");
    }
@Test(description = "Sorting Test  by name (Z to A)")
    public void sortTestZA() {

    List<WebElement> productElements = loginPage.getProductElements();
    List<String> actualProductNames = new ArrayList<>();
    loginPage.selectOption(loginPage.getSortBar(), "Sort by name (Z to A)");
    for (WebElement productElement : productElements) {
        actualProductNames.add(productElement.getText());
    }
    List<String> expectedProductNames = new ArrayList<>(actualProductNames);
    expectedProductNames.sort(Comparator.reverseOrder());
    Assert.assertEquals(actualProductNames, expectedProductNames, "The products are not sorted in reverse alphabetical order");
}
    @Test(description = "Sorting Test by name (A to z)")
       public void sortTestAZ() {

        List<WebElement> productElements = loginPage.getProductElements();
        List<String> actualProductNames = new ArrayList<>();
        loginPage.selectOption(loginPage.getSortBar(), "Sort by name (A to Z)");
        for (WebElement productElement : productElements) {
            actualProductNames.add(productElement.getText());
        }
        System.out.println(actualProductNames);
        List<String> expectedProductNames = new ArrayList<>(actualProductNames);
        expectedProductNames.sort(Comparator.naturalOrder());
        Assert.assertEquals(actualProductNames, expectedProductNames, "The products are not sorted in alphabetical order (A to Z)");
    }

@Test(description = "Sorting Test by price (Low to High)")
public void sortTestByPriceAscending() {
    loginPage.selectOption(loginPage.getSortBar(), "Sort by price (low to high)");

    List<WebElement> productPrices = loginPage.getProductPrices();
    List<Double> actualProductPrices = new ArrayList<>();

    for (WebElement productPrice : productPrices) {
        String priceText = productPrice.getText().replace("$", "");
        actualProductPrices.add(Double.parseDouble(priceText));
    }

    List<Double> expectedPrices = new ArrayList<>(actualProductPrices);
    expectedPrices.sort(Comparator.naturalOrder());
    System.out.println(actualProductPrices);

    Assert.assertEquals(actualProductPrices, expectedPrices,
            "The products are not sorted correctly by price in ascending order");
}
    @Test(description = "Sorting Test by price (High to Low)")
    public void sortTestByPriceDescending() {
        loginPage.selectOption(loginPage.getSortBar(), "Sort by price (high to low)");

        List<WebElement> productPrices = loginPage.getProductPrices();
        List<Double> actualProductPrices = new ArrayList<>();

        for (WebElement productPrice : productPrices) {
            String priceText = productPrice.getText().replace("$", "");
            actualProductPrices.add(Double.parseDouble(priceText));
        }

        List<Double> expectedPrices = new ArrayList<>(actualProductPrices);
        expectedPrices.sort(Comparator.reverseOrder());
        System.out.println(actualProductPrices);

        Assert.assertEquals(actualProductPrices, expectedPrices,
                "The products are not sorted correctly by price in descending order");
    }

}


