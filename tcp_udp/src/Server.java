import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

public class Server {

    public static void main(String args[]) throws IOException {

        String protocol = "UDP";
        String server = "127.0.0.1";
        int port = 9091;
        if (args.length > 0) {
            HashMap<String, String> argumentos = new HashMap<>();

            for (int i = 0; i < args.length; i += 2) {
                if (i + 1 < args.length) {
                    argumentos.put(args[i], args[i + 1]);
                }
            }
            protocol = argumentos.get("protocol");
            server = argumentos.get("server");
            port = Integer.parseInt(argumentos.get("port"));
        }

        if (protocol.equals("TCP")) {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket clientSocket = serverSocket.accept();
            InetAddress c_address = clientSocket.getInetAddress();
            InetAddress s_address = serverSocket.getInetAddress();
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            while (true) {
                try {


                    String input = reader.readLine();
                    System.out.println("> " + c_address.getHostAddress() + " client " + "[" + LocalDate.now() + " " + LocalTime.now() + "] " + protocol + ": " + input);
                    if (input.equals("EXIT")) {
                        writer.println(input);
                        clientSocket.close();
                        break;
                    }
                    String respuesta = realizaOperacion(input);

                    System.out.println("< " + s_address.getHostAddress() + " server " + "[" + LocalDate.now() + " " + LocalTime.now() + "] " + protocol + ": " + respuesta);
                    writer.println(respuesta);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (protocol.equals("UDP")) {
            DatagramSocket socket = new DatagramSocket(port);
            byte[] input = new byte[65535];
            DatagramPacket receivePacket = null;
            while (true) {
                try {
                    receivePacket = new DatagramPacket(input, input.length);
                    socket.receive(receivePacket);
                    InetAddress c_address = receivePacket.getAddress();
                    int port_c = receivePacket.getPort();

                    String mess = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    System.out.println("< " + c_address + " client " + "[" + LocalDate.now() + " " + LocalTime.now() + "] " + protocol + ": " + mess);

                    if (mess.equals("EXIT")) {
                        break;
                    }

                    String resS = realizaOperacion(mess);
                    System.out.println("> " + receivePacket.getAddress() + " server " + "[" + LocalDate.now() + " " + LocalTime.now() + "] " + protocol + ": " + resS);
                    byte[] sendData = resS.getBytes(StandardCharsets.UTF_8);
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, c_address, port_c);
                    socket.send(sendPacket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static StringBuilder data(byte[] a) {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0) {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }

    public static String realizaOperacion(String operacion) {
        String[] messageParts = operacion.split("(?=[-+*/%])|(?<=[-+*/%])");
        int number1 = Integer.parseInt(messageParts[0]);
        String operator = messageParts[1];
        int number2 = Integer.parseInt(messageParts[2]);
        int result = 0;

        if (number2 == 0) return "Esta operación es inválida";

        switch (operator) {
            case "+":
                result = number1 + number2;
                break;
            case "-":
                result = number1 - number2;
                break;
            case "*":
                result = number1 * number2;
                break;
            case "/":
                result = number1 / number2;
                break;
            case "%":
                result = number1 % number2;
        }
        return "" + result;
    }
}
