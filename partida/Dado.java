package partida;
import java.util.concurrent.ThreadLocalRandom;


public class Dado {
    //El dado solo tiene un atributo en nuestro caso: su valor.
    private int valor;


    //Getter para la clase dado
    public int getValor() {
        return valor;
    }


    //Metodo para simular lanzamiento de un dado: devolverá un valor aleatorio entre 1 y 6.
    public int hacerTirada() {
        valor = ThreadLocalRandom.current().nextInt(1,7); //genera un numero entre el 1 y el 6
        return valor;
    }




}
