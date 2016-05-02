/*
 */

package fpuna.ia.othello.jugador;

import fpuna.ia.othello.Utils.Tablero;

/**
 *
 * @author gusamasan
 */
public class JugadorMaquina extends Jugador{
// -----------------------------------------------------------------------------

// -----------------------------------------------------------------------------

    /** Constructores *********************************************************/
    public JugadorMaquina(){

    }
    /**************************************************************************/

    public boolean esHumano(){
        return( false );
    }
    
    public Tablero jugar(short turno){

        return( this.algoritmo
                    .obtenerNuevaConfiguracionTablero( this.tablero, turno )
               );
        
    }
}
