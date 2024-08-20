import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.util.logging.*;

public class Request {
    // ======================================
    // No modifica la firma de la función.
    public Object getData (Logger LOGGER, BufferedReader dataIn, Integer nThreadServer) throws Exception  {
        // ======================================

        ArrayList<String> request = new ArrayList<String>();
        Integer contentLength = 0;
        String line;
        int i = 0;
        String firstLine = "";
        while ((line = dataIn.readLine()) != null) {
            if (line.trim().isEmpty()) break;
            if(i==0){
                firstLine = line;
            }
            if ((line.split(":"))[0].trim().equals("Content-Length")) contentLength = Integer.valueOf((line.split(":"))[1].trim());
            request.add(line);
            i++;
        }// while line
        /// POST Body
        String[] tokens = firstLine.split(" ");

        String method = tokens[0];
        String url, path, protocol;

        if(tokens.length > 2) {
            url = tokens[1];
            LOGGER.info("MENSAJE:["+i+"] "+url);
            protocol = tokens[2];
        }else{
            url = "index.html";
            protocol = tokens[1];
        }

        path = url.split("\\?")[0];
//        LOGGER.info("MENSAJE:["+i+"] "+path);

        String extension = path.substring(path.lastIndexOf(".")+1);
        HashMap<String, String> mimeTypes = new HashMap<String, String>();
        mimeTypes.put("html", "text/html");
        mimeTypes.put("css", "text/css");
        mimeTypes.put("ttf", "font/ttf");
        mimeTypes.put("otf", "application/font-sfnt");
        mimeTypes.put("woff", "application/font-woff");
        mimeTypes.put("woff2", "font/woff2");
        mimeTypes.put("txt", "text/plain");
        mimeTypes.put("xml", "application/xml");
        mimeTypes.put("js", "application/javascript");
        mimeTypes.put("json", "application/json");
        mimeTypes.put("jpg", "image/jpeg");
        mimeTypes.put("jpeg", "image/jpeg");
        mimeTypes.put("png", "image/png");
        mimeTypes.put("gif", "image/gif");
        mimeTypes.put("bmp", "image/bmp");
        mimeTypes.put("webp", "image/webp");
        mimeTypes.put("svg", "image/svg+xml");
        mimeTypes.put("ico", "image/x-icon");
        mimeTypes.put("pdf", "application/pdf");
        mimeTypes.put("zip", "application/zip");
        mimeTypes.put("rar", "application/x-rar-compressed");
        mimeTypes.put("tar", "application/x-tar");
        mimeTypes.put("gz", "application/gzip");
        mimeTypes.put("mp3", "audio/mpeg");
        mimeTypes.put("wav", "audio/wav");
        mimeTypes.put("mp4", "video/mp4");
        mimeTypes.put("avi", "video/x-msvideo");
        mimeTypes.put("mov", "video/quicktime");
        mimeTypes.put("ogg", "application/ogg");
        mimeTypes.put("eot", "application/vnd.ms-fontobject");


        String type = mimeTypes.get(extension);

        char[] inBuffer = new char[contentLength];
        int inputMessageLength = dataIn.read(inBuffer, 0, contentLength);
        String inputMessage = new String(inBuffer, 0, inputMessageLength);
        LOGGER.info("MENSAJE:= "+inputMessage);
        request.add(inputMessage); // Agregar Post Body
//        LOGGER.info("(" + nThreadServer + ") request: " + request );

        /////////////////////////////////////////////////
        //                                             //
        //   Procesar el cuerpo de la solicitud aquí   //
        //                                             //
        /////////////////////////////////////////////////
        String rq = request.stream().collect(Collectors.joining(" || ")); // UN EJEMPLO
//        String saltoLinea = "\n";

//        request.add("\n");


//● Procesar las solicitudes HTTP que llegan al servidor.

//● Leer tanto la cabecera como el cuerpo de la solicitud.

//● Clasificar los métodos de solicitud (GET, POST, HEAD).
//● Almacenar las cabeceras en una estructura adecuada (por
//                ejemplo, HashMap o ArrayList).
//● Extraer correctamente el cuerpo de la solicitud y manejar
//        diferentes tipos de contenido (multipart/form-data,
//                application/json, etc.).
//● Analizar los encabezados recibidos para pasar la
//        información solicitada a la clase Response.
//● Manejar errores de manera adecuada para devolver
//        respuestas de error según sea necesario.

        return rq; // Retornar la lista o procesar según sea necesario en Response.java

    }// getData
}