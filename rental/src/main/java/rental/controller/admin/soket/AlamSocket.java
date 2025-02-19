package rental.controller.admin.soket;

import java.util.List;
import java.util.Vector;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/alarmsocket")
public class AlamSocket {

	private static List<Session> connList = new Vector<>();
	
	@OnOpen
	public void onOpen(Session session) {
		connList.add(session);
	}
	@OnClose
	public void onClose(Session session) {
		connList.remove(session);
	}
	@OnMessage
	public void onMessage(Session session, String message) {
		for(int i=0; i<= connList.size()-1; i++) {
			Session client = connList.get(i);
			try {
				client.getBasicRemote().sendText(message);
			}catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}
