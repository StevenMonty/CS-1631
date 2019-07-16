import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class BaseTest {
	public WebDriver driver;
	private WebDriverWait wait;
	
	private final boolean isWindows = false;
	private final boolean isFirefox = false;
	private final boolean isChrome = false;
	
	@Before
	public void setup() {
//		if (isWindows && isFirefox) {
//			System.setProperty("webdriver.gecko.driver", Paths.get("drivers", "geckodriver.exe").toString());
//			driver = new FirefoxDriver();
//		}
//		else if (isWindows && isChrome) {
//			System.setProperty("webdriver.chrome.driver", Paths.get("drivers", "chromedriver.exe").toString());
//			driver = new ChromeDriver();
//		}
//		else if (!isWindows) {
//			driver = new SafariDriver();
//		}
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 30);
	}
	
	@After
	public void teardown() {
		driver.quit();
	}
	
	public void waitUntil(Function<WebDriver, Boolean> predicate) {
		wait.until(predicate);
	}
}
