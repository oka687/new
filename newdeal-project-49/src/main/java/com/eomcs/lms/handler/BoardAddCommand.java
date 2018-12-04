package com.eomcs.lms.handler;

import java.util.Scanner;
import com.eomcs.lms.dao.impl.MariaDBBoardDAO;
import com.eomcs.lms.domain.Board;

public class BoardAddCommand implements Command{

  Scanner keyboard;
  MariaDBBoardDAO boardDAO;

  public BoardAddCommand(Scanner keyboard, MariaDBBoardDAO boardDAO) {
    this.keyboard = keyboard;
    this.boardDAO = boardDAO;
  }

  public void excute() {
    try {
      Board board = new Board();

      System.out.print("내용? ");
      board.setContents(keyboard.nextLine());
      System.out.print("작성자번호? ");
      board.setWriterNo(Integer.parseInt(keyboard.nextLine()));
      System.out.print("수업번호? ");
      board.setLessonNo(Integer.parseInt(keyboard.nextLine()));

      boardDAO.insert(board);
      System.out.println("입력했습니다!");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
