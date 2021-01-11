package demo;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Fazenda {
	
	private static String patchDriver = "C:\\Users\\olive\\Downloads\\chromedriver.exe";
	
	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriver driver = null;
		try {
			System.setProperty("webdriver.chrome.driver", patchDriver);
			driver = new ChromeDriver();
			driver.get("https://afazenda.r7.com/a-fazenda-12");
			Thread.sleep(3000);
			List<WebElement> a = driver.findElements(By.className("voting-button--hidden"));
			a.get(2).click();
			driver.findElement(By.className("voting-button")).click();
			Thread.sleep(900);
			driver.findElement(By.className("vote-confirmation__button")).click();
			Thread.sleep(900);
			while(1==1) {
				driver.findElement(By.id("760")).click();
				Thread.sleep(900);
				driver.findElement(By.className("voting-button--medium")).click();
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
