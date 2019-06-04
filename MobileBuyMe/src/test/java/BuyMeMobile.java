import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import jdk.internal.org.xml.sax.SAXException;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)


public class BuyMeMobile {
    private static AndroidDriver<MobileElement> driver;
    private static ExtentReports extent = new ExtentReports();
    private static ExtentTest test = extent.createTest(Report.testName, Report.description);
    private static String reportPath;

    static {
        try {
            reportPath = getData(Report.htmlLocation);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (org.xml.sax.SAXException e) {
            e.printStackTrace();
        }
    }

    private static String folderPath = Report.folderLocation;
    String currentTime = String.valueOf(System.currentTimeMillis());

    @BeforeClass
    public static void setUp() throws IOException, SAXException, ParserConfigurationException, org.xml.sax.SAXException {

        DesiredCapabilities buyMeTest = new DesiredCapabilities();
        buyMeTest.setCapability(MobileCapabilityType.PLATFORM_NAME, Constants.PLATFORM_NAME);
        buyMeTest.setCapability(MobileCapabilityType.DEVICE_NAME, Constants.DEVICE_NAME);
        buyMeTest.setCapability(Constants.CAPABILITY_APP_PACKAGE, getData(Constants.APP_PACKAGE_VALUE));
        buyMeTest.setCapability(Constants.CAPABILITY_APP_ACTIVITY, getData(Constants.APP_ACTIVITY_VALUE));
        buyMeTest.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 120);
        buyMeTest.setCapability(MobileCapabilityType.NO_RESET, true);
        driver = new AndroidDriver(new URL(Constants.APPIUM_URL), buyMeTest);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportPath);
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo(Report.environment, Report.environmentKind);
        extent.setSystemInfo(Report.tester, Report.testerName);
    }

    @Test
    public void test1_logIn() throws IOException {
        driver.findElement(LogInScreen.PERSONAL_ZONE_LOCATOR).click();
        // add screenshot
        test.pass(Report.screenshot1, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Report.PNG_LOCATION + currentTime)).build());
        driver.findElement(LogInScreen.LOG_IN_BUTTON_LOCATOR).click();
        driver.findElement(LogInScreen.GOOGLE_LOG_IN_BUTTON_LOCATOR).click();
        WebElement user = driver.findElementByAndroidUIAutomator(LogInScreen.pickTheFirstUser);
        user.click();
        test.log(Status.PASS, Report.logInfo1);//add information to report log

    }

    @Test
    public void test2_homeScreen () throws IOException {
        try {
            driver.findElement(HomeScreen.HOME_SCREEN_BUTTON_LOCATOR).click();
        }catch (org.openqa.selenium.NoSuchElementException e){
            driver.findElement(HomeScreen.BACK_BUTTON_LOCATOR).click();
            driver.findElement(HomeScreen.HOME_SCREEN_BUTTON_LOCATOR).click();
            test.log(Status.INFO, Report.logInfo2);//add information to report log

        }
        // add screenshot
        test.pass(Report.screenshot2, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Report.PNG_LOCATION + currentTime)).build());
        List<MobileElement> category = driver.findElements(HomeScreen.CATEGORY_LOCATOR);
        category.get(3).click();
        // add screenshot
        test.pass(Report.screenshot3, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Report.PNG_LOCATION + currentTime)).build());
        WebElement business = driver.findElementByAndroidUIAutomator(HomeScreen.pickABusiness);
        business.click();
        // add screenshot
        test.pass(Report.screenshot4, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Report.PNG_LOCATION + currentTime)).build());
        driver.findElement(HomeScreen.AMOUNT_FEILD_LOCATOR).sendKeys(HomeScreen.amount);
        driver.findElement(HomeScreen.CHOOSE_BUTTON_LOCATOR).click();
        test.log(Status.PASS, Report.logInfo3);//add information to report log
    }

    @Test
    public void test3_informationScreen () throws IOException {
        // add screenshot
        test.pass(Report.screenshot5, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Report.PNG_LOCATION + currentTime)).build());
        driver.findElement(InformationScreen.TO_WHO_LOCATOR).sendKeys(InformationScreen.receiversName);
        try {
            driver.hideKeyboard();
        }catch (Exception e){
            driver.pressKey(new KeyEvent(AndroidKey.BACK));
            test.log(Status.ERROR, Report.keyboard);//add information to report log
        }
        driver.findElement(InformationScreen.BLESSING_LOCATOR).sendKeys(InformationScreen.blessing);
        try {
            driver.hideKeyboard();
        }catch (Exception e){
            driver.pressKey(new KeyEvent(AndroidKey.BACK));
            test.log(Status.ERROR, Report.keyboard);//add information to report log
        }
        MobileElement senderField = driver.findElement(InformationScreen.SENDER_LOCATOR);
        senderField.clear();
        senderField.sendKeys(InformationScreen.sendersName);
        try {
            driver.hideKeyboard();
        }catch (Exception e){
            driver.pressKey(new KeyEvent(AndroidKey.BACK));
            test.log(Status.ERROR, Report.keyboard);//add information to report log
        }

        //swipe to the bottom of the page
        TouchAction swipeToBottom = new TouchAction(driver);//create touch action object
        Duration oneHundredMillisDuration = Duration.ofMillis(100);//create the action duration object
        LongPressOptions longPressOptions = new LongPressOptions();//create longPressOption object
        ElementOption elementOption = new ElementOption();//create elementOption object
        elementOption.withElement(senderField);
        PointOption pointOption = new PointOption();//create "to" point object
        pointOption.withCoordinates(100,1000);//adding coordinates
        //build the long press action
        longPressOptions.withDuration(oneHundredMillisDuration).withElement(elementOption).build();
        //preform the long press action
        swipeToBottom.longPress(longPressOptions).moveTo(pointOption).release().perform();

        driver.findElement(InformationScreen.GO_BUTTON_LOCATOR).click();
    }

    @Test
    public void test4_sendScreen () throws InterruptedException, IOException {
        // add screenshot
        test.pass(Report.screenshot6, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Report.PNG_LOCATION + currentTime)).build());
        driver.findElement(SendScreen.RADIO_BUTTON_LOCATOR).click();
        List<MobileElement> checkbox = driver.findElements(SendScreen.CHECKBOX_LOCATOR);
        checkbox.get(2).click();
        driver.findElement(SendScreen.EMAIL_FIELD_LOCATOR).click();
        driver.findElement(SendScreen.EMAIL_FIELD_LOCATOR).sendKeys(SendScreen.email);
        try {
            driver.hideKeyboard();
        }catch (Exception e){
            driver.pressKey(new KeyEvent(AndroidKey.BACK));
            test.log(Status.ERROR, Report.keyboard);//add information to report log
        }
        driver.findElement(SendScreen.GO_BUTTON_LOCATOR).click();
        WebDriverWait pageChange = new WebDriverWait(driver,2);
        pageChange.until(ExpectedConditions.visibilityOfElementLocated(SendScreen.FINISH_LOCATOR));//Wait until send screen closed
        test.log(Status.PASS, Report.logInfo4);//add information to report log
        // add screenshot
        test.pass(Report.screenshot7, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Report.PNG_LOCATION + currentTime)).build());
    }

    @AfterClass
    public static void tearDown(){
        extent.flush();// end test and save data into report file
//        driver.quit();
    }

    //Read from xml file
    private static String getData(String keyName) throws ParserConfigurationException, IOException, SAXException, org.xml.sax.SAXException {
        File configXmlFile = new File(Constants.XML_LOCATION);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(configXmlFile);

        if (doc != null) {
            doc.getDocumentElement().normalize();
        }
        assert doc != null;
        return doc.getElementsByTagName(keyName).item(0).getTextContent();
    }

    // take screenshot and return picture path
    private static String takeScreenShot(String ImagesPath) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(ImagesPath + Report.picEnding);
        try {
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ImagesPath + Report.picEnding;
    }

}
