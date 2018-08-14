<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<html>
  <head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
  </head>
  <body>
  <%
    if (session.getAttribute("error") != null && session.getAttribute("error").equals("yes")) {
  %>
  Login error
  <% } %>
  <div id="loginOuterDiv">
    <form id="loginForm" action="LoginServlet" method="post">
      Welcome to the order view interface! Please log in <br>
      <table>
        <tr>
          <td>
            Username:
          </td>
          <td>
            <input name="username" type="text" required title="username">
          </td>
        </tr>
        <tr>
          <td>
            Password:
          </td>
          <td>
            <input name="password" type="password" required title="password">
          </td>
        </tr>
      </table>
      <button id="loginButton" type="submit">Login</button>
    </form>
  </div>
   </body>
 </html>