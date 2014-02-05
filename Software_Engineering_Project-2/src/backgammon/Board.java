package backgammon;

public class Board
{
	static final int NUMBER_OF_PEGS = 24;				// initialising the constants here
	static final int BAR = 2;
	static final int OFF = 2;
	
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
	
	static final String UPPPER_LINE_OF_BOARD = "13----+----+----+----+----18  BAR  19----+----+----+----+----24 OFF\n";
	static final String BOTTOM_LINE_OF_BOARD = "12----+----+----+----+----07  BAR  06----+----+----+----+----01 OFF\n";
	// end constants
	
	int playingBoard[] = new int[NUMBER_OF_PEGS + BAR + OFF];
	
	public String showBlackBar()
	{
		return "  X" + playingBoard[25] + "  ";
	}
	
	public String showBlackOff()
	{
		return "X" + playingBoard[27] + "\n";
	}
	
	public String showWhiteBar()
	{
		return "  O" + playingBoard[24] + "  ";
	}
	
	public String showWhiteOff()
	{
		return "O" + playingBoard[26] + "\n\n";
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
	
	public String printMen( int numberOfMen )
	{
		String returnValue;
		if ( numberOfMen < 0 )
			returnValue = ( "X" + Math.abs ( numberOfMen ) + "  " );	// converts negative numbers to black men
		else if ( numberOfMen > 0 )
			returnValue = ( "O" + numberOfMen + "  " );					// converts positive numbers to white men
		else returnValue = ( " |  " );
		return returnValue;
	}
	
	/**
	 * self-explanatory method
	 */
	public void printBoard()
	{
		String boardPositionCorrection;				// adds whitespaces before men on the display, for a neater look
		
		System.out.print ( UPPPER_LINE_OF_BOARD );
		
		// print the top right quarter of the board
		for ( int counter = TOP_OUTER_START; counter < TOP_OUTER_END; counter++ )
		{
			boardPositionCorrection = ( ( counter == 12 ) ? "" : " " );
			System.out.print ( boardPositionCorrection
					+ printMen ( playingBoard[counter] ) );
		}
		System.out.print ( showBlackBar () );
		
		// print the top left quarter of the board		
		for ( int counter = RED_HOME_START; counter < RED_HOME_END; counter++ )
		{
			boardPositionCorrection = ( ( counter == 18 ) ? "" : " " )
					+ ( ( counter == 23 ) ? " " : "" );
			System.out.print ( boardPositionCorrection
					+ printMen ( playingBoard[counter] ) );
		}
		System.out.print ( showWhiteOff () );
		
		// print the bottom right quarter of the board
		for ( int counter = BOTTOM_OUTER_START; counter > BOTTOM_OUTER_END; counter-- )
		{
			boardPositionCorrection = ( ( counter == 11 ) ? "" : " " );
			System.out.print ( boardPositionCorrection
					+ printMen ( playingBoard[counter] ) );
		}
		System.out.print ( showWhiteBar () );
		
		// print the bottom left quarter of the board
		for ( int counter = WHITE_HOME_START; counter > WHITE_HOME_END; counter-- )
		{
			boardPositionCorrection = ( ( counter == 5 ) ? "" : " " )
					+ ( ( counter == 0 ) ? " " : "" );
			System.out.print ( boardPositionCorrection
					+ printMen ( playingBoard[counter] ) );
		}
		System.out.print ( showBlackOff () );
		
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
