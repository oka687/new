package com.eomcs.lms.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
import org.mariadb.jdbc.Driver;

public class MemberAddCommand implements Command{

  Scanner keyboard;

  public MemberAddCommand(Scanner keyboard) {
    this.keyboard = keyboard;
  }

  public void excute() {    
    
    System.out.print("내용?");
    String content = keyboard.nextLine();
    System.out.print("작성자번호?");
    String mno = keyboard.nextLine();
    System.out.print("수업번호?");
    String lno = keyboard.nextLine();
    
    
    Connection con = null;
    Statement stmt = null;
    
    try {
      
      DriverManager.registerDriver(new Driver());
      
      con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb","study", "1111");
      stmt = con.createStatement();
      
      stmt.executeQuery("insert into board(cont,mno,lno) values('"+content+"', "+mno+", "+lno+")");
      System.out.println("입력하였습니다~~");
    } catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      try {stmt.close();}catch(Exception e) {}
      try {con.close();}catch(Exception e) {}
    }
  }
}