import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * PortForwarder accepts client connections and forwards data between client
 * sockets and server sockets
 */
public class PortForwarder
{
	public static final int SOURCE_PORT = 8080;
	public static final String DESTINATION_HOST = "127.0.0.1";
	public static int DESTINATION_PORTS[] =
	{ 8888, 9999 };
	public static int serverHitcount = 0;
	public static int serverAmount = DESTINATION_PORTS.length;

	public static void main(String[] args) throws IOException
	{
		ServerSocket serverSocket = new ServerSocket(SOURCE_PORT);

		while (true)
		{
			Socket clientSocket = serverSocket.accept();
			ClientThread clientThread = new ClientThread(clientSocket);
			clientThread.start();
		}
	}

	public static int getServer()
	{
		if (serverHitcount > serverAmount)
		{
			serverHitcount = 1;
		}
		return ((serverAmount + 1) % serverHitcount);
	}
}