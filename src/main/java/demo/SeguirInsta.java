package demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeguirInsta {
	private static Set<String> users;
	private static List<String> usersToFollow = new ArrayList<String>();
	private static String urlFoto1 = "https://www.instagram.com/dreamer_artesanatos/followers/";
	private static String patchDriver = "C:\\Users\\olive\\Downloads\\chromedriver.exe";
	private static String pathTxt = "C:\\Users\\olive\\Documents\\seguirG.txt";
	
	public static void main(String[] args) throws InterruptedException {
		//System.setProperty("webdriver.chrome.driver", "D:\\instagram\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", patchDriver);
		WebDriver driver = new ChromeDriver();
		try {
			Utils.logar(driver, "", "");
			driver.get(urlFoto1);
			File myObj = new File(pathTxt);
			if (myObj.createNewFile()) {
				try {
					users = Utils.getAllNamesPicture(driver);
					FileWriter myWriter = new FileWriter(pathTxt);
					for (String user : users) {
						myWriter.write(user+",");
					}
					myWriter.close();
				} catch (Exception e) {
					System.out.println("Erro ao buscar lista com usuarios./n" + e.getMessage());
				}
			}
			
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(myObj));

			String[] st = br.readLine().split(",");
			usersToFollow = Arrays.asList(st);
			System.out.println("Lista de usuario para seguir com: "+ usersToFollow.size());
			Utils.seguir(driver,usersToFollow);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("acabou o processo");
			driver.quit();
		}
	}
}
