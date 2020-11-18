package demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Comentar {
	private static List<String> usersToComment = new ArrayList<String>();
	private static String urlPost = "https://www.instagram.com/p/CGlah4DDt6D/";
	private static String patchDriver = "C:\\Users\\tavo_\\Downloads\\chromedriver\\chromedriver.exe";
	private static String pathTxt = "C:\\Users\\tavo_\\Documents\\seguidores.txt";
	//private static String patchDriver = "C:\\Users\\olive\\Downloads\\chromedriver.exe";
	//private static String pathTxt = "C:\\Users\\olive\\Documents\\seguidores.txt";
	private static Integer quantidadeUsuarios = 1;
	private static String usuario = "";
	private static String senha = "";

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriver driver = null;
		try {
			System.setProperty("webdriver.chrome.driver", patchDriver);
			driver = new ChromeDriver();
			Utils.logar(driver, usuario, senha);

			File myObj = new File(pathTxt);	
			
			BufferedReader br = new BufferedReader(new FileReader(myObj));

			String[] st = br.readLine().split(",");
			usersToComment = Arrays.asList(st);
			System.out.println("Lista de usuario para comentar com: "+ usersToComment.size());
			Utils.commentPost(driver, usersToComment, urlPost, quantidadeUsuarios);
		} catch (Exception e) {
			System.out.println("Erro em algun dos passos que finalizou o processo/n"+e.getMessage());
		} finally {
			driver.quit();
		}
	}

}
