package demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CurtirSeguidores {
	/*
	 * O sistema esta programado para ir em 6 fotos diferentes para achar
	 * seguidores. Caso n�o queira que ele va em 6, s� precisa adcionar // no inicio
	 * da linha aonde esta escrito "urlFotoX" e na linha start(urlFotoX,driver); O
	 * n�mero 6 � o exemplo de uma foto que sera ignorada, caso deseje que ela seja
	 * executada s� � necessario remover a //
	 */

	private static String urlFoto1 = "https://www.instagram.com/gus2rodas/";
	private static String urlFoto2 = "https://www.instagram.com/p/BkERy2PHn0A/?taken-by=mariliamendoncacantora";
	private static String urlFoto3 = "https://www.instagram.com/p/Bj965K-AEL5/?taken-by=fernandoesorocaba";
	private static String urlFoto4 = "https://www.instagram.com/p/BhiZGkhBoY1/?taken-by=rex2501";
	private static String urlFoto5 = "https://www.instagram.com/p/BiHV4ZTHwRM/?taken-by=brunogarciayt";
	private static String urlFoto6 = "https://www.instagram.com/p/BiX1SpAAclm/?taken-by=donas";
	private static Integer total = 0;

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = null;
		try {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Guh\\Downloads\\Guilerme\\chromedriver.exe");
			//System.setProperty("webdriver.chrome.driver", "C:\\Users\\guolivei\\Documents\\Projetos\\chromedriver.exe");
			driver = new ChromeDriver();
			Utils.logar(driver);
			total += Utils.curtidores(urlFoto1, driver);
			total += Utils.curtidores(urlFoto2, driver);
			total += Utils.curtidores(urlFoto3, driver);
			total += Utils.curtidores(urlFoto4, driver);
			total += Utils.curtidores(urlFoto5, driver);
			total += Utils.curtidores(urlFoto6, driver);
		} catch (Exception e) {
			System.out.println(e);
			// Email.enviarEmail(e+"");
		} finally {
			System.out.println("Foram curtidas " + total + " fotos");
			driver.quit();
		}
	}
}