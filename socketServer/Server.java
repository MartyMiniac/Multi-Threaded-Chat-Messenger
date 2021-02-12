package socketServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import gui.ServerGUI;

public class Server implements Runnable {
	protected static ServerSocket ss;
	protected static Socket s;
	private static Thread t;
	protected static ArrayList<DataOutputStream> al;
	protected static int poolSize=0;
	private static boolean loop;
	
	public Server() {
		loop=true;
	}
	
	@Override
	public void run() {
		ServerGUI.addMsg("Server Started");
		al = new ArrayList<DataOutputStream>();
		try {
			ss = new ServerSocket(8080);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			while(loop) {
				try {
					s = ss.accept();
				}
				catch(SocketException e) {
					break;
				}
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());
				t = new Thread(new Service(new DataInputStream(s.getInputStream()),al.size()));
				t.start();
				al.add(dos);
				poolSize++;
				ServerGUI.addMsg("Number of Connected Clients "+poolSize);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static void send(String str) {
		for(DataOutputStream dos:al) {
			if(dos!=null) {
				try {
					dos.writeUTF(str);
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void removeClient(int id) {
		al.set(id, null);
		ServerGUI.addMsg("SERVER : Client ID "+id+" is Disconnected");
		poolSize--;
		ServerGUI.addMsg("SERVER : Number of Connected Clients "+poolSize);
	}
	public static void stop() throws IOException {
			loop=false;
			ss.close();
			ServerGUI.addMsg("Server Stoped");
	}
	public static void showPoolSize() {
		ServerGUI.addMsg("SERVER : Number of Connected Clients "+poolSize);
	}
}
