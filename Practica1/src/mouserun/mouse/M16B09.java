package mouserun.mouse;

import mouserun.game.*;
import java.util.*;

public class M16B09
        extends Mouse {

    private ArrayList<Integer> possiblemoves;
    private int lastMove, move;
    /*Variable que se rellena en el metodo isVisited para saber la casilla que tenemos*/
    private int pos_tablero = 0;
    private ArrayList<casillamod> tablero;

    public M16B09() {
        super("Mickey Mouse");
        tablero = new ArrayList<>();
        possiblemoves = new ArrayList<>();
    }

    @Override
    public int move(Grid currentGrid, Cheese cheese) {
        /*Limpiamos array de posibles movimientos*/
        possiblemoves.clear();
        //pos_tablero=0;
        /*If para el caso inicial*/
        if (tablero.isEmpty()) {
            availableMoves(currentGrid);
            casillamod casilla = new casillamod();
            casilla.x = currentGrid.getX();
            casilla.y = currentGrid.getY();
            casilla.moves = new ArrayList<>();
            casilla.moves = possiblemoves;
            move = casilla.moves.remove(casilla.moves.size() - 1);
            lastMove = move;
            tablero.add(casilla);
        } /*Resto de movimientos*/ else {
            if (isVisited(currentGrid)) {
                casillamod casilla = new casillamod();
                casilla = tablero.get(pos_tablero);
                System.out.println("En la casilla nº: " + pos_tablero + " Movimientos: " + casilla.moves + " Nombre: " + casilla);
                move = casilla.moves.get(casilla.moves.size() - 1);
                lastMove = move;
                tablero.remove(pos_tablero);
                tablero.add(pos_tablero, casilla);
            } else {
                availableMoves(currentGrid);
                casillamod casilla = new casillamod();
                casilla.x = currentGrid.getX();
                casilla.y = currentGrid.getY();

              
                System.out.println("Antes: " + possiblemoves);
                sort(currentGrid, cheese);
                System.out.println("Despues: " + possiblemoves);
                casilla.moves = new ArrayList<>();
                for (int i = 0; i < possiblemoves.size(); i++) {
                    casilla.moves.add(possiblemoves.get(i));
                }
                move = casilla.moves.remove(casilla.moves.size() - 1);
                lastMove = move;
                System.out.println("Casilla nº:" + pos_tablero + " Guardo:" + casilla.moves + " Nombre:" + casilla);
                tablero.add(pos_tablero, casilla);
                pos_tablero++;
            }

        }
        return move;

    }

    public void sort(Grid currentGrid, Cheese cheese) {
        /*Preferencias*/
        int horizontalPosition = currentGrid.getX() - cheese.getX();
        int verticalPosition = currentGrid.getY() - cheese.getY();

        /**
         * **********
         */
        if (horizontalPosition >= verticalPosition) {
            /*Si tenemos que ir hacia la derecha*/
            if (horizontalPosition >= 0 && possiblemoves.contains(RIGHT)) {
                if (verticalPosition >= 0 && possiblemoves.contains(UP)) {
                    if (possiblemoves.contains(DOWN)) {
                        if (possiblemoves.contains(LEFT)) {
                            possiblemoves.contains(LEFT);
                            possiblemoves.add(possiblemoves.size(), DOWN);
                            possiblemoves.add(possiblemoves.size(), UP);
                            possiblemoves.add(possiblemoves.size(), RIGHT);
                        } else {
                            possiblemoves.add(possiblemoves.size(), DOWN);
                            possiblemoves.add(possiblemoves.size(), UP);
                            possiblemoves.add(possiblemoves.size(), RIGHT);
                        }
                    } else if (possiblemoves.contains(LEFT)) {
                        possiblemoves.add(possiblemoves.size(), LEFT);
                        possiblemoves.add(possiblemoves.size(), UP);
                        possiblemoves.add(possiblemoves.size(), RIGHT);
                    } else {
                        possiblemoves.add(possiblemoves.size(), UP);
                        possiblemoves.add(possiblemoves.size(), RIGHT);
                    }
                }
                if (verticalPosition < 0 && possiblemoves.contains(DOWN)) {
                    if (possiblemoves.contains(UP)) {
                        if (possiblemoves.contains(LEFT)) {
                            possiblemoves.add(possiblemoves.size(), LEFT);
                            possiblemoves.add(possiblemoves.size(), UP);
                            possiblemoves.add(possiblemoves.size(), DOWN);
                            possiblemoves.add(possiblemoves.size(), RIGHT);
                        } else {
                            possiblemoves.add(possiblemoves.size(), UP);
                            possiblemoves.add(possiblemoves.size(), DOWN);
                            possiblemoves.add(possiblemoves.size(), RIGHT);
                        }

                    } else {
                        if (possiblemoves.contains(LEFT)) {
                            possiblemoves.add(possiblemoves.size(), LEFT);
                            possiblemoves.add(possiblemoves.size(), DOWN);
                            possiblemoves.add(possiblemoves.size(), RIGHT);
                        } else {
                            possiblemoves.add(possiblemoves.size(), DOWN);
                            possiblemoves.add(possiblemoves.size(), RIGHT);
                        }
                    }
                }
            }
            /*Si no podemos ir a la derecha*/
            if (horizontalPosition >= 0 && !possiblemoves.contains(RIGHT)) {
                if (verticalPosition >= 0 && possiblemoves.contains(UP)) {
                    if (possiblemoves.contains(DOWN)) {
                        possiblemoves.add(possiblemoves.size(), DOWN);
                        possiblemoves.add(possiblemoves.size(), UP);
                    } else {
                        possiblemoves.add(possiblemoves.size(), UP);
                    }
                }
                if (verticalPosition < 0 && possiblemoves.contains(DOWN)) {
                    if (possiblemoves.contains(UP)) {
                        possiblemoves.add(possiblemoves.size(), UP);
                        possiblemoves.add(possiblemoves.size(), DOWN);
                    } else {
                        possiblemoves.add(possiblemoves.size(), DOWN);
                    }
                }

            }
            /*Si tenemos que ir a la izquierda*/
            if (horizontalPosition < 0 && possiblemoves.contains(LEFT)) {
                if (verticalPosition >= 0 && possiblemoves.contains(UP)) {
                    if (possiblemoves.contains(DOWN)) {
                        possiblemoves.add(possiblemoves.size(), DOWN);
                        possiblemoves.add(possiblemoves.size(), UP);
                        possiblemoves.add(possiblemoves.size(), LEFT);
                    } else {
                        possiblemoves.add(possiblemoves.size(), UP);
                        possiblemoves.add(possiblemoves.size(), LEFT);
                    }
                }
                if (verticalPosition < 0 && possiblemoves.contains(DOWN)) {
                    if (possiblemoves.contains(UP)) {
                        possiblemoves.add(possiblemoves.size(), UP);
                        possiblemoves.add(possiblemoves.size(), DOWN);
                        possiblemoves.add(possiblemoves.size(), LEFT);
                    } else {
                        possiblemoves.add(possiblemoves.size(), DOWN);
                        possiblemoves.add(possiblemoves.size(), LEFT);
                    }
                }
            }
            /*Si no podemos ir a la izquierda*/
            if (horizontalPosition >= 0 && !possiblemoves.contains(LEFT)) {
                if (verticalPosition >= 0 && possiblemoves.contains(UP)) {
                    if (possiblemoves.contains(DOWN)) {
                        possiblemoves.add(possiblemoves.size(), DOWN);
                        possiblemoves.add(possiblemoves.size(), UP);
                    } else {
                        possiblemoves.add(possiblemoves.size(), UP);
                    }
                }
                if (verticalPosition < 0 && possiblemoves.contains(DOWN)) {
                    if (possiblemoves.contains(UP)) {
                        possiblemoves.add(possiblemoves.size(), UP);
                        possiblemoves.add(possiblemoves.size(), DOWN);
                    } else {
                        possiblemoves.add(possiblemoves.size(), DOWN);
                    }
                }
            }
        }
        /**
         * *********
         */

        if (verticalPosition > horizontalPosition) {
            /*Si tenemos que ir hacia  arriba*/
            if (verticalPosition >= 0 && possiblemoves.contains(UP)) {
                if (horizontalPosition >= 0 && possiblemoves.contains(RIGHT)) {
                    if (possiblemoves.contains(DOWN)) {
                        possiblemoves.add(possiblemoves.size(), DOWN);
                        possiblemoves.add(possiblemoves.size(), RIGHT);
                        possiblemoves.add(possiblemoves.size(), UP);
                    } else {
                        possiblemoves.add(possiblemoves.size(), RIGHT);
                        possiblemoves.add(possiblemoves.size(), UP);
                    }
                }
                if (horizontalPosition < 0 && possiblemoves.contains(LEFT)) {
                    if (possiblemoves.contains(DOWN)) {
                        possiblemoves.add(possiblemoves.size(), DOWN);
                        possiblemoves.add(possiblemoves.size(), LEFT);
                        possiblemoves.add(possiblemoves.size(), UP);
                    } else {
                        possiblemoves.add(possiblemoves.size(), LEFT);
                        possiblemoves.add(possiblemoves.size(), UP);
                    }
                }
            }
            /*Si no podemos ir hacia arriba*/
            if (verticalPosition >= 0 && !possiblemoves.contains(UP)) {
                if (horizontalPosition >= 0 && possiblemoves.contains(RIGHT)) {
                    if (possiblemoves.contains(DOWN)) {
                        possiblemoves.add(possiblemoves.size(), DOWN);
                        possiblemoves.add(possiblemoves.size(), RIGHT);
                    } else {
                        possiblemoves.add(possiblemoves.size(), RIGHT);
                    }
                }
                if (horizontalPosition < 0 && possiblemoves.contains(LEFT)) {
                    if (possiblemoves.contains(DOWN)) {
                        possiblemoves.add(possiblemoves.size(), DOWN);
                        possiblemoves.add(possiblemoves.size(), LEFT);
                    } else {
                        possiblemoves.add(possiblemoves.size(), LEFT);
                    }
                }

            }
            /*Si tenemos que ir hacia abajo*/
            if (verticalPosition < 0 && possiblemoves.contains(DOWN)) {
                if (horizontalPosition >= 0 && possiblemoves.contains(RIGHT)) {
                    if (possiblemoves.contains(LEFT)) {
                        possiblemoves.add(possiblemoves.size(), LEFT);
                        possiblemoves.add(possiblemoves.size(), RIGHT);
                        possiblemoves.add(possiblemoves.size(), DOWN);
                    } else {
                        possiblemoves.add(possiblemoves.size(), UP);
                        possiblemoves.add(possiblemoves.size(), DOWN);
                    }
                }
                if (horizontalPosition < 0 && possiblemoves.contains(LEFT)) {
                    if (possiblemoves.contains(RIGHT)) {
                        possiblemoves.add(possiblemoves.size(), RIGHT);
                        possiblemoves.add(possiblemoves.size(), LEFT);
                        possiblemoves.add(possiblemoves.size(), DOWN);
                    } else {
                        possiblemoves.add(possiblemoves.size(), LEFT);
                        possiblemoves.add(possiblemoves.size(), DOWN);
                    }
                }
            }
            /*Si no podemos ir hacia abajo*/
            if (verticalPosition >= 0 && !possiblemoves.contains(DOWN)) {
                if (horizontalPosition >= 0 && possiblemoves.contains(RIGHT)) {
                    if (possiblemoves.contains(LEFT)) {
                        possiblemoves.add(possiblemoves.size(), LEFT);
                        possiblemoves.add(possiblemoves.size(), RIGHT);
                    } else {
                        possiblemoves.add(possiblemoves.size(), RIGHT);
                    }
                }
                if (horizontalPosition < 0 && possiblemoves.contains(LEFT)) {
                    if (possiblemoves.contains(RIGHT)) {
                        possiblemoves.add(possiblemoves.size(), RIGHT);
                        possiblemoves.add(possiblemoves.size(), LEFT);
                    } else {
                        possiblemoves.add(possiblemoves.size(), LEFT);
                    }
                }
            }
        }

        if (possiblemoves.contains(BOMB)) {
            possiblemoves.add(4, BOMB);
        }
        oppositeMove();
        if (possiblemoves.contains(lastMove)){
            int a,b;
            a = possiblemoves.get(possiblemoves.indexOf(lastMove));
            b = possiblemoves.get(0);
            possiblemoves.set(possiblemoves.indexOf(lastMove), b);
            possiblemoves.set(0,a);
        }
    }

    public void lastMove() {
        if (lastMove == RIGHT && possiblemoves.contains(LEFT)) {
            for (int i = 0; i < possiblemoves.size(); i++) {
                if (possiblemoves.get(i) == LEFT) {
                    possiblemoves.remove(i);
                }
            }
            possiblemoves.add(0, LEFT);

        } else if (lastMove == LEFT && possiblemoves.contains(RIGHT)) {
            for (int i = 0; i < possiblemoves.size(); i++) {
                if (possiblemoves.get(i) == RIGHT) {
                    possiblemoves.remove(i);
                }
            }
            possiblemoves.add(0, RIGHT);

        } else if (lastMove == UP && possiblemoves.contains(DOWN)) {
            for (int i = 0; i < possiblemoves.size(); i++) {
                if (possiblemoves.get(i) == DOWN) {
                    possiblemoves.remove(i);
                }
            }
            possiblemoves.add(0, DOWN);
        } else {
            for (int i = 0; i < possiblemoves.size(); i++) {
                if (possiblemoves.get(i) == UP) {
                    possiblemoves.remove(i);
                }
            }
            possiblemoves.add(0, UP);
        }
    }

    public void oppositeMove() {

        switch (move) {
            case UP:
                lastMove = DOWN;
                break;
            case DOWN:
                lastMove = UP;
                break;
            case RIGHT:
                lastMove = LEFT;
                break;
            case LEFT:
                lastMove = RIGHT;
                break;

        }
    }

    @Override
    public void newCheese() {
        //tablero.clear();
    }

    @Override
    public void respawned() {
        tablero.clear();
    }
    /*
     Comprueba movimientos disponibles
     */

    public void availableMoves(Grid grid) {
        if (grid.canGoDown()) {
            possiblemoves.add(DOWN);
        }
        if (grid.canGoUp()) {
            possiblemoves.add(UP);
        }
        if (grid.canGoLeft()) {
            possiblemoves.add(LEFT);
        }
        if (grid.canGoRight()) {
            possiblemoves.add(RIGHT);
        }
        /*if (possiblemoves.size() == 4) {
         possiblemoves.add(BOMB);
         }*/
    }
    /*Comprueba si la casilla ha sido visitada anteriormente*/

    public boolean isVisited(Grid grid) {

        for (int i = 0; i < tablero.size(); i++) {
            if (tablero.get(i).x == grid.getX() && tablero.get(i).y == grid.getY()) {
                pos_tablero = i;
                return true;
            }
        }
        return false;
    }

}
/*Esta clase es estática porque java no permite declarar de otra forma 2 clases en el mismo archivo,
 por tanto, lo inicializaremos desde la clase principal, pero no hay posibilidad de proteger los objetos internos*/

class casillamod {

    ArrayList<Integer> moves;
    int x, y;
}
