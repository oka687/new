package com.eomcs.lms.servlet;

import java.io.IOException;
import java.util.Scanner;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

//Spring IoC 컨테이너가 이 클래스의 인스턴스를 자동 생성하도록 
//클래스에 표시해 둔다.
@WebServlet("/board/detail")
public class BoardDetailServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  BoardDao boardDao;
  
  @Override
  public void init () {
    ApplicationContext iocContainer = 
        (ApplicationContext) this.getServletContext()
        .getAttribute("iocContainer");
    this.boardDao = iocContainer.getBean(BoardDao.class);
  }
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      
      int no = Integer.parseInt(request.getParameter("no"));

      Board board = boardDao.findByNo(no);
      
      request.setAttribute("board", board);
      RequestDispatcher rd = request.getRequestDispatcher("/board/detail.jsp");
      
      response.setContentType("text/html;charset=UTF-8");
     rd.include(request,response);
      
    
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServletException(e);
    }
  }

}