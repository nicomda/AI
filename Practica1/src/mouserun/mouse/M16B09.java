package mouserun.mouse;

import mouserun.game.*;
import java.util.*;

public class M16B09
        extends Mouse {
    private ArrayList<Integer> possiblemoves;
    private int lastMove,move;
    /*Variable que se rellena en el metodo isVisited para saber la casilla que tenemos*/
    private int pos_tablero;
    private ArrayList<casillamod> tablero;
    
    public M16B09() {
        super("Mickey Mouse");
        tablero=new ArrayList<>();
        possiblemoves=new ArrayList<>();
    }

    @Override
    public int move(Grid currentGrid, Cheese cheese) {
        /*Limpiamos array de posibles movimientos*/
        possiblemoves.clear();
        pos_tablero=0;
        /*If para el caso inicial*/
        if(tablero.isEmpty()){
        availableMoves(currentGrid);
        casillamod casilla=new casillamod();
        casilla.x=currentGrid.getX();
        casilla.y=currentGrid.getY();
        casilla.moves=new ArrayList<>();
        casilla.moves=possiblemoves;
        move=casilla.moves.remove(casilla.moves.size()-1);
        lastMove=move;
        tablero.add(casilla);
        }
        /*Resto de movimientos*/
        else{
            if(isVisited(currentGrid)){
                Random rnd=new Random();
                move=rnd.nextInt()%4+1;
                lastMove=move;
//                System.out.println("HOLA PACO");
//                casillamod casilla=new casillamod();
//                casilla.x=currentGrid.getX();
//                casilla.y=currentGrid.getY();
//                casilla.moves=tablero.get(pos_tablero).moves;
//                tablero.remove(pos_tablero);
//                move=casilla.moves.get(casilla.moves.size()-1);
//                lastMove=move;
//                tablero.add(casilla);
            }
            else{
            availableMoves(currentGrid);
            casillamod casilla=new casillamod();
            casilla.x=currentGrid.getX();
            casilla.y=currentGrid.getY();
            //Colocamos como última preferencia el contrario de por donde vinimos.
            if(lastMove==RIGHT && possiblemoves.contains(LEFT)){
                for(int i=0; i<possiblemoves.size();i++){
                    if(possiblemoves.get(i)==LEFT){
                            possiblemoves.remove(i);
                    }
                }
                possiblemoves.add(0, LEFT);
              
            }
            else if(lastMove==LEFT && possiblemoves.contains(RIGHT)){
                for(int i=0; i<possiblemoves.size();i++){
                    if(possiblemoves.get(i)==RIGHT){
                            possiblemoves.remove(i);
                    }
                }
                possiblemoves.add(0, RIGHT);
                
            }
            else if(lastMove==UP && possiblemoves.contains(DOWN)){
                for(int i=0; i<possiblemoves.size();i++){
                    if(possiblemoves.get(i)==DOWN){
                            possiblemoves.remove(i);
                    }
                }
                possiblemoves.add(0, DOWN);
            }
            else{
                for(int i=0; i<possiblemoves.size();i++){
                    if(possiblemoves.get(i)==UP){
                            possiblemoves.remove(i);
                    }
                }
                possiblemoves.add(0, UP);
                
            }
            casilla.moves=new ArrayList<>();
            casilla.moves=possiblemoves;
            move=casilla.moves.remove(casilla.moves.size()-1);
            lastMove=move;
            tablero.add(casilla);
            }
        
        
        }
        return move;

    }

    @Override
    public void newCheese() {
        tablero.clear();
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
        if(possiblemoves.size()==4){
            possiblemoves.add(BOMB);
        }
    }
    /*Comprueba si la casilla ha sido visitada anteriormente*/

    public boolean isVisited(Grid grid) {
        
        for(int i=0;i<tablero.size();i++){
            if(tablero.get(i).x==grid.getX()&&tablero.get(i).y==grid.getY()){ 
                pos_tablero=i;
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
        int x,y;
    }
