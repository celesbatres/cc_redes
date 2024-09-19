import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    ArrayList<Console> packets = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        String quijoteStr = "Que trata de la condición y ejercicio del famoso hidalgo don Quijote de la Mancha\n" + "\n" + "En un lugar de la Mancha, de cuyo nombre no quiero acordarme, no ha mucho tiempo que vivía un hidalgo de los de lanza en astillero, adarga antigua, rocín flaco y galgo corredor. Una olla de algo más vaca que carnero, salpicón las más noches, duelos y quebrantos los sábados, lantejas los viernes, algún palomino de añadidura los domingos, consumían las tres partes de su hacienda. El resto della concluían sayo de velarte, calzas de velludo para las fiestas, con sus pantuflos de lo mesmo, y los días de entresemana se honraba con su vellorí de lo más fino. Tenía en su casa una ama que pasaba de los cuarenta, y una sobrina que no llegaba a los veinte, y un mozo de campo y plaza, que así ensillaba el rocín como tomaba la podadera. Frisaba la edad de nuestro hidalgo con los cincuenta años; era de complexión recia, seco de carnes, enjuto de rostro, gran madrugador y amigo de la caza. Quieren decir que tenía el sobrenombre de Quijada, o Quesada, que en esto hay alguna diferencia en los autores que deste caso escriben; aunque por conjeturas verosímiles se deja entender que se llamaba Quijana. Pero esto importa poco a nuestro cuento: basta que en la narración dél no se salga un punto de la verdad.\n" + "\n" + "Es, pues, de saber que este sobredicho hidalgo, los ratos que estaba ocioso, que eran los más del año, se daba a leer libros de caballerías, con tanta afición y gusto, que olvidó casi de todo punto el ejercicio de la caza, y aun la administración de su hacienda; y llegó a tanto su curiosidad y desatino en esto, que vendió muchas hanegas de tierra de sembradura para comprar libros de caballerías en que leer, y así, llevó a su casa todos cuantos pudo haber dellos; y de todos, ningunos le parecían tan bien como los que compuso el famoso Feliciano de Silva; porque la claridad de su prosa y aquellas entricadas razones suyas le parecían de perlas, y más cuando llegaba a leer aquellos requiebros y cartas de desafíos, donde en muchas partes hallaba escrito: «La razón de la sinrazón que a mi razón se hace, de tal manera mi razón enflaquece, que con razón me quejo de la vuestra fermosura». Y también cuando leía: «... los altos cielos que de vuestra divinidad divinamente con las estrellas os fortifican, y os hacen merecedora del merecimiento que merece la vuestra grandeza».\n" + "\n" + "Con estas razones perdía el pobre caballero el juicio, y desvelábase por entenderlas y desentrañarles el sentido, que no se lo sacara ni las entendiera el mesmo Aristóteles, si resucitara para sólo ello. No estaba muy bien con las heridas que don Belianís daba y recebía, porque se imaginaba que, por grandes maestros que le hubiesen curado, no dejaría de tener el rostro y todo el cuerpo lleno de cicatrices y señales. Pero, con todo, alababa en su autor aquel acabar su libro con la promesa de aquella inacabable aventura, y muchas veces le vino deseo de tomar la pluma y dalle fin al pie de la letra, como allí se promete; y sin duda alguna lo hiciera, y aun saliera con ello, si otros mayores y continuos pensamientos no se lo estorbaran. Tuvo muchas veces competencia con el cura de su lugar (que era hombre docto, graduado en Sigüenza), sobre cuál había sido mejor caballero: Palmerín de Ingalaterra, o Amadís de Gaula; mas maese Nicolás, barbero del mismo pueblo, decía que ninguno llegaba al Caballero del Febo, y que si alguno se le podía comparar, era don Galaor, hermano de Amadís de Gaula, porque tenía muy acomodada condición para todo; que no era caballero melindroso, ni tan llorón como su hermano, y que en lo de la valentía no le iba en zaga.\n" + "\n" + "En resolución, él se enfrascó tanto en su lectura, que se le pasaban las noches leyendo de claro en claro, y los días de turbio en turbio; y así, del poco dormir y del mucho leer se le secó el celebro de manera, que vino a perder el juicio. Llenósele la fantasía de todo aquello que leía en los libros, así de encantamentos como de pendencias, batallas, desafíos, heridas, requiebros, amores, tormentas y disparates imposibles; y asentósele de tal modo en la imaginación que era verdad toda aquella máquina de aquellas soñadas invenciones que leía, que para él no había otra historia más cierta en el mundo. Decía él que el Cid Ruy Díaz había sido muy buen caballero; pero que no tenía que ver con el Caballero de la Ardiente Espada, que de sólo un revés había partido por medio dos fieros y descomunales gigantes. Mejor estaba con Bernardo del Carpio, porque en Roncesvalles había muerto a Roldán el encantado, valiéndose de la industria de Hércules, cuando ahogó a Anteo, el hijo de la Tierra, entre los brazos. Decía mucho bien del gigante Morgante, porque, con ser de aquella generación gigantea, que todos son soberbios y descomedidos, él solo era afable y bien criado. Pero, sobre todos, estaba bien con Reinaldos de Montalbán, y más cuando le veía salir de su castillo y robar cuantos topaba, y cuando en allende robó aquel ídolo de Mahoma que era todo de oro, según dice su historia. Diera él, por dar una mano de coces al traidor de Galalón, al ama que tenía, y aun a su sobrina de añadidura.\n" + "\n" + "En efeto, rematado ya su juicio, vino a dar en el más extraño pensamiento que jamás dio loco en el mundo; y fue que le pareció convenible y necesario, así para el aumento de su honra como para el servicio de su república, hacerse caballero andante, y irse por todo el mundo con sus armas y caballo a buscar las aventuras y a ejercitarse en todo aquello que él había leído que los caballeros andantes se ejercitaban, deshaciendo todo género de agravio, y poniéndose en ocasiones y peligros donde, acabándolos, cobrase eterno nombre y fama. Imaginábase el pobre ya coronado por el valor de su brazo, por lo menos, del imperio de Trapisonda; y así, con estos tan agradables pensamientos, llevado del extraño gusto que en ellos sentía, se dio priesa a poner en efeto lo que deseaba. Y lo primero que hizo fue limpiar unas armas que habían sido de sus bisabuelos, que, tomadas de orín y llenas de moho, luengos siglos había que estaban puestas y olvidadas en un rincón. Limpiólas y aderezólas lo mejor que pudo, pero vio que tenían una gran falta, y era que no tenían celada de encaje, sino morrión simple; mas a esto suplió su industria, porque de cartones hizo un modo de media celada, que, encajada con el morrión, hacían una apariencia de celada entera. Es verdad que para probar si era fuerte y podía estar al riesgo de una cuchillada, sacó su espada y le dio dos golpes, y con el primero y en un punto deshizo lo que había hecho en una semana; y no dejó de parecerle mal la facilidad con que la había hecho pedazos, y, por asegurarse deste peligro, la tornó a hacer de nuevo, poniéndole unas barras de hierro por de dentro, de tal manera, que él quedó satisfecho de su fortaleza y, sin querer hacer nueva experiencia della, la diputó y tuvo por celada finísima de encaje.\n" + "\n" + "Fue luego a ver su rocín, y aunque tenía más cuartos que un real y más tachas que el caballo de Gonela, que tantum pellis et ossa fuit, le pareció que ni el Bucéfalo de Alejandro ni Babieca el del Cid con él se igualaban. Cuatro días se le pasaron en imaginar qué nombre le pondría; porque (según se decía él a sí mesmo) no era razón que caballo de caballero tan famoso, y tan bueno él por sí, estuviese sin nombre conocido; y ansí, procuraba acomodársele de manera que declarase quién había sido antes que fuese de caballero andante, y lo que era entonces; pues estaba muy puesto en razón que, mudando su señor estado, mudase él también el nombre, y le cobrase famoso y de estruendo, como convenía a la nueva orden y al nuevo ejercicio que ya profesaba; y así, después de muchos nombres que formó, borró y quitó, añadió, deshizo y tornó a hacer en su memoria e imaginación, al fin le vino a llamar Rocinante, nombre, a su parecer, alto, sonoro y significativo de lo que había sido cuando fue rocín, antes de lo que ahora era, que era antes y primero de todos los rocines del mundo.\n" + "\n" + "Puesto nombre, y tan a su gusto, a su caballo, quiso ponérsele a sí mismo, y en este pensamiento duró otros ocho días, y al cabo se vino a llamar don Quijote; de donde, como queda dicho, tomaron ocasión los autores desta tan verdadera historia que, sin duda, se debía de llamar Quijada, y no Quesada, como otros quisieron decir. Pero, acordándose que el valeroso Amadís no sólo se había contentado con llamarse Amadís a secas, sino que añadió el nombre de su reino y patria, por hacerla famosa, y se llamó Amadís de Gaula, así quiso, como buen caballero, añadir al suyo el nombre de la suya y llamarse don Quijote de la Mancha, con que, a su parecer, declaraba muy al vivo su linaje y patria, y la honraba con tomar el sobrenombre della.\n" + "\n" + "Limpias, pues, sus armas, hecho del morrión celada, puesto nombre a su rocín y confirmándose a sí " + "mismo, se dio a entender que no le faltaba otra cosa sino buscar una dama de quien enamorarse: " + "porque el caballero andante sin amores era árbol sin hojas y sin fruto y cuerpo sin alma. Decíase él: «Si yo, por malos de mis pecados, o por mi buena suerte, me encuentro por ahí con algún gigante, como de ordinario les acontece a los caballeros andantes, y le derribo de un encuentro, o le parto por mitad del cuerpo, o, finalmente, le venzo y le rindo, ¿no será bien tener a quien enviarle presentado, y que entre y se hinque de rodillas ante mi dulce señora, y diga con voz humilde y rendida: «Yo, señora, soy el gigante Caraculiambro, señor de la ínsula Malindrania, a quien venció en singular batalla el jamás como se debe alabado caballero don Quijote de la Mancha, el cual me mandó que me presentase ante vuestra merced, para que la vuestra grandeza disponga de mí a su talante»? ¡Oh, cómo se holgó nuestro buen caballero cuando hubo hecho este discurso, y más cuando halló a quien dar nombre de su dama! Y fue, a lo que se cree, que en un lugar cerca del suyo había una moza labradora de muy buen parecer, de quien él un tiempo anduvo enamorado, aunque, según se entiende, ella jamás lo supo, ni le dio cata dello. Llamábase Aldonza Lorenzo, y a ésta le pareció ser bien darle título de señora de sus pensamientos; y, buscándole nombre que no desdijese mucho del suyo, y que tirase y se encaminase al de princesa y gran señora, vino a llamarla Dulcinea del Toboso, porque era natural del Toboso; nombre, a su parecer, músico y peregrino y significativo, como todos los demás que a él y a sus cosas había puesto.0";
        String mosqueterosStr = "El primer lunes del mes de abril de 1625, el burgo de Meung, donde nació\n" + "el autor del Roman de la Rose, parecía estar en una revolución tan completa\n" + "como si los hugonotes hubieran venido a hacer de ella una segunda Rochelle.\n" + "Muchos burgueses, al ver huir a las mujeres por la calle Mayor, al oír gritar a\n" + "los niños en el umbral de las puertas, se apresuraban a endosarse la coraza y,\n" + "respaldando su aplomo algo incierto con un mosquete o una partesana, se\n" + "dirigían hacia la hostería del Franc Meunier, ante la cual bullía, creciendo de\n" + "minuto en minuto, un grupo compacto, ruidoso y lleno de curiosidad.\n" + "En ese tiempo los pánicos eran frecuentes, y pocos días pasaban sin que\n" + "una aldea a otra registrara en sus archivos algún acontecimiento de ese género.\n" + "Estaban los señores que guerreaban entre sí; estaba el rey que hacía la guerra\n" + "al cardenal; estaba el español que hacía la guerra al rey. Luego, además de\n" + "estas guerras sordas o públicas, secretas o patentes, estaban los ladrones, los\n" + "mendigos, los hugonotes, los lobos y los lacayos que hacían la guerra a todo el\n" + "mundo. Los burgueses se armaban siempre contra los ladrones, contra los\n" + "lobos, contra los lacayos, con frecuencia contra los señores y los hugonotes,\n" + "algunas veces contra el rey, pero nunca contra el cardenal ni contra el español.\n" + "De este hábito adquirido resulta, pues, que el susodicho primer lunes del mes\n" + "de abril de 1625, los burgueses, al oír el barullo y no ver ni el banderín\n" + "amarillo y rojo ni la librea del duque de Richelieu, se precipitaron hacia la hostería del Franc " + "Meunier.";

        ArrayList<String> quijote = generateList(quijoteStr, 3, 12);
        ArrayList<String> mosqueteros = generateList(mosqueterosStr, 2, 12);
        ArrayList<String> combinatedList = new ArrayList<String>(quijote);
        combinatedList.addAll(mosqueteros);
        System.out.print("\n\n\nQUIJOTE");
        for (String line : quijote) {
            System.out.println(line);
        }
        System.out.print("\n\n\nMOSQUETEROS");
        for (String line : mosqueteros) {
            System.out.println(line);
        }
        int windowSize = getModaLenghts(combinatedList);
        int serverPort = 80;

        Random random = new Random();
        int clientPort = random.nextInt(899) + 100;
        int clientSeq = random.nextInt(200000) + 100000;
        int serverSeq = random.nextInt(200000) + 500000;
        int lossPercentage = 0;
        String dirTransmision = "Client";

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String entrance;
        try {
            System.out.print("Window Size Inicial: ");
            entrance = reader.readLine();
            if (entrance.matches("-?\\d+")) {
                windowSize = Integer.parseInt(entrance);
            }

            System.out.print("Puerto del Servidor: ");
            entrance = reader.readLine();
            if (entrance.matches("-?\\d+")) {
                serverPort = Integer.parseInt(entrance);
            }

            System.out.print("Puerto del Cliente: ");
            entrance = reader.readLine();
            if (entrance.matches("-?\\d+")) {
                clientPort = Integer.parseInt(entrance);
            }

            System.out.print("Secuencia del Servidor: ");
            entrance = reader.readLine();
            if (entrance.matches("-?\\d+")) {
                serverSeq = Integer.parseInt(entrance);
            }

            System.out.print("Secuencia del Cliente: ");
            entrance = reader.readLine();
            if (entrance.matches("-?\\d+")) {
                clientSeq = Integer.parseInt(entrance);
            }

            System.out.print("Probabilidad de Pérdida: ");
            entrance = reader.readLine();
            if (entrance.matches("-?\\d+")) {
                lossPercentage = Integer.parseInt(entrance);
            }


            System.out.println("Elija una dirección de transmisión de datos");
            System.out.println("1. Client --> Server");
            System.out.println("2. Server --> Client");
            System.out.println("3. Client <-> Server");
            // Leer un número entero
            System.out.print("Ingrese un número entero: ");
            entrance = reader.readLine();
            int option = 0;
            if (entrance.matches("-?\\d+")) {
                option = Integer.parseInt(entrance);
            }
            switch (option) {
                case 1:
                    dirTransmision = "Client";
                    break;
                case 2:
                    dirTransmision = "Server";
                    break;
                case 3:
                    dirTransmision = "Client_Server";
                    break;
                default:
                    System.out.println("Dirección de transmisión no soportada. Se usará la dirección por defecto");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//      countFailedPackages++, countSentPackages++: When there are packages that were sent and tha
//      countFailedPackeges is 0 then it removes the line from the Arraylist


//crear el console en el handshake y devolverlo

//        handshake();
//        Elegir el Arraylist que se desea recorrer
        ArrayList<String> datos = new ArrayList<>();
        int srcPort = 0, dstPort = 0;
        if (dirTransmision.equals("Client")) {
            datos.addAll(quijote);
            srcPort = clientPort;
            dstPort = serverPort;
        } else if (dirTransmision.equals("Server")) {
            datos.addAll(mosqueteros);
            srcPort = serverPort;
            dstPort = clientPort;
        } else if (dirTransmision.equals("Client_Server")) {
            datos.addAll(combinatedList);
            srcPort = clientPort;
            dstPort = serverPort;
        }


        Console packet = new Console(0, "succeed", "Client", Integer.toString(srcPort), Integer.toString(dstPort),
                "SYN", clientSeq, 0, 0,
                windowSize,
                "");
//aquí el handshake
        packet = handshake(packet, clientSeq, serverSeq);

        /*
         * TODO
         *  - RECORRER EL ARREGLO DE CLIENTE
         *  - En cada iteración se debe generar un estado, en dependencia de eso entonces se decide si se guarda en el
         *    arraylist de Console oficial o se genera un Console que tenga esos errores
         * */

        Random rnd = new Random();
//        String estado = "succeed"; // <- Valor inicial: succeed
        // Verificar que se encuentren dentro de ventana
        // Variables auxiliaress
        int countWindow = 0;
        int failedPackets = 0;
        int lineasRecorridas = 0;

        while (!datos.isEmpty()) {
            for (String data : datos) {
                //data is a new line

                //función que dado un string data y un consoleeeee!!! realice una linea
                countWindow += data.length();
                if (countWindow >= windowSize) {//ya no puede transmitir esta // mandar un tcp window update
                    // tcpWindowUpdate();
                    // No cambia la instancia console
                    // Terminó la ventana
                    //checar si hay paquetes fallidos
                    // Console es otra cosa que el arraylist del quijoteeeeeeeee
                    // si hay un paquete que falló - entonces no se quitan --- hay que tomar en cuenta que se
                    // Contar cuantas lineas se leyeron para quitarlas del arraylist - llevar conteo de líneas
                    countWindow = 0;
                    if (failedPackets > 0) {
                        //Packetes failed en la operación de la ventana
                        failedPackets = 0;
                    } else {
                        for (int i = 0; i < lineasRecorridas; i++) {
                            // Se eliminan las lineas de la ventana que se hayan corrido exitosamente
                            datos.removeFirst();
                        }
                    }
                    break;
                }

                lineasRecorridas++;

                int threshold = rnd.nextInt();
                if (threshold < lossPercentage) {
                    packet.setStatus("fail");
                }

                if (packet.getStatus().equals("fail")) {
//                        Se crea un nuevo console para mantener los últimos datos del console oficial
                    Console cFailed = packet;
//                    cFailed.setStatus("fail");
                    cFailed.push_ack(data);
                    cFailed.setStatus("fail");
                    cFailed.printConsole();

                    packet.nextIndex();
                    //No se manda el ack
                    failedPackets++;
                } else {
//              psh  ACK
                    packet.push_ack(data);
                    packet.printConsole();
//                ack
                    packet.ack();
                    packet.printConsole();
                    lineasRecorridas++;
                }
            }
            countWindow++;
        }
        fin(packet);
    }

    //Recibe los parámetros para inicializar el Console
    public static Console handshake(Console packet, int seqNumber, int ackNumber) {
        //Recibe el paquete ya inicializado con el primer estado de la tabla
        packet.printConsole();
        //SYN, ACK
        packet.nextIndex();
        packet.changeSender();
        packet.setFlags("SYN, ACK");
        packet.setSeqNumber(ackNumber);
        packet.setAckNumber(seqNumber);
        packet.printConsole();

        // ACK para HANDSHAKE
        packet.nextIndex();
        packet.changeSender();
        packet.setFlags("ACK");
        packet.setSeqNumber(packet.getAckNumber());
        packet.setAckNumber(packet.getSeqNumber() + 1);
        packet.printConsole();
        return packet;
    }


    public static void fin(Console packet) {
        // el primer fin_ack es un psh_ack pero con flags diferentes
        // FIN, ACK
        packet.nextIndex();
        packet.push_ack("");
        packet.setFlags("FIN, ACK");
        packet.printConsole();

        // ACK
        packet.changeSender();
        packet.setFlags("ACK");
        packet.setSeqNumber(packet.getAckNumber());
        packet.setAckNumber(packet.getSeqNumber() + 1);
        packet.printConsole();
    }

    public static ArrayList<String> generateList(String text, int firstAleat, int secondAleat) {
        ArrayList<String> list = new ArrayList<>();
        String[] words = text.split(" ");
        System.out.println("WORDS");

        Random rnd = new Random();
        String line = "";
        int random = rnd.nextInt(secondAleat - firstAleat) + firstAleat;
        System.out.println(random);
        int counter = 0;
        for (String word : words) {
            if (counter < random) {
                line += word + " ";
                counter++;
            } else {
                counter = 0;
                list.add(line);
                line = "";
                random = rnd.nextInt(10) + 2;
            }
        }
        return list;
    }

//Parámetros en consola - 3 tipos (Cliente, Servidor, Cliente Servidor)

    public static int getModaLenghts(ArrayList<String> list) {
        HashMap<Integer, Integer> frecuences = new HashMap<>();
        for (String elemento : list) {
            int longitud = elemento.length();
            frecuences.put(longitud, frecuences.getOrDefault(longitud, 0) + 1);
        }

        int moda = -1;
        int maxFrecuencia = 0;

        for (HashMap.Entry<Integer, Integer> entry : frecuences.entrySet()) {
            int longitud = entry.getKey();
            int freq = entry.getValue();
            if (freq > maxFrecuencia) {
                moda = longitud;
                maxFrecuencia = freq;
            }
        }
        return moda;
    }

    public static void handshake() {
        System.out.printf("%-5s | %-10s | %-10s | %-10s | %-10s | %-15s | %-12s | %-12s | %-5s | %-8s | %s%n", "Index", "Status", "Sender", "Src Port", "Dst Port", "Flags", "Seq Number", "Ack Number", "Len", "Window", "Data");
    }

    //    enviar un paquete de cliente: implica un push, ack de parte del cliente y un ack del servidor
    public static void sendClient() {

    }

    //    enviar un paquete de servidor: push, ack de servidor y ack de cliente
    public static void sendServer() {

    }
}

