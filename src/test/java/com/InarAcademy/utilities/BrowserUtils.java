package com.InarAcademy.utilities;

import com.beust.ah.A;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

public class BrowserUtils {
    public static String getScreenshot(String name)  {
        name=new Date().toString().replace(" ","_").replace(":","-")+"_"+name;
        String path;
        if(System.getProperty("os.name").toLowerCase().contains("mac")){
            path=System.getProperty("user.dir")+"/test-output/screenshots/"+name+".png";
        }else{
            path=System.getProperty("user.dir")+"\\test-outpur\\screenshots\\"+name+".png";
        }
        TakesScreenshot screenshot=(TakesScreenshot)Driver.getDriver();
        File source=screenshot.getScreenshotAs(OutputType.FILE);
        File destination=new File(path);
        try {
            FileUtils.copyFile(source,destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
    public static void verifyElementDisplayed(WebElement element,String message){
        try {
            Assert.assertTrue(message,element.isDisplayed());

        }catch (NoSuchElementException e){
            Assert.fail(message);

        }

    }
    public static void doubleClick(WebElement element){
        new Actions(Driver.getDriver()).doubleClick(element).build().perform();

    }
    public static void dragAndDrop(WebElement source,WebElement target){
        Actions actions=new Actions(Driver.getDriver());
        actions.dragAndDrop(source,target).build().perform();
    }
    public static void hoverOver(WebElement element){
        Actions actions=new Actions(Driver.getDriver());
        actions.moveToElement(element).build().perform();

    }
    public static void moveToElement(WebElement element){
        Actions actions=new Actions(Driver.getDriver());
        actions.moveToElement(element).build().perform();
    }
    public static void wait(int sec){
        try {
            Thread.sleep(1000*sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void waitSeconds(int seconds) throws InterruptedException {
        long millis=seconds*1000L;
        long startTime=System.currentTimeMillis();
        long remainingTime=millis;
        while (remainingTime>0){
            try {
                Thread.sleep(remainingTime);
            } catch (InterruptedException e) {
                long elapsedTime=System.currentTimeMillis()-startTime;
                remainingTime=millis-elapsedTime;
                Thread.currentThread().interrupt();
                throw e;
            }
            remainingTime=millis-(System.currentTimeMillis()-startTime);
        }
    }
    public static void navigateToWindow(String targetTitle){
        String currentWindow=Driver.getDriver().getWindowHandle();
        for(String handle:Driver.getDriver().getWindowHandles()){
            Driver.getDriver().switchTo().window(handle);
            if(Driver.getDriver().getTitle().equals(targetTitle)){
                return;
            }
        }
        Driver.getDriver().switchTo().window(currentWindow);
    }
    public static WebElement waitForVisibility(WebElement element,int timeToWaitInSec){
        WebDriverWait wait=new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeToWaitInSec));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    public static WebElement waitForVisibility(By locator, int timeToWaitInSec ){
        WebDriverWait wait=new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeToWaitInSec));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public static WebElement waitForClickability(By locator,int timeToWaitInSec){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeToWaitInSec));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public static WebElement waitForClickable(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeToWaitInSec));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public void switchToFrame(String frameNameOrId){
        Driver.getDriver().switchTo().frame(frameNameOrId);

    }
    public static void clickDynamicElementStartWith(String searchText){
        String xpath="//*[starts-with(text(),'"+searchText+"')]";
        WebElement element=Driver.getDriver().findElement(By.xpath(xpath));
        element.click();
    }
    public static void clickDynamicElementsByAttribute(String attributeName,String attributeValue){
        String xpath="//*[@"+attributeName+"='"+attributeValue+"']";
        WebElement element=Driver.getDriver().findElement(By.xpath(xpath));
        element.click();
    }
    public static String handleAlertAcceptByReturningMessage(){
        Alert alert=Driver.getDriver().switchTo().alert();
        String text=alert.getText();
        alert.accept();
        return text;
    }
    public static void handleAlertAccept() {
        Alert alert = Driver.getDriver().switchTo().alert();
        alert.accept();
    }
    public static String handleAlertDismissByReturningMessage() {
        Alert alert = Driver.getDriver().switchTo().alert();
        String text = alert.getText();
        alert.dismiss();
        return text;
    }
    public  static void handleAlertDismiss() {
        Alert alert = Driver.getDriver().switchTo().alert();
        alert.dismiss();
    }
    public static void switchToTab(String title){
        WebDriver driver=Driver.getDriver();
        for(String windowHandle:driver.getWindowHandles()){
            driver.switchTo().window(windowHandle);
            if(driver.getTitle().equals(title)){
                break;
            }
        }
    }
}
