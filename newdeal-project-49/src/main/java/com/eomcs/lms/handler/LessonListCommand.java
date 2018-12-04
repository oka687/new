package com.eomcs.lms.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import org.mariadb.jdbc.Driver;

public class LessonListCommand implements Command{

  Scanner keyboard;

  public LessonListCommand(Scanner keyboard) {
    this.keyboard = keyboard;
  }

  public void excute() {

    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
      DriverManager.registerDriver(new Driver());
      
      con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "study", "1111");
      stmt = con.createStatement();
      rs = stmt.executeQuery("select bno, cont, cdt ,view, mno, lno from board");

      while (rs.next()) {
        System.out.printf("%3d, %-20s, %s, %d\n", 
            rs.getInt("bno"),
            rs.getString("cont"),
            rs.getDate("cdt"),
            rs.getInt("view"));
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {rs.close();} catch (Exception e2) {}
      try {stmt.close();} catch (Exception e2) {}
      try {con.close();} catch (Exception e2) {}
    }

  }
}
