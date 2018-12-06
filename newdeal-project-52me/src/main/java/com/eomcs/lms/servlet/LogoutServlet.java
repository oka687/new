package com.eomcs.lms.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Spring IoC 컨테이너가 이 클래스의 인스턴스를 자동 생성하도록
// 클래스에 표시해 둔다.
@WebServlet("/auth/logout")
public class LogoutServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.getSession().invalidate();
    response.sendRedirect("login");

  }


}


