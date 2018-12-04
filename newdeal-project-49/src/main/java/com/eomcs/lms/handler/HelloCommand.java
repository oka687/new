package com.eomcs.lms.handler;

import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;

public class HelloCommand implements Command{
  
  Scanner keyboard;
  
  public HelloCommand(Scanner keyboard) {
    this.keyboard = keyboard;
  }

  @Override
  public void excute() {
    System.out.print("이름은?");
    String name = keyboard.nextLine();
    
    System.out.println(name + "님 반갑습니다!");
  }  
}
