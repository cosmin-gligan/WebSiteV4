// http://localhost:8080/static/customer-order-edit-rest.html#customerId=1&orderId=6
var customerId = $.url('#customerId');
var orderId = $.url('#orderId');

$()

$(
    () => {
        $.getJSON(`/api/customers/${customerId}/orders/${orderId}`)
            .done((order) => {
//                console.log(order);
                $('#titleOrderNumber').text(order.number + '/' + order.customer.name);
                $('#buttonDone').attr('href', `/customers/${customerId}/orders/`);
//                $('#buttonCheckOut').attr('href', `/customers/${customerId}/orders/${order.id}/checkout`);
                $('#buttonCheckOut').click(() => {

                     $.ajax({
                                url: `/customers/${customerId}/orders/${order.id}/checkout`,
                                type: 'POST'
                            }).done(exitBtnClick);
                            ;

                });

            });

        $.getJSON(`/api/customers/${customerId}/orders/${orderId}/products`)
            .done((orderProducts) => orderProducts.forEach(addOrderProductRow)
            );

        $('#productAddName').autocomplete({
            source: searchProductByTerm,
            select: (ev, ui) => $('#productAddId').val(ui.item.id)
        });

        $('#buttonProductAdd').click(() => {
            var productId = $('#productAddId').val();
            var productName = $('#productAddName').val();
            var quantity = $('#productAddQuantity').val();
            var price = $('#productAddPrice').val();
            var image = $('#productAddImage').val();
            $('#productAddId, #productAddName, #productAddQuantity').val(null);

            $.ajax({
                url: `/api/customers/${customerId}/orders/${orderId}/products`,
                type: 'POST',
                dataType: 'json',
                contentType: 'application/json',
                data: JSON.stringify({
                    product: {id: productId, name: productName, price: price, weight: 0, image: image},
                    quantity: quantity,
                    productId : productId,
                    orderId : orderId
                })
            }).done(addOrUpdateOrderProductRow);
        });
    }
)

function addOrUpdateOrderProductRow(orderProduct) {
//    console.log('orderProduct', orderProduct);
    var existingRow = $("#op_" + orderProduct.product.id);
    if (existingRow.length == 1) {
        existingRow.find('[name="spanQuantity"]')
            .hide()
            .text(orderProduct.quantity)
            .show('slow');
        existingRow.find('[name="spanValue"]')
                    .hide()
                    .text(orderProduct.value)
                    .show('slow');
    } else {
        addOrderProductRow(orderProduct);
    }
}

function addOrderProductRow(orderProduct) {
    var newRow = $(`
        <tr id="op_${orderProduct.product.id}">
            <th>${orderProduct.product.name}</th>
            <th><span name="spanQuantity">${orderProduct.quantity}</span></th>
            <th><span name="spanValue">${orderProduct.value}</span></th>
            <th align="center"><img src="${orderProduct.product.image}" width="50px" height="50px"/></th>
            <th><button name="buttonProductRemove" class="btn btn-info">Remove</button></th>
        </tr>
    `);
    newRow.find('[name="buttonProductRemove"]').click(() => {
        $.ajax({
            url: `/api/customers/${customerId}/orders/${orderId}/products/${orderProduct.id}`,
            type: 'DELETE'
        }).done(() =>
            newRow.hide(400, () => newRow.remove())
        );
    });

    newRow.hide().insertBefore('#productAddFormRow').show('slow');
}

function searchProductByTerm(term, resultCallback) {
    $.getJSON('/api/products', term)
        .done((products) => {
            resultCallback(products.map((p) => {
                $('#productAddPrice').val(p.price);
                $('#productAddImage').val(p.image);
                return {
                    id: p.id,
                    label: p.name + ' ' + p.price + '$',
                    value: p.name
                }
            }))
        })
}

function exitBtnClick(){
   alert('Order was successfully submited! Check your inbox!')
   document.getElementById("buttonDone").click();
}