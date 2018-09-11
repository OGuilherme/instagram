package demo;

import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class Utils {

	private final static String urlInsta = "https://www.instagram.com/accounts/login/";
	private static int curtidasTotais = 0;
	private static int perfisTotais = 0;
	private static StringBuilder mensagens = new StringBuilder();

	public static void logar(WebDriver driver) {
		Boolean isValid = false;
		driver.get(urlInsta);
		try {
			Thread.sleep(3000);
			while (!isValid) {
				if (!urlInsta.equalsIgnoreCase(driver.getCurrentUrl())) {
					isValid = true;
				}
			}
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println(e);
			//mensagens.append(System.lineSeparator() + e.getMessage());
		}
	}

	public static Integer curtidores(WebDriver driver, String urlFoto, int totalCurtidas) {
		curtidasTotais = totalCurtidas;
		driver.get(urlFoto);
		try {
			Actions actions = new Actions(driver);
			String originalHandle = driver.getWindowHandle();
			driver.findElement(By.cssSelector(".zV_Nj")).click();
			scroll(driver);
			List<WebElement> pessoas = driver.findElements(By.cssSelector(".SAvC5._2dbep.qNELH.kIKUG"));
			int numPessoas = pessoas.size() - 1;
			perfisTotais = numPessoas;
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
						if (!handle.equals(originalHandle)) {
							driver.switchTo().window(handle);
							if (!perfilPublico(driver)) {
								driver.findElement(By.cssSelector(".v1Nh3.kIKUG._bz0w a")).click();
								Thread.sleep(300);
								if (possivelCurtir(driver)) {
									Thread.sleep(300);
									driver.findElement(By.cssSelector(
											".coreSpriteHeartOpen.oF4XW.dCJp8 .glyphsSpriteHeart__outline__24__grey_9.u-__7"))
											.click();
									curtidasTotais++;
								}
							}
						}
					}
				} catch (Exception e) {
					System.out.println(e);
					//mensagens.append(System.lineSeparator() + e.getMessage());
				} finally {
					driver.close();
					driver.switchTo().window(originalHandle);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			//mensagens.append(System.lineSeparator() + e.getMessage());
		} finally {
			if (!mensagens.toString().isEmpty())
				System.out.println();
				//Email.enviarEmail(mensagens.toString());
			System.out.println("curtidas: " + curtidasTotais + " e perfis: " + perfisTotais);
		}
		return curtidasTotais;
	}

	public static Integer curtidores(String urlFoto, WebDriver driver, int totalCurtidas) {
		curtidasTotais = totalCurtidas;
		driver.get(urlFoto);
		try {
			Actions actions = new Actions(driver);
			String originalHandle = driver.getWindowHandle();
			verSeguidores(driver);
			scrollSeguidores(driver);
			List<WebElement> pessoas = driver.findElements(By.cssSelector(".SAvC5._2dbep.qNELH.kIKUG"));
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
						if (!handle.equals(originalHandle)) {
							driver.switchTo().window(handle);
							if (!perfilPublico(driver)) {
								driver.findElement(By.cssSelector(".v1Nh3.kIKUG._bz0w a")).click();
								Thread.sleep(300);
								if (possivelCurtir(driver)) {
									Thread.sleep(300);
									driver.findElement(By.cssSelector(
											".coreSpriteHeartOpen.oF4XW.dCJp8 .glyphsSpriteHeart__outline__24__grey_9.u-__7"))
											.click();
									curtidasTotais++;
								}
							}
						}
					}
				} catch (Exception e) {
					System.out.println(e);
					//mensagens.append(System.lineSeparator() + e.getMessage());
				} finally {
					driver.close();
					driver.switchTo().window(originalHandle);
				}
			}
			perfisTotais += numPessoas;
		} catch (Exception e) {
			System.out.println(e);
			//mensagens.append(System.lineSeparator() + e.getMessage());
		} finally {
			//if (!mensagens.toString().isEmpty())
				//Email.enviarEmail(mensagens.toString());
			System.out.println("curtidas: " + curtidasTotais + " e perfis: " + perfisTotais);
		}
		return curtidasTotais;
	}

	public static void scrollSeguidores(WebDriver driver) {
		try {
			List<WebElement> pessoas = driver.findElements(By.cssSelector(".rKm58._6xe7A .PZuss .wo9IH"));
			int numPessoas = pessoas.size();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			URL jqueryUrl = Resources.getResource("jquery.min.js");
			String jqueryText = Resources.toString(jqueryUrl, Charsets.UTF_8);
			js.executeScript(jqueryText);
			for (int i = 0; i < 3500; i++) {
				js.executeScript("$('div .j6cq2')[0].scroll(0,1500000);");
				Thread.sleep(1500);
				pessoas = driver.findElements(By.cssSelector(".rKm58._6xe7A .PZuss .wo9IH"));
				if (pessoas.size() == numPessoas)
					break;
				numPessoas = pessoas.size();
			}
		} catch (Exception e) {
			System.out.println(e);
			//mensagens.append(System.lineSeparator() + e.getMessage());
		}
	}
	
	public static void scroll(WebDriver driver) {
		try {
			List<WebElement> pessoas = driver.findElements(By.cssSelector(".PZuss .wo9IH"));
			int numPessoas = pessoas.size();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			URL jqueryUrl = Resources.getResource("jquery.min.js");
			String jqueryText = Resources.toString(jqueryUrl, Charsets.UTF_8);
			js.executeScript(jqueryText);
			for (int i = 0; i < 3500; i++) {
				js.executeScript("$('.wwxN2.GD3H5')[0].scroll(0,1500000);");
				Thread.sleep(1500);
				pessoas = driver.findElements(By.cssSelector(".PZuss .wo9IH"));
				if (pessoas.size() == numPessoas)
					break;
				numPessoas = pessoas.size();
			}
		} catch (Exception e) {
			System.out.println(e);
			//mensagens.append(System.lineSeparator() + e.getMessage());			
		}
	}

	public static void verSeguidores(WebDriver driver) {
		try {
			Thread.sleep(1500);
			List<WebElement> menus = driver.findElements(By.cssSelector(".-nal3"));
			menus.get(1).click();
		} catch (Exception e) {
			mensagens.append(System.lineSeparator() + e.getMessage());
		}
	}

	public static Boolean possivelCurtir(WebDriver driver) {
		try {
			driver.findElement(By.className("glyphsSpriteHeart__filled__24__red_5"));
			return false;
		} catch (Exception e) {
			return true;
		}
	}

	public static Boolean perfilPublico(WebDriver driver) {
		try {
			driver.findElement(By.cssSelector(".v1Nh3.kIKUG._bz0w a"));
			return false;
		} catch (Exception e) {
			return true;
		}
	}
}