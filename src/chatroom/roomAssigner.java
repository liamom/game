package chatroom;

import java.util.*;

public class roomAssigner {
	private static ChatRoom room = new ChatRoom();
	
	public static ChatRoom getChatRoom(chatPerson person){
		room.addSession(person);
		return room;
	}
}
