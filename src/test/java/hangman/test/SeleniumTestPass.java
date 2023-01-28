package hangman.test;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class SeleniumTestPass {

	@Test
	void seleniumTestPass() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Desktop\\Selenium\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/HangmanGame/login");

		WebElement username = driver.findElement(By.id("username"));

		WebElement password = driver.findElement(By.id("password"));

		WebElement submitlogin = driver.findElement(By.cssSelector("input[type='submit']"));

		username.sendKeys("Kaan");

		password.sendKeys("123456");
		Thread.sleep(300);

		submitlogin.click();
		Thread.sleep(300);

		WebElement difficulty = driver.findElement(By.cssSelector("input[id='hard']"));

		difficulty.click();
		Thread.sleep(300);

		WebElement submit = driver.findElement(By.cssSelector("input[type='submit']"));

		submit.click();
		Thread.sleep(300);

		String hiddenword = driver.findElement(By.id("hiddenword")).getText();
		System.out.println(hiddenword);
//done
		if (hiddenword.equals("N*n*_******_****n_**x")) {
			// i, e, t, a, l, d, m, o, f
			WebElement me = driver.findElement(By.id("i"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("e"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("t"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("a"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("l"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("d"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("m"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("o"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("f"));
			me.click();
			Thread.sleep(500);

			List<WebElement> l = driver.findElements(By.xpath("//*[contains(text(),'You win!')]"));

		}
//done
		if (hiddenword.equals("A*s***_***a**_**s*****s")) {
			// b, u, r, d, w, i, z, m, y, t, f, e
			WebElement me = driver.findElement(By.id("b"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("u"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("r"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("d"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("w"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("i"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("z"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("m"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("y"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("t"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("f"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("e"));
			me.click();
			Thread.sleep(500);

			List<WebElement> l = driver.findElements(By.xpath("//*[contains(text(),'You win!')]"));
		}
//done
		if (hiddenword.equals("J**j****_*******_****n")) {
			// u, i, t, s, m, a, e,r
			WebElement me = driver.findElement(By.id("u"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("i"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("t"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("s"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("m"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("a"));
			me.click();

			me = driver.findElement(By.id("e"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("r"));
			me.click();
			Thread.sleep(500);

			List<WebElement> l = driver.findElements(By.xpath("//*[contains(text(),'You win!')]"));

		}
//done
		if (hiddenword.equals("Voo*oo")) {
			WebElement me = driver.findElement(By.id("d"));
			me.click();
			Thread.sleep(500);

			List<WebElement> l = driver.findElements(By.xpath("//*[contains(text(),'You win!')]"));
		}
//done
		if (hiddenword.equals("A***a**_*l****_*******ll")) {
			// w, k, r, d, u, t, z, y, n, m, b
			WebElement me = driver.findElement(By.id("w"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("k"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("r"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("d"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("u"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("t"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("z"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("y"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("n"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("m"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("b"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("s"));
			me.click();
			Thread.sleep(500);

			List<WebElement> l = driver.findElements(By.xpath("//*[contains(text(),'You win!')]"));
		}
//done
		if (hiddenword.equals("A_*****_****_s***s")) {
			// q, u, i, e, t, j, n, x, l, k
			WebElement me = driver.findElement(By.id("q"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("u"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("i"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("e"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("t"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("j"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("n"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("x"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("l"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("k"));
			me.click();
			Thread.sleep(500);

			List<WebElement> l = driver.findElements(By.xpath("//*[contains(text(),'You win!')]"));
		}
		// done
		if (hiddenword.equals("T*****_*****_***t")) {
			// w, e, l, v, f, o, x, s, h, u, n
			WebElement me = driver.findElement(By.id("w"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("e"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("l"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("v"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("f"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("o"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("x"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("s"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("h"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("u"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("n"));
			me.click();
			Thread.sleep(500);

			List<WebElement> l = driver.findElements(By.xpath("//*[contains(text(),'You win!')]"));
		}
//done
		if (hiddenword.equals("T*******_******_******s")) {
			// h, r, o, w, i, n, g, a, l, y, p, u, c, e
			WebElement me = driver.findElement(By.id("h"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("r"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("o"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("w"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("i"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("n"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("g"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("a"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("l"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("y"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("p"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("u"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("c"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("e"));
			me.click();
			Thread.sleep(500);

			List<WebElement> l = driver.findElements(By.xpath("//*[contains(text(),'You win!')]"));
		}
//done
		if (hiddenword.equals("B******_*r****_***_b*******r")) {
			// u, z, i, n, g, a, o, d, t, h, e, k, p
			WebElement me = driver.findElement(By.id("u"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("z"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("i"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("n"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("g"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("a"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("o"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("d"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("t"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("h"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("e"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("k"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("p"));
			me.click();
			Thread.sleep(500);

			List<WebElement> l = driver.findElements(By.xpath("//*[contains(text(),'You win!')]"));

		}
//done
		if (hiddenword.equals("C******_******s_***_****s")) {
			// r, o, q, u, e, t, p, l, a, y, f, i, x, g, m
			WebElement me = driver.findElement(By.id("r"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("o"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("q"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("u"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("e"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("t"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("p"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("l"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("a"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("y"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("f"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("i"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("x"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("g"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("m"));
			me.click();
			Thread.sleep(500);

			List<WebElement> l = driver.findElements(By.xpath("//*[contains(text(),'You win!')]"));

		}
//done
		if (hiddenword.equals("J***_s*****_****s_****s")) {
			// a, z, i, n, g, e, r, d, o, p, b, t
			WebElement me = driver.findElement(By.id("a"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("z"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("i"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("n"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("g"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("e"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("r"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("d"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("o"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("p"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("b"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("t"));
			me.click();
			Thread.sleep(500);

			List<WebElement> l = driver.findElements(By.xpath("//*[contains(text(),'You win!')]"));

		}
//done
		if (hiddenword.equals("W****_****_*****s")) {
			// i, m, p, y, g, e, k, a, n
			WebElement me = driver.findElement(By.id("i"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("m"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("p"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("y"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("g"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("e"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("k"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("a"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("n"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("c"));
			me.click();
			Thread.sleep(500);

			List<WebElement> l = driver.findElements(By.xpath("//*[contains(text(),'You win!')]"));
		}
//done
		if (hiddenword.equals("T**_***at__*a**_**_****a")) {
			// h, e, g, r, w, l, o, f, c, i, n
			WebElement me = driver.findElement(By.id("h"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("e"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("g"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("r"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("w"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("l"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("o"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("f"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("c"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("i"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("n"));
			me.click();
			Thread.sleep(500);

			List<WebElement> l = driver.findElements(By.xpath("//*[contains(text(),'You win!')]"));

		}
//done
		if (hiddenword.equals("S***_**_s**_****s")) {
			// a, g, e, o, f, i, x, p, t, h
			WebElement me = driver.findElement(By.id("a"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("g"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("e"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("o"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("f"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("i"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("x"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("p"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("t"));
			me.click();
			Thread.sleep(500);

			me = driver.findElement(By.id("h"));
			me.click();
			Thread.sleep(500);

			List<WebElement> l = driver.findElements(By.xpath("//*[contains(text(),'You win!')]"));

		}
	}
}
