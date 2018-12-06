package com.eomcs.lms.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.eomcs.lms.AppConfig;

@WebListener
public class ContextLoaderListener implements ServletContextListener{
  AnnotationConfigApplicationContext iocContainer;
  //웹 애플리케이션이 시작되거나 종료될 때 호출되는 메소드를 정의하는 것이다.
  //규칙에 따라 리스너를 만들었으면 톰캣 서버에 등록해야 한다.
   @Override
  public void contextInitialized(ServletContextEvent sce) {
    // TODO Auto-generated method stub
    System.out.println("시작");
    
    // AppConfig 클래스가 메모리에 로딩되어 있지 않다면,
    // Spring IoC 컨테이너 준비하기
     iocContainer = 
        new AnnotationConfigApplicationContext(AppConfig.class);
   
    System.out.println(iocContainer.getBeanDefinitionCount());
    String[] names = iocContainer.getBeanDefinitionNames();
    for (String name : names) {
      System.out.printf("%s ===> %s\n", name, 
          iocContainer.getBean(name).getClass().getName());
    }
  
    //Spring IoC 컨테이너를 서블릿이 사용할 수 있도록 'servletContext'라는 보관소에 저장한다.
    ServletContext sc = sce.getServletContext();
    sc.setAttribute("iocContainer", iocContainer);
    
  }
   
   @Override
  public void contextDestroyed(ServletContextEvent sce) {
    
    System.out.println("종료됨");
    
    //Spring IoC 컨테이너의 자원을 해제시킨다.
    this.iocContainer.close();
  }
}
