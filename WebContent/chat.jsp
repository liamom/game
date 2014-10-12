<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
	#textArea{
		height:400px;
		width:200px;
		border:1px solid black;
	}
</style>
</head>
<body>
<div id="textArea"></div>
<div id="input area">
	<input type="text" id="input" />
	<input type="button" id="submit" onclick="sendText(input.value)" value="submit" />
	<input type="button" id="connect" onclick="connect()" value="connect" />
	<input type="checkbox" id="useGroovy" value="useGroovyWebsocket" />
</div>

<!-- Javascript -->
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="jquery.websocket.js"></script>
<script type="text/javascript">
	var ws;
	
	function connect(){
		var roomString = useGroovy.checked ? '/game/chatRoomGroovy' : '/game/chatRoom';
		
		ws = new WebSocket('ws://' + window.location.host + roomString); 
		
		ws.onopen = function(msg) {
		   console.log('Connection successfully opened');
		   writeToScreen('Connection successfully opened');
		};
		
		ws.onmessage = function(msg) {
		        // what do you want
		        console.log(msg.data);
		        
		        writeToScreen(msg.data);
		};

		 ws.onclose = function(msg) {
		  // when connection close
		}
		    ws.error =function(err){
		         console.log(err); //if occurred any error
		}
	}
	
	function sendText(msg){
		ws.send(msg);
	}
	function writeToScreen(msg){
		var text = document.createElement("p");
		text.innerHTML = msg;
		$("#textArea")[0].appendChild(text);
		
	}
</script>
</body>
</html>