let connector
let stompClient = null;
let username = null;

io_jmix_sampler_screen_ui_components_javascript_component_TimePicker = function () {
    connector = this;
    connect()
};


function connect() {
    let socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
}


function onConnected() {
    stompClient.subscribe('/topic/public', onMessageReceived);

    stompClient.send("/app/list.addUser",
        {},
        JSON.stringify({sender: username, type: 'JOIN'})
    );
}

function onError(error) {
    alert("Could not connect to WebSocket server. Please refresh this page to try again!")
}


function onMessageReceived(payload) {
    let message = JSON.parse(payload.body);
    let date = new Date();
    connector.addMessage("username: " + message.sender + " status: " + message.type + " date: " + date)
}
