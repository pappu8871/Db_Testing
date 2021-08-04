package com.bridgelbz.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;

public class DBTesting extends  Base {

    public int count;
    static Connection connection;                                  // Connection object
    private static Statement statement;                                   // Statement object
    public static String DB_URL = "jdbc:mysql://localhost/employee";       // Constant for Database URL
    private WebElement driver;

    @BeforeTest
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");                   // Database connection
        // Get connection to DB
        Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        return con;
    }

    @Test(priority = 0)
    public void get_table_data() throws ClassNotFoundException, SQLException {

        Connection con = this.getConnection();
        Statement statement = con.createStatement();
        String query = "select * from employee_details";     // Get the contents of userinfo table from DB
        System.out.println("/***************************************/");
        ResultSet res = statement.executeQuery(query);

        while (res.next()) {
            int emp_id = res.getInt(1);
            String emp_name = res.getString(2);
            int age = res.getInt(3);
            System.out.println(emp_id + " " + emp_name + "" + age + " "  );
            count++;
        }
    }

    @Test(priority = 1)
    public void insert_table_data() throws ClassNotFoundException, SQLException{
        con = this.getConnection();
        PreparedStatement pst = con.prepareStatement("insert into employee_details values(?,?,?)");
        pst.setInt(1,3001);
        pst.setString(2,"Mohit");
        pst.setInt(3,15);
         pst.executeUpdate();
        get_table_data();
    }

    @Test(priority = 2)
    public void update_table_data() throws ClassNotFoundException, SQLException{
        con = this.getConnection();
        PreparedStatement pst = con.prepareStatement("update employee_details set emp_name = ? where emp_id = ?");

        pst.setString(1,"Mahesh");
        pst.setInt(2,3001);
        // pst.setInt(3,15);
        pst.executeUpdate();
        get_table_data();
    }
    @Test(priority = 3)
    public  void  delete_row_from_table() throws ClassNotFoundException,SQLException{
        con = this.getConnection();
        PreparedStatement pst = con.prepareStatement("Delete from employee_details where emp_id = ?");
        pst.setInt(1, 3001);
        pst.executeUpdate();
        get_table_data();
    }

    @Test(priority =  4)
    public void db_Selenium() throws  SQLException, InterruptedException,ClassNotFoundException{
        setup();
        ResultSet res;

        WebElement username = driver.findElement(By.xpath("//input[@class='_2IX_2- VJZDxU']"));
        WebElement password = driver.findElement(By.xpath("//input[@class='_2IX_2- _3mctLh VJZDxU']"));
        WebElement loginBtn = driver.findElement(By.xpath("//button[@class='_2KpZ6l _2HKlqd _3AWRsL']"));

        con =this.getConnection();
        Statement set = con.createStatement();
        res = statement.executeQuery("Select * from user LIMIT");
        while (res.next()) {
           username.sendKeys(res.getString(2));
           Thread.sleep(200);
        }
        password.sendKeys("Pappu8871312@");
        loginBtn.click();
        Thread.sleep(2000);
//        driver.close();
    }

    @AfterTest
    public void tearDown() throws SQLException {
        if (connection != null) {
            connection.close();          // Close DB connection
        }
    }

}



