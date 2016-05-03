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
public class AlgoritmoMiniMax extends Algoritmo{
// ----------------------------------------------------------------------

// ----------------------------------------------------------------------

    /** Constructores **************************************************/
    private int playerColor;
    public AlgoritmoMiniMax(){

    }
    /*******************************************************************/
    

    @Override
    public Tablero obtenerNuevaConfiguracionTablero( Tablero tablero, short turno ){

        System.out.println( "analizando siguiente jugada con MINIMAX" );
        this.playerColor=turno;
        Tablero tableroJugada=tablero.copiarTablero();
         try{
             miniMax(tableroJugada, this.getProfundidad(), playerColor);
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
     * @return
     */
    public int miniMax(Tablero tablero, int prof, int jugadorActual){
        // Si es final de juego se interpreta la heurística
        if (tablero.EsFinalDeJuego()|| prof==0)
            {
                int value= Heuristica.h2(tablero, playerColor);
                return value;
            }
        // Si no hay movimientos se interpreta la heurística
            if (!tablero.PuedeJugar(jugadorActual))
            {
                int value = miniMax(tablero, prof, -jugadorActual);
                return value;
            }
            //Proceso para generar el movimiento
            ArrayList<Casilla> movimientos = tablero.generarMovimiento(jugadorActual);
            Casilla mejorMovimiento = null;
            int mejorValor;
            if (jugadorActual == this.playerColor)
                mejorValor = -9999;
            else
                mejorValor = 9999;
             //Se crea otro opjeto de tablero para probar el movimiento
            for (Casilla cas : movimientos)
            {
                Tablero currentTablero = tablero.copiarTablero();

                if(jugadorActual == 1)
                    cas.asignarFichaBlanca();
                else if (jugadorActual == -1)
                    cas.asignarFichaNegra();
                currentTablero.ponerFicha(cas);
                currentTablero.imprimirTablero();

                int valorActual = miniMax(currentTablero, prof - 1, -jugadorActual);

                // Maximo
                if (jugadorActual == this.playerColor)
                {
                    if (valorActual > mejorValor)
                    {
                        mejorValor = valorActual;
                        mejorMovimiento = cas;
                    }
                }
                // Minimo
                else
                {
                    if (valorActual < mejorValor)
                    {
                        mejorValor = valorActual;
                        mejorMovimiento = cas;
                    }
                }
            }

            // Con el tablero comprobado, ahora sí, hacemos el mejor movimiento disponible
            if (mejorMovimiento != null)
            {
                tablero.ponerFicha(mejorMovimiento);
            }
            return mejorValor;
        }
        
    }
