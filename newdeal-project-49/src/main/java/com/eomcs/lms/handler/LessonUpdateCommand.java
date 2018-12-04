package com.eomcs.lms.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
import org.mariadb.jdbc.Driver;

public class LessonUpdateCommand implements Command{

  Scanner keyboard;  

  public LessonUpdateCommand(Scanner keyboard) {
    this.keyboard = keyboard;
  }

  public void excute() {
    Connection con = null;
    Statement stmt = null;

    try {
      DriverManager.registerDriver(new Driver());
      con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb","study" ,"1111");
      stmt = con.createStatement();

      System.out.print("번호?");
      String no = keyboard.nextLine();

      System.out.print("내용?");
      String content = keyboard.nextLine();

      stmt.executeUpdate("update board set cont='" + content + "' where bno=" + no);

      System.out.println("변경했습니다!");

    } catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      try {con.close();}catch(Exception e) {}
      try {stmt.close();}catch(Exception e) {}
    }
  }

}
