package socketClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private static Socket s;
	private static DataOutputStream dos;
	private static Thread t;
	public static boolean UPDATE;
	public static String BUFFER;
	public static boolean connect(String ip)  {
		Client.UPDATE=false;
		Client.BUFFER="";
		try {
			s = new Socket(ip, 8080);
			dos = new DataOutputStream(s.getOutputStream());
			t = new Thread(new StreamReadService(new DataInputStream(s.getInputStream())));
			t.start();
			return true;
		}
		catch(UnknownHostException e) {
			System.err.println("Failed to Connect");
			return false;
		}
		catch(IOException e) {
			return false;
			//e.printStackTrace();
		}
	}
	public static void send(String str) {
		try {
			dos.writeUTF(str);			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static void disconnect() {
		try {
			s.close();		
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}