# splib
Java library for using Store procedures in a easy way

## Getting started
You can call a Oracle Stored procedure as
```
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


```

