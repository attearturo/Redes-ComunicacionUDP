package Cliente;
import java.net.*;
import processing.core.*;

public class MainCliente extends PApplet {
	private LogicaCliente cliente;
	private int  x, y;

	static public void main(String[] passedArgs) {
		String[] appletArgs = new String[] { "Cliente.MainCliente" };
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
		cliente = new LogicaCliente(this);
	}

	public void draw() {
		cliente.draw();
	}
	
	@Override
	public void mouseDragged(){
		cliente.mouse();
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
