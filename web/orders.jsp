<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Content</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<div id="outerDiv">
    <div id="customerListDiv">
        <div id="logoutDiv">
            <form action="/LogoutServlet" method="post"><input type="submit" value="Logout"></form><br>
        </div>

        <h3>Customers</h3>

        <select id="customerList" size="20">
        </select>

    </div>
    <div id="ordersDiv">
        <h3>Orders</h3>

        <div id="noResults">There are no orders for this customer</div>
        <div id="orderDetailsDiv"></div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/mustache.js/2.3.0/mustache.min.js"></script>
<script src="script.js"></script>
<script type="text/html" id="orderDetailTemplate">
    <div id="orderDetailsListing">
        {{#orders}}
        <div class="orderDiv">
            <h3>Order Number: {{orderNumber}}</h3>
            <table>
                <tr>
                    <td>Order Date</td>
                    <td>{{orderDate}}</td>
                </tr>
                <tr>
                    <td>Required Date</td>
                    <td>{{requiredDate}}</td>
                </tr>
                <tr>
                    <td>Shipped Date</td>
                    <td>{{shippedDate}}</td>
                </tr>
                <tr>
                    <td>Status</td>
                    <td>{{status}}</td>
                </tr>
                <tr>
                    <td>Comments</td>
                    <td>{{comments}}</td>
                </tr>
            </table>
            <table>
                <tr>
                    <th>Product Name</th>
                    <th>Quantity Ordered</th>
                    <th>Price Per</th>
                </tr>
                {{#orderDetails}}
                <tr>
                    <td>{{productName}}</td>
                    <td>{{quantity}}</td>
                    <td>{{price}}</td>
                </tr>
                {{/orderDetails}}
            </table>
        </div>
        {{/orders}}
    </div>
</script>

</body>
</html>