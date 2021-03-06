package demo;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
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
	private final static String urlInstaBase = "https://www.instagram.com/";
	private static int curtidasTotais = 0;
	private static int perfisTotais = 0;
	private static StringBuilder mensagens = new StringBuilder();
	private static Set<WebElement> pessoasRepetidas = new HashSet<WebElement>();

	public static void logar(WebDriver driver, String usuario, String senha) {
		Boolean isValid = false;
		driver.get(urlInsta);
		try {
			Thread.sleep(20000);
			//List<WebElement> input = null;
			//input = driver.findElements(By.cssSelector("._2hvTZ.pexuQ.zyHYP"));
			//input.get(0).sendKeys(usuario);
			//input.get(1).sendKeys(senha);
			//WebElement button = driver.findElement(By.cssSelector(".sqdOP.L3NKy.y3zKF"));
			//button.sendKeys(Keys.ENTER);
			while (!isValid) {
				if (!urlInsta.equalsIgnoreCase(driver.getCurrentUrl())) {
					isValid = true;
				}
			}
			Thread.sleep(20000);
			System.out.println("Logou com sucesso");
		} catch (Exception e) {
			System.out.println("Erro ao tentar logar./n" + e.getMessage());
		}
	}

	public static Integer curtidores(WebDriver driver, String urlFoto, int totalCurtidas) {
		curtidasTotais = totalCurtidas;
		driver.get(urlFoto);
		try {
			verSeguidores(driver);
			Set<String> users = getAllNamesPicture(driver);
			perfisTotais += users.size();
			for (String user : users) {
				if (curtidasTotais > 1500) {
					throw new Exception();
				}
				try {
					driver.get(urlInstaBase+user);
					Thread.sleep(1000);
					curtirFoto(driver);
				} catch (Exception e) {
					System.out.println("erro ao curtir" + e);
				} finally {
					System.out.println(curtidasTotais + ", quantidade perfis:" + perfisTotais);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("curtidas: " + curtidasTotais + " e perfis: " + perfisTotais);
		}
		return curtidasTotais;
	}
	
	public static Set<String> getAllNamesPicture(WebDriver driver) {
		Set<String> users = new HashSet<String>();
		Integer acabouUsers = 0;
		Integer numUsers = 0;
		verSeguidores(driver);
		try {
			Thread.sleep(1500);

			JavascriptExecutor js = (JavascriptExecutor) driver;

			URL jqueryUrl = Resources.getResource("jquery.min.js");
			String jqueryText = Resources.toString(jqueryUrl, Charsets.UTF_8);
			js.executeScript(jqueryText);
			for (int index = 0; index < 1010; index++) {
				if(acabouUsers >=2)
					break;
				js.executeScript("$('.FPmhX.notranslate.MBL3Z').focus()");

				Thread.sleep(2000);
				List<WebElement> test = driver.findElements(By.cssSelector(".FPmhX.notranslate.MBL3Z"));
				for (WebElement webElement : test) {
					users.add(webElement.getAttribute("title"));
				}
				if(numUsers == users.size()) {
					acabouUsers++;
				}
				numUsers = users.size();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return users;
	}

	public static Integer curtidores(String urlFoto, WebDriver driver, int totalCurtidas) {
		curtidasTotais = totalCurtidas;
		driver.get(urlFoto);
		try {
			Actions actions = new Actions(driver);
			String originalHandle = driver.getWindowHandle();
			verSeguidores(driver);
			List<WebElement> pessoas = driver.findElements(By.cssSelector(".FPmhX.notranslate.MBL3Z"));
			int numPessoas = pessoas.size() - 1;
			for(int i = 0; i < 500;i++) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				URL jqueryUrl = Resources.getResource("jquery.min.js");
				String jqueryText = Resources.toString(jqueryUrl, Charsets.UTF_8);
				js.executeScript(jqueryText);
				js.executeScript("$('.FPmhX.notranslate.MBL3Z')["+ i*10 +"].focus();");
				actions.moveToElement(pessoas.get(pessoas.size())).perform();
				pessoas = driver.findElements(By.cssSelector(".FPmhX.notranslate.MBL3Z"));
				numPessoas = pessoas.size() - 1;
				curtir(driver, actions, originalHandle, numPessoas, pessoas);
			}
			perfisTotais += numPessoas;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("curtidas: " + curtidasTotais + " e perfis: " + perfisTotais);
		}
		return curtidasTotais;
	}

	

	private static void curtir(WebDriver driver, Actions actions, String originalHandle, int numPessoas, List<WebElement> pessoas) throws Exception {
		for (int i = 0; i <= numPessoas; i++) {
			if (curtidasTotais > 1500) {
				throw new Exception();
			}
			try {
				actions.moveToElement(pessoas.get(i)).perform();
				String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
				pessoas.get(i).sendKeys(selectLinkOpeninNewTab);
				Thread.sleep(1000);
				for (String handle : driver.getWindowHandles()) {
					if (!handle.equals(originalHandle)) {
						driver.switchTo().window(handle);
						curtirFoto(driver);
					}
				}
			} catch (Exception e) {
				System.out.println("erro ao curtir" + e);
			} finally {
				for (String handle : driver.getWindowHandles()) {
					if(!handle.equals(originalHandle))
						driver.close();
				}
				driver.switchTo().window(originalHandle);
				System.out.println(curtidasTotais + ", quantidade perfis:" + perfisTotais);
			}
		}
	}

	public static void scrollSeguidores(WebDriver driver) {
		try {
			Thread.sleep(1500);
			List<WebElement> pessoas = driver.findElements(By.cssSelector(".FPmhX.notranslate.MBL3Z"));
			int numPessoas = pessoas.size()-1;
			JavascriptExecutor js = (JavascriptExecutor) driver;
			URL jqueryUrl = Resources.getResource("jquery.min.js");
			String jqueryText = Resources.toString(jqueryUrl, Charsets.UTF_8);
			js.executeScript(jqueryText);
			for (int i = 0; i < 3; i++) {
				Thread.sleep(1500);
				js.executeScript("$('.FPmhX.notranslate.MBL3Z')["+ numPessoas+"].focus();");
				pessoas = driver.findElements(By.cssSelector(".FPmhX.notranslate.MBL3Z"));
				if (pessoas.size() == numPessoas)
					break;
				numPessoas = pessoas.size()-1;
			}
		} catch (Exception e) {
			System.out.println(e);
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
		}
		if (pessoasRepetidas.size() != 0) {
			pessoasScroll = removePessoasRepetidas(pessoasRepetidas, pessoasScroll);
		}
		pessoasRepetidas.addAll(pessoasScroll);
		return pessoasScroll;
	}

	private static List<WebElement> removePessoasRepetidas(Set<WebElement> pessoasRepetidas2,
			List<WebElement> pessoasScroll) {
		List<WebElement> novasPessoas = new ArrayList<WebElement>();
		for (WebElement pessoa : pessoasScroll) {
			if (!pessoasRepetidas2.contains(pessoa)) {
				novasPessoas.add(pessoa);
			}
		}
		return novasPessoas;
	}

	public static void verSeguidores(WebDriver driver) {
		try {
			Thread.sleep(1500);
			List<WebElement> menus = driver.findElements(By.cssSelector(".Nm9Fw .sqdOP.yWX7d._8A5w5"));
			if (menus.size() > 0) {
				menus.get(0).click();
			}
		} catch (Exception e) {
			mensagens.append(System.lineSeparator() + e.getMessage());
		}
	}

	public static Boolean possivelCurtir(WebDriver driver) {
		try {
			driver.findElement(By.className("FY9nT"));
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

	public static Set<String> getAllNames(WebDriver driver) {
		Set<String> users = new HashSet<String>();
		driver.get("https://www.instagram.com/gus2rodas/");
		try {
			Thread.sleep(1500);
			verSeguidores(driver);
			Thread.sleep(1500);

			JavascriptExecutor js = (JavascriptExecutor) driver;

			URL jqueryUrl = Resources.getResource("jquery.min.js");
			String jqueryText = Resources.toString(jqueryUrl, Charsets.UTF_8);
			js.executeScript(jqueryText);
			for (int index = 0; index < 1010; index++) {
				js.executeScript("$('.FPmhX.notranslate._0imsa').focus()");

				Thread.sleep(5000);
				List<WebElement> test = driver.findElements(By.cssSelector(".FPmhX.notranslate._0imsa"));
				System.out.println(index + " - " + test.size());
			}
			List<WebElement> pessoas = driver.findElements(By.cssSelector(".FPmhX.notranslate._0imsa"));
			for (WebElement pessoa : pessoas) {
				users.add(pessoa.getAttribute("text"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return users;
	}

	public static void commentPost(WebDriver driver, List<String> usersToComment, String urlPost, int numUsers)
			throws IOException {
		int jaMarcados = 0;
		int comentariosFeitos = 0;
		Random rand = new Random(); //instance of random class
	    int upperbound = 1000000;

		driver.get(urlPost);
		WebElement textArea = null;
		List<WebElement> button = null;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		URL jqueryUrl = Resources.getResource("jquery.min.js");
		String jqueryText = Resources.toString(jqueryUrl, Charsets.UTF_8);
		js.executeScript(jqueryText);
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		System.out.println("Come�ou a comentar");
		end.add(Calendar.MINUTE, 10);
		for (String user : usersToComment) {
			try {
				Thread.sleep(9000/numUsers);
				textArea = driver.findElement(By.className("Ypffh"));
				Thread.sleep(3000/numUsers);
				textArea.sendKeys(user);
				Thread.sleep(6000/numUsers);
				js.executeScript("$('.KAWZr').click()");
				jaMarcados++;
				if (jaMarcados == numUsers) {
					textArea = driver.findElement(By.className("Ypffh"));
					textArea.sendKeys(" "+rand.nextInt(upperbound));
					button = driver.findElements(By.cssSelector(".sqdOP.yWX7d.y3zKF"));
					Thread.sleep(15000/numUsers);
					button.get(button.size()-1).sendKeys(Keys.ENTER);
					System.out.println("Comentou "+comentariosFeitos+" vezes");
					Thread.sleep(5000/numUsers);
					jaMarcados = 0;
					driver.get(urlPost);
					Thread.sleep(3000/numUsers);
					js = (JavascriptExecutor) driver;
					jqueryUrl = Resources.getResource("jquery.min.js");
					jqueryText = Resources.toString(jqueryUrl, Charsets.UTF_8);
					js.executeScript(jqueryText);
					start = Calendar.getInstance();
					if(start.after(end)) {
						System.out.println("Come�ou a pausa de 10 minutos");
						Thread.sleep(120000);
						start = Calendar.getInstance();
						end = Calendar.getInstance();
						end.add(Calendar.MINUTE, 10);
						System.out.println("Terminou a pausa de 10 minutos");
					}
				}
			} catch (Exception e) {
				System.out.println("Aconteceu um erro enquanto o tentava comentar./n"+e);
			}
		}

	}

	public static void seguir(WebDriver driver, List<String> users) {
		Integer seguindo = 0;
		try {
			for (String user : users) {
				if (seguindo > 1500) {
					throw new Exception();
				}
				try {
					driver.get(urlInstaBase+user);
					Thread.sleep(10000);
					driver.findElement(By.cssSelector(".sqdOP.L3NKy.y3zKF")).click();
					//driver.findElement(By.cssSelector("._5f5mN.jIbKX._6VtSN.yZn4P")).click();
					seguindo++;
					Thread.sleep(4000);
					curtirFoto(driver);
				} catch (Exception e) {
					try {
						driver.findElement(By.cssSelector("._5f5mN.jIbKX._6VtSN.yZn4P")).click();
						seguindo++;
						Thread.sleep(4000);
						curtirFoto(driver);
					} catch (Exception A) {
						System.out.println("erro ao curtir" + A);
					}
				} finally {
					System.out.println(seguindo);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("curtidas: " + curtidasTotais + " e perfis: " + perfisTotais);
		}
	}

	private static void curtirFoto(WebDriver driver) throws InterruptedException {
		if (!perfilPublico(driver)) {
			driver.findElement(By.cssSelector(".v1Nh3.kIKUG._bz0w a")).click();
			Thread.sleep(3000);
			if (possivelCurtir(driver)) {
				driver.findElement(By.cssSelector(
						".ltpMr.Slqrh .QBdPU"))
						.click();
				curtidasTotais++;
				Thread.sleep(1000);
			}
		}
	}

	public static void unfollow(WebDriver driver, List<String> users) {
		String userAtual = "";
		try {
			for (String user : users) {
				try {
					driver.get(urlInstaBase+user);
					userAtual = user;
					Thread.sleep(1000);
					List<WebElement> input = driver.findElements(By.cssSelector(".sqdOP.L3NKy._8A5w5"));
					if(input.size() ==1) {
						input.get(0).click();
					}else {
						input.get(1).click();
					}
					Thread.sleep(2000);
					driver.findElement(By.cssSelector(".aOOlW.-Cab_")).click();
					Thread.sleep(2000);
				} catch (Exception e) {
					System.out.println("erro no usuario "+ userAtual +": " + e);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("processo Finalizado");
		}
		
	}
}