/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fpuna.ia.othello;


import fpuna.ia.othello.Utils.Tablero;

/**
 *
 * @author gusamasan
 */
public interface GUI {

    public void avisarFinalizacionDelJuego( int cantidadFichasNegras, int cantidadFichasBlancas );
    
    public void avisarTurnoFichaBlanca();

    public void avisarTurnoFichaNegra();

    public void avisarPasoTurnoFichaBlanca();

    public void avisarPasoTurnoFichaNegra();
    
    public Tablero getTablero();

    public void setTablero( Tablero tablero );

    public void limpiarAvisoPasoTurno();

    public void refrescarTablero();
}
