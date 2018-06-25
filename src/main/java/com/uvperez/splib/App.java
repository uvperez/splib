package com.uvperez.splib;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String str = "Hola mundo" ;
        System.out.println( str );

        
        //test();

        testStoredJdbc();

    }

    static void testStoredJdbc() {
        DataSource ds = getDataSource();
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
		try {
            conn = ds.getConnection();
            stmt = conn.prepareCall("{CALL employees.list_employees_birthdate(?) }");
            stmt.setString(1, "01/02/1965");
            rs = stmt.executeQuery();
            while (rs.next()) {
                String str = "" + rs.getObject(1) 
                + " " + rs.getObject(2)
                + " " + rs.getObject(3)
                + " " + rs.getObject(4)
                + " " + rs.getObject(5)
                + " " + rs.getObject(6);
                System.out.println(str);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    static void test() {
        DataSource ds = getDataSource();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
		try {
            conn = ds.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from employees where"+
                " birth_date >= str_to_date('01/02/1965','%d/%m/%Y')");
            while (rs.next()) {
                String str = "" + rs.getObject(1) 
                + " " + rs.getObject(2)
                + " " + rs.getObject(3)
                + " " + rs.getObject(4)
                + " " + rs.getObject(5)
                + " " + rs.getObject(6);
                System.out.println(str);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        


    }

    private static DataSource getDataSource() {
        String host = "localhost";
        int port = 3306;
        String user = "employee";
        String pass = "thepass";
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pass);
        dataSource.setDatabaseName("employees");
        dataSource.setPort(port);
        dataSource.setServerName(host);
        dataSource.setNoAccessToProcedureBodies(true);
        return dataSource;
    }

}
