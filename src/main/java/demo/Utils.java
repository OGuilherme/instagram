package demo;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	private final static String urlInstaHome = "https://www.instagram.com/";
	private static int curtidasTotais = 0;
	private static int perfisTotais = 0;
	private static StringBuilder mensagens = new StringBuilder();
	private static List<WebElement> pessoas = new ArrayList<WebElement>();
	private static Set<WebElement> pessoasRepetidas = new HashSet<WebElement>();

	public static void logar(WebDriver driver) {
		Boolean isValid = false;
		driver.get(urlInsta);
		try {
			Thread.sleep(3000);
			while (!isValid) {
				if (urlInstaHome.equalsIgnoreCase(driver.getCurrentUrl())) {
					isValid = true;
				}
			}
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println(e);
			// mensagens.append(System.lineSeparator() + e.getMessage());
		}
	}

	public static Integer curtidores(WebDriver driver, String urlFoto, int totalCurtidas) {
		curtidasTotais = totalCurtidas;
		driver.get(urlFoto);
		try {
			Actions actions = new Actions(driver);
			String originalHandle = driver.getWindowHandle();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			URL jqueryUrl = Resources.getResource("jquery.min.js");
			String jqueryText = Resources.toString(jqueryUrl, Charsets.UTF_8);
			js.executeScript(jqueryText);
			driver.findElement(By.cssSelector("._0mzm-.sqdOP.yWX7d._8A5w5")).click();
			for (int i = 0; i <= 300; i++) {
				pessoas = scroll(driver);
				perfisTotais += pessoas.size();
				for (int j = 0; j < pessoas.size(); j++) {
					if (curtidasTotais <= 1500) {
						try {
							actions.moveToElement(pessoas.get(j)).perform();
							String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
							pessoas.get(j).sendKeys(selectLinkOpeninNewTab);
							Thread.sleep(1000);
							for (String handle : driver.getWindowHandles()) {
								if (!handle.equals(originalHandle)) {
									driver.switchTo().window(handle);
									Thread.sleep(1000);
									if (!perfilPublico(driver)) {
										driver.findElement(By.cssSelector(".v1Nh3.kIKUG._bz0w a")).click();
										Thread.sleep(1000);
										if (possivelCurtir(driver)) {
											Thread.sleep(1000);
											driver.findElement(By.cssSelector(
													".dCJp8.afkep._0mzm- .glyphsSpriteHeart__outline__24__grey_9.u-__7"))
													.click();
											curtidasTotais++;
										}
									}
								}
							}
						} catch (Exception e) {
							System.out.println("erro ao curtir");
							// mensagens.append(System.lineSeparator() + e.getMessage());
						} finally {
							if (!originalHandle.equalsIgnoreCase(driver.getWindowHandle()))
								driver.close();
							driver.switchTo().window(originalHandle);
							System.out.println(curtidasTotais + ", passou por: " + i + "perfis, quantidade perfis:"
									+ perfisTotais);
						}
					} else {
						throw new Exception();
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			// mensagens.append(System.lineSeparator() + e.getMessage());
		} finally {
			if (!mensagens.toString().isEmpty())
				System.out.println();
			// Email.enviarEmail(mensagens.toString());
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
			List<WebElement> pessoas = driver.findElements(By.cssSelector(".FPmhX.notranslate._0imsa"));
			int numPessoas = pessoas.size() - 1;
			for (int i = 0; i <= numPessoas; i++) {
				if (curtidasTotais > 1500) {
					throw new Exception();
				}
				try {
					actions.moveToElement(pessoas.get(i + 1)).perform();
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
											".coreSpriteHeartOpen._0mzm-.dCJp8 .glyphsSpriteHeart__outline__24__grey_9.u-__7"))
											.click();
									curtidasTotais++;
								}
							}
						}
					}
				} catch (Exception e) {
					System.out.println("erro ao curtir");
					// mensagens.append(System.lineSeparator() + e.getMessage());
				} finally {
					driver.close();
					driver.switchTo().window(originalHandle);
					System.out.println(curtidasTotais + ", quantidade perfis:" + perfisTotais);
				}
			}
			perfisTotais += numPessoas;
		} catch (Exception e) {
			System.out.println(e);
			// mensagens.append(System.lineSeparator() + e.getMessage());
		} finally {
			// if (!mensagens.toString().isEmpty())
			// Email.enviarEmail(mensagens.toString());
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
			// mensagens.append(System.lineSeparator() + e.getMessage());
		}
	}

	public static List<WebElement> scroll(WebDriver driver) {
		List<WebElement> pessoasScroll = null;
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			URL jqueryUrl = Resources.getResource("jquery.min.js");
			String jqueryText = Resources.toString(jqueryUrl, Charsets.UTF_8);
			js.executeScript(jqueryText);
			Thread.sleep(1500);
			pessoasScroll = driver.findElements(By.cssSelector("._7UhW9.xLCgt.MMzan.KV-D4.fDxYl a"));
		} catch (Exception e) {
			System.out.println(e);
			// mensagens.append(System.lineSeparator() + e.getMessage());
		}
		if (pessoasRepetidas.size() != 0) {
			pessoasScroll = removePessoasRepetidas(pessoasRepetidas, pessoasScroll);
		}
		pessoasRepetidas.addAll(pessoasScroll);
		return pessoasScroll;
	}

	private static List<WebElement> removePessoasRepetidas(Set<WebElement> pessoasRepetidas2, List<WebElement> pessoasScroll) {
		List<WebElement> novasPessoas = new ArrayList<WebElement>();
		for (WebElement pessoa : pessoasScroll) {
			if(!pessoasRepetidas2.contains(pessoa)) {
				novasPessoas.add(pessoa);
			}
		}
		return novasPessoas;
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
			driver.findElement(By.cssSelector(".v1Nh3.kIKUG._bz0w"));
			return false;
		} catch (Exception e) {
			return true;
		}
	}
}