package com.eomcs.lms.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mariadb.jdbc.Driver;
import com.eomcs.lms.dao.BoardDAO;
import com.eomcs.lms.domain.Board;

public class MariaDBBoardDAO implements BoardDAO{
  
  SqlSessionFactory sqlSessionFactory;
  
  public MariaDBBoardDAO(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  public List<Board> findAll() throws Exception {
    try(SqlSession sqlsession = sqlSessionFactory.openSession();) {
      return sqlsession.selectList("BoardDAO.findAll");
      }
    }

  public Board findByNo(int no) throws Exception{
    DriverManager.registerDriver(new Driver());
    try(
        SqlSession sqlsession = sqlSessionFactory.openSession();){

      
          return sqlsession.selectOne("BoardDAO.findByNo", no);
    }
  }

  public int insert(Board board) throws Exception {
    DriverManager.registerDriver(new Driver());
    try(
        SqlSession sqlsession = sqlSessionFactory.openSession();
        ) {

      int count =  sqlsession.insert("BoardDAO.insert", board);
    
       sqlsession.commit();//마리아디비는 오토커밋이 되지 않기 때문에 사용자가 직접 명령을 내려줘야 한다.
       return count;
    
    } 
  }

  public int update(Board board) throws Exception {
   
    try (SqlSession sqlsession = sqlSessionFactory.openSession();){
      
      int count =  sqlsession.update("BoardDAO.update", board);
      
      sqlsession.commit();//마리아디비는 오토커밋이 되지 않기 때문에 사용자가 직접 명령을 내려줘야 한다.
      return count;
    }
  }

  public int delete(int no) throws Exception{


    try(SqlSession sqlsession = sqlSessionFactory.openSession();) {
      int count =  sqlsession.delete("BoardDAO.delete", no);
      
      sqlsession.commit();//마리아디비는 오토커밋이 되지 않기 때문에 사용자가 직접 명령을 내려줘야 한다.
      return count;

    }
  }
}

