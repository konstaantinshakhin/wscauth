/**
 * Created by Kostya on 07.10.2015.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var wsUri = "ws://" + document.location.host + document.location.pathname + "websocketauthendpoint";
var websocket = new WebSocket(wsUri);


websocket.onmessage = function(evt) { onMessage(evt) };
websocket.onerror = function(evt) { onError(evt) };

function sendText(json) {
    console.log("sending text: " + json);
    websocket.send(json);
}

var output = document.getElementById("output");
websocket.onopen = function(evt) { onOpen(evt) };

function writeToScreen(message) {
    output.innerHTML += message + "<br>";
}
function onMessage(evt) {
    console.log("received: " + evt.data);
}

function onOpen() {
    writeToScreen("Connected to " + wsUri);
}

function onError(evt) {
    writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
}