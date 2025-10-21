package monopoly;

import partida.*;
import java.util.ArrayList;

public class Casilla {

    //Atributos:
    private String nombre; //Nombre de la casilla
    private String tipo; //Tipo de casilla (Solar, Especial, Transporte, Servicios, Comunidad, Suerte y Impuesto).
    private float valor; //Valor de esa casilla (en la mayoría será valor de compra, en la casilla parking se usará como el bote).
    private int posicion; //Posición que ocupa la casilla en el tablero (entero entre 1 y 40).
    private Jugador duenho; //Dueño de la casilla (por defecto sería la banca).
    private Grupo grupo; //Grupo al que pertenece la casilla (si es solar).
    private float impuesto; //Cantidad a pagar por caer en la casilla: el alquiler en solares/servicios/transportes o impuestos.
    private float hipoteca; //Valor otorgado por hipotecar una casilla
    private ArrayList<Avatar> avatares; //Avatares que están situados en la casilla.
    private float valorHotel;
    private float valorCasa;
    private float valorPiscina;
    private float valorPistaDeporte;
    private float alquilerCasa;
    private float alquilerHotel;
    private float alquilerPiscina;
    private float alquilerPistaDeporte;


    //Constructores:
    public Casilla() {
        this.avatares = new ArrayList<>();
        this.duenho  = null;
    }//Parámetros vacíos

