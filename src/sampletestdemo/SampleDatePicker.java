package sampletestdemo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class SampleDatePicker {

	String url = " https://jqueryui.com/datepicker/";
	String id = "//*[@id=\"datepicker\"]";
	List<String> Month = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August",
			"September", "October", "November", "December");
	int expMonth;
	int expYear;
	String expDate = null;
	String reqMonth = null;
	String reqYear = null;
	boolean isDateFound;
	String date = "September/02/2018";
	String datePattern = "MMM/dd/yyyy";

	@Test
	public void launchPage() throws InterruptedException, ParseException {
		parseDate(date, datePattern);
		WebDriver driver = launchBrowser(url);
		//driver.switchTo().frame(0);
		driver.findElement(By.xpath(id)).click();

		isDateFound = true;

		while (isDateFound) {
			reqMonth = driver.findElement(By.className("ui-datepicker-month")).getText();

			reqYear = driver.findElement(By.className("ui-datepicker-year")).getText();

			if (Month.indexOf(reqMonth) + 1 == expMonth && (expYear == Integer.parseInt(reqYear))) {
				selectDate(driver, expDate);
				isDateFound = false;
			} else if (Month.indexOf(reqMonth) + 1 < expMonth && (expYear == Integer.parseInt(reqYear))
					|| expYear > Integer.parseInt(reqYear)) {
				driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[2]/span")).click();
			} else if (Month.indexOf(reqMonth) + 1 > expMonth && (expYear == Integer.parseInt(reqYear))
					|| expYear < Integer.parseInt(reqYear)) {
				driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[1]/span")).click();
			}
		}
	}

	public void selectDate(WebDriver driver, String str) {
		List<WebElement> value;
		WebElement datepicker;
		try {
			datepicker = driver.findElement(By.id("ui-datepicker-div"));
			value = datepicker.findElements(By.tagName("td"));
			for (WebElement cell : value) {
				if (cell.getText().equalsIgnoreCase(str)) {
					cell.findElement(By.linkText(str)).click();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void parseDate(String Date, String dateFormat) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

		sdf.setLenient(false);
		Date d1 = sdf.parse(Date);
		String strDate = sdf.format(d1);
		String[] w = strDate.split("/");
		expDate = w[1];
		expYear = Integer.parseInt(w[2]);
		System.out.println(expDate);
		Integer i = Integer.parseInt(expDate);
		Integer.toString(i);
		System.out.println(i);
		switch (w[0]) {
		case "Jan":
		case "January":
		case "01":
			expMonth = 1;
			break;
		case "Feb":
		case "February":
		case "02":
			expMonth = 2;
			break;
		case "Mar":
		case "March":
		case "03":
			expMonth = 3;
			break;
		case "Apr":
		case "April":
		case "04":
			expMonth = 4;
			break;
		case "May":
		case "05":
			expMonth = 5;
			break;
		case "Jun":
		case "June":
		case "06":
			expMonth = 6;
			break;
		case "Jul":
		case "July":
		case "07":
			expMonth = 7;
			break;
		case "Aug":
		case "August":
		case "08":
			expMonth = 8;
			break;
		case "Sep":
		case "September":
		case "09":
			expMonth = 9;
			break;
		case "Oct":
		case "October":
		case "10":
			expMonth = 10;
			break;
		case "Nov":
		case "November":
		case "11":
			expMonth = 11;
			break;
		case "Dec":
		case "December":
		case "12":
			expMonth = 12;
			break;
		}
	}

	public WebDriver launchBrowser(String url) {
		WebDriver driver = null;
		try {
			driver = new FirefoxDriver();
			driver.get(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}
}