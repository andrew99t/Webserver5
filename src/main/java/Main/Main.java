package Main;

import websrv.Server;

import java.io.IOException;
import java.net.ServerSocket;

import static websrv.Server.initializeServer;

public class Main {

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;

		String html="<html><head><title>Server Tiution Andrei</title></head><body><ch1>pagina de test</ch1></body></html>";
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
					new Server(serverSocket.accept(),html);
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
