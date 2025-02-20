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
        // 첫 번째 사용자일 경우 메시지를 보내지 않음
        if (!connList.isEmpty()) {
            broadcastMessage("새로운 사용자가 로그인했습니다.");
        }
        connList.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        connList.remove(session);
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        broadcastMessage(message);
    }

    // 모든 클라이언트에게 메시지 전송
    private void broadcastMessage(String message) {
        for (Session client : connList) {
            try {
                client.getBasicRemote().sendText(message);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
