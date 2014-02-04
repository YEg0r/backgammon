package backgammon;

public class Board
{
	static final int NUMBER_OF_PEGS = 24;				// initialising the constants here
	static final int BAR = 2;
	static final int RED_HOME_START = 18;
	static final int RED_HOME_END = 24;
	static final int WHITE_HOME_START = 5;
	static final int WHITE_HOME_END = -1;
	static final int TOP_OUTER_START = 12;
	static final int TOP_OUTER_END = 18;
	static final int BOTTOM_OUTER_START = 11;
	static final int BOTTOM_OUTER_END = 5;
	static final String GREATER_THAN = ">";
	static final String LESS_THAN = "<";
	static final String UPPPER_LINE_OF_BOARD = "13----+----+----+---+----18  BAR  19----+----+----+----+---24 OFF\n";
	static final String BOTTOM_LINE_OF_BOARD = "12----+----+----+---+----07  BAR  06----+----+----+----+---01 OFF\n";
	// end constants
	
	int playingBoard[] = new int[NUMBER_OF_PEGS + BAR];
	
	/**
	 * this method is used in one of our nested loops to have a variable looping
	 * condition
	 * 
	 * @param numberOne
	 * @param comparator
	 *            the operation to be performed on the numbers
	 * @param numberTwo
	 * @return true/false
	 */
	private boolean compareNumbers( int numberOne, String comparator,
			int numberTwo )
	{
		boolean returnValue = false;
		if ( comparator.equals ( "<" ) )
			returnValue = ( numberOne < numberTwo );
		else if ( comparator.equals ( ">" ) )
			returnValue = ( numberOne > numberTwo );
		return returnValue;
	}
	
	public void initialiseBoard()
	{
		for ( int i = 0; i < 24; i++ )
		{
			
			switch ( i )
			{
				case 1:
					playingBoard[i] = 2;
					break;
				case 5:
					playingBoard[i] = -5;
					break;
				case 9:
					playingBoard[i] = -3;
				case 11:
					playingBoard[i] = 5;
					break;
				case 12:
					playingBoard[i] = -5;
					break;
				case 16:
					playingBoard[i] = 3;
					break;
				case 18:
					playingBoard[i] = 5;
					break;
				case 23:
					playingBoard[i] = -2;
					break;
				default:
					playingBoard[i] = 0;
					//break;
					
			}
			
		}
	}
	
	/**
	 * self-explanatory method
	 */
	public void printBoard()
	{
		int start, limit, increment;
		
		System.out.print ( UPPPER_LINE_OF_BOARD );
		for ( int quarterOfBoard = 0; quarterOfBoard < 4; quarterOfBoard++ )	// a nested loop
		{
			String endOfLine = "";			//	these two variables need to be re-initialised every run
			String comparator = "";			//
			switch ( quarterOfBoard )
			{
				case 0:
					start = TOP_OUTER_START;
					limit = TOP_OUTER_END;
					endOfLine += "  " + playingBoard[25] + "   ";
					increment = 1;
					comparator += LESS_THAN;
					break;
				case 1:
					start = RED_HOME_START;
					limit = RED_HOME_END;
					endOfLine += "\n\n";					// insert off() method here later
					increment = 1;
					comparator += LESS_THAN;
					break;
				case 2:
					start = BOTTOM_OUTER_START;
					limit = BOTTOM_OUTER_END;
					endOfLine += "  " + playingBoard[24] + "   ";
					increment = -1;
					comparator += GREATER_THAN;
					break;
				default:
					start = WHITE_HOME_START;
					limit = WHITE_HOME_END;
					endOfLine += "\n";					// insert off() method here later
					increment = -1;
					comparator += GREATER_THAN;
					break;
			}
			for ( int counter = start; compareNumbers ( counter, comparator,
					limit ); counter += increment )		// this nested loop is mentioned with the compareNumbers() method above
			{
				int numberOfMen = playingBoard[counter];
				if ( numberOfMen < 0 )
					System.out
							.print ( ( ( ( counter == 23 ) || ( counter == 0 ) ) ? " "
									: "" )
									+ "X" + Math.abs ( numberOfMen ) + "  " );
				else if ( numberOfMen > 0 )
					System.out
							.print ( ( ( ( counter == 23 ) || ( counter == 0 ) ) ? " "
									: "" )
									+ "O" + numberOfMen + "  " );
				else System.out.print ( "  |  " );
			}
			System.out.print ( endOfLine );
		}
		System.out.print ( BOTTOM_LINE_OF_BOARD );
	}
	
	public int rollDice()
	{
		return ( ( ( int ) ( Math.random () * 101 ) ) % 6 ) + 1;
	}
	
	public static void main( String[] args )
	{
		
		Board b = new Board ();
		b.initialiseBoard ();
		b.printBoard ();
		
	}
}
