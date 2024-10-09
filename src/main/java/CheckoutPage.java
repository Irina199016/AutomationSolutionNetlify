import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;


// Declaring the CheckoutPage class, which extends the BasePage class.
// By extending BasePage, CheckoutPage inherits the WebDriver instance and the PageFactory initialization.
public class CheckoutPage extends BasePage {

    // Declaring a public WebDriverWait variable named 'wait'.
    // WebDriverWait is used for implementing explicit waits during interactions with web elements.
    public WebDriverWait wait;
    public SoftAssert softAssert;

    // Constructor for the CheckoutPage class that takes a WebDriver object as an argument.
    // This constructor calls the parent class (BasePage) constructor to initialize the WebDriver instance
    // and sets up the PageFactory to initialize the web elements on this page.
    public CheckoutPage(WebDriver driver) {
        // Calling the parent class (BasePage) constructor using 'super' to initialize the WebDriver.
        super(driver);

        // Initializing the WebDriverWait object with a 10-second timeout.
        // This will be used to wait for certain conditions or elements during test execution.
        wait = new WebDriverWait(driver, 10);
        softAssert = new SoftAssert();
    }

    // Locating the search bar element using the @FindBy annotation.
    // @FindBy is a Selenium annotation that helps locate elements on the web page.
    // Here, the element is being located by its 'id' attribute with the value "input-search".
    // Declare the WebElement as private to enforce encapsulation
    // This ensures that 'searchBar' cannot be accessed directly from outside this class
    @FindBy(id = "input-search")
    private WebElement searchBar;

    // A public method to set a value in the search bar.
    // This method interacts with the searchBar element and sends the text "mouse" to it.
    // Public method to interact with the private 'searchBar' element
    // Provides controlled access to the encapsulated WebElement
    public void setSearchBar(String awesome) {
        // Typing the word "mouse" into the search bar.
        searchBar.sendKeys("Awesome");
    }

    // Locating the search button element using the @FindBy annotation.
    // The element is being located by its CSS selector, which identifies elements based on their classes.
    // Here, the button has the classes "btn", "btn-light", and "btn-sm".
    @FindBy(css = ".btn.btn-light.btn-sm")
    private WebElement searchButton;

    public void clickSearchButton() {
        searchButton.click();
    }

    @FindBy(linkText = "Refined Frozen Mouse")
    private WebElement frozenMouse;

    public WebElement getFrozenMouse() {
        return frozenMouse;
    }

    @FindBy(linkText = "Awesome Granite Chips")
    private WebElement awesomeChipsProduct;

    public void clickAwesomeGraniteChipsLink() {
        awesomeChipsProduct.click();
    }

    @FindBy(css = ".svg-inline--fa.fa-cart-plus.fa-w-18.fa-3x")
    private WebElement cartIcon;

    public void clickCartIcon() {
        cartIcon.click();
    }

    @FindBy(css = ".svg-inline--fa.fa-shopping-cart.fa-w-18 ")
    private WebElement shoppingCartIcon;

    public void clickShoppingCartIcon() {
        shoppingCartIcon.click();
    }

    @FindBy(css = ".btn.btn-success")
    private WebElement checkoutButton;

    public void clickCheckooutButton() {
        checkoutButton.click();
    }

    @FindBy(id = "first-name")
    private WebElement firstNameField;

    public void setFirstNameField() {
        firstNameField.sendKeys("Ion");
    }

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    public void setLastNameField() {
        lastNameField.sendKeys("Ioan");
    }

    @FindBy(id = "address")
    private WebElement addressField;

    public void setAddressField() {
        addressField.sendKeys("Floresti");

    }

    @FindBy(css = ".btn.btn-success")
    private WebElement continueCheckout;

    public void clickContinueCheckout() {
        continueCheckout.click();
    }

    @FindBy(css = ".btn.btn-success")
    private WebElement completeYourOrder;

    public void clickCompleteYourOrder() {
        completeYourOrder.click();
    }

    @FindBy(css = ".text-muted")
    private WebElement successMessage;

    public WebElement getSuccessMessage() {
        return successMessage;
    }

    @FindBy(css = ".svg-inline--fa.fa-heart.fa-w-16.fa-3x")
    private WebElement heartIcon;

    public void clickHeartIcon() {
        heartIcon.click();
    }

    @FindBy(css = ".fa-layers-counter.shopping_cart_badge")
    private WebElement shoppingCartBadge;

    public WebElement getShoppingCartBadge() {
        return shoppingCartBadge;
    }

    public void clickShoppingCartBadge() {
        shoppingCartBadge.click();
    }

    public WebElement getAwesomeChipsProduct() {
        return awesomeChipsProduct;
    }

