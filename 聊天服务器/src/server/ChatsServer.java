package server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class Server{
	ServerSocket  ss;
	List<Socket> sockets;
	public void start(){
		try {
			ss = new ServerSocket(38383);
			sockets = new ArrayList<Socket>();
			while(true){
				Socket s = ss.accept();
				sockets.add(s);
				new Task(s).run();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public class Task extends Thread{
		Socket socket;
		public  Task(Socket s){
			this.socket = s;
		}
		@Override
		public void run() {
			try {
				InputStream in = socket.getInputStream();
				BufferedReader br =  new BufferedReader(new InputStreamReader(in,"utf-8"));
				while(true){
					String line = br.readLine();
					if(line == null){
						br.close();
						throw new RuntimeException("客户端已关闭");
					}
					for(Socket s:sockets){
						OutputStream out = s.getOutputStream();
						PrintWriter pw = new PrintWriter(new OutputStreamWriter(out,"utf-8"),true);
						pw.println(line);
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					socket.close();
					sockets.remove(socket);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}

public class ChatsServer {
	public static void main(String[] args) {
		new Server().start();
	}
}
