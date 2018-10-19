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

	private static String urlFoto1 = "https://www.instagram.com/p/BoDkVGuHVjv/?hl=pt-br&taken-by=gusttavolima";
	private static String urlFoto2 = "https://www.instagram.com/p/BoEb38tnWV2/?hl=pt-br&taken-by=jorgeemateus";
	private static String urlFoto3 = "https://www.instagram.com/p/Bn_-pFgDNJa/?hl=pt-br&taken-by=wesleysafadao";
	private static String urlFoto4 = "https://www.instagram.com/p/BnekEinhYlV/?taken-by=thaysonvrog.oficial";
	private static String urlFoto5 = "https://www.instagram.com/p/BmY1TcxHX11/?taken-by=chavao13";
	private static String urlFoto6 = "https://www.instagram.com/p/Bnyr50QlyVD/?taken-by=gus2rodas";
		
	private static Integer total = 0;

	public static void main(String[] args) throws InterruptedException {
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\Guh\\Downloads\\Guilerme\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\guolivei\\Documents\\Projetos\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		try {
			Utils.logar(driver);
			total = Utils.curtidores(driver, urlFoto1, total);
			if (total <= 1500)
				total = Utils.curtidores(driver, urlFoto2, total);
			if (total <= 1500)
				total = Utils.curtidores(driver, urlFoto3, total);
//			if (total <= 1500)
//				total = Utils.curtidores(driver, urlFoto4, total);
//			if (total <= 1500)
//				total = Utils.curtidores(driver, urlFoto5, total);
//			if (total <= 1500)
//				total = Utils.curtidores(driver, urlFoto6, total);
		} catch (Exception e) {
			System.out.println(e);
			// Email.enviarEmail(e+"");
		} finally {
			System.out.println("Foram curtidas " + total + " fotos");
			driver.quit();
		}
	}
}