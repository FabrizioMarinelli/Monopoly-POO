package monopoly;

import  java.util.ArrayList;
import partida.*;
import java.util.Scanner;
import java.io.BufferedReader;
import partida.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
public class Menu {

    //Atributos
    private ArrayList<Jugador> jugadores; //Jugadores de la partida.
    private ArrayList<Avatar> avatares; //Avatares en la partida.
    private int turno = 0; //Índice correspondiente a la posición en el arrayList del jugador (y el avatar) que tienen el turno
    private int lanzamientos; //Variable para contar el número de lanzamientos de un jugador en un turno.
    private Tablero tablero; //Tablero en el que se juega.
    private Dado dado1; //Dos dados para lanzar y avanzar casillas.
    private Dado dado2;
    private Jugador banca; //El jugador banca.
    private boolean tirado; //Booleano para comprobar si el jugador que tiene el turno ha tirado o no.
    private boolean solvente; //Booleano para comprobar si el jugador que tiene el turno es solvente, es decir, si ha pagado sus deudas.
    //Añadimos una nueva clase indice para saber cual es el jugador actual
    private int indiceJugadorActual;
    // Método para inciar una partida: crea los jugadores y avatares.


    private void iniciarPartida() {
        jugadores = new ArrayList<>();
        avatares = new ArrayList<>();
        System.out.println("modo sin documento...");
        Scanner myObj = new Scanner(System.in);
        //System.out.println("linea " + fileScanner.nextLine());
        System.out.println("Indicar numero de jugadores: ");
        System.out.println("Crear jugador (NombreJugador) (TipoAvatar) ");
        String comando  = myObj.nextLine();
        comando = comando.toLowerCase();
        String[] comandoSplit = comando.split("[\\s]");

    }
    // Método para inciar una partida: crea los jugadores y avatares.
    private void iniciarPartida(BufferedReader br) {
        jugadores = new ArrayList<>();
        avatares = new ArrayList<>();
        Scanner fileScanner = new Scanner(br);
        Scanner myObj = new Scanner(System.in);
        try {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.toLowerCase();
                analizarComando(linea);
            }
        }catch (java.io.IOException e){
            System.err.println("Error leyendo el archivo: " + e.getMessage());

        }

    }
    public Menu(String[] args){
        this.banca = new Jugador();
        this.tablero = new Tablero(banca);
        if (args.length > 0) {
            String ruta = args[0];
            try (BufferedReader br = Files.newBufferedReader(Paths.get(ruta), StandardCharsets.UTF_8);){
                //System.out.println("linea " + fileScanner.nextLine());
                iniciarPartida(br);

            } catch (IOException e) {
                System.err.println("No pude abrir/leer el archivo: " + ruta + " -> " + e.getMessage());
                return;
            }
        } else {
            // Modo interactivo (terminal)
            System.out.println("Entrando sin documento: ");
            iniciarPartida();

        }
    }
    /*Método que interpreta el comando introducido y toma la accion correspondiente.
    * Parámetro: cadena de caracteres (el comando).
    */
    private void analizarComando(String comando) {
        String[] comandoSplit;
        comandoSplit = comando.split("[\\s]");
        switch (comandoSplit[0]) {
            case "crear":
                System.out.println("Llamando a crear Jugador");
                Jugador nuevoJugador = new  Jugador(comandoSplit[2],comandoSplit[3],tablero.encontrar_casilla("Salida"),avatares);
                jugadores.add(nuevoJugador);
                System.out.println("se ha creado el jugador " + nuevoJugador.getNombre() + " avatar " +nuevoJugador.getAvatar().getId());
                break;
            case "jugador":
                System.out.println("Indicando jugador con el turno");
                break;
            case "listar":
                System.out.println("listando jugadores/ o en venta");
                break;
            case "lanzar":
                System.out.println("lanzando dados (con o sin forzado)");
                break;
            case "acabar":
                System.out.println("acabar turno");
                break;
            case "salir":
                System.out.println("Salir de la carcel");
                break;
            case "describir":
                System.out.println("describir casilla o jugador");
                break;
            case "comprar":
                System.out.println("comprar propiedad");
                break;
            case "ver":
                System.out.println("mostrando tablero");
                break;
            default:
                System.out.println("Comando invalido");
                break;
        }

    }

    /*Método que realiza las acciones asociadas al comando 'describir jugador'.
    * Parámetro: comando introducido
     */
    private void descJugador(String[] partes) {
    }

    /*Método que realiza las acciones asociadas al comando 'describir avatar'.
    * Parámetro: id del avatar a describir.
    */
    private void descAvatar(String ID) {
    }

    /* Método que realiza las acciones asociadas al comando 'describir nombre_casilla'.
    * Parámetros: nombre de la casilla a describir.
    */
    private void descCasilla(String nombre) {
    }

    //Método que ejecuta todas las acciones relacionadas con el comando 'lanzar dados'.
    private void lanzarDados() {
        //Creamos los dados con los que realizaremos las tiradas
        Dado dado1 = new Dado();
        Dado dado2 = new Dado();
        //Hacemos las tiradas
        int tiradaDado1 = dado1.hacerTirada();
        int tiradaDado2  =dado1.hacerTirada();
        //calculamos el valor total
        int valorTirada = tiradaDado1 + tiradaDado2;

        System.out.println("Dados lanzados" + tiradaDado1 + "+" + tiradaDado2);
        System.out.println("El jugador" + "avanza" + valorTirada + "posiciones.");

        //comprobamos si la tirada es doce, entonces el jugador vuelve a lanzar los dados
        if (valorTirada == 12){
            lanzarDados();
        }

    }

    /*Método que ejecuta todas las acciones realizadas con el comando 'comprar nombre_casilla'.
    * Parámetro: cadena de caracteres con el nombre de la casilla.
     */
    private void comprar(String nombre) {
    }

    //Método que ejecuta todas las acciones relacionadas con el comando 'salir carcel'. 
    private void salirCarcel() {
    }

    // Método que realiza las acciones asociadas al comando 'listar enventa'.
    private void listarVenta() {
    }

    // Método que realiza las acciones asociadas al comando 'listar jugadores'.
    private void listarJugadores() {
    }

    // Método que realiza las acciones asociadas al comando 'listar avatares'.
    private void listarAvatares() {
    }


    //Para qye quiero eso???
    //Estaba en el esquelto???

   /* // Método que realiza las acciones asociadas al comando 'acabar turno'.
    private void acabarTurno() {
    }
   /* public Menu(){
        Scanner myObj = new Scanner(System.in);

        System.out.println("Introduzca la cantidad de jugadores");
        int cantidadJugadores = myObj.nextInt();
        System.out.println("hay " + cantidadJugadores + " jugadores");

    }
    */
    //Método que inicializa y muestra por pantalla el tablero
    public void iniciarTablero() {
        System.out.println("Bienvenido al Monopoly creado por Carolina, Fabrizio y Maria");
        System.out.println(tablero.toString());
    }

    //Método para acabar el turno de un jugador
    public void acabarTurno(){
        indiceJugadorActual = (indiceJugadorActual+1)%jugadores.size();
        Jugador siguiente = jugadores.get(indiceJugadorActual);
        System.out.println("Turno del jugador: " + siguiente.getNombre());
    }
}
