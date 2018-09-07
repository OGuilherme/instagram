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

	private static String urlFoto1 = "https://www.instagram.com/p/BkIcoLmAkQv/?taken-by=neymarjr";
	private static String urlFoto2 = "https://www.instagram.com/p/BkERy2PHn0A/?taken-by=mariliamendoncacantora";
	private static String urlFoto3 = "https://www.instagram.com/p/Bj965K-AEL5/?taken-by=fernandoesorocaba";
	private static String urlFoto4 = "https://www.instagram.com/p/BhiZGkhBoY1/?taken-by=rex2501";
	private static String urlFoto5 = "https://www.instagram.com/p/BiHV4ZTHwRM/?taken-by=brunogarciayt";
	private static String urlFoto6 = "https://www.instagram.com/p/BiX1SpAAclm/?taken-by=donas";

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		try {
			Utils.logar(driver);
			Utils.curtidores(driver, urlFoto1);
			Utils.curtidores(driver, urlFoto2);
			Utils.curtidores(driver, urlFoto3);
			Utils.curtidores(driver, urlFoto4);
			Utils.curtidores(driver, urlFoto5);
			Utils.curtidores(driver, urlFoto6);
		} catch (Exception e) {
			Email.enviarEmail(e+"");
		} finally {
			driver.quit();
		}
	}
}