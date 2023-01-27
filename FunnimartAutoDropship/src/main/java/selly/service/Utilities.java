package selly.service;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class Utilities {
    public static void sleep(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error when sleeps");
        }
    }

    public static void moveFolder(String folderName) {
        File src = new File("D://funniMartDownload");
        File dest = new File("D://funniMart//" + folderName + "");

        try {
            if (!dest.exists()) {
                FileUtils.moveDirectory(src, dest);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createNewFolder(String pathStr) {
        File path = new File(pathStr);
        if (!path.exists()) {
            path.mkdirs();

        }

    }

    public static void createFileAndInsert(String content, String fileName) {
        BufferedWriter buffredWrite = null;
        try {

            File newFile = new File(fileName);
            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            FileWriter writeFile = new FileWriter(newFile);
            buffredWrite = new BufferedWriter(writeFile);
            System.out.println("content" + content);
            buffredWrite.write(content);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("error create file: " + ex);
        } finally {
            try {
                if (buffredWrite != null)
                    buffredWrite.close();
            } catch (Exception ex) {
                System.out.println("Error in closing the BufferedWriter" + ex);
            }
        }
    }

    public static void gotoTopPage(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight, 0)");
    }

    public static void gotToEndPage(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    public static void clichByJS(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public static Path zipSlipProtect(ZipEntry zipEntry, Path targetDir) throws IOException {
        Path targetDirResolved = targetDir.resolve(zipEntry.getName());
        Path normalizePath = targetDirResolved.normalize();
        if (!normalizePath.startsWith(targetDir)) {
            throw new IOException("Bad zip entry: " + zipEntry.getName());
        }

        return normalizePath;
    }

    public static void main(String[] args) {
        String source = "D:/funniMart/Áo sat nách nam dáng thể thao thoáng mát ASN001 - UC-D9F4-275/funi-ao-sat-nach-nam-dang-the-thao-thoang-mat-asn001.zip";
        String targetDir = "D:/funniMart/ao-khoac";
        try {
            unzipFolder(source, targetDir);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    private static void unzip(String zipFilePath, String destDir) {
        File dir = new File(destDir);
        // create output directory if it doesn't exist
        if (!dir.exists())
            dir.mkdirs();
        FileInputStream fis;
        //buffer for read and write data to file
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while (ze != null) {
                //String updateDir
                if (!ze.getName().contains("jpg")) {
                    new File(destDir + "/" + ze.getName()).mkdirs();
                    //destDir = destDir+"/"+ze.getName();
                } else {
                    String fileName = ze.getName();
                    File newFile = new File(destDir + File.separator + fileName);
                    System.out.println("Unzipping to " + newFile.getAbsolutePath());
                    //create directories for sub directories in zip
                    File parentFile = new File(newFile.getParent());
                    if (!parentFile.exists()) {
                        parentFile.mkdirs();
                        //new File(newFile.getParent()).mkdirs();
                        System.out.println("newFile.getParent(): " + newFile.getParent());
                    }
                    FileOutputStream fos = new FileOutputStream(newFile);
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                    //close this ZipEntry
                    zis.closeEntry();
                    ze = zis.getNextEntry();
                }

            }
            //close last ZipEntry
            zis.closeEntry();
            zis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void unzipFolder(String source, String targetFolder) throws IOException {
        Path targetPath = Paths.get(targetFolder) ;
        InputStream iS = new FileInputStream(source);
        targetPath = targetPath.toAbsolutePath();
        try (ZipInputStream zipStream = new ZipInputStream(iS)) {
            for (ZipEntry ze; (ze = zipStream.getNextEntry()) != null; ) {
                Path resolvedPath = targetPath.resolve(ze.getName()).normalize();
                if (!resolvedPath.startsWith(targetPath)) {
                    throw new RuntimeException("Entry is invalid path: " + ze.getName());
                }
                if (ze.isDirectory()) {
                    Files.createDirectories(resolvedPath);
                } else {
                    Files.createDirectories(resolvedPath.getParent());
                    Files.copy(zipStream, resolvedPath);
                }
            }
        }
    }

}
