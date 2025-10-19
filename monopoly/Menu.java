package monopoly;

import  java.util.ArrayList;
import partida.*;
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
    public Menu(){
        this.banca = new Jugador();
        this.tablero = new Tablero(banca);
    }


    // Método para inciar una partida: crea los jugadores y avatares.
    private void iniciarPartida() {
    }
    
    /*Método que interpreta el comando introducido y toma la accion correspondiente.
    * Parámetro: cadena de caracteres (el comando).
    */
    private void analizarComando(String comando) {


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
        Casilla casilla = new Casilla();
        System.out.println(casilla.infoCasilla(nombre));
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
        int valorDados = tiradaDado1 + tiradaDado2;

        System.out.println("Dados lanzados" + tiradaDado1 + "+" + tiradaDado2);
        System.out.println("El jugador" + "avanza" + valorDados + "posiciones.");



    }

    /*Método que ejecuta todas las acciones realizadas con el comando 'comprar nombre_casilla'.
    * Parámetro: cadena de caracteres con el nombre de la casilla.
     */
    private void comprar(String nombre) {
    }

    //Método que ejecuta todas las acciones relacionadas con el comando 'salir carcel'. 
    private void salirCarcel(Jugador jugador) {
        //Si ha salido de la carcel por jugar una tarjeta de salida de la cárcel, la tarjeta debería de proporcionar un mensaje de aviso
        if(jugador.getTiradasCarcel() > 2){
            if(jugador.getFortuna() >= 500_000f){
                jugador.setFortuna(jugador.getFortuna() - 500_000f);
                System.out.println(jugador.getNombre() + " ha pagado 500.000 y ha salido de la cárcel.");
            }else{
                //jugador.setEliminado(true);
            }
        }
        jugador.setTiradasCarcel(0);
        jugador.setEnCarcel(false);
        System.out.println(jugador.getNombre() + " ha salido de la cárcel.");
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

   // Método que realiza las acciones asociadas al comando 'acabar turno'.
    private void acabarTurno() {
        indiceJugadorActual = (indiceJugadorActual+1)%jugadores.size();
        Jugador siguiente = jugadores.get(indiceJugadorActual);
        System.out.println("Turno del jugador: " + siguiente.getNombre());
    }

    //Método que inicializa y muestra por pantalla el tablero
    public void iniciarTablero() {
        System.out.println("Bienvenido al Monopoly creado por Carolina, Fabrizio y Maria");
        System.out.println(tablero.toString());
    }
}
