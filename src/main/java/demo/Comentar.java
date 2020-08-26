package demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Comentar {
	@SuppressWarnings("unused")
	private static Set<String> users;
	private static List<String> usersToComment = new ArrayList<String>();
	private static String urlPost = "https://www.instagram.com/p/CDCndHohqJv/";
	private static String patchDriver = "C:\\Users\\olive\\Downloads\\chromedriver.exe";
	private static String pathTxt = "C:\\Users\\olive\\Documents\\seguidores.txt";
	private static Integer quantidadeUsuarios = 2;

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriver driver = null;
		try {
			System.setProperty("webdriver.chrome.driver", patchDriver);
			driver = new ChromeDriver();
			Utils.logar(driver);

			File myObj = new File(pathTxt);
			if (myObj.createNewFile()) {
				try {
					users = Utils.getAllNames(driver);
					FileWriter myWriter = new FileWriter(pathTxt);
					for (String user : users) {
						myWriter.write("@" + user);
					}
					myWriter.close();
				} catch (Exception e) {
					System.out.println(e);
				}
			}
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(myObj));

			String[] st = br.readLine().split(",");
			usersToComment = Arrays.asList(st);

			Utils.commentPost(driver, usersToComment, urlPost, quantidadeUsuarios);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			driver.quit();
		}
	}

}
