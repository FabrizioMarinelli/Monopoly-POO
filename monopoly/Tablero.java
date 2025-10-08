package monopoly;

import partida.*;
import java.util.ArrayList;
import java.util.HashMap;


public class Tablero {
    //Atributos.
    private ArrayList<ArrayList<Casilla>> posiciones; //Posiciones del tablero: se define como un arraylist de arraylists de casillas (uno por cada lado del tablero).
    private HashMap<String, Grupo> grupos; //Grupos del tablero, almacenados como un HashMap con clave String (será el color del grupo).
    private Jugador banca; //Un jugador que será la banca.

    //Constructor: únicamente le pasamos el jugador banca (que se creará desde el menú).
    public Tablero(Jugador banca) {
        this.banca = banca; //Guarda una referencia al objeto jugador que actuará como la banca. Así se permite que el tablero sepa quien es la banca
        this.posiciones =  new ArrayList<>();   //Guardará los cuatro lados del tablero
        this.grupos = new HashMap<>();  //Almacenará los diferentes gurpos de solares organizados según su color
        this.generarCasillas(); //Crea las 40 casillas llamando a las funciones insertar()
    }


    //Método para crear todas las casillas del tablero. Formado a su vez por cuatro métodos (1/lado).
    private void generarCasillas() {
        this.insertarLadoSur();
        this.insertarLadoOeste();
        this.insertarLadoNorte();
        this.insertarLadoEste();
    }

    //Método para insertar las casillas del lado norte.
    private void insertarLadoNorte() {
        ArrayList<Casilla> ladoNorte = new ArrayList<>();
        ladoNorte.add(new Casilla("Parking", TipoCasilla.Especial));
        ladoNorte.add(new Casilla("Solar12", TipoCasilla.Solar, "Rojo"));
        ladoNorte.add(new Casilla("Suerte", TipoCasilla.Suerte));
        ladoNorte.add(new Casilla("Solar13", TipoCasilla.Solar, "Rojo"));
        ladoNorte.add(new Casilla("Solar14", TipoCasilla.Solar, "Rojo"));
        ladoNorte.add(new Casilla("Trans3", TipoCasilla.Transporte));
        ladoNorte.add(new Casilla("Solar15", TipoCasilla.Solar, "Marron"));
        ladoNorte.add(new Casilla("Solar16", TipoCasilla.Solar, "Marron"));
        ladoNorte.add(new Casilla("Serv2", TipoCasilla.Servicio));
        ladoNorte.add(new Casilla("Solar17", TipoCasilla.Soalr, "Marron"));
        ladoNorte.add(new Casilla("IrCarcel", TipoCasilla.Especial));
    }

    //Método para insertar las casillas del lado sur.
    private void insertarLadoSur() {
        ArrayList<Casilla> ladoSur = new ArrayList<>();
        ladoSur.add(new Casilla("Cárcel", TipoCasilla.Especial));
        ladoSur.add(new Casilla("Solar5", TipoCasilla.Solar, "Celeste"));
        ladoSur.add(new Casilla("Solar4", TipoCasilla.Solar, "Celeste"));
        ladoSur.add(new Casilla("Suerte", TipoCasilla.Suerte));
        ladoSur.add(new Casilla("Solar3", TipoCasilla.Solar, "Celeste"));
        ladoSur.add(new Casilla("Trans1", TipoCasilla.Transporte));
        ladoSur.add(new Casilla("Imp1", TipoCasilla.Impuestos));
        ladoSur.add(new Casilla("Solar2", TipoCasilla.Solar, "Blanco"));
        ladoSur.add(new Casilla("Caja", TipoCasilla.Caja));
        ladoSur.add(new Casilla("Solar1", TipoCasilla.Soalr, "Blanco"));
        ladoSur.add(new Casilla("Salida", TipoCasilla.Especial));
    }

    //Método que inserta casillas del lado oeste.
    private void insertarLadoOeste() {
        ArrayList<Casilla> ladoOeste = new ArrayList<>();
        ladoOeste.add(new Casilla("Solar11", TipoCasilla.Solar, "Amarillo"));
        ladoOeste.add(new Casilla("Solar10", TipoCasilla.Solar, "Amarillo"));
        ladoOeste.add(new Casilla("Caja", TipoCasilla.Caja));
        ladoOeste.add(new Casilla("Solar9", TipoCasilla.Solar, "Amarillo"));
        ladoOeste.add(new Casilla("Trans2", TipoCasilla.Transporte));
        ladoOeste.add(new Casilla("Solar8", TipoCasilla.Solar, "Morado"));
        ladoOeste.add(new Casilla("Solar7", TipoCasilla.Solar, "Morado"));
        ladoOeste.add(new Casilla("Serv1", TipoCasilla.Servicio));
        ladoOeste.add(new Casilla("Solar6", TipoCasilla.Solar, "Morado"));
    }

    //Método que inserta las casillas del lado este.
    private void insertarLadoEste() {
        ArrayList<Casilla> ladoEste = new ArrayList<>();
        ladoEste.add(new Casilla("Solar18", TipoCasilla.Solar, "Verde"));
        ladoEste.add(new Casilla("Solar19", TipoCasilla.Solar, "Verde"));
        ladoEste.add(new Casilla("Caja", TipoCasilla.Caja));
        ladoEste.add(new Casilla("Solar20", TipoCasilla.Solar, "Verde"));
        ladoEste.add(new Casilla("Trans4", TipoCasilla.Transporte));
        ladoEste.add(new Casilla("Suerte", TipoCasilla.Suerte));
        ladoEste.add(new Casilla("Solar21", TipoCasilla.Solar, "Azul"));
        ladoEste.add(new Casilla("Imp1", TipoCasilla.Impuestos));
        ladoEste.add(new Casilla("Solar22", TipoCasilla.Solar, "Azul"));
    }

    //Para imprimir el tablero, modificamos el método toString().
    @Override
    public String toString() {


    }

    //Método usado para buscar la casilla con el nombre pasado como argumento:
    public Casilla encontrar_casilla(String nombre){

        return null;
    }
}
