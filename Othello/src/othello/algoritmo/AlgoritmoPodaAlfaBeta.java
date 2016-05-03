/*
 */

package othello.algoritmo;

import othello.Utils.Casilla;
import othello.Utils.Heuristica;
import othello.Utils.Tablero;
import java.util.ArrayList;

/**
 *
 * @author gusamasan
 */
public class AlgoritmoPodaAlfaBeta extends Algoritmo{
// ----------------------------------------------------------------------

// ----------------------------------------------------------------------

    /** Constructores **************************************************/
    private int playerColor;
    public AlgoritmoPodaAlfaBeta(){

    }
    /*******************************************************************/
    

    @Override
    public Tablero obtenerNuevaConfiguracionTablero( Tablero tablero, short turno ){

        System.out.println( "analizando siguiente jugada con ALFABETA" );
        this.playerColor=turno;
        Tablero tableroJugada=tablero.copiarTablero();
         try{
             int beta = Integer.MAX_VALUE;
             int alfa = Integer.MIN_VALUE;
             alfaBeta(tableroJugada, this.getProfundidad(), playerColor, alfa, beta);
            Thread.sleep( 1000 );
        }
        catch( Exception e ){
            e.printStackTrace();
        }

        return( tableroJugada );
    }

     /**
     *
     * Éste es el método que tenemos que implementar.
     * 
     * Algoritmo AlfaBeta para determinar cuál es el siguiente mejor movimiento
     * 
     * @param tablero
     * Configuración actual del tablero
     * @param prof
     * Profundidad de búsqueda
     * @param jugadorActual
     * Nos indica a qué jugador (FICHA_BLANCA ó FICHA_NEGRA) le toca
     * @param alfa
     * @param beta
     * Parámetros alfa y beta del algoritmo
     * @return
     */
    public int alfaBeta(Tablero tablero, int prof, int jugadorActual, int alfa, int beta)
    {        

	 // si el juego llega al final o no puede buscar mas
         if (tablero.EsFinalDeJuego()|| prof==0)
            {
                int value= Heuristica.h2(tablero, playerColor);
                return value;
            }

        // Si este jugador no puede jugar, pasa el turno
        if (!tablero.PuedeJugar(jugadorActual))
        {
            int value = alfaBeta(tablero, prof, -jugadorActual, alfa, beta);
            return value;
        }

        // cogemos las casillas en las cuales podemos jugar
        ArrayList<Casilla> casillas = tablero.generarMovimiento(jugadorActual);
        
        // ahora tenemos que encontrar el mejor movimiento actual
        Casilla bestMovement = null;

        for (Casilla cas: casillas)
        {
            // se realiza una copia del objeto tablero.
            Tablero currentTablero = tablero.copiarTablero();

            // se realiza un movimiento en el tablero creado
            if(jugadorActual == 1)
                cas.asignarFichaBlanca();
            else if (jugadorActual == -1)
                cas.asignarFichaNegra();
            currentTablero.ponerFicha(cas);
            currentTablero.imprimirTablero();

            // Se evalua a ver si el movimiento es bueno.
            int valorActual = alfaBeta(currentTablero, prof - 1, -jugadorActual, alfa, beta);

            // Maximo
            if (jugadorActual == this.playerColor)
            {
                if (valorActual > alfa)
                {
                    alfa = valorActual;
                    bestMovement = cas;
                }
                // Es poda?
                if (alfa >= beta)
                    return alfa;
            }
            // Minimo
            else
            {
                if (valorActual < beta)
                {
                    beta = valorActual;
                    bestMovement = cas;
                }
                // Es poda?
                if(alfa >= beta)
                    return beta;
            }
        }
        // Realizar ahora sí el mejor movimiento disponible.
        if (bestMovement != null)
        {
            tablero.ponerFicha(bestMovement);

        }
        // Devolver el valor para el movimiento
        if (jugadorActual == this.playerColor)
            return alfa;

        else
            return beta;
    }
}
