package demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Start {
	/*
	 * O sistema esta programado para ir em 6 fotos diferentes para achar
	 * seguidores. Caso não queira que ele va em 6, só precisa adcionar // no inicio
	 * da linha aonde esta escrito "urlFotoX" e na linha start(urlFotoX,driver); O
	 * número 6 é o exemplo de uma foto que sera ignorada, caso deseje que ela seja
	 * executada só é necessario remover a //
	 */
	//Só arrumar essa url aqui da foto um e ja era
	private static String urlFoto1 = "https://www.instagram.com/p/CFeoM8BFQaF/";
	private static String urlFoto2 = "https://www.instagram.com/p/CG5c-sflUEW/";
	private static String urlFoto3 = "https://www.instagram.com/p/CHLIEQ-lXIw/";
	private static String urlFoto4 = "https://www.instagram.com/p/CHRAZCDhKx4/";
	private static String urlFoto5 = "https://www.instagram.com/p/CHWVtGVhF0L/";
	private static String urlFoto6 = "https://www.instagram.com/p/CHOCz59BZda/";
	private static Integer total = 0;
	private static String usuario = "mmgengenharia";
	private static String senha = "Chupabife123";

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\tavo_\\Downloads\\chromedriver\\chromedriver.exe");
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\olive\\Downloads\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		try {
			Utils.logar(driver, usuario, senha);
			total = Utils.curtidores(driver, urlFoto1, total);
//			if (total <= 1500)
			total = Utils.curtidores(driver, urlFoto2, total);
//			if (total <= 1500)
				total = Utils.curtidores(driver, urlFoto3, total);
//			if (total <= 1500)
				total = Utils.curtidores(driver, urlFoto4, total);
//			if (total <= 1500)
				total = Utils.curtidores(driver, urlFoto5, total);
//			if (total <= 1500)
				total = Utils.curtidores(driver, urlFoto6, total);
		} catch (Exception e) {
			System.out.println(e);
			// Email.enviarEmail(e+"");
		} finally {
			System.out.println("Foram curtidas " + total + " fotos");
			driver.quit();
		}
	}
}