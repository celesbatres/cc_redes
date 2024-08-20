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
////        byte[] input = new byte[65535];
////        DatagramPacket receivePacket = null;
//        while (true) {
//            try {
//                receivePacket = new DatagramPacket(input, input.length);
////                socket.receive(receivePacket);
//                InetAddress c_address = receivePacket.getAddress();
//                int port_c = receivePacket.getPort();
//                String mess = new String(receivePacket.getData());
////                System.out.println("< " + c_address + " client " + "[" + LocalDate.now() + " " + LocalTime.now() + "]" +
////                        " UDP: " + mess);
//
//                if (mess.equals("EXIT")) {
//                    break;
//                }
//
////                LOGGER.info("Received DNS query from " + receivePacket.getAddress().getHostAddress());
//
////                LOGGER.info("< "+mess);
////                byte[] response = handleQuery(receivePacket.getData(), receivePacket.getLength());
//                LOGGER.info("Query: "+mess);
//                LOGGER.info("Package HEX: " + bytesToHex(receivePacket.getData(), receivePacket.getLength()));
//                LOGGER.info("Filtrated: " + filterPrintableChars(receivePacket.getData(), receivePacket.getLength()));
//                // Enviar la respuesta de vuelta al cliente
////                DatagramPacket responsePacket = new DatagramPacket(response, response.length, packet.getAddress(), packet.getPort());
////                socket.send(responsePacket);
//
//                String resS = "Respuesta del Servidor";
////                System.out.println("> " + receivePacket.getAddress() + " server " + "[" + LocalDate.now() + " " + LocalTime.now() + "] UDP: " + resS);
//                byte[] sendData = resS.getBytes(StandardCharsets.UTF_8);
//                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, c_address, port_c);
//                socket.send(sendPacket);
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
    }

    private static String bytesToHex(byte[] bytes, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(String.format("%x ", bytes[i]));
        }
        return sb.toString().trim();
    }

    private static String filterPrintableChars(byte[] data, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = (char) data[i];
            if (c >= 32 && c <= 126) { // Imprimible ASCII range
                sb.append(c);
            } else {
                sb.append('.');
            }
        }
        return sb.toString();
    }

    private static byte[] handleQuery(byte[] query, int queryLength) {
        // Crear un array de bytes para la respuesta
        byte[] response = new byte[512];

        // Copiar el identificador de la consulta a la respuesta
        response[0] = query[0];
        response[1] = query[1];

        // Flags: Respuesta estándar, no autoritativa, no truncada, sin errores
        response[2] = (byte) 0x81;
        response[3] = (byte) 0x80;

        // Número de preguntas: 1
        response[4] = 0x00;
        response[5] = 0x01;

        // Número de respuestas: 1
        response[6] = 0x00;
        response[7] = 0x01;

        // Número de registros de autoridad: 0
        response[8] = 0x00;
        response[9] = 0x00;

        // Número de registros adicionales: 0
        response[10] = 0x00;
        response[11] = 0x00;

        // Copiar la sección de preguntas desde la consulta a la respuesta
        System.arraycopy(query, 12, response, 12, queryLength - 12);

        int responseIndex = queryLength;

        // Respuesta: Puntero al nombre de dominio (0xc00c)
        response[responseIndex++] = (byte) 0xc0;
        response[responseIndex++] = 0x0c;

        // Tipo de registro: A (0x0001)
        response[responseIndex++] = 0x00;
        response[responseIndex++] = 0x01;

        // Clase: IN (0x0001)
        response[responseIndex++] = 0x00;
        response[responseIndex++] = 0x01;

        // TTL (Time to Live): 300 segundos (0x0000012c)
        response[responseIndex++] = 0x00;
        response[responseIndex++] = 0x00;
        response[responseIndex++] = 0x01;
        response[responseIndex++] = 0x2c;

        // Longitud de datos: 4 bytes (IPv4 address)
        response[responseIndex++] = 0x00;
        response[responseIndex++] = 0x04;

        // Dirección IP (por ejemplo, 192.168.1.1)
        response[responseIndex++] = (byte) 192;
        response[responseIndex++] = (byte) 168;
        response[responseIndex++] = 1;
        response[responseIndex++] = 1;

        return response;
    }
}