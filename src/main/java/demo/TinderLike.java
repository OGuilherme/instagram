package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TinderLike {
	private final static String urlTinder = "https://tinder.com/app/recs";

	public static void main(String[] args) throws InterruptedException {
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\Guh\\Downloads\\Guilerme\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\olive\\Downloads\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		try {
			driver.get(urlTinder);
			for (int i = 0; i<=100;i++) {
				Thread.sleep(100);
				driver.findElement(By.cssSelector(".recsGamepad__button--like")).click();
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			driver.quit();
		}
	}
}
