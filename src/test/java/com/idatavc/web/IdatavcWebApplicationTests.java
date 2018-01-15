package com.idatavc.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IdatavcWebApplicationTests {

	@Test
	public void contextLoads() {
		System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");

		try {
			WebDriver driver = new ChromeDriver();
			driver.get("http://www.google.com/xhtml");
			Thread.sleep(5000);  // Let the user actually see something!
			WebElement searchBox = driver.findElement(By.name("q"));
			searchBox.sendKeys("ChromeDriver");
			searchBox.submit();
			Thread.sleep(5000);  // Let the user actually see something!
			driver.quit();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
