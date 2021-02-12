package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Gui {
	JFrame frame;
	private static JTextArea chatArea;
	JTextField chatBox;
	JScrollPane sp;
	JButton sendBTN;
	public Gui(String title) {
		frame = new JFrame(title);
		
		chatArea = new JTextArea();
		sp = new JScrollPane(chatArea);
		sp.setBounds(5, 55, 550, 490);
		chatArea.setEditable(false);
		
		chatBox = new JTextField();
		chatBox.setBounds(5, 555, 400, 20);
		
		sendBTN = new JButton("Send");
		sendBTN.setBounds(410, 555, 150, 20);
		
		frame.add(sp);
		frame.add(chatBox);
		frame.add(sendBTN);
		
		frame.setSize(580, 620);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void addMsg(String str) {
		chatArea.setText(chatArea.getText()+"\n"+str);
		System.out.println(str);
	}
	public static void clearMessage() {
		chatArea.setText("");
	}
}
