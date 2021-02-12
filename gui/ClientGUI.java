package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import socketClient.Client;

public class ClientGUI extends Gui implements ActionListener  {
	private JTextField ipaddress;
	private JTextField username;
	private JButton connectBTN;
	private JButton setNameBTN;
	
	private static boolean connected;
	
	public ClientGUI() {
		super("Client");

		ipaddress = new JTextField("");
		ipaddress.setBounds(5, 5, 400, 20);
		
		connectBTN = new JButton("Connect");
		connectBTN.setBounds(410, 5, 150, 20);
		
		username = new JTextField("");
		username.setBounds(5, 25, 400, 20);
		
		setNameBTN = new JButton("Set Name");
		setNameBTN.setBounds(410, 25, 150, 20);

		frame.add(ipaddress);
		frame.add(connectBTN);
		
		frame.add(username);
		frame.add(setNameBTN);
		
		connectBTN.setEnabled(false);
		sendBTN.setEnabled(false);
		
		connectBTN.addActionListener(this);
		setNameBTN.addActionListener(this);
		sendBTN.addActionListener(this);
		
		connected=false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==connectBTN) {
			connectFunc();
		}
		if(e.getSource()==sendBTN) {
			sendFunc();
		}
		if(e.getSource()==setNameBTN) {
			setNameFunct();
		}
	}

	private void setNameFunct() {
		username.setEnabled(false);
		setNameBTN.setEnabled(false);
		connectBTN.setEnabled(true);
		frame.setTitle("Client - "+username.getText());
	}

	private void sendFunc() {
		Client.send(username.getText()+" : "+chatBox.getText());
	}

	private void connectFunc() {
		if(connected) {
			Client.disconnect();
			frame.setTitle("Client - "+username.getText()+" : Disconnected");
			connectBTN.setText("Connect");
			sendBTN.setEnabled(false);
			connected=false;
		}
		else {
			if(Client.connect(ipaddress.getText())) {
				clearMessage();
				frame.setTitle("Client - "+username.getText()+" : Connected");
				connectBTN.setText("Disconnect");
				sendBTN.setEnabled(true);
				connected=true;
			}
			else {
				addMsg("ERROR : Connection Failed, Please check the Entered IP Address");
			}
		}
	}
}
