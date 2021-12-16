package websrv;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class Server extends Thread {
	protected Socket clientSocket;
	private String formatedHtml;
	OutputStream outputStream =null;
	public static String SERVER_STATUS = "STOP_SERVER";
	
	public Server(Socket clientSocket,String webroot) {
		
		this.clientSocket=clientSocket;
		this.formatedHtml="HTTP/1.1 200 OK"+"\n\r"+"Content-Lenght" + webroot.getBytes().length+"\n\r"+"\n\r"+"\n\r"+webroot+"\n\r"+"\n\r";
		if(SERVER_STATUS.equals("EXIT")) System.exit(1);
		if(SERVER_STATUS.equals("RUN_SERVER")) start();
		if(SERVER_STATUS.equals("STOP_SERVER")) StopServer();
		
		
		
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

	public static void initializeServer() {

		System.out.println("Enter SERVER STATUS:\t0: STOP\t1: MAINTENANCE\t2: RUN\t9: EXIT\n");
		System.out.println("CURRENT SERVER STATUS: " + SERVER_STATUS);
		Scanner myObj = new Scanner(System.in, "UTF-8");
		if(myObj.nextLine().equals("0")) SERVER_STATUS = "STOP_SERVER";
		if(myObj.nextLine().equals("2")) SERVER_STATUS = "RUN_SERVER";
		if(myObj.nextLine().equals("9")) SERVER_STATUS = "EXIT";
		System.out.println("\nNEW CURRENT SERVER STATUS: " + SERVER_STATUS + "\n");

		if(!SERVER_STATUS.equals("EXIT")) initializeServer();
	}

	private void StopServer() {
		// do nothing
	}
}
