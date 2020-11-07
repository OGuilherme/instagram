package demo;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ListaTelefonica {
	
	private static Set<String> contatos = new HashSet<String>();
	private static List<String> contatosList = new ArrayList<String>();
	private static String pathTxt = "C:\\Users\\olive\\Desktop\\lista.txt";
	private static String pathTxtLimpo = "C:\\Users\\olive\\Desktop\\listaLimpa.txt";
	private static String patchDriver = "C:\\Users\\olive\\Downloads\\chromedriver_win32\\chromedriver.exe";
	private static String url = "https://api.whatsapp.com/send?phone=numeroReplace&text=Bom%20dia,%20Aposentados%20e%20Pensionistas%20do%20INSS,%205%%20a%20mais%20de%20margem%20pra%20crédito%20consignado%20no%20valor%20de%20até%202%20vezes%20o%20valor%20do%20seu%20beneficio%20conte%20conosco";
	private static String urlWhatsappWeb = "https://web.whatsapp.com/";

	public static void main(String[] args) throws IOException {
		File myObj = new File(pathTxtLimpo);
		String erroNumber = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(myObj));
			String[] st = br.readLine().split(",");
			contatosList = Arrays.asList(st);
			Runtime rt = Runtime.getRuntime();
			for (String contato : contatosList) {
				erroNumber = contato;
				String newUrl = url.replace("numeroReplace", "55"+contato);
				rt.exec("rundll32 url.dll,FileProtocolHandler " + newUrl);
				Thread.sleep(1500);
				Robot robot = new Robot();
				//robot.mouseMove(1000, 220);
				//robot.mousePress(InputEvent.BUTTON1_MASK);
				//robot.mouseRelease(InputEvent.BUTTON1_MASK);
				Thread.sleep(1000);
				robot.mouseMove(1000, 900);
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
				Thread.sleep(1000);
				robot.mouseMove(470, 15);
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
				Thread.sleep(500);
			}	
		}catch(Exception e) {
			System.out.println(erroNumber+"  "+ e);
		}
//			WebDriver driver = null;
//			try {
//				System.setProperty("webdriver.chrome.driver", patchDriver);
//				ChromeOptions options= new ChromeOptions();
//				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//				driver = new ChromeDriver(capabilities);
//				driver.get(urlWhatsappWeb);
//				JavascriptExecutor js = (JavascriptExecutor) driver;
//				URL jqueryUrl = Resources.getResource("jquery.min.js");
//				String jqueryText = Resources.toString(jqueryUrl, Charsets.UTF_8);
//				js.executeScript(jqueryText);
//				for (String contato : contatosList) {
//					String newUrl = url.replace("numeroReplace", "55"+contato);
//					driver.get(newUrl);
//					Thread.sleep(500);
//					List<WebElement> button = driver.findElements(By.cssSelector("._36or._2y_c._2z0c._2z07"));
//					button.get(0).click();
//					Thread.sleep(500);
//				}				
//				System.out.println("teste");
//			} catch (Exception e) {
//				System.out.println("Erro em algun dos passos que finalizou o processo/n"+e.getMessage());
//			} finally {
//				driver.quit();
//			}
//			
//		}catch(Exception e) {
//			System.out.println(e);
//		}
	}
	
	private void limpaLista() {
		File myObj = new File(pathTxt);
		File myObj2 = new File(pathTxtLimpo);
		try {
			BufferedReader br = new BufferedReader(new FileReader(myObj));
			String st;
			while ((st = br.readLine()) != null) {
				contatos.add(st);
		    }
			if (myObj2.exists()) {
				try {
					FileWriter myWriter = new FileWriter(pathTxtLimpo);
					for (String user : contatos) {
						myWriter.write(user);
					}
					myWriter.close();
				} catch (Exception e) {
					System.out.println("Erro ao buscar lista com usuarios./n" + e.getMessage());
				}
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
