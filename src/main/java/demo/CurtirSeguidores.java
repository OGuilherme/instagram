package demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CurtirSeguidores {
	/*
	 * O sistema esta programado para ir em 6 fotos diferentes para achar
	 * seguidores. Caso não queira que ele va em 6, só precisa adcionar // no inicio
	 * da linha aonde esta escrito "urlFotoX" e na linha start(urlFotoX,driver); O
	 * número 6 é o exemplo de uma foto que sera ignorada, caso deseje que ela seja
	 * executada só é necessario remover a //
	 */

	private static String urlFoto1 = "https://www.instagram.com/p/CHMga8EA-qg/";
	private static String urlFoto2 = "https://www.instagram.com/aln1001/";
	private static String urlFoto3 = "https://www.instagram.com/dgdigoo/";
	private static String urlFoto4 = "https://www.instagram.com/rex2501/";
	private static String urlFoto5 = "https://www.instagram.com/arnondograu_/";
	private static String urlFoto6 = "https://www.instagram.com/mcrick/";
	private static Integer total = 0;
	private static String usuario = "";
	private static String senha = "";

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = null;
		try {
			//System.setProperty("webdriver.chrome.driver", "C:\\Users\\olive\\Downloads\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\guolivei\\Documents\\Projetos\\chromedriver.exe");
			driver = new ChromeDriver();
			Utils.logar(driver, usuario, senha);
			if (total <= 1500)
				total = Utils.curtidores(urlFoto1, driver, 0);
		} catch (Exception e) {
			System.out.println(e);
			//Email.enviarEmail(e+"");
		} finally {
			System.out.println("Foram curtidas " + total + " fotos");
			driver.quit();
		}
	}
}