    @FindBy(css = ".svg-inline--fa.fa-heart-broken.fa-w-16.fa-2x")
    private WebElement brokenHeartIcon;

    public void clickBrokenHeartIcon() {
        brokenHeartIcon.click();
    }

    @FindBy(linkText = "Awesome Soft Shirt")
    private WebElement awesomeShirt;

    public void clickAwesomeShirt() {
        awesomeShirt.click();
    }

    @FindBy(css = ".svg-inline--fa.fa-cart-plus.fa-w-18.fa-3x ")
    private WebElement add2Cart;

    public void clickAdd2Cart() {
        add2Cart.click();
    }

    @FindBy(css = ".svg-inline--fa.fa-shopping-cart.fa-w-18 ")
    private WebElement cartBtn;

    public void clickCartBtn() {
        cartBtn.click();
    }

    @FindBy(css = ".svg-inline--fa.fa-plus-circle.fa-w-16 ")
    private WebElement plusOne;

    public void clickPlusOne() {
        plusOne.click();
    }

    @FindBy(css = ".amount-total")
    private WebElement validationTotal;

    public WebElement getValidationTotal() {
        return validationTotal;
    }

    @FindBy(xpath = "(//td[@class='amount'])[1]")
    private WebElement itemPrice;
    @FindBy(xpath = "(//td[@class='amount'])[2]")
    private WebElement taxPrice;
    @FindBy(xpath = "(//td[@class='amount'])[3]")
    private WebElement totalPrice;

    public double productPrice() {
        String amountValue = itemPrice.getText();
        String cleanAmountValue = amountValue.replace("$", "");
        return Double.parseDouble(cleanAmountValue);
    }

    public double taxPrice() {
        String taxValue = taxPrice.getText();
        String cleanTaxValue = taxValue.replace("$", "");
        return Double.parseDouble(cleanTaxValue);
    }

    public double totalPrice() {
        String totalValue = totalPrice.getText();
        String cleanTotalValue = totalValue.replace("$", "");
        return Double.parseDouble(cleanTotalValue);
    }

    @FindBy(linkText = "Awesome Metal Chair")
    private WebElement awesomeMetalChair;

    public void clickAwesomeMetalChair() {
        awesomeMetalChair.click();
    }

    @FindBy(css = ".svg-inline--fa.fa-cart-plus.fa-w-18.fa-3x")
    private WebElement chartIconForMetalChair;

    public void clickChartIconForMetalChair() {
        chartIconForMetalChair.click();
    }

    @FindBy(css = ".fa-layers-counter.shopping_cart_badge")
    private WebElement counterShoppingCartBadge;

    public void clickCounterShoppingCartBadge() {
        counterShoppingCartBadge.click();
    }

    @FindBy(css = ".svg-inline--fa.fa-minus-circle.fa-w-16")
    private WebElement deleteOneProduct;

    public void clickDeleteOneProduct() {
        deleteOneProduct.click();
    }

    @FindBy(css = ".text-center.container")
    private WebElement deleteMessage;

    public WebElement getDeleteMessage() {
        return deleteMessage;
    }

    @FindBy(css = ".btn.btn-link")
    private WebElement loginButton;

    public void clickLoginButton() {
        loginButton.click();
    }

    @FindBy(id = "user-name")
    private WebElement userNameField;

    public void setUserNameField() {
        userNameField.sendKeys("dino");
    }

    @FindBy(id = "password")
    private WebElement passwordField;

    public void setPasswordField() {
        passwordField.sendKeys("choochoo");
    }

    @FindBy(css = ".btn.btn-primary")
    private WebElement blueLoginButton;

    public void clickBlueLoginButton() {
        blueLoginButton.click();
    }

    public void addProductToWishlist() {
        clickAwesomeGraniteChipsLink();
        clickHeartIcon();


    }

    public void addProductToCart() {
        clickAwesomeShirt();
        clickAdd2Cart();
        clickCartBtn();
    }

    @FindBy(css = ".svg-inline--fa.fa-heart.fa-w-16 ")
    private WebElement wishlistIcon;

    public void clickOnWishlistIcon() {
        wishlistIcon.click();
    }

    @FindBy(css = ".text-muted")
    private WebElement wishlistMessage;

    public WebElement getWishlistMessage() {
        return wishlistMessage;
    }

    @FindBy(css = ".svg-inline--fa.fa-question.fa-w-12")
    private WebElement questionSign;

    public void clickOnQuestionSign() {
        questionSign.click();
    }

    @FindBy(css =".text-muted")
    private WebElement questionMessage;

    public WebElement getQuestionMessage() {
        return questionMessage;
    }
}



