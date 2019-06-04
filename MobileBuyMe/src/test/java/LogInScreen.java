import org.openqa.selenium.By;

public class LogInScreen {
    public static final By PERSONAL_ZONE_LOCATOR = By.id("il.co.mintapp.buyme:id/profileTab");
    public static final By LOG_IN_BUTTON_LOCATOR = By.id("il.co.mintapp.buyme:id/email");
    public static final By GOOGLE_LOG_IN_BUTTON_LOCATOR = By.id("il.co.mintapp.buyme:id/googleButton");
    public static final String pickTheFirstUser = "new UiSelector().index(0).enabled(true)";

}
