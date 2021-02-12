package socketServer;

import java.io.DataInputStream;

import gui.ServerGUI;

public class Service implements Runnable {
	DataInputStream dis;
	private int ID;
	public Service(DataInputStream dis, int ID) {
		this.dis=dis;
		this.ID=ID;
	}
	@Override
	public void run() {
		ServerGUI.addMsg("SERVER : New Client Connected with ID "+ID);
		while(true) {
			try {
				String str=dis.readUTF();
				ServerGUI.addMsg(str);
				Server.send(str);
			}
			catch(Exception e) {
				Server.removeClient(ID);
				break;
			}
		}
	}

}
