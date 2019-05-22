public class Puzzle 
{
	private int rows;
    private int columns;
    private char[][] grid;
    private Object[] words;
    
    int[][] deltas = {{0,-1}, {-1,-1}, {-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}};  
    
    public Puzzle(int rows, int columns, char[][] grid, Object[] words) 
    {
    	this.rows = rows;
        this.columns = columns;
        this.grid = grid;
        this.words = words;
    }
    
    public void printPuzzle()
    {
//    	System.out.println("*******Puzzle Class*******\n");
    	
    	for( int r = 0; r < rows; r++ )
        {
    		for( int c = 0; c < columns; c++ )
            {
    			System.out.print(grid[r][c] + " ");
            }
    		System.out.println("\n");
        }
    }
    
    public void printWords()
    {
//    	System.out.println("*******Words*******\n");
    	
        for( int r = 0; r < words.length; r++ )
        {
        	 System.out.println(words[r]);
        }
        
        System.out.println("\n");
    }
    
    public void printDeltas()
    {
    	System.out.println("*******Deltas*******\n");
    	
    	for(int i=0; i < deltas.length; i++)
    	{
    		System.out.println(deltas[i][0] + "," + deltas[i][1]);
    	}
    	
    	System.out.println("\n");
    }
    
    public void getWord()
    {
    	for( int i = 0; i < words.length; i++ )
        {
    		solvePuzzle(words[i].toString());
        }
    }
    
    public void solvePuzzle(String word)
    {
    	boolean match = false;
    	
    	for(int r = 0; r < rows; r++)
    	{
    		for(int c =0; c < columns; c++)
    		{
    			match = getDeltas(r, c, word);
    			
    			if(match == true)
    			{
    				return;
    			}
    		}
    	}
    	
    	if(match == false)
		{
			System.out.println(word + " not found");
		}
    }
    
    public boolean getDeltas(int rowP, int colP, String word)
    {
    	boolean match;
    	
    	for(int d =0; d < deltas.length; d++)
    	{
    		match = findWord(rowP,colP,deltas[d][0],deltas[d][1],word);
    		
    		if(match == true)
			{
				return match;
			}
    	}
    	
    	return false;
    }
    
    public boolean findWord(int rowP, int colP, int deltaRow, int deltaColumn, String word)
    {
    	int startRow = rowP;
    	int startCol = colP;
    	int count = 0;
    	
    	for(int i=0; i < word.length(); i++)
    	{
//    		System.out.println("Position: " + rowP + "," + colP);
    		
    		if( (rowP >= 0 && rowP < rows) && (colP >=0 && colP < columns) && (grid[rowP][colP] == word.charAt(i)))
    		{
//    			System.out.println(grid[rowP][colP] + " == " + word.charAt(i));
    			
    			count++;
    			
    			if(count == word.length() )
        		{
    				System.out.println(word + " found at start: " + startRow + "," + startCol + " end: " + rowP + "," + colP  );
        			return true;
        		}
    		}
    		else
    		{
    			return false;
    		}
    		
    		rowP = rowP + deltaRow;
    		colP = colP + deltaColumn;	
    	}
    	
    	return false;
    }
}