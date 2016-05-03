/*
 * 
 */

package fpuna.ia.othello.gui;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.*;

import fpuna.ia.othello.ConstanteOthello;
import fpuna.ia.othello.gui.accion.AccionJuegoNuevo;
import fpuna.ia.othello.gui.accion.AccionConfiguracionLista;
import fpuna.ia.othello.jugador.*;

import fpuna.ia.othello.GUI;
import fpuna.ia.othello.Juego;
import fpuna.ia.othello.Utils.Tablero;
/**
 *
 * @author gusamasan
 */
public class OthelloGUI extends JFrame implements GUI{
// ----------------------------------------------------------------------

    private short   turno;

    private boolean pararJuego;

    private Escaque senialTurno ,
                    senialPasoTurno;

    private TableroGUI elTableroGUI;

    private JTabbedPane pestanias;

    private Juego   elJuego;

    private ButtonGroup eleccionAlgoritmoJugador1   ,
                        eleccionAlgoritmoJugador2   ,
                        eleccionFichaJugadorUno     ,
                        eleccionJugadorUno          ,
                        eleccionJugadorDos;

    private JTextField  eleccionProfundidadAlgoritmo1,
                        eleccionProfundidadAlgoritmo2;
    
// ----------------------------------------------------------------------

    /** Constructores ***************************************************/
    public OthelloGUI(){
        super( "Othello" );        
        
        this.inicializar();
    }
    /********************************************************************/

    public void avisarFinalizacionDelJuego( int cantidadFichasNegras ,
                                            int cantidadFichasBlancas ){
    // ------------------------------------------------------------------------

        int diferencia;

    // ------------------------------------------------------------------------

        diferencia  = cantidadFichasNegras - cantidadFichasBlancas;

        if( diferencia > 0 ){
            javax.swing.JOptionPane.showMessageDialog( this, 
            "¡El jugador con fichas NEGRAS es el ganador! con " + diferencia + " fichas a favor" );
        }
        else if( diferencia < 0 ){
            javax.swing.JOptionPane.showMessageDialog( this,
            "¡El jugador con fichas BLANCAS es el ganador! con " + ( -diferencia ) + " fichas a favor" );
        }
        else
            javax.swing.JOptionPane.showMessageDialog( this,
            "¡¡¡Empate....!!!" );
    }
        
    public void avisarTurnoFichaBlanca(){
        this.senialTurno.mostrarFichaBlanca();
    }

    public void avisarTurnoFichaNegra(){
        this.senialTurno.mostrarFichaNegra();
    }

    public void avisarPasoTurnoFichaBlanca(){
        this.senialPasoTurno.mostrarFichaBlanca();
    }

    public void avisarPasoTurnoFichaNegra(){
        this.senialPasoTurno.mostrarFichaNegra();
    }

    public void deshabilitarAvisos(){
        this.senialTurno.setEnabled( false );
        this.senialPasoTurno.setEnabled( false );
        this.senialPasoTurno.limpiar();
    }

    public void jugar(){

        this.elJuego.start();
    }

    public void habilitarAvisos(){
        this.senialTurno.mostrarFichaNegra();
        this.limpiarAvisoPasoTurno();
        this.senialTurno.setEnabled( true );
        this.senialPasoTurno.setEnabled( true );
    }

    private void inicializar(){
    // -------------------------------------------------------------------

    // -------------------------------------------------------------------       

        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        //this.setLayout( new GridLayout(1,1 ));
        //this.setLayout( null );

        //this.setSize( 100, 100 );
        java.awt.Dimension tamanio;

        tamanio = new java.awt.Dimension( 1020, 750 );
        //this.setPreferredSize( tamanio );
        this.setMaximumSize( tamanio );
        //this.setSize( 350, 350 );
        
        this.add( this.obtenerPestanias() );
        
        this.pack();
        this.setVisible(true);

        //this.cargarConfiguracion();
        //this.tablero   = new Tablero();
    }

