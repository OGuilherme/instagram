package demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DesseguirInsta {
	private static List<String> usersToUnfollow = new ArrayList<String>();
	private static String patchDriver = "C:\\Users\\olive\\Downloads\\chromedriver.exe";
	private static String pathTxt = "C:\\Users\\olive\\Documents\\seguindoG.txt";
	
	public static void main(String[] args) throws InterruptedException {
		//System.setProperty("webdriver.chrome.driver", "D:\\instagram\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", patchDriver);
		WebDriver driver = new ChromeDriver();
		try {
			Utils.logar(driver, "", "");
			File myObj = new File(pathTxt);
			
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(myObj));

			String[] st = br.readLine().split(",");
			usersToUnfollow = Arrays.asList(st);
			System.out.println("Lista de usuario para deixar de seguir com: "+ usersToUnfollow.size());
			Utils.unfollow(driver,usersToUnfollow);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("acabou o processo");
			driver.quit();
		}
	}

}
