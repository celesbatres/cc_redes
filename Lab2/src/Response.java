import java.awt.image.LookupOp;
import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.util.stream.*;
import java.util.logging.*;

public class Response {
    // ======================================
    // No modifica la firma de la función.
    public void sendData (Logger LOGGER, PrintStream dataOut, Integer nThreadServer, Object request) throws Exception {
        ArrayList<String> response = new ArrayList<String>();
        HashMap<Integer, String> codes = new HashMap<>();
        codes.put(200, "OK");
        codes.put(404, "Not Found");
        codes.put(500, "Internal Server Error");
        int status_code = 200;
        try {
            // ======================================
            String[] requestString = request.toString().split("\\|\\|");
            for (int i = 0; i < requestString.length; i++) {
                LOGGER.info("request: " + i + requestString[i]);
            }


            LOGGER.info("RESPUESTA" + requestString[0]);

            String[] firstLine = requestString[0].split(" ");
            String method = firstLine[0];
            String url = firstLine[1];
            String protocol = "";

            if (firstLine.length > 2) {
                url = firstLine[1];
                protocol = firstLine[2];
            } else {
                url = "index.html";
                protocol = firstLine[1];
            }


            String path = url.split("\\?")[0];


            LOGGER.info("PATHH:  "+path);
            File file = new File("./www/"+path);
            if (!file.exists()) {
                status_code = 404;
            }

            String extension = path.substring(path.lastIndexOf(".") + 1);
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


            /////////////////////////////////////////////////
            //                                             //
            //   Procesar el cuerpo de la Respuesta aquí   //
            //                                             //
            /////////////////////////////////////////////////
            String fileData = "";
            try {
                fileData = new String(Files.readAllBytes(Paths.get("./www/" + path)));
                System.out.println(fileData);
            } catch (NoSuchFileException e) {
                System.err.println("Error: El archivo no existe - " + e.getMessage());
            } catch (IOException e) {
                System.err.println("Error al leer el archivo - " + e.getMessage());
            }


//        String fileData = new String(Files.readAllBytes(Paths.get("./www/"+path)));
            fileData = fileData.replace("{fieldTest_DEMO}", "El servidor cambió esto y agregó un número aleatorio: " + (new Random()).nextInt(1000));
            byte[] bytesFileData = fileData.getBytes();


            response.add(protocol + " " + status_code + " " + codes.get(status_code));
            response.add("Content-Type: " + type);
//            response.add("ClaseCC8: Alumnos");
            response.add("Content-Length: " + fileData.length());
            response.add("");

//            response.add(fileData);
//        dataOut.write(bytesFileData, 0, bytesFileData.length);

            dataOut.print(response.stream().collect(Collectors.joining("\r\n")));
            for (byte dataBank : fileData.getBytes()) {
                dataOut.write(dataBank);
            }
            LOGGER.info("(" + nThreadServer + ") response: " + response);
            LOGGER.info("(" + nThreadServer + ") request: " + request);
            // ESTE ES UN EJEMPLO ESTATICO
            // con lo minimo en el Header
            // Y RESPONDE LO MISMO A TODO
        }catch(Exception e) {
            status_code = 500;
            response.add(status_code, codes.get(status_code));
        }
    }// sendData
}