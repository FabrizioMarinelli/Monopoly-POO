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
        Casilla Casilla = new Casilla();
        this.insertarLadoSur();
        this.insertarLadoOeste();
        this.insertarLadoNorte();
        this.insertarLadoEste();
    }

    //Método para insertar las casillas del lado norte.
    private void insertarLadoNorte() {
        ArrayList<Casilla> ladoNorte = new ArrayList<>();

        //Estructura necesaria para cada tipo de casilla
        //Especial -> (nombre, tipo, pos, banca)
        //Solar -> (nombre, tipo, pos, valor, banca)
        //Comunidad -> (nombre, tipo, pos, banca)
        //Impuesto -> (nombre, pos, impuesto, banca)
        //Transporte -> (nombre, tipo, pos, valor, banca)
        //Suerte -> (nombre, tipo, pos, banca)

        ladoNorte.add(new Casilla("Solar11", "Solar", 21, 2_200_000f, banca));
        ladoNorte.add(new Casilla("Solar12", "Solar", 22, 2_200_000f, banca));
        ladoNorte.add(new Casilla("Suerte2", "Suerte", 23, banca));
        ladoNorte.add(new Casilla("Solar13", "Solar", 24, 2_200_000f, banca));
        ladoNorte.add(new Casilla("Solar14", "Solar", 25, 2_400_000f, banca));
        ladoNorte.add(new Casilla("Trans3", "Transporte", 26, 500_000f, banca));
        ladoNorte.add(new Casilla("Solar15", "Solar", 27, 2_600_000f, banca));
        ladoNorte.add(new Casilla("Solar16", "Solar", 28, 2_600_000f, banca));
        ladoNorte.add(new Casilla("Serv2", "Servicios", 29, 500_000f, banca));
        ladoNorte.add(new Casilla("Solar17", "Solar", 30, 2_800_000f, banca));
        ladoNorte.add(new Casilla("IrCarcel", "Especial", 31, banca));

        posiciones.add(ladoNorte);
    }

    //Método para insertar las casillas del lado sur.
    private void insertarLadoSur() {
        ArrayList<Casilla> ladoSur = new ArrayList<>();

        ladoSur.add(new Casilla("Salida",  "Especial",   1,  banca));
        ladoSur.add(new Casilla("Solar1",  "Solar",      2,  600_000f, banca));
        ladoSur.add(new Casilla("Caja",    "Comunidad",  3,  banca));
        ladoSur.add(new Casilla("Solar2",  "Solar",      4,  600_000f, banca));
        ladoSur.add(new Casilla("Imp1",    5,             2_000_000f, banca));
        ladoSur.add(new Casilla("Trans1",  "Transporte", 6,  500_000f, banca));
        ladoSur.add(new Casilla("Solar3",  "Solar",      7, 1_000_000f, banca));
        ladoSur.add(new Casilla("Suerte",  "Suerte",     8,  banca));
        ladoSur.add(new Casilla("Solar4",  "Solar",      9, 1_000_000f, banca));
        ladoSur.add(new Casilla("Carcel",  "Especial",  10,  banca));

        posiciones.add(ladoSur);
    }

    //Método que inserta casillas del lado oeste.
    private void insertarLadoOeste() {
        ArrayList<Casilla> ladoOeste = new ArrayList<>();

        ladoOeste.add(new Casilla("Solar5", "Solar", 11, 1_200_000f, banca));
        ladoOeste.add(new Casilla("Solar6", "Solar", 12, 1_400_000f, banca));
        ladoOeste.add(new Casilla("Serv1", "Servicios", 13, 500_000f, banca));
        ladoOeste.add(new Casilla("Solar7", "Solar", 14, 1_400_000f, banca));
        ladoOeste.add(new Casilla("Solar8", "Solar", 15, 1_600_000f, banca));
        ladoOeste.add(new Casilla("Trans2", "Transporte", 16, 500_000f, banca));
        ladoOeste.add(new Casilla("Solar9", "Solar", 17, 1_800_000f, banca));
        ladoOeste.add(new Casilla("Caja2", "Comunidad", 18, banca));
        ladoOeste.add(new Casilla("Solar10", "Solar", 19, 1_800_000f, banca));
        ladoOeste.add(new Casilla("Parking", "Especial", 20, banca));

        posiciones.add(ladoOeste);
    }

    //Método que inserta las casillas del lado este.
    private void insertarLadoEste() {
        ArrayList<Casilla> ladoEste = new ArrayList<>();

        ladoEste.add(new Casilla("Solar18", "Solar", 32, 3_000_000f, banca));
        ladoEste.add(new Casilla("Solar19", "Solar", 33, 3_000_000f, banca));
        ladoEste.add(new Casilla("Caja3", "Comunidad", 34, banca));
        ladoEste.add(new Casilla("Solar20", "Solar", 35, 3_200_000f, banca));
        ladoEste.add(new Casilla("Trans4", "Transporte", 36, 500_000f, banca));
        ladoEste.add(new Casilla("Solar21", "Solar", 37, 3_500_000f, banca));
        ladoEste.add(new Casilla("Suerte3", "Suerte", 38, banca));
        ladoEste.add(new Casilla("Imp2", 39, 2_000_000f, banca));
        ladoEste.add(new Casilla("Solar22", "Solar", 40, 4_000_000f, banca));

        posiciones.add(ladoEste);
    }

    //Para imprimir el tablero, modificamos el método toString().
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(); //StringBuilder para construir de manera eficiente el texto

        //Obtenemos las listas de las casillas de los diferentes lados del tablero
        ArrayList<Casilla> norte = posiciones.get(0);
        ArrayList<Casilla> sur = posiciones.get(1);
        ArrayList<Casilla> este = posiciones.get(2);
        ArrayList<Casilla> oeste = posiciones.get(3);

        //Imprimimos el lado norte
        for (Casilla c: norte){
            sb.append("|").append(c.getNombre());   //Permite que cada casilla se imprima así: |Contenido|
        }
        sb.append("|\n");   //Cuando se termine queremos que haga un salto de línea para que comience con los laterales

        // Imprimir los lados de los lados
        //En el for se imprimirá cada línea del medio del tablero
        for (int i = 0; i < oeste.size(); i++){
            //Imprimimos la casilla del lado izquiero con sus barras laterales
            sb.append(String.format("|%-10s", oeste.get(i).getNombre()));   //El '%' es un especificador de formato y '-10s' permite alinear el texto ocupando un tamaño de 10 caracteres
            //Imprimimos varios de espacios en blaco
            for (int j = 0; j < norte.size() - 2; j++){
                //Metemos más espacios en el medio para que el tablero tenga forma rectangular
                sb.append("           ");
            }
            //Imprimimos la casilla de la derecha
            if (i < este.size()){
                sb.append(String.format("%10s|", este.get(i).getNombre()));
            }
            //Imprimimos el salto de línea para comenzar a realizar la línea inferior
            sb.append("\n");
        }

        //Imprimimos el lado sur del revés para que se pueda cerrar el rectángulo
        //Si no las casillas saldrían en un orden inverso
        for (int i = sur.size() - 1; i >= 0; i--){
            //Usamos get(i) para imprimir las casillas
            sb.append("|").append(sur.get(i).getNombre());
        }
        sb.append("|\n");
        return sb.toString();
    }

    //Método usado para buscar la casilla con el nombre pasado como argumento:
    public Casilla encontrar_casilla(String nombre){
        //Recorremos las listas
        for (ArrayList<Casilla> lado : posiciones){
            for (Casilla c : lado){
                //Devolvemos cada casillaº
                if (c.getNombre().equalsIgnoreCase(nombre)){
                    return c;
                }
            }
        }
        return null;
    }

    /*
      TO DO:
      Poner la instruccion en menu para probar la ejecucuión
      Revisar hashmap colores
      Revisar hashmap colores
    */
}
