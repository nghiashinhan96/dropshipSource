package selly.service;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.*;
import java.util.Optional;

public class Utilities {
    public static void sleep(int sleepTime){
        try{
            Thread.sleep(sleepTime);
        } catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Error when sleeps");
        }
    }

    public static void moveFolder(String folderName){
        File src = new File("D://sellyDownload");
        File dest = new File("D://selly//"+folderName+"");

        try {
            if(!dest.exists()){
                FileUtils.moveDirectory(src, dest);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createNewFolder(String pathStr){
        File path  = new File(pathStr);
        if(!path.exists()){
                 path.mkdirs();

        }

    }

    public static  void createFileAndInsert(String content, String fileName){
        BufferedWriter buffredWrite = null;
        try{

            File newFile = new File(fileName);
            if(!newFile.exists()){
                newFile.createNewFile();
            }
            FileWriter writeFile = new FileWriter(newFile);
            buffredWrite = new BufferedWriter(writeFile);
            System.out.println("content"+content);
            buffredWrite.write(content);
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("error create file: "+ex);
        }
        finally
        {
            try{
                if(buffredWrite!=null)
                    buffredWrite.close();
            }catch(Exception ex){
                System.out.println("Error in closing the BufferedWriter"+ex);
            }
        }
    }

    public static void main(String[] args) {
        createNewFolder("D://selly//sellDownload");
    }

    public static void gotoTopPage(WebDriver driver){
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight, 0)");
    }

    public static void gotToEndPage(WebDriver driver){
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    public static void clichByJS(WebDriver driver, WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
}
