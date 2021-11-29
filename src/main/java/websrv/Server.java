package websrv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Server extends Thread {
	protected Socket clientSocket;
	private String formatedHtml;
	OutputStream outputStream =null;
	
	public Server(Socket clientSocket,String webroot) {
		
		this.clientSocket=clientSocket;
		this.formatedHtml="HTTP/1.1 200 OK"+"\n\r"+"Content-Lenght" + webroot.getBytes().length+"\n\r"+"\n\r"+"\n\r"+webroot+"\n\r"+"\n\r";
		
		
		
		
		start();
	}
	
	
	public void run() {
		
		
		System.out.println("New Communication Thread Started");

		try {
			
			outputStream = clientSocket.getOutputStream();
			
			
			
			
			outputStream.write(formatedHtml.getBytes());
			
			outputStream.close();
			clientSocket.close();
		} catch (IOException e) {
			System.err.println("Problem with Communication Server");
			System.exit(1);
		}
	}
}