    public void cargarConfiguracion(){
    // ------------------------------------------------------------------------

        Jugador jugadorUno, jugadorDos;

    // ------------------------------------------------------------------------

        this.elJuego    = new Juego( this );

        jugadorUno  = FabricaAbstractaJugador
                        .obtenerJugador(    this.obtenerEleccionJugadorUno()                ,
                                            this.obtenerEleccionAlgoritmoJugadorUno()       ,
                                            Integer.parseInt(
                                                        this.obtenerEleccionProfundidadAlgoritmoUno()
                                                        ), this.elJuego );

        jugadorDos  = FabricaAbstractaJugador
                        .obtenerJugador(    this.obtenerEleccionJugadorDos()              ,
                                            this.obtenerEleccionAlgoritmoJugadorDos()     ,
                                            Integer.parseInt(
                                                        this.obtenerEleccionProfundidadAlgoritmoDos()
                                                        ), this.elJuego );

        jugadorUno.setTablero( this.elTableroGUI.getTablero() );
        jugadorDos.setTablero( this.elTableroGUI.getTablero() );

        if( this.eleccionFichaJugadorUno.getSelection()
                .getActionCommand()
                .equals( ConstanteOthello.FICHA_NEGRA )){

            jugadorUno.setColorFichaAsignada( ConstanteOthello.FICHA_NEGRA );
            jugadorDos.setColorFichaAsignada( ConstanteOthello.FICHA_BLANCA);

            this.elJuego.setJugadorDeTurno(         jugadorUno );
            this.elJuego.setJugadorConFichaNegra(   jugadorUno );
            this.elJuego.setJugadorConFichaBlanca(  jugadorDos );
        }else{            

            jugadorUno.setColorFichaAsignada( ConstanteOthello.FICHA_BLANCA );
            jugadorDos.setColorFichaAsignada( ConstanteOthello.FICHA_NEGRA  );

            this.elJuego.setJugadorDeTurno(         jugadorDos );
            this.elJuego.setJugadorConFichaNegra(   jugadorDos );
            this.elJuego.setJugadorConFichaBlanca(  jugadorUno );
        }
        
    }

    public void limpiarAvisoPasoTurno(){
        this.senialPasoTurno.limpiar();
    }

    public String obtenerEleccionAlgoritmoJugadorUno(){
        return( this.eleccionAlgoritmoJugador1.getSelection().getActionCommand() );
    }

    public String obtenerEleccionAlgoritmoJugadorDos(){
        return( this.eleccionAlgoritmoJugador2.getSelection().getActionCommand() );
    }

    public String obtenerEleccionFichaJugadorUno(){        
        return( this.eleccionFichaJugadorUno.getSelection().getActionCommand() );
    }

    public String obtenerEleccionJugadorDos(){
        return( this.eleccionJugadorDos.getSelection().getActionCommand() );
    }

    public String obtenerEleccionJugadorUno(){
        return( this.eleccionJugadorUno.getSelection().getActionCommand() );
    }

    public String obtenerEleccionProfundidadAlgoritmoUno(){
        return( this.eleccionProfundidadAlgoritmo1.getText().trim() );
    }

    public String obtenerEleccionProfundidadAlgoritmoDos(){
        return( this.eleccionProfundidadAlgoritmo2.getText().trim() );
    }

    public Juego obtenerJuego(){
        return( this.elJuego );
    }
    
