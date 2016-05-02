/* 
 */

package fpuna.ia.othello.gui.accion;

import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import fpuna.ia.othello.algoritmo.AlgoritmoHumano;
import fpuna.ia.othello.gui.OthelloGUI;
import fpuna.ia.othello.ConstanteOthello;
import fpuna.ia.othello.Juego;
import fpuna.ia.othello.jugador.JugadorHumano;
import fpuna.ia.othello.Utils.Casilla;
import fpuna.ia.othello.gui.Escaque;

/**
 *
 * @author gusamasan
 */
public class AccionJugadaHumano implements ActionListener{
// ------------------------------------------------------------------------

    private OthelloGUI othelloGUI;

// ------------------------------------------------------------------------

    /** Constructores ****************************************************/
    public AccionJugadaHumano( OthelloGUI othelloGUI ){
        this.othelloGUI    = othelloGUI;
           
    }
    /*********************************************************************/

    public void actionPerformed( ActionEvent evento ){
    // ------------------------------------------------------------------------

        Escaque         escaqueJugado;
        Casilla         casillaJugada;
        JugadorHumano jugadorHumano;
        Juego elJuego;

    // ------------------------------------------------------------------------

        elJuego = this.othelloGUI.obtenerJuego();

        if( elJuego.getJugadorDeTurno().esHumano() ){

            //JOptionPane.showMessageDialog(othelloGUI, "Prueba" );
            escaqueJugado   = (Escaque)evento.getSource();

            casillaJugada   = new Casilla( escaqueJugado.getFila(), escaqueJugado.getColumna() );

            jugadorHumano   = ( JugadorHumano )elJuego.getJugadorDeTurno();
            
            if( jugadorHumano.getColorFichaAsignada().equals( ConstanteOthello.FICHA_NEGRA ) )
                casillaJugada.asignarFichaNegra();
            else
                casillaJugada.asignarFichaBlanca();
            
            ( (AlgoritmoHumano)jugadorHumano.getAlgoritmo() ).asignarCasillaJuagada( casillaJugada );

            jugadorHumano.continuar();
        }
    }

}
