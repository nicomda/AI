/*
 */

package fpuna.ia.othello.algoritmo;

import fpuna.ia.othello.Utils.Casilla;
import fpuna.ia.othello.Utils.Tablero;

/**
 *
 * @author gusamasan
 */
public class AlgoritmoHumano extends Algoritmo{
// ----------------------------------------------------------------------

    private Casilla casillaJugada;
    
// ----------------------------------------------------------------------

    /** Constructores **************************************************/
    public AlgoritmoHumano(){

    }
    /*******************************************************************/
    

    public void asignarCasillaJuagada( Casilla casilla ){
        this.casillaJugada = casilla;
    }

    public Tablero obtenerNuevaConfiguracionTablero( Tablero tablero, short turno ){

        System.out.println( "analizando siguiente jugada con HUMANO" );

        while(!tablero.ponerFicha( this.casillaJugada )){
            if( !tablero.PuedeJugar( this.casillaJugada.obtenerColorFicha() ) )
                return( null );
        };

        return( tablero );
    }
}
