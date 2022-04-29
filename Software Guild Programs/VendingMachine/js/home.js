$(document).ready(function () {
    loadItems();
});

var userMoney = 0;
var itemPrice = 0;
var stock = 0;

const getUrl = 'http://vending.us-east-1.elasticbeanstalk.com/items';
function postUrl(userMoney, itemId) {
    return 'http://vending.us-east-1.elasticbeanstalk.com/money/' + userMoney + '/item/' + itemId
}


// Load item properties onto buttons
function loadItems() {
    clearItems();
    var itemRows = $('#itemTable');

    $.ajax({
        type: 'GET',
        url: getUrl,
        success: function(itemArray) {

            $.each(itemArray, function(index, item){
                var id = item.id;
                var name = item.name;
                var price = item.price;
                var quantity = item.quantity;

                var row = '<button style="margin:25px;height:200px;width:200px" onclick="selectItem('+ id + ', ' + price + ', ' + quantity + ')">' + id + '<br><br>' + name + '<br><br>$' + price + 
                    '<br><br>Quantity Left:' + quantity + '</button>';

                itemRows.append(row);
            })
        },
        error: webServiceError,
    })
}

// Transaction logic for checking and responding to errors then posting data and retrieving item with change
function transaction() {

    $('#errorMessages').empty();
    var itemId = $('#inputId').text();

    if (itemId == '') {
        selectionError();
        return;
    }

    if (checkFunds(userMoney) == true) {
        return;
    } else if (checkStock() == true) {
        return;
    } else {

        $('#makeChange').show();

        $.ajax({
            type: 'POST',
            url: postUrl(userMoney, itemId),
            success: function(changes) {
                var changeOne = [
                    {name: "Quarters", amount: changes.quarters},
                    {name: "Dimes", amount: changes.dimes},
                    {name: "Nickels", amount: changes.nickels},
                    {name: "Pennies", amount: changes.pennies}
                ];

                var due = changeOne.filter((coin) => {
                    return coin.amount > 0;
                });

                var strng = '';

                due.forEach((coin) => {
                    strng += coin.name + ': ' + coin.amount + ' ';
                });
 
                $('#change').append(strng);
                userMoney = 0;

                $('#errorMessages')
                    .append($('<li>')
                    .attr({class: 'list-group-item list-group-item-success'})
                    .text('THANK YOU!!!'));
                $('#moneyDisplay').empty();
                $('#moneyDisplay').append('0.00');
                loadItems(); 
            }, 
            error: webServiceError,
        })
    }
}

// Clears text boxes, sets variables with proper selected properties
function selectItem(id, price, quantity) {
    $('#errorMessages').empty();
    $('#inputId').empty();
    $('#inputId').append(id);
    itemPrice = price;
    stock = quantity;
}

function clearItems() {
    $('#itemTable').empty();
}

function getMoney(funds) {
    if (funds == "dollar") {
        $('#moneyDisplay').empty();
        userMoney += 1.00;
        return $('#moneyDisplay').append(userMoney);
    } else if (funds == "quarter") {
        $('#moneyDisplay').empty();
        userMoney += .25;
        return $('#moneyDisplay').append(userMoney);
    } else if (funds == "dime") {
        $('#moneyDisplay').empty();
        userMoney += .10;
        return $('#moneyDisplay').append(userMoney);
    } else {
        $('#moneyDisplay').empty();
        userMoney += .05;
        Math.round(userMoney);
        return $('#moneyDisplay').append(userMoney);
    }
}

function changeReturn() {
    $('#errorMessages').empty();
    $('#inputId').empty();
    $('#change').empty();
    $('#makeChange').hide();
}

function checkFunds(userMoney) {
    var balance = itemPrice - userMoney;
    if (userMoney < itemPrice) {
        insufFundsError(balance);
        loadItems()
    return true;
    }
    return false;
}

function checkStock() {
    if (stock == 0) {
        outOfStockError();
        loadItems()
    return true;
    }
    return false;
}

function webServiceError() {
    $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service. Please try again later.'));
}

function selectionError() {
    $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Please make a selection!'));
}

function outOfStockError() {
    $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('OUT OF STOCK!'));
}

function insufFundsError(balance) {
    $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Please deposit $' + balance));
}