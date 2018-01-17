package org.crm.web;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import net.sf.json.JSONObject;
import org.po.Message;
import org.po.Web_User;

@ServerEndpoint(value="/chat",configurator=GetHttpSessionConfigurator.class)
public class ChatRoom {
	//记录当前的在线的人数
	private static int onlineCount;
	//set集合存放每个用户的当前ChatRoom对象
	private static Map<String,ChatRoom> chat = new ConcurrentHashMap<String,ChatRoom>();
	//通过session来向客户端发送消息
	private Session session;
	//储存用户的session
	private HttpSession httpSession;
	//当前用户对象
	private Web_User user;
	
	@OnOpen
	public void openChat(Session session,EndpointConfig config) {
		this.session = session;
		httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
		user = (Web_User) httpSession.getAttribute("user");
		//添加当前对象
		chat.put(user.getNick(), this);
		//用户上线
		onlineCount++;
		System.out.println(user.getNick()+"上线了");
	}
	@OnClose
	public void closeChat() {
		//删除当前对象
		chat.remove(user.getNick());
		//用户下线
		onlineCount--;
		System.out.println(user.getNick()+"下线了");
	}
	@OnMessage
	public void sendMsg(String msg,Session session) {
		//解析json串
		JSONObject jsonObject = JSONObject.fromObject(msg);
		String fromName = jsonObject.getString("fromName");
		String toName = jsonObject.getString("toName");
		String content = jsonObject.getString("content");
		Iterator<Entry<String, ChatRoom>> it = chat.entrySet().iterator();
		Gson gson = new Gson();
		while(it.hasNext()) {
			Entry<String, ChatRoom> entry = it.next();
			//获取集合中的用户昵称
			String nick = entry.getKey();
			//获取集合中用户的ChatRoom对象
			ChatRoom chatRoom = entry.getValue();
			//发送给指定的用户
			if(fromName.equals(nick) || toName.equals(nick)) {
				Message maessageFrom = new Message(fromName,content);
				chatRoom.sendToMessage(gson.toJson(maessageFrom));

			}
		}
	}
	//发送消息
	public void sendToMessage(String msg) {
		try {
			System.out.println(msg);
			this.session.getBasicRemote().sendText(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@OnError
	public void errorChat(Session session,Throwable error) {
		System.out.println("error");
		error.printStackTrace();
	}
	
	
}
