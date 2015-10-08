<%@ page import="java.sql.*, javax.sql.*, java.io.*, javax.naming.*" %>
<html>
<head><title>Hello world from JSP</title></head>
<body>
<%
    InitialContext ctx;
    DataSource ds;
    Connection conn;
    Statement stmt;
    ResultSet rs;

    try {
        ctx = new InitialContext();
        ds = (DataSource) ctx.lookup("jdbc/oracle_ds");
        //ds = (DataSource) ctx.lookup("jdbc/MySQLDataSource");
        conn = ds.getConnection();
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT * FROM TOKENS");

        while(rs.next()) {
%>
<h3>Name: <%= rs.getString("TOKEN") %></h3>
<h3>Population: <%= rs.getString("USER_MAIL") %></h3>
<%
    }
}
catch (SQLException se) {
%>
<%= se.getMessage() %>
<%
}
catch (NamingException ne) {
%>
<%= ne.getMessage() %>
<%
    }
%>
</body>
</html>
