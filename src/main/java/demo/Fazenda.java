package demo;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Fazenda {
	
	private static String patchDriver = "C:\\Users\\olive\\Downloads\\chromedriver.exe";
	
	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriver driver = null;
		try {
			System.setProperty("webdriver.chrome.driver", patchDriver);
			driver = new ChromeDriver();
			driver.get("https://afazenda.r7.com/a-fazenda-12");
			Thread.sleep(3000);
			while(1==1) {
				driver.findElement(By.id("561")).click();
				Thread.sleep(500);
				driver.findElement(By.className("voting-button")).click();
				Thread.sleep(900);
				driver.findElement(By.className("vote-confirmation__button")).click();
				Thread.sleep(900);
			}
		}catch(Exception e) {
			System.out.println(e);
		}finally {
			driver.quit();
		}
	}
}
