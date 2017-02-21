package Servidor;
import java.net.*;
import java.util.Observable;
import java.util.Observer;
import Servidor.ComunicacionServidor;
import processing.core.*;

public class LogicaServidor implements Observer {
	private PApplet app;
	private ComunicacionServidor comunicacionServidor;
	private InetAddress IPcliente;
	private int puerto;
	private DatagramPacket paquete;
	private int mouseX, mouseY;

	public LogicaServidor(PApplet app) {
		this.app = app;
		this.mouseX = app.width / 2;
		this.mouseY = app.width / 2;

		this.comunicacionServidor = new ComunicacionServidor();
		this.comunicacionServidor.addObserver(this);
		(new Thread(this.comunicacionServidor)).start();

		this.puerto = 4000;

		
			try {
				this.IPcliente = InetAddress.getByName("localhost");
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public void update(Observable o, Object data) {
		DatagramPacket paqueteRecibido = (DatagramPacket) data;
		String datosDefinitivos = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());

		String[] posiciones = datosDefinitivos.split(":");

		mouseX = Integer.parseInt(posiciones[0]);
		mouseY = Integer.parseInt(posiciones[1]);

		System.out.println("mouseX: " + mouseX + "mouseY: " + mouseY);
	}

	public void draw(){
		app.background(230);
		app.fill(20);
		app.ellipse(mouseX, mouseY, 50, 50);
	}
	 
	public void mouse(){
		String mensajeGenerado = app.mouseX+":"+app.mouseY;
		byte[] algo = mensajeGenerado.getBytes();
		comunicacionServidor.enviarMensaje(algo, IPcliente, puerto);
	}
}
