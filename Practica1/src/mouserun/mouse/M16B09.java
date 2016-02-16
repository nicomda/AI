package mouserun.mouse;		
import mouserun.game.*;		
import java.util.*;			
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class M16B09
	extends Mouse				
{
        /*Array para guardar el mapa de bettermoves*/
        ArrayList <ArrayList> casillas;
        /*Array de movimientos posibles, versión inicial sin preferencia, no es completamente funcional*/
        ArrayList <Integer> possiblemoves;
        /*Array de movimientos posibles y preferencia*/
        ArrayList <Integer> bettermoves;
        /*Guarda la referencia a la última casilla visitada para establecer como última preferencia por donde vinimos*/
	private Grid lastGrid;
        /*Guarda las coordenadas de los Grids visitados*/
        ArrayList <String> visitedGrids;
        private String position;
        private int move,arraypos,lastmove;
        
        
        
        

	public M16B09()
	{
		super("Mickey Mouse");
                casillas=new ArrayList();
                visitedGrids=new ArrayList();
	}
	
	public int move(Grid currentGrid, Cheese cheese)
	{
            possiblemoves=new ArrayList();
            arraypos=0;
            position=currentGrid.getX()+" "+currentGrid.getY();
            lastGrid=currentGrid;
            if(!isVisited(currentGrid)){
            visitedGrids.add(position);
            availableMoves(currentGrid);
            if(possiblemoves.size()>2){
            move=possiblemoves.remove(possiblemoves.size()-1);}
            else move=possiblemoves.get(possiblemoves.size()-1);
            casillas.add(possiblemoves);
            lastmove=move;
            
            
            }
            else{
            possiblemoves=casillas.get(arraypos);
            if (possiblemoves.size()>2){
            move=possiblemoves.remove(possiblemoves.size()-1);
            casillas.set(arraypos,possiblemoves);
            }
            else move=possiblemoves.get(possiblemoves.size()-1);
            lastmove=move;
            }
            if (possiblemoves.size() ==0){
                return lastmove;
            }
            return move;
            
            
	}
	
	public void newCheese()
	{
	casillas=new ArrayList();
        visitedGrids=new ArrayList();
	}
	
	public void respawned()
	{
	casillas=new ArrayList();
        visitedGrids=new ArrayList();
	}
        /*
        Comprueba movimientos disponibles
        */
        public void availableMoves(Grid grid){
            if(grid.canGoDown())possiblemoves.add(DOWN);
            if(grid.canGoUp())possiblemoves.add(UP);
            if(grid.canGoLeft())possiblemoves.add(LEFT);
            if(grid.canGoRight())possiblemoves.add(RIGHT);
            casillas.add(possiblemoves);
        }
        /*Comprueba si la casilla ha sido visitada anteriormente*/
        public boolean isVisited(Grid grid){
            for(int x=0;x<visitedGrids.size();x++){
                if(visitedGrids.get(x).contentEquals(position)){
                    arraypos=x;
                    return true;
                }
                
            }
            return false;
            
        }
        
//        public void betterMoves(Grid grid, Cheese cheese){
//            case
//            if((grid.getX()-cheese.getX())>=(grid.getY()-cheese.getY())){
//                if(grid.getX()-cheese.getX()<=0){
//                    if(grid.canGoRight()){
//                        bettermoves.add(Mouse.RIGHT);
//                    }
//                    
//                }
//                else{
//                    bettermoves.add(Mouse.LEFT);
//                }
//            }
//            else{
//                if(grid.getY()-cheese.getY()<=0){
//                 possiblemoves.add(Mouse.UP);
//                }
//                else{
//                    possiblemoves.add(Mouse.DOWN);
//                }
//                
//            }
//        }
	
}