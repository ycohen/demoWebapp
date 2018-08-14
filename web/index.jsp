<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<html>
  <head>
    <title>Login</title>
  </head>
  <body>
  <%
    if (session.getAttribute("error") != null && session.getAttribute("error").equals("yes")) {
  %>
  Login error
  <% } %>
  <form id="loginForm" action="LoginServlet" method="post">
    <div>Userame: <input name="username" type="text" required title="username"></div>
    <div>Password: <input name="password" type="password" required title="password"></div>
    <button type="submit">Submit</button>
  </form>
  </body>
</html>