class Console {
    public static int index;
    private String status;
    private String sender;
    private String srcPort;
    private String dstPort;
    private String flags;
    private int seqNumber;
    private int ackNumber;
    private int len;
    private int window;
    private String data;

    public static void setIndex(int index) {
        Console.index = index;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSrcPort() {
        return srcPort;
    }

    public void setSrcPort(String srcPort) {
        this.srcPort = srcPort;
    }

    public String getDstPort() {
        return dstPort;
    }

    public void setDstPort(String dstPort) {
        this.dstPort = dstPort;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    public int getSeqNumber() {
        return seqNumber;
    }

    public void setSeqNumber(int seqNumber) {
        this.seqNumber = seqNumber;
    }

    public int getAckNumber() {
        return ackNumber;
    }

    public void setAckNumber(int ackNumber) {
        this.ackNumber = ackNumber;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public int getWindow() {
        return window;
    }

    public void setWindow(int window) {
        this.window = window;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Console(int index, String status, String sender, String srcPort, String dstPort, String flags, int seqNumber, int ackNumber, int len, int window, String data) {
        this.index = index;
        this.status = status;
        this.sender = sender;
        this.srcPort = srcPort;
        this.dstPort = dstPort;
        this.flags = flags;
        this.seqNumber = seqNumber;
        this.ackNumber = ackNumber;
        this.len = len;
        this.window = window;
        this.data = data;
    }

    public int nextIndex() {
        this.index++;
        return this.index;
    }

    public int getIndex() {
        return this.index;
    }

    public void failure() {
//Es un push, ack pero con estado fallido
    }

    //Imprime una línea de como se encuentra la consola actualmente
    public void printConsole() {
        System.out.printf("%-5s | %-10s | %-10s | %-10s | %-10s | %-15s | %-12s | %-12s | %-5s | %-8s | %s%n", this.index, this.status, this.sender, this.srcPort, this.dstPort, this.flags, this.seqNumber, this.ackNumber, this.len, this.window, this.data);
    }

//ESTO AFUERA DE LA CLASS -> Seteo de consola afuera
//    Devuelve un arraylist con 3 elementos del hanshake
    //    public void handshake(){
    //        ArrayList<String> handshakes = new ArrayList<>();
    //
    //        handshakes.add("Client");
    //
    //    }


    public void push_ack(String data) {
        this.changeSender();
        this.flags = "PSH, ACK";
        this.len = data.length();
        this.data = data.substring(0, 7);
//        el seq pasa a ser el ack y el ack el seq
        int aux = this.seqNumber;
        this.seqNumber = this.ackNumber;
        this.ackNumber = aux;
    }

    public void ack() {
        this.changeSender();
        this.flags = "ACK";
        this.seqNumber = this.ackNumber;
        this.ackNumber += this.len;
        this.len = 0;
        this.data = "";

    }


    //    public void process
// si falla entonces crear otros consoleeeeeeeeeeeeeee y al console actual solo sumarle index
    public void tcpWindowUpdate(int window) {
        this.status = "TCP Window Update";
        this.window = window;
        changeSender();
    }

    //NOTAAAAAAAAAAAAAAAAAAAAAAAA --- Luego de llamar a la función tcpWindowUpdate se debe llamar a la función
// changeSender();
    public void changeSender() {
        if (this.sender.equals("Client")) {
            this.sender = "Server";
        } else {
            this.sender = "Client";
        }
        String aux = this.srcPort;
        this.srcPort = this.dstPort;
        this.dstPort = aux;
    }

    //Modifica el valor de window y devuelve un boolean que indica si se cambio
    public boolean checkWindow(int len) {
        return len > this.window ? true : false;
    }
}