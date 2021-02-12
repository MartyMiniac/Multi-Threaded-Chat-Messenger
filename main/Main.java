package main;

import java.io.IOException;

public class Main {
	public static void main(String args[]) throws IOException {
		switch(args[0].toLowerCase().trim()) {
		case "server":
			MainServer.run();
			break;
		case "client":
			MainClient.run();
			break;
		default:
				System.out.println("Invalid Operation");
		}
	}
}