    private JTabbedPane obtenerPestanias(){
    // ----------------------------------------------------------------------        

        //JComponent panelJuego;
        JComponent panelTablero;
        JSeparator separador;

    // ----------------------------------------------------------------------

        separador  = new JSeparator();
        separador.setOrientation( JSeparator.VERTICAL );
        
        pestanias           = new JTabbedPane();        

        //panelJuego          = new JPanel();
        panelTablero        = new JPanel();
        

        
        //panelJuego.setLayout(new BoxLayout( panelJuego, BoxLayout.LINE_AXIS ));

        this.elTableroGUI      = new TableroGUI( this );
        
        panelTablero.setLayout(new BoxLayout( panelTablero, BoxLayout.PAGE_AXIS));
        //panelJuego.setLayout( new GridLayout( 3, 1 ) );
        panelTablero.add( this.elTableroGUI );
        panelTablero.add( new JSeparator() );
        panelTablero.add( this.obtenerBotonNuevoJuevo() );

        JSplitPane panelJuego = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                           panelTablero, this.obtenerPanelMensaje() );

        //panelJuego.add( panelTablero    );
        //panelJuego.add( separador );
        //panelJuego.add( this.obtenerPanelMensaje()    );

        pestanias.addTab("Juego", null, panelJuego ,"Does nothing");

        pestanias.setMnemonicAt(0, KeyEvent.VK_1);
        
        pestanias.addTab("Configuración", null, this.obtenerPanelConfiguracion(),
                          "Definición de los parámetros del juego");
        pestanias.setMnemonicAt(1, KeyEvent.VK_2);        

