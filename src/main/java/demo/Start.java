package demo;

import java.net.URL;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class Start {
	/*
	 * O sistema esta programado para ir em 6 fotos diferentes para achar
	 * seguidores. Caso não queira que ele va em 6, só precisa adcionar // no inicio
	 * da linha aonde esta escrito "urlFotoX" e na linha start(urlFotoX,driver); O
	 * número 6 é o exemplo de uma foto que sera ignorada, caso deseje que ela seja
	 * executada só é necessario remover a //
	 */

	private static String urlFoto1 = "https://www.instagram.com/p/Bj5BaoYBU8j/?hl=pt-br&taken-by=lucaslucco";
	private static String urlFoto2 = "https://www.instagram.com/p/Bj25U-PgbAB/?taken-by=luansantana";
	private static String urlFoto3 = "https://www.instagram.com/p/BiX1SpAAclm/?taken-by=donas";
	private static String urlFoto4 = "https://www.instagram.com/p/BhiZGkhBoY1/?taken-by=rex2501";
	private static String urlFoto5 = "https://www.instagram.com/p/BiHV4ZTHwRM/?taken-by=brunogarciayt";
	private static String urlFoto6 = "https://www.instagram.com/p/BiX1SpAAclm/?taken-by=donas";
	private final static String urlInsta = "https://www.instagram.com/accounts/login/";
	private static int curtidasTotais = 0;
	private static int perfisTotais = 0;

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		try {
			logar(driver);
			Thread.sleep(6000);
			start(urlFoto1, driver);
			start(urlFoto2, driver);
			//start(urlFoto3, driver);
			//start(urlFoto4, driver);
			//start(urlFoto5, driver);
			//start(urlFoto6, driver);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			driver.quit();
			System.out.println(
					"acabou cao, curtimos " + curtidasTotais + " fotos, passou por: " + perfisTotais + " perfis");
		}

	}

	public static void start(String urlFoto, WebDriver driver) {
		try {
			System.out.println("Inicio do processo " + new Date());
			driver.get(urlFoto);
			curtidores(driver);
			System.out.println("Fim do processo " + new Date());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void logar(WebDriver driver) {
		Boolean isValid = false;
		driver.get(urlInsta);
		try {
			Thread.sleep(6000);
			while (!isValid) {
				if (!urlInsta.equalsIgnoreCase(driver.getCurrentUrl())) {
					isValid = true;
				}
			}
		} catch (Exception e) {
			System.out.println("Aguarde");
		}
	}

	public static void curtidores(WebDriver driver) {
		int curtidas = 0;
		try {
			Actions actions = new Actions(driver);
			String originalHandle = driver.getWindowHandle();
			driver.findElement(By.cssSelector(".zV_Nj")).click();
			scroll(driver);
			List<WebElement> pessoas = driver.findElements(By.cssSelector(".UYK0S._2dbep.qNELH.kIKUG"));
			int numPessoas = pessoas.size() - 1;
			for (int i = 0; i <= numPessoas; i++) {
				if (curtidasTotais > 1500) {
					throw new Exception();
				}
				try {
					actions.moveToElement(pessoas.get(i));
					String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
					pessoas.get(i).sendKeys(selectLinkOpeninNewTab);
					Thread.sleep(1000);
					for (String handle : driver.getWindowHandles()) {
						if (curtidasTotais > 2500) {
							break;
						}
						if (!handle.equals(originalHandle)) {
							driver.switchTo().window(handle);
							driver.findElement(By.cssSelector(".v1Nh3.kIKUG._bz0w a")).click();
							Thread.sleep(600);
							if (possivelCurtir(driver)) {
								driver.findElement(By.cssSelector(".Szr5J.coreSpriteHeartOpen")).click();
								curtidas++;
								curtidasTotais++;
							}
						}
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				} finally {
					driver.close();
					driver.switchTo().window(originalHandle);
				}
			}
			perfisTotais += numPessoas;
			System.out.println("curtidas: " + curtidas + " e perfis: " + numPessoas);
		} catch (Exception e) {
			System.out.println("Erro :" + e);
		}
	}

	public static void scroll(WebDriver driver) {
		try {
			System.out.println("Inicio do processo de scroll " + new Date());
			List<WebElement> pessoas = driver.findElements(By.cssSelector(".UYK0S._2dbep.qNELH.kIKUG"));
			int numPessoas = pessoas.size();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			URL jqueryUrl = Resources.getResource("jquery.min.js");
			System.out.println(jqueryUrl.getPath());
			String jqueryText = Resources.toString(jqueryUrl, Charsets.UTF_8);
			js.executeScript(jqueryText);
			for (int i = 0; i < 3500; i++) {
				Thread.sleep(1500);
				js.executeScript("$('div .wwxN2.GD3H5')[0].scroll(0,1500000);");
				pessoas = driver.findElements(By.cssSelector(".UYK0S._2dbep.qNELH.kIKUG"));
				if (pessoas.size() == numPessoas)
					break;
				numPessoas = pessoas.size();
			}
			System.out.println("Fim do processo de scroll " + new Date());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static Boolean possivelCurtir(WebDriver driver) {
		try {
			driver.findElement(By.className("coreSpriteHeartFull"));
			return false;
		} catch (Exception e) {
			return true;
		}
	}
}