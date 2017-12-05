<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>

<%!
    final String JNDINAME = "java:comp/env/jdbc/test" ;
%>
<%
    Connection conn = null ;
    try
    {
        // 初始化查找命名空间
        Context ctx = new InitialContext() ;
        // 找到DataSource
        DataSource ds = (DataSource)ctx.lookup(JNDINAME) ;
        conn = ds.getConnection() ;
        String sql="select Email,Password from table_name where username=?";
        PreparedStatement pwdQuery=conn.prepareStatement(sql);
        pwdQuery.setString(1,"admin");
        ResultSet result=  pwdQuery.executeQuery();
        if(!result.next()){
            return;
        }
        System.out.println(result.getString("Password")) ;
    }
    catch(Exception e)
    {
        System.out.println("哈哈找不到数据库");
        System.out.println(e) ;
    }
%>
<%=conn%>
<%
    // 将连接重新放回到池中
    conn.close() ;
%>