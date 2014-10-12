package chatroom;

import java.io.IOException;
import java.util.*;

import javax.websocket.Session;

public class ChatRoom {
	private final int chatRoomSize = 4;
	private List<chatPerson> players;
	private UUID roomId;
	
	ChatRoom(){
		players = new ArrayList<chatPerson>(chatRoomSize);
		setRoomId(UUID.randomUUID());
	}
	
	public boolean addSession(chatPerson session){
		if(players.size() < chatRoomSize){
			players.add(session);
			session.name = "Guest " + players.indexOf(session) + " : ";
			return true;
		}else{
			return false;
		}
	}
	
	public void sendToRoom(String msg){
		for(chatPerson person : players){
			Session sess = person.session;
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

	public UUID getRoomId() {
		return roomId;
	}

	public void setRoomId(UUID roomId) {
		this.roomId = roomId;
	}
	
	
	
}
