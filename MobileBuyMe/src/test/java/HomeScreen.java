import org.openqa.selenium.By;

public class HomeScreen {
    public static final By HOME_SCREEN_BUTTON_LOCATOR = By.id("il.co.mintapp.buyme:id/bb_bottom_bar_icon");
    public static final By BACK_BUTTON_LOCATOR = By.className("android.widget.ImageButton");
    public static final By CATEGORY_LOCATOR = By.id("il.co.mintapp.buyme:id/t_title");
    public static final String pickABusiness = "new UiSelector().index(6).clickable(true)";
    public static final By AMOUNT_FEILD_LOCATOR = By.id("il.co.mintapp.buyme:id/priceEditText") ;
    public static String amount = "150";
    public static final By CHOOSE_BUTTON_LOCATOR = By.id("il.co.mintapp.buyme:id/purchaseButton") ;

}
