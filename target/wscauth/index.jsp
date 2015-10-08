<html>
<body>
<h3>websocketauth</h3>

<div id="output"></div>
<div>
    <span>Subject:</span>
    <input id="subject" type="text" />
    <br />
    <span>Content:</span>
    <input id="content" type="text" />
</div>
<div>
    <input type="submit" value="Send message" onclick="send()" />
</div>
<div id="messages"></div>
<script type="text/javascript" src="websocket.js"></script>
</body>
</html>
