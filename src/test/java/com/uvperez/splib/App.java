package com.uvperez.splib;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.pool.OracleDataSource;

/**
 * Hello world!
 *
 */
public class App 
{

    public App(){}

    public static void main( String[] args )
    {
        try {
            test_dbutils();
        
        
			//testJdbc();
		} catch (Exception e) {
			e.printStackTrace();
		}

    }

    static void testJdbc() throws SQLException {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            App app = new App();
            DataSource ds = app.getDataSource(); 
            String sql = "{ ? = call PKG_SAMPLE.SAMPLE_LIST(?)}";
            conn = ds.getConnection();
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1,-10);
            cs.setInt(2, 202);
            cs.execute();
            rs = (ResultSet) cs.getObject(1);
            while(rs.next()) {
                System.out.println(rs.getObject(1) + " " + rs.getObject(2));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
				rs.close();
			}

			if (cs != null) {
				cs.close();
			}

			if (conn != null) {
				conn.close();
			}
        }

    }

	static void test_dbutils() throws SQLException {
        Connection conn = null;
		try {
            System.out.println( "INIT" );
            App app = new App();
            DataSource ds = app.getDataSource();
            conn = ds.getConnection();
            String sql = "{ ? = call PKG_SAMPLE_EMPLOYEE.LIST_EMPLOYEE(?)}";
            ResultSetHandler<List<Employee>> rsh = new BeanListHandler<Employee>(Employee.class);
            SpOracleQueryRunner queryRunner = new SpOracleQueryRunner();
            Object[] params = new Object[2];
            params[0] = new OutParameter<ResultSet>(OracleTypes.CURSOR, ResultSet.class);
            params[1] = 202;
            List<Employee> lst = queryRunner.query(conn, sql, rsh, params);
            System.out.println(lst);

            System.out.println( "END" );

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) conn.close();
        }
    }

    DataSource getDataSource() throws SQLException {
        OracleDataSource ds = new OracleDataSource();
        ds.setUser("theuser");
        ds.setPassword("thepassword");
        ds.setURL("jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS =(PROTOCOL=TCP)(HOST=sample) (PORT=1521))(CONNECT_DATA=(SERVICE_NAME=service))))");
        return ds;
    }
}
