var webSocket;

function connect() {
    webSocket = new WebSocket('ws://localhost/sup');
    webSocket.onmessage = function (resp) {

        let data = resp['data'];
        let json = JSON.parse(data);
        $('#messageList').append("<strong>" + json['sender'] + ": </strong>" + json['text'] + "<br>")
    }
}

function send(text, sender, receiver) {

    let message = {
        'text': text,
        'sender': sender,
        'receiver': receiver
    };

    document.getElementById("message").value = "";
    webSocket.send(JSON.stringify(message));
}

$(document).ready(function () {
    connect();
    $('#receiver').change(function () {
        window.location.href = location.protocol + '//' + location.host + "/manage"
            + '?receiver=' + $(this).val();
    });
    let queryParams = new URLSearchParams(window.location.search);
    if (queryParams.has("receiver")) {
        console.log(queryParams.get("receiver"));
        document.getElementById("receiver").value = queryParams.get("receiver");
    }
});