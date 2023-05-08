import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;

public class Main
{

	public static void main(String[] args) throws IOException, InterruptedException
	{
		System.out.println("Starting tor...");

		Process tor = new ProcessBuilder("/Applications/Tor Browser.app/Contents/MacOS/Tor/tor.real",
										 "--detached").start();




		System.out.println("Starting Chrome...");

		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--proxy-server=\"socks5://localhost:9050\"");
		WebDriver driver = new ChromeDriver(options);

		System.out.println("Opening behance");

		driver.get("https://www.behance.net/gallery/159669381/Scope");

		Thread.sleep(5000);
	}
}