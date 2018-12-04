package com.eomcs.lms.dao.impl;


import java.util.HashMap;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.MemberDAO;
import com.eomcs.lms.domain.Member;

public class MariaDBMemberDAO implements MemberDAO{

  SqlSessionFactory sqlSessionFactory;

  public MariaDBMemberDAO(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory=sqlSessionFactory;
  }

  @Override
  public Member findByEmailPassword(String email, String password) throws Exception {

    try(SqlSession sqlsession = sqlSessionFactory.openSession();)
    {
      HashMap<String, Object> params = new HashMap<>();

      params.put("email", email);
      params.put("password", password);

      return sqlsession.selectOne("MemberDAO.findByEmailPassword", params);

    }

  }
}
