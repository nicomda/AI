package mouserun.mouse;		
import mouserun.game.*;		
import java.util.*;			

public class M16B09
	extends Mouse				
{
        ArrayList <ArrayList> casillas;
        ArrayList <Integer> possiblemoves;
	private Grid lastGrid;

	public M16B09()
	{
		super("Mickey Mouse");
                casillas=new ArrayList();
	}
	
	public int move(Grid currentGrid, Cheese cheese)
	{
            possiblemoves=new ArrayList();

            
            casillas.add(possiblemoves);
            return 0;
	}
	
	public void newCheese()
	{
	
	}
	
	public void respawned()
	{
	
	}
	
        
	
	
}