package com.eomcs.lms.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
import org.mariadb.jdbc.Driver;

public class LessonAddCommand implements Command{

  Scanner keyboard;

  public LessonAddCommand(Scanner keyboard) {
    this.keyboard = keyboard;
  }

  public void excute() {

    System.out.print("내용? ");
    String content = keyboard.nextLine();

    System.out.print("작성자번호? ");
    String writerNo = keyboard.nextLine();

    System.out.print("수업번호? ");
    String lessonNo = keyboard.nextLine();

    Connection con = null;;
    Statement stmt =null;;

    try {
      //MariaDB JDBC Driver(java.sql.Driver) 구현체를 로딩
      DriverManager.registerDriver(new Driver());

      //DBMS에 연결하기
      con = DriverManager.getConnection(
          "jdbc:mariadb://localhost:3306/studydb",
          "study" ,"1111");

      //SQL 전송을 담당할 객체를 준비
      stmt = con.createStatement();

      //SQL을 서버에 전송 => 서버에서 결과를 가져올 역할을 하는 객체를 리턴
      stmt.executeUpdate("insert into board(cont,mno,lno)"
          + " values('"+content+ "',"
          + writerNo + ","
          + lessonNo + ")" );

      System.out.println("입력했습니다!");

    } catch (Exception e) {
      e.printStackTrace();

    } finally {
      try {stmt.close();}catch(Exception e) {}
      try {con.close();}catch(Exception e) {}
    }
  }

}
