package backgammon;

public class Board
{
	static final int NUMBER_OF_PEGS = 24;				// initialising the constants here
	static final int BAR = 2;
	
	/*---------------------------------------------------------------------*/
	static final int RED_HOME_START = 18;
	static final int RED_HOME_END = 24;
	static final int WHITE_HOME_START = 5;
	static final int WHITE_HOME_END = -1;
	static final int TOP_OUTER_START = 12;
	static final int TOP_OUTER_END = 18;
	static final int BOTTOM_OUTER_START = 11;
	static final int BOTTOM_OUTER_END = 5;
	/*
	 * these constants hold the values of the most crucial pegs on the board,
	 * adjusted for an array index starting at 0
	 */
	
	static final String GREATER_THAN = ">";
	static final String LESS_THAN = "<";
	static final String UPPPER_LINE_OF_BOARD = "13----+----+----+----+----18  BAR  19----+----+----+----+----24 OFF\n";
	static final String BOTTOM_LINE_OF_BOARD = "12----+----+----+----+----07  BAR  06----+----+----+----+----01 OFF\n";
	// end constants
	
	int playingBoard[] = new int[NUMBER_OF_PEGS + BAR];
	
	public String showBlackBar()
	{
		return "  X" + playingBoard[25] + " ";
	}
	
	public String showWhiteBar()
	{
		return "  O" + playingBoard[24] + " ";
	}
	
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
	
	/**
	 * self-explanatory method
	 */
	public void initialiseBoard()
	{
		for ( int i = 0; i < 24; i++ )
		{
			
			switch ( i )
			{
				case 0:
					playingBoard[i] = 2;
					break;
				case 5:
					playingBoard[i] = -5;
					break;
				case 7:
					playingBoard[i] = -3;
					break;
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
		int start, limit, increment;			// start, end and increment of nested loop. they change depending on the value of the outer loop's counter
		
		System.out.print ( UPPPER_LINE_OF_BOARD );
		for ( int quarterOfBoard = 0; quarterOfBoard < 4; quarterOfBoard++ )	// outer loop of nested loop
		{
			String endOfLine = "";		// this variable will contain either the "Bar" or "Off"
			String compareOperation = "";		// used to change the condition on the inner loop
			// the two variables above ^ need to be re-initialised every run of the loop
			switch ( quarterOfBoard )
			{
				case 0:
					start = TOP_OUTER_START;				// setting parameters for inner loop
					limit = TOP_OUTER_END;					//
					endOfLine += showBlackBar ();			//
					increment = 1;							//
					compareOperation += LESS_THAN;				//
					break;
				case 1:
					start = RED_HOME_START;
					limit = RED_HOME_END;
					endOfLine += "\n\n";					// insert off() method here later
					increment = 1;
					compareOperation += LESS_THAN;
					break;
				case 2:
					start = BOTTOM_OUTER_START;				// setting parameters for inner loop
					limit = BOTTOM_OUTER_END;				//
					endOfLine += showWhiteBar ();			//
					increment = -1;							//
					compareOperation += GREATER_THAN;				//
					break;
				default:
					start = WHITE_HOME_START;
					limit = WHITE_HOME_END;
					endOfLine += "\n";					// insert off() method here later
					increment = -1;
					compareOperation += GREATER_THAN;
					break;
			}
			for ( int counter = start; compareNumbers ( counter,
					compareOperation, limit ); counter += increment )		// this nested loop is mentioned with the compareNumbers() method above
			{
				int numberOfMen = playingBoard[counter];
				String board_Position_Correction = ( ( ( counter == 23 ) || ( counter == 0 ) ) ? " "
						: "" )
						+ ( ! ( ( counter == 12 ) || ( counter == 11 ) ) ? " "
								: "" );
				// this variable above ^ adjusts the exact position of the men on the printed-out board, for a neater look
				if ( numberOfMen < 0 )
					System.out.print ( board_Position_Correction + "X"
							+ Math.abs ( numberOfMen ) + "  " );	// converts negative numbers to black men
				else if ( numberOfMen > 0 )
					System.out.print ( board_Position_Correction + "O"
							+ numberOfMen + "  " );					// converts positive numbers to white men
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
