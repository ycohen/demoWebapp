$(document).ready(function() {
    console.log("Ready");
    get('/rest/customers', "", function(data) {
        var customerList = $('#customerList');
        $('option.customer').remove();

        var customers = data['customers'];

        customers.forEach(function(element) {
            customerList.append('<option class="customer" onclick="getOrdersFor(\'' + element['customerNumber'] + '\')">' + element['customerName'] + '</option>');
        });
    })
});

function getOrdersFor(customer) {
    get('/rest/orders', {customerKey: customer}, function(data) {
        var orders = data['orders'];

        if (orders && orders.length === 0) {
            $('#orderDetailsListing').hide();
            $('#noResults').show();
        } else {
            var orderDetails = $('#orderDetailsDiv');
            var html = Mustache.to_html($('#orderDetailTemplate').html(), data);
            orderDetails.html(html);
            orderDetails.show();
            $('#noResults').hide();
        }
    });
}

function get(url, data, success) {
    $.get({
        url: url,
        data: data
    }).done(function(data) {
        success(data);
    }).fail(function(jqXHR, textStatus, errorThrown) {
        if (jqXHR.status === 401) {
            location.replace('index.jsp');
        }
    });
}
