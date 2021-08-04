package com.bridgelbz.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import java.sql.*;

public class Base {

        public static Connection con;
        //Database Username
        public static String DB_USER = "root";
        // Database Password
        public static String DB_PASSWORD = "pappu@123";

        public void setup() throws InterruptedException{
                WebDriverManager.chromedriver().setup();
                WebDriverManager.firefoxdriver().setup();
                WebDriver driver = new ChromeDriver();
//                WebDriver driver1= new FirefoxDriver();
                driver.manage().window().maximize();
                driver.get("https://www.flipkart.com/");
//                driver1.get("https://www.amazon.in/");
                Thread.sleep(1000);

        }
//
//        @AfterTest
//        public void tearDown() throws SQLException {
//                con.close();
//        }

}







