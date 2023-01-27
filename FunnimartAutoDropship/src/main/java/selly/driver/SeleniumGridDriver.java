package selly.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SeleniumGridDriver {

    private WebDriver driver;
    private String browser;
    private String baseUrl;
    private String os;
    private String hub;

    public SeleniumGridDriver(String os, String browser, String baseUrl, String hub, String seleHost, String selePort) throws
            MalformedURLException {
        // String host = System.getProperty("seleniumHubHost"); // TODO: uncomment this line if deploy on docker
        //String host = "10.10.10.10";
        //String host = "localhost";
        //String host="tpf-opensource-selenium-hub";
        //String host = "10.1.64.41";
        //System.setProperty("webdriver.chrome.driver", "D://TPFico//FromThaiNT7//project1//project1//chromedriver.exe");

        this.browser = browser;
        this.os = os;
        this.baseUrl = baseUrl;
        this.hub = hub;

//        Platform platform = Platform.fromString(os.toUpperCase());
        if (browser.equalsIgnoreCase("chrome")) {
            Map<String, Object> prefs = new HashMap<>();
//To Turns off multiple download warning
            prefs.put("profile.default_content_settings.popups", 0);
            prefs.put("profile.default_content_setting_values.automatic_downloads", 1);
//Turns off download prompt
            prefs.put("download.prompt_for_download", false);
            prefs.put("download.default_directory", "D:\\funniMartDownload");
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setExperimentalOption("prefs", prefs);
            chromeOptions.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors"));
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("ignore-certificate-errors");
            //chromeOptions.addArguments("headless");
            chromeOptions.addArguments("--incognito");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--disable-dev-shm-usage");
            chromeOptions.addArguments("start-maximized");
//            chromeOptions.addArguments("window-size=2560x3000");
            chromeOptions.addArguments("window-size=2560x7000");
            //chromeOptions.addArguments("window-size=1920x1080");
//            chromeOptions.addArguments("start-maximized");
//          chromeOptions.addArguments("window-size=1920x1080");

            //chromeOptions.addArguments("start-maximized");
//            chromeOptions.addArguments("window-size=2560x3000");
            //chromeOptions.addArguments("window-size=1920x1080");
//            chromeOptions.addArguments("start-maximized");
//            chromeOptions.addArguments("window-size=2560x3000");
            //chromeOptions.addArguments("--window-size=2560,1080");
//            chromeOptions.setCapability("platform", platform);
            this.driver = new RemoteWebDriver(new URL("http://" + seleHost + ":" + selePort + "/wd/hub"), chromeOptions);
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            this.driver = new RemoteWebDriver(new URL("http://" + seleHost + ":" + selePort + "/wd/hub"), firefoxOptions);
        } else {
            InternetExplorerOptions ieOption = new InternetExplorerOptions();
            this.driver = new RemoteWebDriver(new URL("http://" + seleHost + ":" + selePort + "/wd/hub"), ieOption);
        }

        this.driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        this.driver.get(baseUrl);
    }

    public String getOs() {
        return this.os;
    }

    public String getBrowser() {
        return this.browser;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public String getHub() {
        return this.hub;
    }

    public WebDriver getDriver() {
        return this.driver;
    }
}
