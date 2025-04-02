package mycom.springbootmvc.bookmanager.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {
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