import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.util.stream.*;
import java.util.logging.*;


public class Response {
    // ======================================
    // No modifica la firma de la función.
    public void sendData(Logger LOGGER, PrintStream dataOut, Integer nThreadServer, Object request) throws Exception {
        // ======================================
        @SuppressWarnings("unchecked")
        ArrayList<String> HTTP_request = (ArrayList<String>) request;
        ArrayList<String> response = new ArrayList<String>();
        // Ya viene spliteado

        if (HTTP_request.get(0).contains("/sendSMTP")) {
            String post_request = HTTP_request.get(HTTP_request.size() - 1);
            LOGGER.info(post_request);
            String[] parametros_post = post_request.split(";\\|;");


            String from = parametros_post[0].substring(1).split(":")[1];
            String to = parametros_post[1].split(":")[1];
            String body = parametros_post[2]+"\n"+parametros_post[3];
//            ArrayList<String> body = new ArrayList<>();
//            body.add(parametros_post[2]);
//            String[] mensaje = parametros_post[3].split("\\n");
//            for (int i = 0; i < mensaje.length; i++) {
//                body.add(mensaje[i]);
//            }
//            body.add(parametros_post[3]);

//            String body = parametros_post[2]+"\n"+parametros_post[3];
            LOGGER.info("FROMMMM: " + from);
            LOGGER.info("TOOOOOO: " + to);
//        LOGGER.info("SUBJECT: "+ subject);
//            LOGGER.info("BODYYYY: " + body);
            SMTPClient smtp = new SMTPClient();
            smtp.start();
            // Recorrer el arreglo parametros_post maybe
            // LOGGER.info("PRIMER PARAMETRO POSSTT"+parametros_post[0]);


            // response al cliente, cliente al servidor, servidor a la base de datos
            // Al cliente darle la ip al server a conectarse, el puerto, y el mensaje, el main, to y data
            /////////////////////////////////////////////////
            //                                             // 
            //   Procesar el POST del Correo aquí          //
            //   Implemente el SMTP Client                 //
            //                                           //
            /////////////////////////////////////////////////
// El client debe mandar al server con HALO
// Crear una instancia del cliente para usar un metodo y mandar al servidor

            LOGGER.info("(" + nThreadServer + ") HTTP_request.getLast() : " + HTTP_request.getLast());

            response.add("HTTP/1.1 200 OK");
            dataOut.print(response.stream().collect(Collectors.joining("\r\n")));
            smtp.sendMessage(from, to, body);
        } else {
            String fileData = new String(Files.readAllBytes(Paths.get("./www/index.html")));
            response.add("HTTP/1.1 200 OK");
            response.add("Content-Type: text/html");
            response.add("Content-Length: " + fileData.length());
            response.add("");
            response.add(fileData);
            dataOut.print(response.stream().collect(Collectors.joining("\r\n")));
            LOGGER.info("(" + nThreadServer + ") HTTP_request.get(0) : " + HTTP_request.get(0));
        }
        LOGGER.info("(" + nThreadServer + ") response: " + response);


    }// sendData
}