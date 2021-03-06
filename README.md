# splib
Java library for using Store procedures in an easy way

## Requeriments
You should have dbutils 1.6 in your dependencies:
```
    <dependency>
      <groupId>commons-dbutils</groupId>
      <artifactId>commons-dbutils</artifactId>
      <version>1.6</version>
    </dependency>
```
Also you have to config **ojdbc6.jar** in your dependencies

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

