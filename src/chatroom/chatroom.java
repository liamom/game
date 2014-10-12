package chatroom;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chatRoom")
public class chatroom {
	
	@OnOpen
	public void start(Session session){
		message(session,"hello");
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
		for (Session sess : session.getOpenSessions()) {
	      try {
             if (sess.isOpen())
                sess.getBasicRemote().sendText(msg);
	       } catch (IOException e) { 
	    	   try {
	    		   sess.close();
				} catch (IOException e1) {
					System.err.println("OMG ERROR");
				}
	       }
		}
	}
}
