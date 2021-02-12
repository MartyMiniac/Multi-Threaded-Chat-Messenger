package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;

import socketServer.Server;

public class ServerGUI extends Gui implements ActionListener {
	private JButton startBTN;
	private JButton stopBTN;
	private JButton respondBTN;
	private JButton onlineUserBTN;
	private String myip;
	private Thread serverThread;
	private Server serverObject;
	public ServerGUI() {
		super("Server");
		
		frame.setSize(580, 590);

		startBTN = new JButton("Start the Server");
		startBTN.setBounds(5, 5, 150, 20);
		
		stopBTN = new JButton("Stop the Server");
		stopBTN.setBounds(305, 5, 150, 20);
		
		respondBTN = new JButton("Respond");
		respondBTN.setBounds(5, 30, 150, 20);
		
		onlineUserBTN = new JButton("Online User");
		onlineUserBTN.setBounds(305, 30, 150, 20);

		frame.add(startBTN);
		frame.add(stopBTN);
		frame.add(respondBTN);
		frame.add(onlineUserBTN);
		
		stopBTN.setEnabled(false);
		respondBTN.setEnabled(false);
		onlineUserBTN.setEnabled(false);
		
		startBTN.addActionListener(this);
		stopBTN.addActionListener(this);
		respondBTN.addActionListener(this);
		onlineUserBTN.addActionListener(this);
		
		frame.remove(sendBTN);
		frame.remove(chatBox);
		
		myip="Unable to resolve Host IP Address";
		try {
			myip="Server IP : "+InetAddress.getLocalHost().toString();
		}
		catch(UnknownHostException e) {
			System.err.println("Unable to get Host IP Address");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==startBTN) {
			startBTNFunc();
		}
		if(e.getSource()==stopBTN) {
			stopBTNFunc();
		}
		if(e.getSource()==respondBTN) {
			Server.send("This Message is From the Server");
		}
		if(e.getSource()==onlineUserBTN) {
			Server.showPoolSize();
		}
	}
	
	private void startBTNFunc() {
		serverObject = new Server();
		serverThread = new Thread(serverObject);
		serverThread.start();
		startBTN.setEnabled(false);
		stopBTN.setEnabled(true);
		respondBTN.setEnabled(true);
		onlineUserBTN.setEnabled(true);
		addMsg(myip);		
	}
	
	private void stopBTNFunc()  {
		try {
			Server.stop();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		serverThread.interrupt();
		startBTN.setEnabled(true);
		stopBTN.setEnabled(false);
		respondBTN.setEnabled(false);
		onlineUserBTN.setEnabled(false);	
		
	}
}
