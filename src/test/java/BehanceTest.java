import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class BehanceTest
{

	private static ChromeDriver driver;
	private final By appreciateCssSelector = By.cssSelector("div.e2e-Appreciate-appreciate-button span.Tooltip-trigger-eZ_");
	private final By rejectCookiesCssSelector = By.cssSelector("button#onetrust-reject-all-handler");

	private final By gotItCssSelector = By.cssSelector("div.PrimaryNav-tooltipContent-Ct4 > button");
	private Process tor;

	@BeforeAll
	static void beforeAll()
	{
		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--proxy-server=socks5://localhost:9050");

		driver = new ChromeDriver(options);
	}

	@BeforeEach
	void setUp() throws IOException
	{

	}

	@AfterEach
	void tearDown()
	{
		tor.destroy();
	}

	@AfterAll
	static void afterAll()
	{
		driver.quit();
	}

	@Test
	void test() throws IOException
	{
		System.out.println("Starting tor...");
		tor = new ProcessBuilder("/Applications/Tor Browser.app/Contents/MacOS/Tor/tor.real").start();

		driver.get("https://www.behance.net/gallery/159669381/Scope");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//		wait.until(ExpectedConditions.presenceOfElementLocated(rejectCookiesCssSelector));
//
//		driver.findElement(rejectCookiesCssSelector).click();

		wait.until(ExpectedConditions.presenceOfElementLocated(gotItCssSelector));

		driver.findElement(gotItCssSelector).click();

		WebElement appreciate = driver.findElement(appreciateCssSelector);

		scrollTo(appreciate);

		appreciate.click();
	}

	private static void scrollTo(WebElement appreciate)
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(appreciate);
		actions.perform();
	}
}