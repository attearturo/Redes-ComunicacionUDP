package Cliente;
import processing.core.*;

import java.io.IOException;
import java.net.*;
import java.util.Observable;
import java.util.Observer;


public class ComunicacionCliente extends Observable implements Runnable {
	private DatagramSocket puerta;
	private final int PORT = 4000;
	
	public ComunicacionCliente(){
		try {
			System.out.println("Iniciando socket en el puerto: "+PORT);
			puerta = new DatagramSocket(PORT);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void enviarMensaje(byte[] datos, InetAddress IPdestino, int puertodestino){
		DatagramPacket paquete = new DatagramPacket(datos, datos.length, IPdestino, puertodestino);
		
		try {
			puerta.send(paquete);
			System.out.println("Los datos fueron enviado");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public DatagramPacket recibirMensaje() {

		byte[] buzon = new byte[1024];
		DatagramPacket paquete = new DatagramPacket(buzon, buzon.length);

		try {

			puerta.receive(paquete);
			System.out.println("Datos recibidos desde " + paquete.getAddress() + ": " + paquete.getPort());
			return paquete;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	


	@Override
	public void run() {
		while (true) {
			if (puerta != null) {
				DatagramPacket data = recibirMensaje();
				if (data != null) {
					setChanged();
					notifyObservers(data);
					clearChanged();
				}
			}
		}

	}
}
