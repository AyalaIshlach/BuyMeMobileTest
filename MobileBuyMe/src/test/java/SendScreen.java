import org.openqa.selenium.By;

public class SendScreen {
    public static final By RADIO_BUTTON_LOCATOR = By.id("il.co.mintapp.buyme:id/nowRadioButton") ;
    public static final By CHECKBOX_LOCATOR = By.id("il.co.mintapp.buyme:id/optionCheckBox");
    public static final By EMAIL_FIELD_LOCATOR = By.className("android.widget.EditText");
    public static String email = "gal1234@gmail.com";
    public static final By GO_BUTTON_LOCATOR = By.id("il.co.mintapp.buyme:id/goNextButton") ;
    public static final By FINISH_LOCATOR = By.id("il.co.mintapp.buyme:id/paymentTitle") ;
}