        return( pestanias );
    }

    private JComponent obtenerPanelConfiguracion(){
    // ----------------------------------------------------------------------

        JPanel panel;

    // ----------------------------------------------------------------------

        panel   = new JPanel();
        //panel.setSize(400, 400 );
        panel.setLayout( new BoxLayout( panel, BoxLayout.Y_AXIS ) );
        //panel.setLayout( new GridLayout( 4, 2 ) );


        this.eleccionJugadorUno         = new ButtonGroup();
        JPanel panelJugadorUno          = this.obtenerOpcionesJugador( "Jugador (1)", this.eleccionJugadorUno );

        this.eleccionJugadorDos         = new ButtonGroup();
        JPanel panelJugadorDos          = this.obtenerOpcionesJugador( "Jugador (2)", this.eleccionJugadorDos );

        this.eleccionAlgoritmoJugador1  = new ButtonGroup();
        this.eleccionAlgoritmoJugador2  = new ButtonGroup();
        
        JPanel panelAlgoritmo   = this.obtenerOpcionesAlgoritmo( "Algoritmo para Jugador (1)", this.eleccionAlgoritmoJugador1 );
        JPanel panelAlgoritmo2  = this.obtenerOpcionesAlgoritmo( "Algoritmo para Jugador (2)", this.eleccionAlgoritmoJugador2 );


        JPanel panelFicha   = this.obtenerOpcionesFichas();

        JPanel panelVarios   = new JPanel();        
        panelVarios.setLayout( new GridLayout( 3, 1) );
        panelVarios.add(new JLabel("Varios"));
        panelVarios.add( new JSeparator() );
        panelVarios.add( this.obtenerOpcionesVarios() );        


        JPanel izquierda = new JPanel();
        JPanel derecha = new JPanel();

        izquierda.setLayout( new GridLayout(3, 1 ) );
        derecha.setLayout( new GridLayout(2, 1 ) );

        
        panel.add( panelJugadorUno );
        panel.add( panelJugadorDos );
        panel.add( panelFicha );
        panel.add( panelAlgoritmo );
        panel.add( panelAlgoritmo2 );
        panel.add( panelVarios );
        panel.add( new JSeparator() );
        panel.add( this.obtenerBotonConfiguracionLista() );
        
        return( panel );
    }

    private JPanel obtenerPanelMensaje(){
    // ----------------------------------------------------------------------

        JPanel  panelMensaje, panelTurno, panelPaso, panelPrincipal;        

    // ----------------------------------------------------------------------

        this.senialTurno         = new Escaque();
        this.senialPasoTurno     = new Escaque();
        
        this.senialTurno.mostrarFichaNegra();
        this.senialTurno.setEnabled( false );
        this.senialPasoTurno.setEnabled( false );
        //escaquePaso.

        panelMensaje    = new JPanel();
        panelTurno      = new JPanel();
        panelPaso       = new JPanel();
        panelPrincipal  = new JPanel();
        
        //panelMensaje.setLayout( new BoxLayout( panelMensaje, BoxLayout.Y_AXIS ) );
        //panelTurno.setLayout( new BoxLayout( panelTurno, BoxLayout.X_AXIS ) );
        panelMensaje.setLayout( new GridLayout( 4, 1 ) );
        panelTurno.setLayout( new GridLayout( 2, 2 ) );        
        panelPaso.setLayout( new BoxLayout( panelPaso, BoxLayout.X_AXIS ) );
                
        panelTurno.add( new JLabel( "JUEGA" ) );
        panelTurno.add( this.senialTurno );
        panelTurno.add( new JLabel( "PASA TURNO" ) );        
        panelTurno.add( this.senialPasoTurno );

        panelMensaje.add( panelTurno );
        panelMensaje.add( panelPaso );

        panelPrincipal.add( panelMensaje );
        
        return( panelPrincipal );
    }
    private JButton obtenerBotonConfiguracionLista(){
    // ------------------------------------------------------------------------

        JButton boton;

    // ------------------------------------------------------------------------

        boton   = new JButton( "Listo" );
        boton.addActionListener( new AccionConfiguracionLista( this, this.pestanias ) );
        
        return( boton );
    }

    /**
     * Retorn el botón "nuevo juego" cuya función es reiniciar el juego
     *
     * @return objeto del tipo <JButton> utilizable para reiniciar el juego
     *
     * @autor gusamasan
     */
    private JButton obtenerBotonNuevoJuevo(){
    // ----------------------------------------------------------------------

        JButton boton;

    // ----------------------------------------------------------------------

        boton  = new JButton( "jugar" );
        boton.addActionListener( new AccionJuegoNuevo( this, this.elTableroGUI ) );

        return( boton );
    }

    private JPanel obtenerOpcionesAlgoritmo( String titulo, ButtonGroup eleccionAlgoritmo ){
    // ----------------------------------------------------------------------

        JPanel panel;

    // ----------------------------------------------------------------------
        
        panel   = new JPanel();
        panel.setLayout( new GridLayout( 6, 1 ) );        

        JRadioButton minimax = new JRadioButton( "MiniMax" );
        minimax.setMnemonic(KeyEvent.VK_M );
        minimax.setActionCommand( ConstanteOthello.ALGORITMO_MINIMAX );
        minimax.setSelected(true);

        JRadioButton alfaBeta = new JRadioButton( "Alfa Beta" );
        alfaBeta.setMnemonic(KeyEvent.VK_L );
        alfaBeta.setActionCommand( ConstanteOthello.ALGORITMO_PODA_ALFA_BETA );

        JRadioButton aleatorio = new JRadioButton( "Aleatorio" );
        aleatorio.setMnemonic(KeyEvent.VK_A );
        aleatorio.setActionCommand( ConstanteOthello.ALGORITMO_ALEATORIO );

        //eleccionAlgoritmo = new ButtonGroup();
        eleccionAlgoritmo.add( minimax      );
        eleccionAlgoritmo.add( alfaBeta     );
        eleccionAlgoritmo.add( aleatorio    );

        panel.add( new JLabel( titulo ) );
        panel.add( new JSeparator() );
        panel.add( minimax      );
        panel.add( alfaBeta     );
        panel.add( aleatorio    );
        panel.add( new JLabel( "" ) );

        return( panel );
    }

    private JPanel obtenerOpcionesJugador( String etiqueta, ButtonGroup grupo ){
    // ----------------------------------------------------------------------

        JPanel panel;

    // ----------------------------------------------------------------------

        JSeparator separador   = new JSeparator(SwingConstants.HORIZONTAL );
        separador.setSize( 10, 10 );

        panel   = new JPanel();
        //panel.setLayout( new BoxLayout( panel, BoxLayout.Y_AXIS ) );
        panel.setLayout( new GridLayout( 5, 1 ) );

        JRadioButton jugadorHumano = new JRadioButton( "Humano" );
        jugadorHumano.setMnemonic(KeyEvent.VK_O );
        jugadorHumano.setActionCommand( ConstanteOthello.JUGADOR_HUMANO );
        jugadorHumano.setSelected(true);

        JRadioButton jugadorMaquina = new JRadioButton( "Máquina" );
        jugadorMaquina.setMnemonic(KeyEvent.VK_I );
        jugadorMaquina.setActionCommand( ConstanteOthello.JUGADOR_MAQUINA );

        
        //&&grupo = new ButtonGroup();
        grupo.add( jugadorHumano    );
        grupo.add( jugadorMaquina   );
        

        panel.add( new JLabel( etiqueta ) );
        panel.add( separador );
        panel.add( jugadorHumano      );
        panel.add( jugadorMaquina     );
        panel.add( new JLabel( "" ) );

        return( panel );
    }

    private JPanel obtenerOpcionesFichas(){
    // ----------------------------------------------------------------------

        JPanel panel;

    // ----------------------------------------------------------------------

        panel   = new JPanel();
        //panel.setLayout( new BoxLayout( panel, BoxLayout.Y_AXIS ) );
        panel.setLayout( new GridLayout( 5, 1 ) );

        JRadioButton fichaNegra = new JRadioButton( "Negra" );
        fichaNegra.setMnemonic(KeyEvent.VK_N );
        fichaNegra.setActionCommand( ConstanteOthello.FICHA_NEGRA );
        fichaNegra.setSelected(true);

        JRadioButton fichaBlanca = new JRadioButton( "Blanca" );
        fichaBlanca.setMnemonic(KeyEvent.VK_B );
        fichaBlanca.setActionCommand( ConstanteOthello.FICHA_BLANCA );

        this.eleccionFichaJugadorUno = new ButtonGroup();
        this.eleccionFichaJugadorUno.add( fichaNegra      );
        this.eleccionFichaJugadorUno.add( fichaBlanca     );

        panel.add( new JLabel( "Ficha para Jugador (1) " ) );
        panel.add( new JSeparator() );
        panel.add( fichaNegra      );
        panel.add( fichaBlanca     );
        panel.add( new JLabel( "" ) );

        return( panel );
    }


    private JComponent obtenerOpcionesVarios(){
    // ----------------------------------------------------------------------

        JPanel panel;

    // ----------------------------------------------------------------------

        panel   = new JPanel();
        panel.setSize( 150, 10 );
        panel.setLayout( new BoxLayout( panel, BoxLayout.X_AXIS ) );

        this.eleccionProfundidadAlgoritmo1 = new JTextField( "3", 5 );
        this.eleccionProfundidadAlgoritmo2 = new JTextField( "3", 5 );

        panel.add(  new JLabel( "Profundidad (Jugador 1)" ) );
        panel.add(  this.eleccionProfundidadAlgoritmo1    );
        panel.add( new JLabel( "                           " ) );
        panel.add(  new JLabel( "Profundidad (Jugador 2)" ) );
        panel.add(  this.eleccionProfundidadAlgoritmo2    );
        panel.add( new JLabel( "                           " ) );

        return( panel );
    }

    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }

    public void pararJuego(){
        this.elJuego.pararJuego();
        this.elJuego.getJugadorConFichaNegra().getTablero().restarurarTablero();
        
    }

    public void refrescarTablero(){
        this.elTableroGUI.refrescarTablerGUI();
    }

    public Tablero getTablero(){
        return( this.elTableroGUI.getTablero() );
    }

    public void setTablero( Tablero tablero ){
        this.elTableroGUI.setTablero(tablero);
    }
}
