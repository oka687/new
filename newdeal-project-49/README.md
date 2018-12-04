# eomcs-java-project-49

## 작업

### MyBatis 라이브러리 추가

- `mavenrepository.com`에서 `mybatis` 라이브러리를 검색해서 정보를 알아낸다.
- build.gradle 파일에 등록
- `$gradle eclipse` 실행하여 이클립스 설정 파일을 갱신
- 이클립스에서 프로젝트 `Refresh`

### MyBatis 설정 파일 추가

- `mybatis.org` 사이트의 문서를 참고하여 `mybatis-config.xml` 파일을 작성한다.
- 링크 : http://www.mybatis.org/mybatis-3/getting-started.html
- jdbc.properties 파일을 작성한다.
    - DBMS 연결 정보를 둔다.
    - MyBatis 설정 파일에서 참고할 것이다.
- 디렉토리는 `src/main/resources/com/eomcs/lms/config`
- `src/main/resources` 폴더를 추가한 후 자바 소스 폴더로 등록해야 한다.
- 즉 폴더를 만든 후에 `$gradle eclipse`를 다시 실행하고 프로젝트에서 Refesh한다.

### SQL 분리

- `src/main/resources/com/eomcs/lms/mapper/BoardMapper.xml` 파일 작성한다.
- `BoardDAO` 클래스에 있는 SQL을 이 파일로 옮긴다.
-  MyBatis 문서를 참고하여 작성

### SqlSessionFactory 인스턴스 생성

- `App` 클래스에서 MyBatis SqlSessionFactory 객체를 준비한다.
- DAO 객체가 사숑할 수 있게 생성자에 주입한다.

### DAO에 적용