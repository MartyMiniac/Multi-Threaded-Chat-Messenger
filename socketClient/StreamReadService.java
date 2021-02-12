package socketClient;

import java.io.DataInputStream;
import java.io.IOException;

import gui.ClientGUI;

public class StreamReadService implements Runnable {
	DataInputStream dis;
	public StreamReadService(DataInputStream dis) {
		this.dis=dis;
	}
	@Override
	public void run() {
		while(true) {
			try {
				String str=dis.readUTF();
				ClientGUI.addMsg(str);
			}
			catch(IOException e) {
				break;
			}
		}
	}

}
