package com.eomcs.lms.handler;

import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.dao.impl.MariaDBBoardDAO;
import com.eomcs.lms.domain.Board;

public class BoardListCommand implements Command{

  Scanner keyboard;
  MariaDBBoardDAO boardDAO;

  public BoardListCommand(Scanner keyboard, MariaDBBoardDAO boardDAO) {
    this.keyboard = keyboard;
    this.boardDAO = boardDAO;
  }

  public void excute() {
    try {
      List<Board> list = boardDAO.findAll();

      for(Board board : list) {
        System.out.printf("%3d, %-20s, %s, %d\n",
            board.getNo(),
            board.getContents(),
            board.getCreatedDate(),
            board.getViewCount()); 
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

