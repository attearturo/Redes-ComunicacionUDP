package Servidor;
import java.net.*;

import processing.core.*;

public class Servidor extends PApplet {
	private LogicaServidor servidor;
	
	static public void main(String[] passedArgs) {
		String[] appletArgs = new String[] { "Servidor.Servidor" };
		if (passedArgs != null) {
			PApplet.main(concat(appletArgs, passedArgs));
		} else {
			PApplet.main(appletArgs);
		}
	}
	

	public void settings() {
		size(500, 500);
	}

	public void setup() {
		servidor = new LogicaServidor(this);
	}

	public void draw() {
		servidor.draw();
	}
	
	@Override
	public void mouseDragged(){
		servidor.mouse();
	}

	@Override
	public void keyPressed() {
		if (key == ' ') {
			try {
				System.out.println("Host addr: " + InetAddress.getLocalHost().getHostAddress());
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
	}

}
