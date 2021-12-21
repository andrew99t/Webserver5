package websrv;

import junit.framework.TestCase;
import junit.framework.TestResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest extends TestCase {

    Server server =null;
    String webroot= null;


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testRun() {
    }

    @Test
    public void Server() throws IOException {
        ServerSocket serverSocket = new ServerSocket(10019);
        Socket clientSocket = serverSocket.accept();
        server = new Server(clientSocket, webroot);
    }

    @Test
    public void InitializeServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(10013);
        Socket clientSocket = serverSocket.accept();
        server = new Server(clientSocket, webroot);
        server.initializeServer();
        server.SERVER_STATUS = "STOP_SERVER";
    }

    public TestResult run() {


        return null;
    }


}