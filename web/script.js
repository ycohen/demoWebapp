$(document).ready(function() {
    console.log("Ready");
    $.get({
        url: '/rest/customers'
    }).done(function(data, status, jqxhr) {
        var customerList = $('#customerList');
        $('option.customer').remove();

        x = {'data': data, 'status': status, 'jqxhr': jqxhr};

        var customers = data['customers'];

        customers.forEach(function(element) {
            customerList.append('<option class="customer" onclick="getOrdersFor(\'' + element + '\')">' + element + '</option>');
        });
    })
});

function getOrdersFor(customer) {
    $.get({
        url: '/rest/orders',
        data: {customer: customer}
    }).done(function(data, status, jqxhr) {
        console.log("Orders for " + customer);
        console.log("Right away, sir!");

        $('tr.order').remove();

        var ordersList = $('#ordersTable');
        var orders = data['orders'];

        if (orders.length === 0) {
            ordersList.hide();
            $('#noResults').show();
        } else {
            orders.forEach(function (element) {
                ordersList.append('<tr class="order">' +
                    '<td>' + element['orderDate'] + '</td>' +
                    '<td>' + element['productName'] + '</td>' +
                    '<td>' + element['quantity'] + '</td>' +
                    '<td>' + element['price'] + '</td>' +
                    '</tr>');
            });
            ordersList.show();
            $('#noResults').hide();
        }
    });
}