import java.io.*;
import java.net.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Scanner;

public class Client {

    public static void main(String args[]) throws IOException
    {

        String protocol = "UDP";
        String server = "127.0.0.1";
        int port = 9091;
        if(args.length>0){
            HashMap<String, String> argumentos = new HashMap<>();

            for (int i = 0; i < args.length; i+=2) {
                if (i+1<args.length){
                    argumentos.put(args[i], args[i+1]);
                }
            }
            protocol = argumentos.get("protocol");
            server = argumentos.get("server");
            port = Integer.parseInt(argumentos.get("port"));
        }

        // Seteo de Consola
        try{
            if(protocol.equals("TCP")){
                Socket socket = new Socket(server, port);
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                InetAddress ip_server = socket.getInetAddress();
                InetAddress ip_client = socket.getLocalAddress();

                while(true){
                    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    System.out.print("Ingrese la operacion a realizar: ");
                    String input = reader.readLine();
                    System.out.println("< " + ip_client + " client " + "[" + LocalDate.now() + " " + LocalTime.now() + "] "+ protocol + ": "+input);

                    // Enviar el mensaje al servidor
                    writer.println(input);

                    String respuesta = serverReader.readLine();
                    if(respuesta.equals("EXIT")){
                        socket.close();
                        break;
                    }
                    System.out.println("> " + ip_server + " server " + "[" + LocalDate.now() + " " + LocalTime.now() + "] "+ protocol + ": "+ respuesta);
                }
            }else if(protocol.equals("UDP")){
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                DatagramSocket ds = new DatagramSocket();
                InetAddress ip = InetAddress.getLocalHost();
                byte buffer[] = null;

                while (true)
                {
                    System.out.print("Ingrese la operacion a realizar: ");
                    String input = reader.readLine();
                    System.out.println("< " + ip + " client " + "[" + LocalDate.now() + " " + LocalTime.now() + "] " + protocol + ": "+ input);
                    buffer = input.getBytes();

                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, ip, port);
                    ds.send(packet);
                    if (input.equals("EXIT")){
                        ds.close();
                        break;
                    }

                    byte[] respuesta = new byte[1024];
                    DatagramPacket receivePacket = new DatagramPacket(respuesta, respuesta.length);

                    ds.receive(receivePacket);
//
                    String respuestaS = new String(receivePacket.getData(), 0, receivePacket.getLength());
//                    String respuesta2 = new String(packet.getData());
                    System.out.println("> " + ip + "server " + "[" +LocalDate.now() + " " + LocalTime.now() + "] "+ protocol + ": "+ respuestaS);

                }
            }
        }catch (IOException e){
            System.out.println("Alerta! Errores encontrados durante ejecución");
        }
    }

    public static StringBuilder data(byte[] a)
    {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0)
        {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }
}
