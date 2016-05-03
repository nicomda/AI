package mouserun.mouse;

import mouserun.game.*;
import java.util.*;

public class M16B09a
        extends Mouse {

    private ArrayList<Integer> possiblemoves;
    ArrayList<Integer> possiblemoves2;
    private int lastMove, move;
    /*Variable que se rellena en el metodo isVisited para saber la casilla que tenemos*/
    private int pos_tablero = 0;
    private int casillasVisitadas = 0;
    private ArrayList<casillamod> tablero;
    ArrayList<String> posicionesvisitadas = new ArrayList<>();
    
    int control =0;
    public M16B09a() {
        super("Mickey Mouse");
        tablero = new ArrayList<>();
        possiblemoves = new ArrayList<>();
        possiblemoves2= new ArrayList<>();
    }

    @Override
    public long incExploredGrids() {
        return super.incExploredGrids(); //To change body of generated methods, choose Tools | Templates.
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
            sort(currentGrid, cheese);
            for (int i = 0; i < possiblemoves2.size(); i++) {
                casilla.moves.add(possiblemoves2.get(i));
            }
            System.out.println("Movimientos de inicio:" + casilla.moves);
            move = casilla.moves.remove(casilla.moves.size() - 1);
            System.out.print(("Guardo: " + casilla.moves));
            lastMove = move;
            tablero.add(pos_tablero, casilla);
            pos_tablero++;
        } /*Resto de movimientos*/ else {
            if (isVisited(currentGrid)) {
                casillamod casilla = new casillamod();
                casilla = tablero.get(pos_tablero);
                //System.out.println("En la casilla nº: " + pos_tablero + " Movimientos: " + casilla.moves + " Nombre: " + casilla);
                move = casilla.moves.remove(casilla.moves.size() - 1);
                lastMove = move;
                tablero.remove(pos_tablero);
                tablero.add(pos_tablero, casilla);
            } else {
                availableMoves(currentGrid);
                casillamod casilla = new casillamod();
                casilla.x = currentGrid.getX();
                casilla.y = currentGrid.getY();

                //System.out.println("Antes: " + possiblemoves);
                sort(currentGrid, cheese);
                //System.out.println("Despues: " + possiblemoves);
                casilla.moves = new ArrayList<>();
                for (int i = 0; i < possiblemoves2.size(); i++) {
                    casilla.moves.add(possiblemoves2.get(i));
                }
                move = casilla.moves.remove(casilla.moves.size() - 1);
                //System.out.println("Muevo a:" + move);
                lastMove = move;
                
                if(!posicionesvisitadas.contains(casilla.x+""+casilla.y)){
                    posicionesvisitadas.add(casilla.x+""+casilla.y);
                    incExploredGrids();
                }
                
                //System.out.println("Casilla nº:" + pos_tablero + " Guardo:" + casilla.moves + " Nombre:" + casilla);
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
        possiblemoves2.clear();
                if (horizontalPosition >= verticalPosition) {
            /*Si tenemos que ir hacia la derecha*/
            if (horizontalPosition >= 0 && possiblemoves.contains(RIGHT)) {
                if (verticalPosition >= 0 && possiblemoves.contains(UP)) {
                    if (possiblemoves.contains(DOWN)) {
                        if (possiblemoves.contains(LEFT)) {
                            possiblemoves2.add(LEFT);
                            possiblemoves2.add(DOWN);
                            possiblemoves2.add(UP);
                            possiblemoves2.add(RIGHT);
                        } else {
                            possiblemoves2.add(DOWN);
                            possiblemoves2.add(UP);
                            possiblemoves2.add(RIGHT);
                        }
                    }
                    if (!possiblemoves.contains(DOWN)) {
                        if (possiblemoves.contains(LEFT)) {
                            possiblemoves2.add(LEFT);
                            possiblemoves2.add(UP);
                            possiblemoves2.add(RIGHT);
                        } else {
                            possiblemoves2.add(UP);
                            possiblemoves2.add(RIGHT);
                        }
                    }
                }
                if (verticalPosition >= 0 && !possiblemoves.contains(UP)) {
                    if (possiblemoves.contains(DOWN)) {
                        if (possiblemoves.contains(LEFT)) {
                            possiblemoves2.add(LEFT);
                            possiblemoves2.add(DOWN);
                            possiblemoves2.add(RIGHT);
                        } else {
                            possiblemoves2.add(DOWN);
                            possiblemoves2.add(RIGHT);
                        }
                    }
                    if (!possiblemoves.contains(DOWN)) {
                        if (possiblemoves.contains(LEFT)) {
                            possiblemoves2.add(LEFT);
                            possiblemoves2.add(RIGHT);
                        } else {
                            possiblemoves2.add(RIGHT);
                        }
                    }
                }

                if (verticalPosition < 0 && possiblemoves.contains(DOWN)) {
                    if (possiblemoves.contains(UP)) {
                        if (possiblemoves.contains(LEFT)) {
                            possiblemoves2.add(LEFT);
                            possiblemoves2.add(UP);
                            possiblemoves2.add(DOWN);
                            possiblemoves2.add(RIGHT);
                        } else {
                            possiblemoves2.add(UP);
                            possiblemoves2.add(DOWN);
                            possiblemoves2.add(RIGHT);
                        }
                    }
                    if (!possiblemoves.contains(UP)) {
                        if (possiblemoves.contains(LEFT)) {
                            possiblemoves2.add(LEFT);
                            possiblemoves2.add(DOWN);
                            possiblemoves2.add(RIGHT);
                        } else {
                            possiblemoves2.add(DOWN);
                            possiblemoves2.add(RIGHT);
                        }
                    }
                }
                if (verticalPosition < 0 && !possiblemoves.contains(DOWN)) {
                    if (possiblemoves.contains(UP)) {
                        if (possiblemoves.contains(LEFT)) {
                            possiblemoves2.add(LEFT);
                            possiblemoves2.add(UP);
                            possiblemoves2.add(RIGHT);
                        } else {
                            possiblemoves2.add(UP);
                            possiblemoves2.add(RIGHT);
                        }
                    }
                    if (!possiblemoves.contains(UP)) {
                        if (possiblemoves.contains(LEFT)) {
                            possiblemoves2.add(LEFT);
                            possiblemoves2.add(RIGHT);
                        } else {
                            possiblemoves2.add(RIGHT);
                        }
                    }
                }
            }
            /**
             * **************************************
             */
            if (horizontalPosition >= 0 && !possiblemoves.contains(RIGHT)) {
                if (verticalPosition >= 0 && possiblemoves.contains(UP)) {
                    if (possiblemoves.contains(DOWN)) {
                        if (possiblemoves.contains(LEFT)) {
                            possiblemoves2.add(LEFT);
                            possiblemoves2.add(DOWN);
                            possiblemoves2.add(UP);
                        } else {
                            possiblemoves2.add(DOWN);
                            possiblemoves2.add(UP);
                        }
                    }
                    if (!possiblemoves.contains(DOWN)) {
                        if (possiblemoves.contains(LEFT)) {
                            possiblemoves2.add(LEFT);
                            possiblemoves2.add(UP);
                        } else {
                            possiblemoves2.add(UP);
                        }
                    }
                }
                if (verticalPosition >= 0 && !possiblemoves.contains(UP)) {
                    if (possiblemoves.contains(DOWN)) {
                        if (possiblemoves.contains(LEFT)) {
                            possiblemoves2.add(LEFT);
                            possiblemoves2.add(DOWN);
                        } else {
                            possiblemoves2.add(DOWN);
                        }
                    }
                    if (!possiblemoves.contains(DOWN)) {
                        if (possiblemoves.contains(LEFT)) {
                            possiblemoves2.add(LEFT);
                        } else {
                            System.out.println("ERROR!!!!!!");
                        }
                    }
                }

                if (verticalPosition < 0 && possiblemoves.contains(DOWN)) {
                    if (possiblemoves.contains(UP)) {
                        if (possiblemoves.contains(LEFT)) {
                            possiblemoves2.add(LEFT);
                            possiblemoves2.add(UP);
                            possiblemoves2.add(DOWN);
                        } else {
                            possiblemoves2.add(UP);
                            possiblemoves2.add(DOWN);
                        }
                    }
                    if (!possiblemoves.contains(UP)) {
                        if (possiblemoves.contains(LEFT)) {
                            possiblemoves2.add(LEFT);
                            possiblemoves2.add(DOWN);
                        } else {
                            possiblemoves2.add(DOWN);
                        }
                    }
                }
                if (verticalPosition < 0 && !possiblemoves.contains(DOWN)) {
                    if (possiblemoves.contains(UP)) {
                        if (possiblemoves.contains(LEFT)) {
                            possiblemoves2.add(LEFT);
                            possiblemoves2.add(UP);
                        } else {
                            possiblemoves2.add(UP);
                        }
                    }
                    if (!possiblemoves.contains(UP)) {
                        if (possiblemoves.contains(LEFT)) {
                            possiblemoves2.add(LEFT);
                        } else {
                            System.out.println("ERRORR!!!!!");
                        }
                    }
                }
            }


            /*AQUI ACABA LA DERECHA*/
            if (horizontalPosition < 0 && possiblemoves.contains(LEFT)) {
                if (verticalPosition >= 0 && possiblemoves.contains(UP)) {
                    if (possiblemoves.contains(DOWN)) {
                        if (possiblemoves.contains(RIGHT)) {
                            possiblemoves2.add(RIGHT);
                            possiblemoves2.add(DOWN);
                            possiblemoves2.add(UP);
                            possiblemoves2.add(LEFT);
                        } else {
                            possiblemoves2.add(DOWN);
                            possiblemoves2.add(UP);
                            possiblemoves2.add(LEFT);
                        }
                    }
                    if (!possiblemoves.contains(DOWN)) {
                        if (possiblemoves.contains(RIGHT)) {
                            possiblemoves2.add(RIGHT);
                            possiblemoves2.add(UP);
                            possiblemoves2.add(LEFT);
                        } else {
                            possiblemoves2.add(UP);
                            possiblemoves2.add(LEFT);
                        }
                    }
                }
                if (verticalPosition >= 0 && !possiblemoves.contains(UP)) {
                    if (possiblemoves.contains(DOWN)) {
                        if (possiblemoves.contains(RIGHT)) {
                            possiblemoves2.add(RIGHT);
                            possiblemoves2.add(DOWN);
                            possiblemoves2.add(LEFT);
                        } else {
                            possiblemoves2.add(DOWN);
                            possiblemoves2.add(LEFT);
                        }
                    }
                    if (!possiblemoves.contains(DOWN)) {
                        if (possiblemoves.contains(RIGHT)) {
                            possiblemoves2.add(RIGHT);
                            possiblemoves2.add(LEFT);
                        } else {
                            possiblemoves2.add(LEFT);
                        }
                    }
                }

                if (verticalPosition < 0 && possiblemoves.contains(DOWN)) {
                    if (possiblemoves.contains(UP)) {
                        if (possiblemoves.contains(RIGHT)) {
                            possiblemoves2.add(RIGHT);
                            possiblemoves2.add(UP);
                            possiblemoves2.add(DOWN);
                            possiblemoves2.add(LEFT);
                        } else {
                            possiblemoves2.add(UP);
                            possiblemoves2.add(DOWN);
                            possiblemoves2.add(LEFT);
                        }
                    }
                    if (!possiblemoves.contains(UP)) {
                        if (possiblemoves.contains(RIGHT)) {
                            possiblemoves2.add(RIGHT);
                            possiblemoves2.add(DOWN);
                            possiblemoves2.add(LEFT);
                        } else {
                            possiblemoves2.add(DOWN);
                            possiblemoves2.add(LEFT);
                        }
                    }
                }
                if (verticalPosition < 0 && !possiblemoves.contains(DOWN)) {
                    if (possiblemoves.contains(UP)) {
                        if (possiblemoves.contains(RIGHT)) {
                            possiblemoves2.add(RIGHT);
                            possiblemoves2.add(UP);
                            possiblemoves2.add(LEFT);
                        } else {
                            possiblemoves2.add(UP);
                            possiblemoves2.add(LEFT);
                        }
                    }
                    if (!possiblemoves.contains(UP)) {
                        if (possiblemoves.contains(RIGHT)) {
                            possiblemoves2.add(RIGHT);
                            possiblemoves2.add(LEFT);
                        } else {
                            possiblemoves2.add(LEFT);
                        }
                    }
                }
            }

            /**
             * *****************
             */
            if (horizontalPosition < 0 && !possiblemoves.contains(LEFT)) {
                if (verticalPosition >= 0 && possiblemoves.contains(UP)) {
                    if (possiblemoves.contains(DOWN)) {
                        if (possiblemoves.contains(RIGHT)) {
                            possiblemoves2.add(RIGHT);
                            possiblemoves2.add(DOWN);
                            possiblemoves2.add(UP);
                        } else {
                            possiblemoves2.add(DOWN);
                            possiblemoves2.add(UP);
                        }
                    }
                    if (!possiblemoves.contains(DOWN)) {
                        if (possiblemoves.contains(RIGHT)) {
                            possiblemoves2.add(RIGHT);
                            possiblemoves2.add(UP);

                        } else {
                            possiblemoves2.add(UP);

                        }
                    }
                }
                if (verticalPosition >= 0 && !possiblemoves.contains(UP)) {
                    if (possiblemoves.contains(DOWN)) {
                        if (possiblemoves.contains(RIGHT)) {
                            possiblemoves2.add(RIGHT);
                            possiblemoves2.add(DOWN);

                        } else {
                            possiblemoves2.add(DOWN);

                        }
                    }
                    if (!possiblemoves.contains(DOWN)) {
                        if (possiblemoves.contains(RIGHT)) {
                            possiblemoves2.add(RIGHT);

                        } else {
                            System.out.println("EROOORRR");
                        }
                    }
                }

                if (verticalPosition < 0 && possiblemoves.contains(DOWN)) {
                    if (possiblemoves.contains(UP)) {
                        if (possiblemoves.contains(RIGHT)) {
                            possiblemoves2.add(RIGHT);
                            possiblemoves2.add(UP);
                            possiblemoves2.add(DOWN);

                        } else {
                            possiblemoves2.add(UP);
                            possiblemoves2.add(DOWN);

                        }
                    }
                    if (!possiblemoves.contains(UP)) {
                        if (possiblemoves.contains(RIGHT)) {
                            possiblemoves2.add(RIGHT);
                            possiblemoves2.add(DOWN);

                        } else {
                            possiblemoves2.add(DOWN);

                        }
                    }
                }
                if (verticalPosition < 0 && !possiblemoves.contains(DOWN)) {
                    if (possiblemoves.contains(UP)) {
                        if (possiblemoves.contains(RIGHT)) {
                            possiblemoves2.add(RIGHT);
                            possiblemoves2.add(UP);

                        } else {
                            possiblemoves2.add(UP);

                        }
                    }
                    if (!possiblemoves.contains(UP)) {
                        if (possiblemoves.contains(RIGHT)) {
                            possiblemoves2.add(RIGHT);

                        } else {
                            System.out.println("ERROOOOR");
                        }
                    }
                }
            }
            /*AQUI ACABA LA IZQUIERDA*/
        }
        if (verticalPosition > horizontalPosition) {
            if (verticalPosition >= 0 && possiblemoves.contains(UP)) {
                if (horizontalPosition >= 0 && possiblemoves.contains(RIGHT)) {
                    if (possiblemoves.contains(LEFT)) {
                        if (possiblemoves.contains(DOWN)) {
                            possiblemoves2.add(DOWN);
                            possiblemoves2.add(LEFT);
                            possiblemoves2.add(RIGHT);
                            possiblemoves2.add(UP);
                        } else {
                            possiblemoves2.add(LEFT);
                            possiblemoves2.add(RIGHT);
                            possiblemoves2.add(UP);
                        }
                    }
                    if (!possiblemoves.contains(LEFT)) {
                        if (possiblemoves.contains(DOWN)) {
                            possiblemoves2.add(DOWN);
                            possiblemoves2.add(RIGHT);
                            possiblemoves2.add(UP);
                        } else {
                            possiblemoves2.add(RIGHT);
                            possiblemoves2.add(UP);
                        }
                    }
                }
                if (horizontalPosition >= 0 && !possiblemoves.contains(RIGHT)) {
                    if (possiblemoves.contains(LEFT)) {
                        if (possiblemoves.contains(DOWN)) {
                            possiblemoves2.add(DOWN);
                            possiblemoves2.add(LEFT);
                            possiblemoves2.add(UP);
                        } else {
                            possiblemoves2.add(LEFT);
                            possiblemoves2.add(UP);
                        }
                    }
                    if (!possiblemoves.contains(LEFT)) {
                        if (possiblemoves.contains(DOWN)) {
                            possiblemoves2.add(DOWN);
                            possiblemoves2.add(UP);
                        } else {
                            possiblemoves2.add(UP);
                        }
                    }
                }

                if (horizontalPosition < 0 && possiblemoves.contains(LEFT)) {
                    if (possiblemoves.contains(RIGHT)) {
                        if (possiblemoves.contains(DOWN)) {
                            possiblemoves2.add(DOWN);
                            possiblemoves2.add(RIGHT);
                            possiblemoves2.add(LEFT);
                            possiblemoves2.add(UP);
                        } else {
                            possiblemoves2.add(RIGHT);
                            possiblemoves2.add(LEFT);
                            possiblemoves2.add(UP);
                        }
                    }
                    if (!possiblemoves.contains(RIGHT)) {
                        if (possiblemoves.contains(DOWN)) {
                            possiblemoves2.add(DOWN);
                            possiblemoves2.add(LEFT);
                            possiblemoves2.add(UP);
                        } else {
                            possiblemoves2.add(LEFT);
                            possiblemoves2.add(UP);
                        }
                    }
                }
                if (horizontalPosition < 0 && !possiblemoves.contains(LEFT)) {
                    if (possiblemoves.contains(RIGHT)) {
                        if (possiblemoves.contains(DOWN)) {
                            possiblemoves2.add(DOWN);
                            possiblemoves2.add(RIGHT);
                            possiblemoves2.add(UP);
                        } else {
                            possiblemoves2.add(RIGHT);
                            possiblemoves2.add(UP);
                        }
                    }
                    if (!possiblemoves.contains(RIGHT)) {
                        if (possiblemoves.contains(DOWN)) {
                            possiblemoves2.add(DOWN);
                            possiblemoves2.add(UP);
                        } else {
                            possiblemoves2.add(UP);
                        }
                    }
                }
            }

            if (verticalPosition >= 0 && !possiblemoves.contains(UP)) {
                if (horizontalPosition >= 0 && possiblemoves.contains(RIGHT)) {
                    if (possiblemoves.contains(LEFT)) {
                        if (possiblemoves.contains(DOWN)) {
                            possiblemoves2.add(DOWN);
                            possiblemoves2.add(LEFT);
                            possiblemoves2.add(RIGHT);

                        } else {
                            possiblemoves2.add(LEFT);
                            possiblemoves2.add(RIGHT);

                        }
                    }
                    if (!possiblemoves.contains(LEFT)) {
                        if (possiblemoves.contains(DOWN)) {
                            possiblemoves2.add(DOWN);
                            possiblemoves2.add(RIGHT);

                        } else {
                            possiblemoves2.add(RIGHT);

                        }
                    }
                }
                if (horizontalPosition >= 0 && !possiblemoves.contains(RIGHT)) {
                    if (possiblemoves.contains(LEFT)) {
                        if (possiblemoves.contains(DOWN)) {
                            possiblemoves2.add(DOWN);
                            possiblemoves2.add(LEFT);

                        } else {
                            possiblemoves2.add(LEFT);

                        }
                    }
                    if (!possiblemoves.contains(LEFT)) {
                        if (possiblemoves.contains(DOWN)) {
                            possiblemoves2.add(DOWN);

                        } else {
                            System.out.println("EROROOOROR");
                        }
                    }
                }

                if (horizontalPosition < 0 && possiblemoves.contains(LEFT)) {
                    if (possiblemoves.contains(RIGHT)) {
                        if (possiblemoves.contains(DOWN)) {
                            possiblemoves2.add(DOWN);
                            possiblemoves2.add(RIGHT);
                            possiblemoves2.add(LEFT);

                        } else {
                            possiblemoves2.add(RIGHT);
                            possiblemoves2.add(LEFT);

                        }
                    }
                    if (!possiblemoves.contains(RIGHT)) {
                        if (possiblemoves.contains(DOWN)) {
                            possiblemoves2.add(DOWN);
                            possiblemoves2.add(LEFT);

                        } else {
                            possiblemoves2.add(LEFT);

                        }
                    }
                }
                if (horizontalPosition < 0 && !possiblemoves.contains(LEFT)) {
                    if (possiblemoves.contains(RIGHT)) {
                        if (possiblemoves.contains(DOWN)) {
                            possiblemoves2.add(DOWN);
                            possiblemoves2.add(RIGHT);

                        } else {
                            possiblemoves2.add(RIGHT);

                        }
                    }
                    if (!possiblemoves.contains(RIGHT)) {
                        if (possiblemoves.contains(DOWN)) {
                            possiblemoves2.add(DOWN);

                        } else {
                            System.out.println("EORORORO");
                        }
                    }
                }
            }
            if (verticalPosition < 0 && possiblemoves.contains(DOWN)) {
                if (horizontalPosition >= 0 && possiblemoves.contains(RIGHT)) {
                    if (possiblemoves.contains(LEFT)) {
                        if (possiblemoves.contains(UP)) {
                            possiblemoves2.add(UP);
                            possiblemoves2.add(LEFT);
                            possiblemoves2.add(RIGHT);
                            possiblemoves2.add(DOWN);
                        } else {
                            possiblemoves2.add(LEFT);
                            possiblemoves2.add(RIGHT);
                            possiblemoves2.add(DOWN);
                        }
                    }
                    if (!possiblemoves.contains(LEFT)) {
                        if (possiblemoves.contains(UP)) {
                            possiblemoves2.add(UP);
                            possiblemoves2.add(RIGHT);
                            possiblemoves2.add(DOWN);
                        } else {
                            possiblemoves2.add(RIGHT);
                            possiblemoves2.add(DOWN);
                        }
                    }
                }
                if (horizontalPosition >= 0 && !possiblemoves.contains(RIGHT)) {
                    if (possiblemoves.contains(LEFT)) {
                        if (possiblemoves.contains(UP)) {
                            possiblemoves2.add(UP);
                            possiblemoves2.add(LEFT);
                            possiblemoves2.add(DOWN);
                        } else {
                            possiblemoves2.add(LEFT);
                            possiblemoves2.add(DOWN);
                        }
                    }
                    if (!possiblemoves.contains(LEFT)) {
                        if (possiblemoves.contains(UP)) {
                            possiblemoves2.add(UP);
                            possiblemoves2.add(DOWN);
                        } else {
                            possiblemoves2.add(DOWN);
                        }
                    }
                }

                if (horizontalPosition < 0 && possiblemoves.contains(LEFT)) {
                    if (possiblemoves.contains(RIGHT)) {
                        if (possiblemoves.contains(UP)) {
                            possiblemoves2.add(UP);
                            possiblemoves2.add(RIGHT);
                            possiblemoves2.add(LEFT);
                            possiblemoves2.add(DOWN);
                        } else {
                            possiblemoves2.add(RIGHT);
                            possiblemoves2.add(LEFT);
                            possiblemoves2.add(DOWN);
                        }
                    }
                    if (!possiblemoves.contains(RIGHT)) {
                        if (possiblemoves.contains(UP)) {
                            possiblemoves2.add(UP);
                            possiblemoves2.add(LEFT);
                            possiblemoves2.add(DOWN);
                        } else {
                            possiblemoves2.add(LEFT);
                            possiblemoves2.add(DOWN);
                        }
                    }
                }
                if (horizontalPosition < 0 && !possiblemoves.contains(LEFT)) {
                    if (possiblemoves.contains(RIGHT)) {
                        if (possiblemoves.contains(UP)) {
                            possiblemoves2.add(UP);
                            possiblemoves2.add(RIGHT);
                            possiblemoves2.add(DOWN);
                        } else {
                            possiblemoves2.add(RIGHT);
                            possiblemoves2.add(DOWN);
                        }
                    }
                    if (!possiblemoves.contains(RIGHT)) {
                        if (possiblemoves.contains(UP)) {
                            possiblemoves2.add(UP);
                            possiblemoves2.add(DOWN);
                        } else {
                            possiblemoves2.add(DOWN);
                        }
                    }
                }
            }

            if (verticalPosition < 0 && !possiblemoves.contains(DOWN)) {
                if (horizontalPosition >= 0 && possiblemoves.contains(RIGHT)) {
                    if (possiblemoves.contains(LEFT)) {
                        if (possiblemoves.contains(UP)) {
                            possiblemoves2.add(UP);
                            possiblemoves2.add(LEFT);
                            possiblemoves2.add(RIGHT);

                        } else {
                            possiblemoves2.add(LEFT);
                            possiblemoves2.add(RIGHT);

                        }
                    }
                    if (!possiblemoves.contains(LEFT)) {
                        if (possiblemoves.contains(UP)) {
                            possiblemoves2.add(UP);
                            possiblemoves2.add(RIGHT);

                        } else {
                            possiblemoves2.add(RIGHT);

                        }
                    }
                }
                if (horizontalPosition >= 0 && !possiblemoves.contains(RIGHT)) {
                    if (possiblemoves.contains(LEFT)) {
                        if (possiblemoves.contains(UP)) {
                            possiblemoves2.add(UP);
                            possiblemoves2.add(LEFT);

                        } else {
                            possiblemoves2.add(LEFT);

                        }
                    }
                    if (!possiblemoves.contains(LEFT)) {
                        if (possiblemoves.contains(UP)) {
                            possiblemoves2.add(UP);

                        } else {
                            System.out.println("EROROOOROR");
                        }
                    }
                }

                if (horizontalPosition < 0 && possiblemoves.contains(LEFT)) {
                    if (possiblemoves.contains(RIGHT)) {
                        if (possiblemoves.contains(UP)) {
                            possiblemoves2.add(UP);
                            possiblemoves2.add(RIGHT);
                            possiblemoves2.add(LEFT);

                        } else {
                            possiblemoves2.add(RIGHT);
                            possiblemoves2.add(LEFT);

                        }
                    }
                    if (!possiblemoves.contains(RIGHT)) {
                        if (possiblemoves.contains(UP)) {
                            possiblemoves2.add(UP);
                            possiblemoves2.add(LEFT);

                        } else {
                            possiblemoves2.add(LEFT);

                        }
                    }
                }
                if (horizontalPosition < 0 && !possiblemoves.contains(LEFT)) {
                    if (possiblemoves.contains(RIGHT)) {
                        if (possiblemoves.contains(UP)) {
                            possiblemoves2.add(UP);
                            possiblemoves2.add(RIGHT);

                        } else {
                            possiblemoves2.add(RIGHT);

                        }
                    }
                    if (!possiblemoves.contains(RIGHT)) {
                        if (possiblemoves.contains(UP)) {
                            possiblemoves2.add(UP);

                        } else {
                            System.out.println("EORORORO");
                        }
                    }
                }
            }
        }
        System.out.println("Array normal:" +possiblemoves);
        System.out.println("Array ordenado:"+possiblemoves2);
        
        if (possiblemoves.contains(BOMB)) {
            possiblemoves2.add(BOMB);
        }
        oppositeMove();
        if (possiblemoves2.contains(lastMove)){
           possiblemoves2.remove(possiblemoves2.indexOf(lastMove));
           ArrayList<Integer> aux = new ArrayList<>();
           for(int i=0;aux.size()!=possiblemoves2.size();i++){
               aux.add(possiblemoves2.get(i));
           }
           System.out.println("Array aux"+aux);
           possiblemoves2.clear();
           possiblemoves2.add(lastMove);
           System.out.println("Array aux:"+aux);
           System.out.println("Array possiblemoves2:"+possiblemoves2);
           for(int i=0;aux.size()+1!=possiblemoves2.size();i++){
               possiblemoves2.add(aux.get(i));
           }
           System.out.println("ARRAY:"+possiblemoves2);
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
        pos_tablero=0;
        tablero.clear();
        possiblemoves.clear();
    }

    @Override
    public void respawned() {
        pos_tablero=0;
        tablero.clear();
        possiblemoves.clear();
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
        if (possiblemoves.size() == 4) {
         possiblemoves.add(BOMB);
         }
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

//class casillamod {
//
//    ArrayList<Integer> moves;
//    int x, y;
//}
