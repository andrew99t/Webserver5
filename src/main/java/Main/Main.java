package Main;

import websrv.Server;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

import static websrv.Server.initializeServer;

public class Main {
	public static ServerSocket serverSocket = null;

	public static void main(String[] args) throws IOException {

		String Server_Root = "src/main/resources/HTML/Index.html";
		//String html="<html><head><title>Server TITI</title></head><body><ch1>pagina de test</ch1></body></html>";
		Scanner scanner = null;
		String Root_html=null;
		try {
			scanner = new Scanner(new File(Server_Root));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Root_html = scanner.useDelimiter("\\Z").next();
		scanner.close();
		Thread startServer=new Thread() {
			public void run() {
				initializeServer();
			}
		};
		startServer.start();

		try {
			serverSocket = new ServerSocket(10008);
			System.out.println("Connection Socket Created");
			try {
				while (true) {
					System.out.println("Waiting for Connection");
					new Server(serverSocket.accept(), Root_html);
				}
			} catch (IOException e) {
				System.err.println("Accept failed.");
				System.exit(1);
			}
		} catch (IOException e) {
			System.err.println("Could not listen on port: 10008.");
			System.exit(1);
		} finally {
			try {
				serverSocket.close();
			} catch (IOException e) {
				System.err.println("Could not close port: 10008.");
				System.exit(1);
			}
		}
	}
}
