import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.util.List;


// Declaring the CheckoutPage class, which extends the BasePage class.
// By extending BasePage, CheckoutPage inherits the WebDriver instance and the PageFactory initialization.
public class LoginPage extends BasePage {

    // Declaring a public WebDriverWait variable named 'wait'.
    // WebDriverWait is used for implementing explicit waits during interactions with web elements.
    public WebDriverWait wait;
    public SoftAssert softAssert;

    // Constructor for the CheckoutPage class that takes a WebDriver object as an argument.
    // This constructor calls the parent class (BasePage) constructor to initialize the WebDriver instance
    // and sets up the PageFactory to initialize the web elements on this page.
    public LoginPage(WebDriver driver) {
        // Calling the parent class (BasePage) constructor using 'super' to initialize the WebDriver.
        super(driver);

        // Initializing the WebDriverWait object with a 10-second timeout.
        // This will be used to wait for certain conditions or elements during test execution.
        wait = new WebDriverWait(driver, 10);
        softAssert = new SoftAssert();
    }


    @FindBy(id = "input-search")
    private WebElement searchBar;

    public void setSearchBar() {
        // Typing the word "mouse" into the search bar.
        searchBar.sendKeys("mouse");
    }

    @FindBy(css = ".svg-inline--fa.fa-sign-in-alt.fa-w-16 ")
    private WebElement loginButton;

    public void clickLoginButton() {
        loginButton.click();
    }

    @FindBy(id = "user-name")
    private WebElement userNameField;

    public void setUserNameField(String name) {
        userNameField.sendKeys(name);
    }

    @FindBy(id = "password")
    private WebElement passwordField;

    public void setPasswordField(String password) {
        passwordField.sendKeys(password);
    }

    @FindBy(css = ".btn.btn-primary")
    private WebElement loginBtn;

    public void clickLoginBtn() {
        loginBtn.click();
    }

    @FindBy(linkText = "dino")
    private WebElement userLoggedIn;

    public WebElement getUserLoggedIn() {
        return userLoggedIn;
    }
    @FindBy(css = ".svg-inline--fa.fa-undo.fa-w-16")
    private WebElement resetButton;
    public void clickResetButton() {
        resetButton.click();
    }
    public void loginPage(){
        clickLoginButton();
        setUserNameField("dino");
        setPasswordField("choochoo");
        clickLoginBtn();
    }
    @FindBy(css=".sort-products-select.form-control.form-control-sm")
    private WebElement sortBar;
    public WebElement getSortBar(){
        return sortBar;
    }
    public void selectOption(WebElement element, String option){
        Select optionSelect = new Select(element);
        optionSelect.selectByVisibleText(option);
    }
    @FindBy(css=".card-link")
    private List<WebElement> productElements;
    public List<WebElement> getProductElements(){
        return productElements;
    }

    @FindBy(xpath = "//span[@style='font-weight: bold; font-size: 16px;']")
    private List<WebElement> productPrices;
    public List <WebElement> getProductPrices() {
        return productPrices;
    }
}
