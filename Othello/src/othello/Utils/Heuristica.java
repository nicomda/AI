/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package othello.Utils;

/**
 *
 * @author user
 */
public class Heuristica {

/* Heurística basada en el valor de las esquinas.
     '( ( 120 -20 20 5 5 20 -20 120 ) 
     ( -20 -40 -5 -5 -5 -5 -40 -20 ) 
     ( 20 -5 15 3 3 15 -5 20 ) 
     ( 5 -5 3 3 3 3 -5 5 ) 
     ( 5 -5 3 3 3 3 -5 5 ) 
     ( 20 -5 15 3 3 15 -5 20 ) 
     ( -20 -40 -5 -5 -5 -5 -40 -20 ) 
     ( 120 -20 20 5 5 20 -20 120 )))) 
     */
    int[][] weight = 
       {{120, -20, 20, 5, 5, 20, -20, 120},
        {-20, -40, -5, -5, -5, -5, -40, -20},
        {20, -5, 15, 3, 3, 15, -5, 20},
        {5, -5, 3, 3, 3, 3, -5, 5},
        {5, -5, 3, 3, 3, 3, -5, 5},
        {20, -5, 15, 3, 3, 15, -5, 20},
        {-20, -40, -5, -5, -5, -5, -40, -20},
        {120, -20, 20, 5, 5, 20, -20, 120}
    };
    
    public  int h1(Casilla casilla)
    {       
        return weight[casilla.col][casilla.fila];
    }


    //Una heuristica posible a usar
    public static int h2(Tablero tablero,int playerColor)
    {
        int score = Puntos(playerColor, tablero) - Puntos(-playerColor, tablero);

        // If the game is over
        if (tablero.EsFinalDeJuego())
        {
            // if player has won
            if (score > 0)
                return 100;
            // if player has lost (or tied)
            else
                return -100;
        }

        // if game isn't over, return relative advatage
        return score;
    }

    public static int Puntos(int playerColor, Tablero tablero)
    {
        int points = 0;

        for (int x = 0; x < Tablero.CANTIDAD_FILAS_DEFECTO; x++)
            for (int y = 0; y < Tablero.CANTIDAD_COLUMNAS_DEFECTO; y++)
                if (tablero.getMatrizTablero()[x][y].obtenerColorFicha() == playerColor)
                    points++;

        return points;
    }



}
