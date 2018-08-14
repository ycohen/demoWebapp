<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Content</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<div id="logoutDiv">
    <form action="/LogoutServlet" method="post"><input type="submit" value="Logout"></form><br>
</div>
<div id="customerListDiv">
    <h3>
        Customers
    </h3>
    <select id="customerList" size="20">
    </select>

</div>
<div id="orderListDiv">
    <h3>Orders</h3>
    <table id="ordersTable">
        <tr>
            <th> Order Date</th>
            <th>Product Name</th>
            <th>Quantity</th>
            <th>Price</th>
        </tr>
    </table>

    <div id="noResults">There are no orders for this customer</div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="script.js">
</script>
</body>
</html>
