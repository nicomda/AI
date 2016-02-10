package mouserun.mouse;		
import mouserun.game.*;		
import java.util.*;			
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class M16B09
	extends Mouse				
{
        ArrayList <ArrayList> casillas;
        ArrayList <Integer> possiblemoves;
        ArrayList <Integer> bettermoves;
	private Grid lastGrid;
        private int x,y;
        ArrayList <String> visitedGrids;
        private String position;
        private int move,arraypos;
        
        
        

	public M16B09()
	{
		super("Mickey Mouse");
                casillas=new ArrayList();
                visitedGrids=new ArrayList();
	}
	
	public int move(Grid currentGrid, Cheese cheese)
	{
            possiblemoves=new ArrayList();
            arraypos=-1;
            position=currentGrid.getX()+" "+currentGrid.getY();
            lastGrid=currentGrid;
            if(!isVisited(currentGrid)){
            visitedGrids.add(position);
            availableMoves(currentGrid);
            if(possiblemoves.size()>1){
            move=possiblemoves.remove(possiblemoves.size());}
            else move=possiblemoves.get(possiblemoves.size());
            casillas.add(possiblemoves);
            return move;
            
            }
            else{
            possiblemoves=casillas.get(arraypos);
            if (possiblemoves.size()>1){
            move=possiblemoves.remove(possiblemoves.size());
            casillas.set(arraypos,possiblemoves);
            }
            else move=possiblemoves.get(possiblemoves.size());
            System.out.println(possiblemoves.size());
            return move;
            }
            

            
            
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
        
        public void availableMoves(Grid grid){
            if(grid.canGoDown())possiblemoves.add(DOWN);
            if(grid.canGoUp())possiblemoves.add(UP);
            if(grid.canGoLeft())possiblemoves.add(LEFT);
            if(grid.canGoRight())possiblemoves.add(RIGHT);
            casillas.add(possiblemoves);
        }
        
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