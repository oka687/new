package com.eomcs.lms;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.eomcs.lms.dao.MemberDAO;
import com.eomcs.lms.dao.impl.MariaDBBoardDAO;
import com.eomcs.lms.dao.impl.MariaDBMemberDAO;
import com.eomcs.lms.handler.BoardAddCommand;
import com.eomcs.lms.handler.BoardDeleteCommand;
import com.eomcs.lms.handler.BoardDetailCommand;
import com.eomcs.lms.handler.BoardListCommand;
import com.eomcs.lms.handler.BoardUpdateCommand;
import com.eomcs.lms.handler.Command;
import com.eomcs.lms.handler.HelloCommand;
import com.eomcs.lms.handler.LessonAddCommand;
import com.eomcs.lms.handler.LessonDeleteCommand;
import com.eomcs.lms.handler.LessonDetailCommand;
import com.eomcs.lms.handler.LessonListCommand;
import com.eomcs.lms.handler.LessonUpdateCommand;
import com.eomcs.lms.handler.LoginCommand;
import com.eomcs.lms.handler.MemberAddCommand;
import com.eomcs.lms.handler.MemberDeleteCommand;
import com.eomcs.lms.handler.MemberDetailCommand;
import com.eomcs.lms.handler.MemberListCommand;
import com.eomcs.lms.handler.MemberUpdateCommand;

public class App {

  static Scanner keyboard = new Scanner(System.in);
  static Stack<String> commandHistory = new Stack<>();
  static Queue<String> commandHistory2 = new LinkedList<>();

  public static void main(String[] args) throws Exception{

    //MyBatis SqlSessionFactory 준비
    String resource = "com/eomcs/lms/conf/mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory =
        new SqlSessionFactoryBuilder().build(inputStream);


    MariaDBBoardDAO boardDAO = new MariaDBBoardDAO(sqlSessionFactory);
    MemberDAO memberDAO = new MariaDBMemberDAO(sqlSessionFactory);

    HashMap<String, Command> commandMap = new HashMap<>();


    commandMap.put("/board/list", 
        new BoardListCommand(keyboard, boardDAO));
    commandMap.put("/board/detail",
        new BoardDetailCommand(keyboard, boardDAO));
    commandMap.put("/board/add",
        new BoardAddCommand(keyboard, boardDAO));
    commandMap.put("/board/update",
        new BoardUpdateCommand(keyboard, boardDAO));
    commandMap.put("/board/delete",
        new BoardDeleteCommand(keyboard, boardDAO));

    commandMap.put("/lesson/list", 
        new LessonListCommand(keyboard));
    commandMap.put("/lesson/detail", 
        new LessonDetailCommand(keyboard));
    commandMap.put("/lesson/add", 
        new LessonAddCommand(keyboard));
    commandMap.put("/lesson/update", 
        new LessonUpdateCommand(keyboard));
    commandMap.put("/lesson/delete", 
        new LessonDeleteCommand(keyboard));

    commandMap.put("/member/list", 
        new MemberListCommand(keyboard));
    commandMap.put("/member/detail", 
        new MemberDetailCommand(keyboard));
    commandMap.put("/member/add", 
        new MemberAddCommand(keyboard));
    commandMap.put("/member/update", 
        new MemberUpdateCommand(keyboard));
    commandMap.put("/member/delete", 
        new MemberDeleteCommand(keyboard));

    commandMap.put("hello", new HelloCommand(keyboard));

    commandMap.put("/auth/login", 
        new LoginCommand(keyboard, memberDAO));

    while (true) {
      String command = prompt();

      // 사용자가 입력한 명령을 스택에 보관한다.
      commandHistory.push(command);

      // 사용자가 입력한 명령을 큐에 보관한다.
      commandHistory2.offer(command);


      Command commandHandler = commandMap.get(command);

      if(commandHandler != null) {
        try {          
          commandHandler.excute();
        }catch (Exception e) {
          System.out.println("예외 발생~!" + e.toString());
        }  
      }

      else if (command.equals("quit")) {
        System.out.println("안녕!");
        break;

      } else if (command.equals("history")) {
        printCommandHistory();

      } else if (command.equals("history2")) {
        printCommandHistory2();

      } else {
        System.out.println("실행할 수 없는 명령입니다.");
      }

      System.out.println(); 
    }

    keyboard.close();
  }

  private static void printCommandHistory() {
    Iterator<String> iterator = commandHistory.iterator();

    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
  }

  private static void printCommandHistory2() {
    Iterator<String> iterator = commandHistory2.iterator();

    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
  }

  private static String prompt() {
    System.out.print("명령> ");
    return keyboard.nextLine().toLowerCase();
  }
}
