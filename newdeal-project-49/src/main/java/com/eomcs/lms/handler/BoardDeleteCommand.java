package com.eomcs.lms.handler;

import java.util.Scanner;
import com.eomcs.lms.dao.impl.MariaDBBoardDAO;

public class BoardDeleteCommand implements Command{

  Scanner keyboard;
  MariaDBBoardDAO boardDAO;

  public BoardDeleteCommand(Scanner keyboard, MariaDBBoardDAO boardDAO) {
    this.keyboard = keyboard;
    this.boardDAO = boardDAO;
  }

  public void excute() {

    try {
      System.out.print("번호?");
      int no = Integer.parseInt(keyboard.nextLine());

      if(boardDAO.delete(no) > 0) {
        System.out.println("삭제했습니다!");
      }else
        System.out.println("해당 번호의 게시물이 없습니다.");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}




