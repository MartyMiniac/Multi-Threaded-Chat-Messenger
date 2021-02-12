package test;

import socketServer.Server;

public class ServerTest {
	public static void main(String args[]) {
		Server s = new Server();
		Thread t = new Thread(s);
		t.start();
	}
}
