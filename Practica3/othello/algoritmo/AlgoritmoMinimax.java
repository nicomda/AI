/*
 */

package fpuna.ia.othello.algoritmo;

import fpuna.ia.othello.Utils.Casilla;
import fpuna.ia.othello.Utils.Heuristica;
import fpuna.ia.othello.Utils.Tablero;
import java.util.ArrayList;

/**
 *
 * @author gusamasan
 */
public class AlgoritmoMinimax extends Algoritmo{
// ----------------------------------------------------------------------

// ----------------------------------------------------------------------

    /** Constructores **************************************************/

    private int playerColor;
    public AlgoritmoMinimax(){

    }
    /*******************************************************************/
    

    public Tablero obtenerNuevaConfiguracionTablero( Tablero tablero,short turno ){

        System.out.println( "analizando siguiente jugada con MINIMAX" );
        this.playerColor=turno;
        Tablero tableroJugada=tablero.copiarTablero();
         try{
            MiniMax(tableroJugada, this.getProfundidad(), turno);
            Thread.sleep( 1000 );
        }
        catch( Exception e ){
            e.printStackTrace();
        }

        return( tableroJugada );
    }


     public int MiniMax(Tablero tablero, int prof, int jugadorActual)
     {
            if (tablero.EsFinalDeJuego())
            {
                int value= Heuristica.h2(tablero, playerColor);
                return value;
            }
             if (prof == 0)
            {
                int value = Heuristica.h2(tablero, playerColor);
                return value;
            }
            if (!tablero.PuedeJugar(jugadorActual))
            {
                int value = MiniMax(tablero, prof, -jugadorActual);
                return value;
            }
            ArrayList<Casilla> movimientos = tablero.generarMovimiento(jugadorActual);
            Casilla mejorMovimiento = null;
            int mejorValor;
            if (jugadorActual == this.playerColor)
                mejorValor = -9999;
            else
                mejorValor = 9999;

            for (Casilla cas : movimientos)
            {
                Tablero tableroActual = tablero.copiarTablero();

                if(jugadorActual == 1)
                    cas.asignarFichaBlanca();
                else if (jugadorActual == -1)
                    cas.asignarFichaNegra();
                tableroActual.ponerFicha(cas);
                tableroActual.imprimirTablero();

                int valorActual = MiniMax(tableroActual, prof - 1, -jugadorActual);

                // MAX
                if (jugadorActual == this.playerColor)
                {
                    if (valorActual > mejorValor)
                    {
                        mejorValor = valorActual;
                        mejorMovimiento = cas;
                    }
                }
                // MIN
                else
                {
                    if (valorActual < mejorValor)
                    {
                        mejorValor = valorActual;
                        mejorMovimiento = cas;
                    }
                }
            }

            // Hacer el mejor movimiento
            if (mejorMovimiento != null)
            {
                tablero.ponerFicha(mejorMovimiento);
            }
            return mejorValor;
        }
}
