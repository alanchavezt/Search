import java.io.*;
import java.util.*;

public class Search 
{
	public static final int ROWS = 100;
    public static final int COLUMNS = 100;
    
    private int rows;
    private int columns;
    private char[][] grid;
    private Object[] theWords;
    
    Scanner fileIn = null;
	String line;
    
    private void openFile(String fileName )
    {
    	System.out.println("File Name: " + fileName);
    	try {
    		fileIn = new Scanner(new File(fileName));
		} catch (FileNotFoundException x) {
			System.out.println("File open failed.");
			x.printStackTrace();
			System.exit(0);   // TERMINATE THE PROGRAM
		}
    }
    
    private void readFile() throws IOException
    {
        String line;
        List<String> puzzleLines = new ArrayList<String>();
        List<String> words = new ArrayList<String>();

        if( fileIn.hasNextLine() == false )
            throw new IOException( "No lines in puzzle file" );

        line = fileIn.nextLine();
        columns = line.length( );
        puzzleLines.add( line );

        while(fileIn.hasNextLine())
        {
        	line = fileIn.nextLine();
        	
            if( line.length( ) == columns ) 
            {
//            	System.out.println(line + " - " + line.length());
            	puzzleLines.add(line);
            }
            else if(line.length() == 0)
			{
				System.out.println("\nEmpty Line : " + line.length() + "\n");
			} 
            else if(line.length() != columns)
            {
//            	System.err.println( "Puzzle is not rectangular; skipping row" );
//            	System.out.println( "Printing each word: " + line );
            	words.add(line);
            }        
        }
        
        rows = puzzleLines.size();
        grid = new char[rows][columns];
        Iterator<String> itr = puzzleLines.iterator();
        
        for( int r = 0; r < rows; r++ )
        {
            String theLine = (String) itr.next();
            grid[r] = theLine.toCharArray();
        }
        
        theWords = words.toArray();
        
    }
    
    private void printGrid()
    {
//    	System.out.println("*******Puzzle*******\n");
    	
    	for( int r = 0; r < rows; r++ )
        {
    		for( int c = 0; c < columns; c++ )
            {
    			System.out.print(grid[r][c] + " ");
            }
    		System.out.println("\n");
        }
    }
    
    private void printWords()
    {
//    	System.out.println("*******Words*******\n");
    	
        for( int r = 0; r < theWords.length; r++ )
        {
        	 System.out.println(theWords[r]);
//        	 System.out.println(theWords[r].toString().charAt(1));
        }
    }
	
	public static void main(String[] args) throws IOException
	{ 	
//		if (args.length < 1) {
//		    System.out.println("No Parameters");
//		} else {
//		    System.out.println(args.length + " Parameters:");
//		    int i = 0;
//		    for (i = 0; i < args.length; i++) {
//		        System.out.println(args[i]);
//		    }
//		}
		
		Search obj = new Search();
		obj.openFile(args[0]);
		obj.readFile();
//		obj.printGrid();
//		obj.printWords();
		
		Puzzle objPuzzle = new Puzzle(obj.rows, obj.columns, obj.grid, obj.theWords);
		
		objPuzzle.printPuzzle();
		objPuzzle.printWords();
		objPuzzle.getWord();
		
	}

}


