package monopoly;

import partida.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Tablero {
    // Códigos ANSI para colorear las casillas en la consola
    private static final String RESET    = "\u001B[0m";
    private static final String ROJO     = "\u001B[31m";
    private static final String VERDE    = "\u001B[32m";
    private static final String AMARILLO = "\u001B[33m";
    private static final String AZUL     = "\u001B[34m";
    private static final String MAGENTA  = "\u001B[35m";
    private static final String CIAN     = "\u001B[36m";
    private static final String BLANCO   = "\u001B[37m";

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

    //Getter para la posiciones
    public ArrayList<ArrayList<Casilla>> getPosiciones() {
        return posiciones;
    }


    //Método para colorear las casillas
    private String colorearCasilla(Casilla c) {
        String color = RESET;
        //En funcion de lo que obtenga la cadena será un color u otro
        if(c.getTipo().equalsIgnoreCase("solar")){
            //Como en solares en funcion del número de idendtificacion se utilia un color u otro utilizamos un switch
            switch (c.getNombre().toLowerCase()) {
                case "solar1": case "solar2":
                    color = BLANCO;
                    break;
                case "solar3": case "solar4": case "solar5":
                    color = CIAN;
                    break;
                case "solar6": case "solar7": case "solar8":
                    color = MAGENTA;
                    break;
                case "solar9": case "solar10": case "solar11":
                    color = "\u001B[38;5;208m"; //Naranja
                    break;
                case "solar12": case "solar13": case "solar14":
                    color = ROJO;
                    break;
                case "solar15": case "solar16": case "solar17":
                    color = AMARILLO;
                    break;
                case "solar18": case "solar19": case "solar20":
                    color = VERDE;
                    break;
                case "solar21": case "solar22":
                    color = AZUL;
                    break;
                default:
                    color = RESET;
            }
        }else if (c.getTipo().equalsIgnoreCase("suerte")){
            color = BLANCO;
        }else if (c.getTipo().equalsIgnoreCase("comunidad")){
            color = BLANCO;
        }else if (c.getTipo().equalsIgnoreCase("servicios")){
            color = BLANCO;
        }else if (c.getTipo().equalsIgnoreCase("transporte")){
            color = BLANCO;
        }else if (c.getTipo().equalsIgnoreCase("especial")){
            color = BLANCO;
        }

        int tamano = c.getNombre().length();    //Lo usaremos para ajustar el espacio de todas las casillas al mismo tamaño
        //Ponemos RESET porque no queremos que se siga escribiendo en el color que estamos utilizando en consola
        return color + c.getNombre() + " ".repeat(10-tamano) + RESET;
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

        ladoNorte.add(new Casilla("Parking", "Especial", 21, banca));
        ladoNorte.add(new Casilla("Solar12", "Solar", 22, 2_200_000f, banca, null, null, 1_100_000f, 0f, 1_500_000f, 1_500_000f, 300_000f, 600_000f, 2_200_000f, 10_500_000f, 2_100_000f, 2_100_000f));
        ladoNorte.add(new Casilla("Suerte2", "Suerte", 23, banca));
        ladoNorte.add(new Casilla("Solar13", "Solar", 24, 2_200_000f, banca, null, null, 1_100_000f, 0f, 1_500_000f, 1_500_000f, 300_000f, 600_000f, 2_200_000f, 10_500_000f, 2_100_000f, 2_100_000f));
        ladoNorte.add(new Casilla("Solar14", "Solar", 25, 2_400_000f, banca, null, null, 1_200_000f, 0f, 1_600_000f, 1_600_000f, 400_000f, 700_000f, 2_400_000f, 11_000_000f, 2_400_000f, 2_400_000f));
        ladoNorte.add(new Casilla("Trans3", "Transporte", 26, 500_000f, banca,null, null, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
        ladoNorte.add(new Casilla("Solar15", "Solar", 27, 2_600_000f, banca, null, null, 1_300_000f, 0f, 1_700_000f, 1_700_000f, 500_000f, 800_000f, 2_600_000f, 11_500_000f, 2_600_000f, 2_600_000f));
        ladoNorte.add(new Casilla("Solar16", "Solar", 28, 2_600_000f, banca, null, null, 1_300_000f, 0f, 1_700_000f, 1_700_000f, 500_000f, 800_000f, 2_600_000f, 11_500_000f, 2_600_000f, 2_600_000f));
        ladoNorte.add(new Casilla("Serv2", "Servicios", 29, 500_000f, banca, null, null, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
        ladoNorte.add(new Casilla("Solar17", "Solar", 30, 2_800_000f, banca, null, null, 1_400_000f, 0f, 1_800_000f, 1_800_000f, 600_000f, 900_000f, 2_800_000f, 12_000_000f, 2_800_000f, 2_800_000f));
        ladoNorte.add(new Casilla("IrCarcel", "Especial", 31, banca));
        posiciones.add(ladoNorte);
    }

    //Método para insertar las casillas del lado sur.
    private void insertarLadoSur() {
        ArrayList<Casilla> ladoSur = new ArrayList<>();

        ladoSur.add(new Casilla("Salida", "Especial", 1, banca));
        ladoSur.add(new Casilla("Solar1", "Solar", 2, 600_000f, banca, null, null, 300_000f, 0f, 500_000f, 500_000f, 300_000f, 700_000f, 1_800_000f, 9_000_000f, 1_800_000f, 1_800_000f));
        ladoSur.add(new Casilla("Caja1", "Comunidad", 3, banca));
        ladoSur.add(new Casilla("Solar2", "Solar", 4, 600_000f, banca, null, null, 300_000f, 0f, 500_000f, 500_000f, 300_000f, 700_000f, 1_800_000f, 9_000_000f, 1_800_000f, 1_800_000f));
        ladoSur.add(new Casilla("Imp1", 5, 2_000_000f, banca));
        ladoSur.add(new Casilla("Trans1", "Transporte", 6, 500_000f, banca, null, null, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
        ladoSur.add(new Casilla("Solar3", "Solar", 7, 1_000_000f, banca, null, null, 500_000f, 0f, 800_000f, 800_000f, 400_000f, 900_000f, 3_000_000f, 14_000_000f, 3_000_000f, 3_000_000f));
        ladoSur.add(new Casilla("Suerte1", "Suerte", 8, banca));
        ladoSur.add(new Casilla("Solar4", "Solar", 9, 1_000_000f, banca, null, null, 500_000f, 0f, 800_000f, 800_000f, 400_000f, 900_000f, 3_000_000f, 14_000_000f, 3_000_000f, 3_000_000f));
        ladoSur.add(new Casilla("Solar5", "Solar", 10, 1_000_000f, banca, null, null, 500_000f, 0f, 800_000f, 800_000f, 400_000f, 900_000f, 3_000_000f, 14_000_000f, 3_000_000f, 3_000_000f));
        ladoSur.add(new Casilla("Carcel", "Especial", 11, banca));
        posiciones.add(ladoSur);
    }

    //Método que inserta casillas del lado oeste.
    private void insertarLadoOeste() {
        ArrayList<Casilla> ladoOeste = new ArrayList<>();

        ladoOeste.add(new Casilla("Solar6", "Solar", 12, 1_200_000f, banca, null, null, 600_000f, 0f, 900_000f, 900_000f, 500_000f, 1_100_000f, 3_600_000f, 16_000_000f, 3_600_000f, 3_600_000f));
        ladoOeste.add(new Casilla("Solar7", "Solar", 13, 1_400_000f, banca, null, null, 700_000f, 0f, 1_000_000f, 1_000_000f, 600_000f, 1_200_000f, 4_200_000f, 17_000_000f, 4_200_000f, 4_200_000f));
        ladoOeste.add(new Casilla("Serv1", "Servicios", 14, 500_000f, banca, null, null, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
        ladoOeste.add(new Casilla("Solar8", "Solar", 15, 1_400_000f, banca, null, null, 700_000f, 0f, 1_000_000f, 1_000_000f, 600_000f, 1_200_000f, 4_200_000f, 17_000_000f, 4_200_000f, 4_200_000f));
        ladoOeste.add(new Casilla("Solar9", "Solar", 16, 1_600_000f, banca, null, null, 800_000f, 0f, 1_100_000f, 1_100_000f, 700_000f, 1_400_000f, 4_800_000f, 19_000_000f, 4_800_000f, 4_800_000f));
        ladoOeste.add(new Casilla("Trans2", "Transporte", 17, 500_000f, banca, null, null, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
        ladoOeste.add(new Casilla("Solar10", "Solar", 18, 1_800_000f, banca, null, null, 900_000f, 0f, 1_200_000f, 1_200_000f, 800_000f, 1_600_000f, 5_400_000f, 20_000_000f, 5_400_000f, 5_400_000f));
        ladoOeste.add(new Casilla("Caja2", "Comunidad", 19, banca));
        ladoOeste.add(new Casilla("Solar11", "Solar", 20, 1_800_000f, banca, null, null, 900_000f, 0f, 1_200_000f, 1_200_000f, 800_000f, 1_600_000f, 5_400_000f, 20_000_000f, 5_400_000f, 5_400_000f));
        posiciones.add(ladoOeste);
    }

    //Método que inserta las casillas del lado este.
    private void insertarLadoEste() {
        ArrayList<Casilla> ladoEste = new ArrayList<>();

        ladoEste.add(new Casilla("Solar18", "Solar", 32, 3_000_000f, banca, null, null, 1_500_000f, 0f, 1_900_000f, 1_900_000f, 700_000f, 1_000_000f, 3_000_000f, 12_500_000f, 3_000_000f, 3_000_000f));
        ladoEste.add(new Casilla("Solar19", "Solar", 33, 3_000_000f, banca, null, null, 1_500_000f, 0f, 1_900_000f, 1_900_000f, 700_000f, 1_000_000f, 3_000_000f, 12_500_000f, 3_000_000f, 3_000_000f));
        ladoEste.add(new Casilla("Caja3", "Comunidad", 34, banca));
        ladoEste.add(new Casilla("Solar20", "Solar", 35, 3_200_000f, banca, null, null, 1_600_000f, 0f, 2_000_000f, 2_000_000f, 800_000f, 1_100_000f, 3_200_000f, 13_000_000f, 3_200_000f, 3_200_000f));
        ladoEste.add(new Casilla("Trans4", "Transporte", 36, 500_000f, banca, null, null, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
        ladoEste.add(new Casilla("Solar21", "Solar", 37, 3_500_000f, banca, null, null, 1_700_000f, 0f, 2_200_000f, 2_200_000f, 900_000f, 1_200_000f, 3_500_000f, 14_000_000f, 3_500_000f, 3_500_000f));
        ladoEste.add(new Casilla("Suerte3", "Suerte", 38, banca));
        ladoEste.add(new Casilla("Imp2", 39, 2_000_000f, banca));
        ladoEste.add(new Casilla("Solar22", "Solar", 40, 4_000_000f, banca, null, null, 2_000_000f, 0f, 2_500_000f, 2_500_000f, 1_000_000f, 1_500_000f, 4_000_000f, 15_000_000f, 4_000_000f, 4_000_000f));
        posiciones.add(ladoEste);
    }

    //Método que devuelve un texto con el nombre de la casilla y la incial del jugador que se encuentra en ella
//La hacemos private porque solo la vamos a utilizar dentro de la clase en la que la acabamos de definir
    private String mostrarAvatares(Casilla c) {
        StringBuilder sb = new StringBuilder();

        // Si hay avatares en la casilla, mostramos sus iniciales junto un '&' como se muestra en el .pdf
        if(c.getAvatares() != null && !c.getAvatares().isEmpty()){
            sb.append(" &");
            for (Avatar a : c.getAvatares()) {
                Jugador j = a.getJugador();
                //Comprobamos que los datos del jugador no estén vacíos
                if (j != null && j.getNombre() != null && !j.getNombre().isEmpty()){
                    //En caso de que no lo sean pasamos us inicial a mayúsculas
                    //Así tenemos un "formato" específico para los avatares
                    char inicial = Character.toUpperCase(j.getNombre().charAt(0));
                    sb.append(inicial);
                }
            }
        }
        return sb.toString();
    }


    //Para imprimir el tablero, modificamos el método toString().
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(); //StringBuilder para construir de manera eficiente el texto

        //Obtenemos las listas de las casillas de los diferentes lados del tablero
        ArrayList<Casilla> norte = posiciones.get(2);
        ArrayList<Casilla> sur = posiciones.get(0);
        ArrayList<Casilla> este = posiciones.get(3);
        ArrayList<Casilla> oeste = posiciones.get(1);

        //Imprimimos el lado norte
        for(int i = 0; i < norte.size(); i++){
            //Imprimimos la casilla del lado izquiero con sus barras laterales
            sb.append("|" + colorearCasilla(norte.get(i)) + mostrarAvatares(norte.get(i)));
        }
        sb.append("|\n");   //Cuando se termine queremos que haga un salto de línea para que comience con los laterales

        //Imprimir los lados de los lados
        //En el for se imprimirá cada línea del medio del tablero
        for(int i = 0; i < oeste.size(); i++){
            //Imprimimos la casilla del lado izquiero con sus barras laterales
            sb.append("|" + colorearCasilla(oeste.get(i)) + mostrarAvatares(oeste.get(i)) + "|");

            //Metemos más espacios en el medio para que el tablero tenga forma rectangular
            sb.append(" ".repeat(11*9-1));

            //Imprimimos la casilla de la derecha
            if (i < este.size()){
                sb.append("|" + colorearCasilla(este.get(i)) + mostrarAvatares(este.get(i)) + "|");
            }
            //Imprimimos el salto de línea para comenzar a realizar la línea inferior
            sb.append("\n");
        }
        //Imprimimos el lado sur del revés para que se pueda cerrar el rectángulo
        //Si no las casillas saldrían en un orden inverso
        for(int i = 0; i < sur.size(); i++){
            //Imprimimos la casilla del lado izquiero con sus barras laterales
            sb.append("|" + colorearCasilla(sur.get(i)) + mostrarAvatares(sur.get(i)));
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


    //Método para describir la casilla
   /* public Casilla describirCasilla(String nombre){


    }*/
}
