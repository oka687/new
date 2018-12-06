package com.eomcs.lms.servlet;

import java.io.IOException;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

//Spring IoC 컨테이너가 이 클래스의 인스턴스를 자동 생성하도록 
//클래스에 표시해 둔다.
@WebServlet("/board/update")
public class BoardUpdateServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  BoardDao boardDao;
  @Override
  public void init () {
    ApplicationContext iocContainer = (ApplicationContext) this.getServletContext().getAttribute("iocContainer");
    this.boardDao = iocContainer.getBean(BoardDao.class);
  }
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      //post 요청으로 들어온 데이터는 UTF-8로 인코딩 되어 있다.
    //그런데 request.getparameter()의 리턴 값은 유니코드(2바이트)이다.
    //즉, UTF-8을 유니코드로 JVM이 다루는 유니코드로 변환한 후에 리턴하는 것이다.
    //문제는 클라이언트가 보낸 데이터가 UTF-8로 되어 있다고 알려주지 않으면 getParameter()는 클라이언트가 보낸 데이터가 ISO88559-1이라고 착각을 한다.
    //즉, 영어라고 착각하고 영어를 유니코드로 바꾸는 것이다. 그래서 한글 입력 데이터가 UTF-8로 인코딩 된 한글 데이터가 유니코드로 바뀔 대 깨지는 것이다.
    //getParameter를 최초로 호출하기 전에 클라이언트가 보낸 데이터가 UTF-8로 되어 있다고 알려줘야 한다.
    
  
    
    try {
      Board board = new Board();
      board.setNo(Integer.parseInt(request.getParameter("no")));
      board.setContents(request.getParameter("contents"));
      
      
      boardDao.update(board);
      //데이터 변경한 후 웹브라우저에게 목록 URL을 다시 요청하라고 응답한다.
      response.sendRedirect("list");
      
  
    
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServletException(e);
    }
  }
}