    /*Constructor para casillas tipo Solar, Servicios o Transporte:
     * Parámetros: nombre casilla, tipo (debe ser solar, serv. o transporte), posición en el tablero, valor y dueño.
     */
    public Casilla(String nombre,
                   String tipo,
                   int posicion,
                   float valor,
                   Jugador duenho,
                   Grupo grupo,
                   ArrayList<Avatar> avatares,
                   float impuesto,
                   float hipoteca,
                   float valorHotel,
                   float valorCasa,
                   float valorPiscina,
                   float valorPistaDeporte,
                   float alquilerCasa,
                   float alquilerHotel,
                   float alquilerPiscina,
                   float alquilerPistaDeporte) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.posicion = posicion;
        this.valor = valor;
        this.duenho = duenho;
        this.grupo = grupo;
        this.avatares = new ArrayList<>();
        this.impuesto = impuesto;
        this.hipoteca = hipoteca;
        this.valorHotel = valorHotel;
        this.valorCasa = valorCasa;
        this.valorPiscina = valorPiscina;
        this.valorPistaDeporte = valorPistaDeporte;
        this.alquilerCasa = alquilerCasa;
        this.alquilerHotel = alquilerHotel;
        this.alquilerPiscina = alquilerPiscina;
        this.alquilerPistaDeporte = alquilerPistaDeporte;
    }


    /*Constructor utilizado para inicializar las casillas de tipo IMPUESTOS.
     * Parámetros: nombre, posición en el tablero, impuesto establecido y dueño.
     */
    public Casilla(String nombre, int posicion, float impuesto, Jugador duenho) {
        this.nombre = nombre;
        this.tipo = "Impuesto";
        this.posicion = posicion;
        this.valor = valor;
        this.duenho = null;
        this.avatares = new ArrayList<>();
        this.impuesto = impuesto; //esto hay q cambiarlo mas adelante
        this.hipoteca = 0;//esto tb hay que cambiarlos
    }

    /*Constructor utilizado para crear las otras casillas (Suerte, Caja de comunidad y Especiales):
     * Parámetros: nombre, tipo de la casilla (será uno de los que queda), posición en el tablero y dueño.
     */
    public Casilla(String nombre, String tipo, int posicion, Jugador duenho) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.posicion = posicion;
        this.valor = valor;
        this.avatares = new ArrayList<>();
        this.impuesto = 0;
        this.hipoteca = 0;
    }

    //GETTERS Y SETTERS
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public Jugador getDuenho() {
        return duenho;
    }

    public void setDuenho(Jugador duenho) {
        this.duenho = duenho;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public float getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(float impuesto) {
        this.impuesto = impuesto;
    }

    public float getHipoteca() {
        return hipoteca;
    }

    public void setHipoteca(float hipoteca) {
        this.hipoteca = hipoteca;
    }

    public ArrayList<Avatar> getAvatares() {
        return avatares;
    }

    public void setAvatares(ArrayList<Avatar> avatares) {
        this.avatares = avatares;
    }

    //Método utilizado para añadir un avatar al array de avatares en casilla.
    public void anhadirAvatar(Avatar av) {
        //Se comprueba si existe el avatar y si esta registrado en los avatares
        if(av != null){
            //Se añade el avatar
            avatares.add(av);
            //Se establece la posicion del avatar a esa casilla
            av.setLugar(this);
        }
    }

    //Método utilizado para eliminar un avatar del array de avatares en casilla.
    public void eliminarAvatar(Avatar av) {
        //Se comprueba que exista el avatar
        if(av != null){
            avatares.remove(av);
        }
    }

    /*Método para evaluar qué hacer en una casilla concreta. Parámetros:
     * - Jugador cuyo avatar está en esa casilla.
     * - La banca (para ciertas comprobaciones).
     * - El valor de la tirada: para determinar impuesto a pagar en casillas de servicios.
     * Valor devuelto: true en caso de ser solvente (es decir, de cumplir las deudas), y false
     * en caso de no cumplirlas.*/
    public boolean evaluarCasilla(Jugador actual, Jugador banca, int tirada) {
        //comprobamos que el tipo pasado sea correcto
        if(tipo == null) tipo = "";
        //se hace un switch para definir el funcionamineto de unas casillas o de otras
        switch (tipo.toLowerCase()){
            case "solar":
                //Se comprueba si la propiedad tiene dueño o no
                if(duenho == null || duenho.equals(banca)){
                    System.out.println(actual.getNombre() + " ha caído en " + nombre + ". Está en venta por " + valor + "€.");
                    return true;
                }
                //Si la propiedad pertenece al jugador no tiene que pagar nada
                if (duenho.equals(actual)) {
                    System.out.println(actual.getNombre() + " ha caído en su propia propiedad, no tiene que pagar nada.");
                    return true;
                }
                //Si la propiedad tiene un propietario distinto al jugador que cayo en ella, debeb de cobrarle el alquiler
                System.out.println(actual.getNombre() + " ha caído en " + nombre + ", propiedad de " + duenho.getNombre() + ".");
                System.out.println("Debe pagar " + impuesto + "€ de alquiler.");


                //se resta el alquiler a la fortuna del jugador que cayó en la casilla
                actual.sumarFortuna(-impuesto);

                //se suma el alquiler a los gastos
                actual.sumarGastos(impuesto);
                //y se le suma el precio del alquiler a la fortuna de propietario
                duenho.sumarFortuna(impuesto);
                System.out.println(actual.getNombre() + " paga " + impuesto + "€ de alquiler a" + duenho.getNombre() + ".");

                return actual.getFortuna() >= 0; // true si no ha quebrado

            case "servicios":
                //comprobamos que el duenho sea la banca y si queremos podemos comprar
                if (duenho == null || duenho.equals(banca)) {
                    System.out.println(actual.getNombre() + " ha caído en el servicio " + nombre + ". Está en venta por " + valor + "€.");
                    return true;
                }
                //Si el propietario es el jugador no tiene que pagar nada
                if (duenho.equals(actual)) {
                    System.out.println(actual.getNombre() + " ha caído en su propio servicio. No paga nada.");
                    return true;
                }

                // Contar cuántos servicios tiene el dueño
                int numServicios = 0;
                for (Casilla c : duenho.getPropiedades()) {
                    if (c.getTipo().equalsIgnoreCase("servicios")) numServicios++;
                }

                // Calcular multiplicador segun el numero de servicios que tenga el dueño
                int multiplicador = (numServicios == 2) ? 10 : 4;
                //Calulamos el percio del alquiler
                float alquilerServicio = multiplicador * tirada * impuesto;

                System.out.println(actual.getNombre() + " ha caído en el servicio " + nombre + " de " + duenho.getNombre() + ". Debe pagar " + alquilerServicio + "€ (" + multiplicador + " × tirada × factorServicio).");

                //Cambiamos la fortuna del jugador actual y del dueño del servicio
                actual.sumarFortuna(-alquilerServicio);
                actual.sumarGastos(alquilerServicio);
                duenho.sumarFortuna(alquilerServicio);

                //Esta linea indica si el jugador aun tiene dinero
                return actual.getFortuna() >= 0;

            case "transporte":
                //Comprobamos si la casilla perenece a la banca
                if (duenho == null || duenho.equals(banca)) {
                    System.out.println(actual.getNombre() + " ha caído en el transporte " + nombre + ". Está en venta por " + valor + "€.");
                    return true;
                }
                //Comprobamos que la casilla no pertenezca ya al jugador
                if (duenho.equals(actual)) {
                    System.out.println(actual.getNombre() + " ha caído en su propio transporte. No paga nada.");
                    return true;
                }

                // Calcular alquiler total: suma de los alquileres (impuestos) de todos los transportes del dueño
                float alquilerTotal = 0;
                int numTransportes = 0;
                for (Casilla c : duenho.getPropiedades()) {
                    if (c.getTipo().equalsIgnoreCase("transporte")) {
                        alquilerTotal += c.getImpuesto();
                        numTransportes++;
                    }
                }

                System.out.println(actual.getNombre() + " ha caído en el transporte " + nombre + " de " + duenho.getNombre() + ". El propietario posee " + numTransportes + " transportes.");
                System.out.println("Debe pagar un alquiler total de " + alquilerTotal + "€.");

                //Se actualizan las fortunas del jugador y del dueño de la casilla
                actual.sumarFortuna(-alquilerTotal);
                actual.sumarGastos(alquilerTotal);
                duenho.sumarFortuna(alquilerTotal);

                return actual.getFortuna() >= 0;


            case "impuesto":
                //Cuando se cae en la casilla de impuesto se debe pagar siempre
                System.out.println(actual.getNombre() + " ha caído en la casilla de impuesto: " + nombre + ".");
                System.out.println("Debe pagar " + impuesto + "€.");
                //a la fortuna del jugador se resta el impuesto necesario
                actual.sumarFortuna(-impuesto);
                //se le suma a los gastos del jugador el impuesto a pagar
                actual.sumarGastos(impuesto);
                banca.sumarFortuna(impuesto);

                return actual.getFortuna() >= 0;

            case "especial":
                // Según el nombre de la casilla
                if (nombre.equalsIgnoreCase("IrCarcel")) {//Si tiene que ir a la carcel se avisa
                    System.out.println(actual.getNombre() + " ha caído en 'Ir a la cárcel'.");
                    // No movemos aquí porque no tenemos el tablero.
                    // El controlador del turno debe llamar a actual.encarcelar(posiciones) después.
                    // Se pone a true que el jugador este en la carcel
                    actual.setEnCarcel(true);
                    return true;
                } else if (nombre.equalsIgnoreCase("Parking")) {    //Si se cae en el parking recibe un valor que se suma a su fortuna
                    System.out.println(actual.getNombre() + " ha caído en el Parking. Recibe " + valor + "€ del bote.");
                    actual.sumarFortuna(valor);
                    this.valor = 0;
                    return true;
                } else if (nombre.equalsIgnoreCase("Carcel")) { // Si se cae en la casilla de carcel, pero no le toca quedarse ahi, se considera que está de visita
                    System.out.println(actual.getNombre() + " está de visita en la cárcel.");
                    return true;
                } else if (nombre.equalsIgnoreCase("Salida")) { //Cuando se pasa por la salida se reciben 2 millones, que se suman a la fortuna
                    System.out.println(actual.getNombre() + " ha pasado por la salida. ¡Recibe 2.000.000€!");
                    actual.sumarFortuna(2_000_000f);
                    return true;
                } else {
                    System.out.println(actual.getNombre() + " ha caído en una casilla especial: " + nombre);
                    return true;
                }

            case "suerte": // Aun falta por implementar
                System.out.println(actual.getNombre() + " ha caído en una casilla de Suerte. (Pendiente implementar)");
                return true;

            case "comunidad":   // Aun falta por implementar
                System.out.println(actual.getNombre() + " ha caído en una casilla de Comunidad. (Pendiente implementar)");
                return true;

            default:
                System.out.println(actual.getNombre() + " ha caído en una casilla desconocida: " + nombre);
                return true;
        }
    }

    /*Método usado para comprar una casilla determinada. Parámetros:
     * - Jugador que solicita la compra de la casilla.
     * - Banca del monopoly (es el dueño de las casillas no compradas aún).*/
    public void comprarCasilla(Jugador solicitante, Jugador banca) {
        //se comprueba si la casilla pertenece a la banca o no
        if(duenho == null || duenho.equals(banca)){
            //se comprueba que el solicitante tenga el dinero suficiente para comprar la casilla
            if(solicitante.getFortuna()>= valor){
                //Se le resta el valor de la propiedad a la fortuna del jugador
                solicitante.sumarFortuna(-valor);
                //Se le suma el valor a los gastos del solicitante
                solicitante.sumarGastos(valor);
                //Y se suma ese valor a la fortuna de la banca
                banca.sumarFortuna(valor);

                //se añade la propieda a las propiedades del solicitante
                this.duenho = solicitante;
                solicitante.anhadirPropiedad(this);
                System.out.println("El jugador" + solicitante.getNombre() + "haa compprado la propiedad:" + nombre);
            }
            System.out.println("El jugador" + solicitante.getNombre() + "no dispone del dinero necesario para comprar la propiedad.");

        }
        System.out.println("La propiedad ya pertenece a " +  duenho.getNombre());

    }

    /*Método para añadir valor a una casilla. Utilidad:
     * - Sumar valor a la casilla de parking.
     * - Sumar valor a las casillas de solar al no comprarlas tras cuatro vueltas de todos los jugadores.
     * Este método toma como argumento la cantidad a añadir del valor de la casilla.*/
    public void sumarValor(float suma) {
        this.valor += suma;
    }

    /*Método para mostrar información sobre una casilla.
     * Devuelve una cadena con información específica de cada tipo de casilla.*/
    public String infoCasilla(String nombre) {
        //Comprobamos que la casilla de la cual nos piden datos es un Solar
        //La función equalsIgnoreCase() funciona igual que equals() menos porque omite la diferencia entre mayúsculas y minúsculas
        //No es necesario que se realice ninguna modificación en la implementación de la funcion equalsIgnoreCase(),
        //pues en este caso solo estamos comparando texto, no estamos comparando objetos.
        if(!tipo.equalsIgnoreCase("Solar")){
            return "Esta casilla no es un solar\n";
        }

        StringBuilder sb = new StringBuilder();

        //Información del tipo
        sb.append("Tipo: ").append(tipo).append("\n");

        //Información del grupo/color
        if(grupo != null){
            sb.append("Grupo: ").append(grupo.getColorGrupo()).append("\n");
        }else{
            sb.append("Grupo: No pertenece a un grupo\n");
        }

        //Información del propietario
        if(duenho != null){
            sb.append("Duenho: ").append(duenho.getNombre()).append("\n");
        }else{
            sb.append("Duenho: Banca\n");
        }

        //Información de valores varios
        sb.append("Valor: ").append(valor).append("\n");
        sb.append("Alquiler: ").append(impuesto).append("\n");
        sb.append("valor hotel: ").append(String.format("%.0f", valorHotel)).append(",\n");
        sb.append("valor casa: ").append(String.format("%.0f", valorCasa)).append(",\n");
        sb.append("valor piscina: ").append(String.format("%.0f", valorPiscina)).append(",\n");
        sb.append("valor pista de deporte: ").append(String.format("%.0f", valorPistaDeporte)).append(",\n");
        sb.append("alquiler casa: ").append(String.format("%.0f", alquilerCasa)).append(",\n");
        sb.append("alquiler hotel: ").append(String.format("%.0f", alquilerHotel)).append(",\n");
        sb.append("alquiler piscina: ").append(String.format("%.0f", alquilerPiscina)).append(",\n");
        sb.append("alquiler pista de deporte: ").append(String.format("%.0f", alquilerPistaDeporte)).append("\n");

        return sb.toString();
    }

    /* Método para mostrar información de una casilla en venta.
     * Valor devuelto: texto con esa información.
     */
    public String casEnVenta() {
        return "A";
    }
}