package chatroom;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chatRoom")
public class chatPerson {
	private ChatRoom chatRoom;
	public Session session; 
	public String name;
	
	@OnOpen
	public void start(Session session){
		this.session = session;
		chatRoom = roomAssigner.getChatRoom(this);
	}
	

    @OnClose
    public void end() {
        //message("bye");
    }
    
    @OnError
    public void onError(Throwable t) throws Throwable {
        //log.error("Chat Error: " + t.toString(), t);
    }
	
	@OnMessage
	public void message(Session session, String msg){
		chatRoom.sendToRoom(name + " : " + msg);
	}
}
