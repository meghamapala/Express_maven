package com.bit.test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;

import com.google.common.io.Files;

import sun.util.calendar.BaseCalendar.Date;

public class SeleniumMethod {
	
	WebDriver dr;
	Actions a;
	//String value;
	
	public void multipleWindowHandle() {
		String pwin=dr.getWindowHandle();
		Actions a=new Actions(dr);
		a.contextClick(null).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER);
		Set<String> cwin=dr.getWindowHandles();
		for(String win:cwin) {
			if(!win.equals(pwin)) {
				dr.switchTo().window(win);
			}
		}
	}
	
	//I Frame
	public void multipleFrame(String value) {
		dr.switchTo().frame(0);
		
		//drag and drop
		a.dragAndDropBy(null, 10, 200).build().perform();
		
		//drag and drop all the iframes
		List<WebElement> elements=dr.findElements(By.tagName("iframe"));
		for(WebElement ele:elements) {
			//String value = null;
			if(ele.getAttribute("class").equals(value)){
				dr.switchTo().frame(value);
				
			}
		}
		
	}
	
	public void screenshot()throws IOException {
		   int r=(int) Math.random();
		   Date d=new Date();
		  // d.addDate(r, r, r);
		   SimpleDateFormat s=new SimpleDateFormat("mm/dd");
	       File f=	((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
	       Files.copy(f, new File("Path of the File"+r+".png"));
	}
	
	public void scrolldown(WebElement ele) {
		Coordinates c=((Locatable)ele).getCoordinates();//Coordinates (interface)
		c.inViewPort();
		
		JavascriptExecutor j=(JavascriptExecutor)dr;//javascriptExecutor is interface 
		j.executeScript("window.scrollBy(0,1000");
		j.executeScript("arguments[0].click", ele);
		j.executeScript("document.getelementById('value')");
		
	}
	
	public void cookiehandle(String value) {
		Set<Cookie> cookies=dr.manage().getCookies();
		cookies.clear();
		
		for(Cookie cookie:cookies) {
			if(cookie.getValue().equals(value)) {
				System.out.println("matched");
			}else {
				System.out.println("not matched");
			}
		}
		String arr[]= null;
		int i=0;
		for(Cookie cookie:cookies){
		String name=cookie.getName();
		arr[i]=name;
	}
	}
	
	public void alertHandle() {
		Alert a=dr.switchTo().alert();
		a.accept();
		a.dismiss();
		a.getText();
		a.sendKeys("gjh");
		a.accept();
		a.notify();
	}
}
