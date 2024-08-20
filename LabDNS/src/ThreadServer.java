import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;

public class ThreadServer implements Runnable {
    private Integer nThreadServer;
    byte[] input = new byte[65535];
    private Integer delay;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmm");
    private DatagramSocket socket;


    public ThreadServer(Integer id, DatagramSocket socket, Integer delay) {
        this.nThreadServer = id;
        this.socket = socket;
        this.delay = delay;
    }

    @Override
    public void run() {
        byte[] input = new byte[65535];
        DatagramPacket receivePacket = null;
        while (true) {
            try {
                receivePacket = new DatagramPacket(input, input.length);
                socket.receive(receivePacket);
                InetAddress c_address = receivePacket.getAddress();
                int port_c = receivePacket.getPort();
                String mess = new String(receivePacket.getData());
                System.out.println(mess);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }







}
