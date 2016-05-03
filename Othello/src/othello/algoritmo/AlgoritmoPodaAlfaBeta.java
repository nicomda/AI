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

	 // Si el juego termina, termina la búsqueda
        if (tablero.EsFinalDeJuego())
        {
            int value= Heuristica.h2(tablero, playerColor);
            return value;
        }
        // Al final del espacio de búsqueda
        if (prof == 0)
        {
            int value = Heuristica.h2(tablero, playerColor);
            return value;
        }

        // Si este jugador no puede jugar, pasa el turno
        if (!tablero.PuedeJugar(jugadorActual))
        {
            int value = alfaBeta(tablero, prof, -jugadorActual, alfa, beta);
            return value;
        }

        // Obtiene la lista de movimientos posibles, ordenado por la mayor cantidad de piezas volteadas
        ArrayList<Casilla> movimientos = tablero.generarMovimiento(jugadorActual);
        
        // Encontrar el mejor movimiento actual
        Casilla mejorMovimiento = null;

        for (Casilla cas: movimientos)
        {
            // Copiar el tablero
            Tablero tableroActual = tablero.copiarTablero();

            // Realizar el movimiento seleccionado
            if(jugadorActual == 1)
                cas.asignarFichaBlanca();
            else if (jugadorActual == -1)
                cas.asignarFichaNegra();
            tableroActual.ponerFicha(cas);
            tableroActual.imprimirTablero();

            // Evaluar el tablero
            int valorActual = alfaBeta(tableroActual, prof - 1, -jugadorActual, alfa, beta);

            // MAX
            if (jugadorActual == this.playerColor)
            {
                if (valorActual > alfa)
                {
                    alfa = valorActual;
                    mejorMovimiento = cas;
                }
                // Poda?
                if (alfa >= beta)
                    return alfa;
            }
            // MIN
            else
            {
                if (valorActual < beta)
                {
                    beta = valorActual;
                    mejorMovimiento = cas;
                }
                // Poda?
                if(alfa >= beta)
                    return beta;
            }
        }
        // Realizar el mejor movimiento
        if (mejorMovimiento != null)
        {
            tablero.ponerFicha(mejorMovimiento);

        }
        // Retornar el valor para el mejor movimiento
        if (jugadorActual == this.playerColor)
            return alfa;

        else
            return beta;
    }
}
