import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.io.*;
import java.net.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.*;
import java.nio.ByteBuffer;


public class Server {
    private DatagramSocket socket;
    private Integer nThreads, portServer, delay;
    private ThreadPoolExecutor pool;
    static Logger LOGGER = Logger.getLogger("Server");

    private Server(Integer nThreads, Integer portServer, Integer delay) {
        this.nThreads = nThreads;
        this.portServer = portServer;
        this.delay = delay;
    }

    private void start() throws IOException {
        socket = new DatagramSocket(portServer);
        pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(nThreads);
        for (int i = 0; i < nThreads; i++) {
            ThreadServer threadServer = new ThreadServer(i, socket, delay);
            pool.execute(threadServer);
        }
        pool.shutdown();
    }

    public static void main(String[] args) throws SocketException {
        Server newServer = new Server( 5, 53, 3000 );
        try {
            newServer.start();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}