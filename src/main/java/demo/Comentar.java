package demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Comentar {
	private static Set<String> users;
	private static List<String> usersToComment = new ArrayList<String>();
	private static String urlPost = "https://www.instagram.com/p/CDCndHohqJv/";
	
	private static Integer quantidadeUsuarios = 2;

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriver driver = null;
		try {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\olive\\Downloads\\chromedriver.exe");
			driver = new ChromeDriver();
			Utils.logar(driver);

			File myObj = new File("C:\\Users\\olive\\Documents\\seguidores.txt");
			if (myObj.createNewFile()) {
				try {
					users = Utils.getAllNames(driver);
					FileWriter myWriter = new FileWriter("C:\\Users\\olive\\Documents\\seguidores.txt");
					for (String user : users) {
						myWriter.write("@" + user + ",");
					}
					myWriter.close();
				} catch (Exception e) {
					System.out.println(e);
				}
			}
			
			File file = new File("C:\\Users\\olive\\Documents\\seguidores.txt");

			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(file));

			String st;
			while ((st = br.readLine()) != null) {
				usersToComment.add(st.replace(",", ""));
			}

			Utils.commentPost(driver, usersToComment, urlPost, quantidadeUsuarios);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			driver.quit();
		}
	}

}
