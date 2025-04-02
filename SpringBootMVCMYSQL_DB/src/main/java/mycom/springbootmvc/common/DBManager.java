package mycom.springbootmvc.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {
    // Connection Pool (DataSource) 객체를 직접 획득 X <- Spring DI
//    public static Connection getConnection() {
//        Connection con = null;
//        try {
//            Context context = new InitialContext();
//            DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/madang");
//            con = ds.getConnection();
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//
//        return con;
//    }

    // Connection Pool 로부터 얻는 Connection 객체의 close()는 오버라이딩 되어있음
    // Connection 을 끊지 않고 Connection Pool 에 반납되도록
    public static void releaseConnection(PreparedStatement pstmt, Connection con) {
        try {
            if( pstmt != null ) pstmt.close();
            if( con != null ) con.close();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void releaseConnection(ResultSet rs, PreparedStatement pstmt, Connection con) {
        try {
            if( rs != null ) rs.close();
            if( pstmt != null ) pstmt.close();
            if( con != null ) con.close();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
