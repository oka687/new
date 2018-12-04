package com.eomcs.lms.handler;

import java.util.Scanner;
import com.eomcs.lms.dao.MemberDAO;
import com.eomcs.lms.domain.Member;

public class LoginCommand implements Command{

  Scanner keyboard;
  MemberDAO memberDAO;

  public LoginCommand(Scanner keyboard, MemberDAO memberDAO) {
    this.keyboard = keyboard;
    this.memberDAO = memberDAO;
  }

  public void excute() {
    try {

      System.out.print("이메일? ");
      String email = keyboard.nextLine();

      System.out.print("암호? ");
      String password = keyboard.nextLine();

      Member member = memberDAO.findByEmailPassword(email, password);

      if(member != null) 
        System.out.printf("%s님 환영합니다\n",member.getName());
      else
        System.out.println("이메일 또는 암호가 일치하지 않습니다.");


    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
