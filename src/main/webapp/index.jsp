<%--
  Created by IntelliJ IDEA.
  User: kshahin
  Date: 10/9/2015
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script src="websocket.js"></script>
</head>
<body>
<div>
    <span>email:</span>
    <input id="email" type="text" />
    <br />
    <span>password:</span>
    <input id="password" type="text" />
</div>
<div>
    <input type="submit" value="login" onclick="send()" />
</div>
<div id="messages"></div>

</body>
</html>
