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
    public Menu(){
        this.banca = new Jugador();
        this.tablero = new Tablero(banca);
    }


    // Método para inciar una partida: crea los jugadores y avatares.
    private void iniciarPartida() {
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
        Scanner fileScanner = new Scanner(br);
        Scanner myObj = new Scanner(System.in);
        try {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.toLowerCase();
                System.out.println(linea);
                analizarComando(linea);
            }
        }catch (java.io.IOException e){
            System.err.println("Error leyendo el archivo: " + e.getMessage());
        }
    }

    public Menu(String[] args){
        jugadores = new ArrayList<>();
        avatares = new ArrayList<>();
        this.banca = new Jugador();
        this.tablero = new Tablero(banca);
        indiceJugadorActual = 0;
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
                if ((comandoSplit.length == 4) && (comandoSplit[3].equals("coche")||comandoSplit[3].equals("esfinge")||comandoSplit[3].equals("sombrero")||comandoSplit[3].equals("pelota") ) ){
                    Jugador nuevoJugador = new  Jugador(comandoSplit[2],comandoSplit[3],tablero.encontrar_casilla("Salida"),avatares);
                    jugadores.add(nuevoJugador);
                    System.out.println("""
                            {
                                nombre: %s,
                                avatar: %s
                            }
                            """.formatted(nuevoJugador.getNombre(),nuevoJugador.getAvatar().getId()));
                } else {
                    System.out.println("comando invalido");
                }
                break;
            case "jugador":
                if(!jugadores.isEmpty()) {
                    System.out.println("""
                            {
                                nombre: %s,
                                avatar: %s
                            }
                            """.formatted(jugadores.get(indiceJugadorActual).getNombre(), jugadores.get(indiceJugadorActual).getAvatar().getId()));
                } else {
                    System.out.println("Todavia no hay jugadores");
                }
                break;
            case "listar":
                if (comandoSplit.length != 2) {
                    System.out.println("comando invalido");
                }else{
                    if (comandoSplit[1].equals("jugadores")) {
                        for (Jugador jugador : jugadores) {
                            System.out.println(jugador.toString());
                        }

                    } else if (comandoSplit[1].equals("enventa")) {
                        System.out.println("propieda");
                        // falta implementar
                    }
                }
                System.out.println("listando jugadores/ o en venta");
                break;
            case "lanzar":
                System.out.println("lanzando dados (con o sin forzado)");
                break;
            case "acabar":
                if (!jugadores.isEmpty()) {
                    acabarTurno();
                } else {
                    System.out.println("Todavia no hay jugadores");
                }
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
                System.out.println(tablero.toString());
                break;
            case "ver tablero":
                verTablero();
                break;
            default:
                System.out.println("Comando invalido");
                break;
        }
    }

    /*Método que lanza los dados con un valor forzado, es decir que se le pasa el valor de los dados como argumentos
     * Parámetro: el valor de los dados
     * */
    private void lanzarDadosForzado(int dado1, int dado2) {
        //Declaramos cual es el jugador actual
        Jugador jugadorActual = jugadores.get(indiceJugadorActual);
        Avatar avatar = jugadorActual.getAvatar();

        //El valor de la tirada sera la suma de los dados
        int valorTirada = dado1 + dado2;

        System.out.println("\n Tirada forzada " + jugadorActual.getNombre() + " obtiene: " + dado1 + " + " + dado2 + " = " + valorTirada);

        //Movemos al avatar de lugar
        Casilla casillaInicial = avatar.getLugar();
        avatar.moverAvatar(tablero.getPosiciones(), valorTirada);
        Casilla casillaFinal = avatar.getLugar();

        System.out.println("El avatar " + avatar.getId() + " avanza " + valorTirada + " posiciones, desde " + casillaInicial.getNombre() + " hasta " + casillaFinal.getNombre() + ".");


        // Evaluar la casilla en la que cayó el personaje
        boolean sigueEnJuego = casillaFinal.evaluarCasilla(jugadorActual, banca, valorTirada);

        // Si el jugador cayó en IrCarcel lo metems en la carcel
        if (jugadorActual.isEnCarcel()) {
            jugadorActual.encarcelar(tablero.getPosiciones());
            return;
        }

        // Si el jugador se quedó sin dinero no puede continuar jugando y tiene que declararse en bancarota  o hipotecar
        if (!sigueEnJuego) {
            System.out.println( jugadorActual.getNombre() + " no tiene suficiente dinero. Debe hipotecar o declararse en bancarrota.");
            return;
        }

    }

    /*Método que realiza las acciones asociadas al comando 'describir jugador'.
    * Parámetro: comando introducido
     */
    private void descJugador(String[] partes) {
        for (Jugador jugador1 : jugadores) {
            if (jugador1.getNombre().equals(partes[0])) {
                jugador1.toString();
                break;
            }
        }
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

        //Declaramos al jugador actual
        Jugador jugadorActual = jugadores.get(indiceJugadorActual);
        Avatar avatar = jugadorActual.getAvatar();

        //Declaramos variable para contar las veces que se sacaron dobles
        int contador = 0;
        boolean repetirTirada = false;

        do {
            //Hacemos las tiradas
            int tiradaDado1 = dado1.hacerTirada();
            int tiradaDado2 = dado2.hacerTirada();
            //calculamos el valor total
            int valorTirada = tiradaDado1 + tiradaDado2;

            System.out.println("Dados lanzados" + tiradaDado1 + "+" + tiradaDado2);
            System.out.println("El jugador" + "avanza" + valorTirada + "posiciones.");

            //Comprobamos que el contador de dobles no sea igual a 3, de ser a si encarcelamos al jugador
            if (contador == 3) {
                System.out.println(jugadorActual.getNombre() + " ha sacado tres dobles seguidos. Va a la cárcel.");
                jugadorActual.encarcelar(tablero.getPosiciones());
            }

            //comprobamos si se sacaron dobles
            if (tiradaDado1 == tiradaDado2) {
                contador ++;
                repetirTirada = true;
                System.out.println("Has sacado dobles vuelves a tirar.");
            }

            //Movemos al avatar el numero de posiciones que le corresponda
            avatar.moverAvatar(tablero.getPosiciones(), valorTirada);

            //FALTA IMPLEMENTAR VER TABLERO

            //Declaramos el lugar actual del avatar para poder evaluar su posicion
            Casilla casillaActual = avatar.getLugar();
            boolean sigueEnJuego = casillaActual.evaluarCasilla(jugadorActual, banca, valorTirada);

            //Si el jugador cae en irCarcel se debe encarcelar al jugador
            if (jugadorActual.isEnCarcel()) {
                jugadorActual.encarcelar(tablero.getPosiciones());
                return;
            }

            //Si el jugador se queda sin dinero suficiente debe declararse en bancarota
            if (!sigueEnJuego) {
                System.out.println(jugadorActual.getNombre() + " no tiene suficiente dinero. Debe hipotecar o declararse en bancarrota.");
                return;
            }

        }while (repetirTirada);

    }

    /*Método que ejecuta todas las acciones realizadas con el comando 'comprar nombre_casilla'.
    * Parámetro: cadena de caracteres con el nombre de la casilla.
     */
    private void comprar(String nombre) {
        //  Establecemos el jugador actual
        Jugador jugadorActual = jugadores.get(indiceJugadorActual);

        // Declaramos una variable para buscar la casilla que queremos comprar
        Casilla casillaComprar = tablero.encontrar_casilla(nombre);

        // Declaramos el resto de varibles necesarias
        float precio = casillaComprar.getValor();
        String tipo = casillaComprar.getTipo().toLowerCase();

        // Comprobamos si el tipo de la casilla que queremos comprar es correcta
        if (!(tipo.equals("solar") || tipo.equals("transporte") || tipo.equals("servicios"))) {
            System.out.println("La casilla " + nombre + " no se puede comprar.");
            return;
        }
        // Comprobamos si la casilla pertenece a otro jugador o a la banca
        if (!casillaComprar.getDuenho().equals(banca)) {
            System.out.println("La casilla " + nombre + " pertenece a " + casillaComprar.getDuenho() + ".");
            return;
        }

        // Comprobamos que el jugador tiene dienero suficiente para comprar la propiedad
        if (jugadorActual.getFortuna() < precio) {
            System.out.println("No tienes suficiente dinero para comprar " + nombre + ". Precio: " + precio);
            return;
        }

        // Comprobamos que el jugador este en la casilla para poder comprarla
        if (!jugadorActual.getAvatar().getLugar().equals(casillaComprar)) {
            System.out.println("No estás en la casilla " + nombre + ". Solo puedes comprar donde estás.");
            return;
        }

        // Permitimos que el jugador compre la casilla actual
        casillaComprar.comprarCasilla(jugadorActual, banca);
        System.out.println(jugadorActual.getNombre() + " ha comprado la casilla '" + nombre + "' por " + precio + ".");

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
        System.out.println("Propiedades en venta:");

        // Declaramos una variable para saber si la propiedad estan en venta
        boolean estaEnVenta = false;

        // Recorremos las casillas del tablero y comprobamos cuales estan disponibles para vender
        for (ArrayList<Casilla> lado : tablero.getPosiciones()) {
            for (Casilla c : lado) {
                // Solo nos interesan las que pueden ser compradas es decir, los solares, las casillas de transporte y los servicios
                if (c.getTipo().equalsIgnoreCase("Solar") ||
                        c.getTipo().equalsIgnoreCase("Transporte") ||
                        c.getTipo().equalsIgnoreCase("Servicios")) {

                    // Si la casilla aún pertenece a la banca, está en venta, por lo que si se imprimira
                    if (c.getDuenho() == banca) {
                        estaEnVenta = true; //Establecemos la casilla de propiedades en venta en verdadero
                        System.out.println("{");
                        System.out.println("  tipo: " + c.getTipo() + ","); //Imprimimos el tipo de casilla
                        if (c.getTipo().equalsIgnoreCase("Solar")) {    //Si es un solar imprimimos el grupo al que pertenece
                            System.out.println("  grupo: " + c.getGrupo().getColorGrupo() + ",");
                        }
                        System.out.println("  valor: " + c.getValor());   //Se imprime el valor de la casilla
                        System.out.println("}");
                    }
                }
            }
        }

        //Si no hay ninguna propiedad para comprar se imprime por pantalla
        if (!estaEnVenta) {
            System.out.println("No hay propiedades en venta actualmente.");
        }

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

    //Método que nos permite ver el tablero durante la partida
    public void verTablero(){
        System.out.println("Estado actual del tablero:\n\n");
        System.out.println(tablero.toString());

    }
}
