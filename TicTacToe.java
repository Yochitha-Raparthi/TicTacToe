//package console_based_application;
//
//import java.util.Scanner;
//
//public class TicTacToe {
//
//    static char[][] board=new char[3][3];
//    static char player='X';
//    static Scanner sc=new Scanner(System.in);
//	public static void main(String[] args) {
//		
//		for(int i=0;i<board.length;i++) {
//			for(int j=0;j<board.length;j++) {
//				board[i][j]=' ';
//			}
//		}
//
//		System.out.println("Start game.....");
//		while(true) {
//			printBoard();
//			System.out.println("Enter row what you want?");
//			int row=sc.nextInt();
//			System.out.println("Enter column what you want?");
//			int col=sc.nextInt();
//			if(row<0||row>2||col<0||col>2||board[row][col]!=' ') {
//				System.out.println("Invalid choice... Select another choice");
//				continue;
//			}
//			board[row][col]=player;
//			if(isPlayerWin()) {
//				System.out.println(player+" is Winner.....");
//				printBoard();
//				break;
//			}
//			if(isBoardFull()) {
//				System.out.println("Game complete No are win.. Match draw....");
//				printBoard();
//				break;
//			}
//			player=((player=='X')?'O':'X');
//		}
//		
//		
//	}
//	private static boolean isBoardFull() {
//		for(int i=0;i<board.length;i++) {
//			for(int j=0;j<board.length;j++) {
//				if(board[i][j]==' ') {
//					return false;
//				}
//			}
//		}
//		return true;
//	}
//	private static boolean isPlayerWin() {
//		//rows
//		for(int i=0;i<board.length;i++) {
//			if(board[i][0]==player&&board[i][1]==player&&board[i][2]==player) {
//				return true;
//			}
//		}
//		
//		//columns
//		for(int i=0;i<board.length;i++) {
//			if(board[0][i]==player&&board[1][i]==player&&board[2][i]==player) {
//				return true;
//			}
//		}
//		
//		//diagonals
//		if(board[0][0]==player&&board[1][1]==player&&board[1][1]==player) {
//			return true;
//		}
//		if(board[0][2]==player&&board[1][1]==player&&board[2][0]==player) {
//			return true;
//		}
//		return false;
//	}
//	private static void printBoard() {
//		System.out.println("+---+---+---+");
//		for(int i=0;i<board.length;i++) {
//			System.out.print("|");
//			for(int j=0;j<board.length;j++) { 
//				System.out.print(board[i][j]+"  |");
//			}
//			System.out.println();
//			System.out.println("+---+---+---+");
//		}
//		
//		
//	}
//	
//
//}



/// Add some colors

package console_based_application;

import java.util.Scanner;

public class TicTacToe {

    // ANSI Color Codes
    static final String RESET = "\u001B[0m";
    static final String RED = "\u001B[31m";
    static final String GREEN = "\u001B[32m";
    static final String YELLOW = "\u001B[33m";
    static final String MAGENTA = "\u001B[35m";
    static final String CYAN = "\u001B[36m";
    static final String WHITE = "\u001B[37m";
    static final String BOLD = "\u001B[1m";

    // Game variables
    static char[][] board = new char[3][3];
    static char player = 'X';

    static Scanner sc = new Scanner(System.in);

    // Score variables
    static int xWins = 0;
    static int oWins = 0;
    static int draws = 0;

    public static void main(String[] args) {

        printBanner();

        String choice;

        do {
            initializeBoard();
            player = 'X';

            playGame();
            printScoreBoard();

            System.out.print(YELLOW +
                "\nDo you want to play again? (Y/N): " + RESET);

            choice = sc.next();

        } while (choice.equalsIgnoreCase("Y"));

        System.out.println(CYAN +
            "\n====================================");
        System.out.println("       THANK YOU FOR PLAYING!");
        System.out.println("===================================="
            + RESET);

        sc.close();
    }

    // Display game title
    private static void printBanner() {

        System.out.println(CYAN + BOLD);
        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║          TIC TAC TOE GAME        ║");
        System.out.println("║          JAVA CONSOLE GAME       ║");
        System.out.println("╚══════════════════════════════════╝");
        System.out.println(RESET);

        System.out.println(CYAN + "Player 1 : X" + RESET);
        System.out.println(MAGENTA + "Player 2 : O" + RESET);

        System.out.println(YELLOW +
            "\nInstructions:");
        System.out.println("1. Enter row and column from 0 to 2.");
        System.out.println("2. Select an empty cell.");
        System.out.println("3. Match three symbols to win." + RESET);
    }

    // Initialize empty board
    private static void initializeBoard() {

        for (int i = 0; i < board.length; i++) {

            for (int j = 0; j < board[i].length; j++) {

                board[i][j] = ' ';
            }
        }
    }

    // Main game logic
    private static void playGame() {

        while (true) {

            printBoard();

            String playerColor = player == 'X' ? CYAN : MAGENTA;

            System.out.println(playerColor + BOLD +
                "\nPlayer " + player + "'s Turn" + RESET);

            int row = readInput("Enter row    (0-2): ");
            int col = readInput("Enter column (0-2): ");

            if (!isValidMove(row, col)) {

                System.out.println(RED +
                    "\nInvalid move! Please select an empty cell."
                    + RESET);

                continue;
            }

            board[row][col] = player;

            // Check winning condition
            if (isPlayerWin()) {

                printBoard();

                System.out.println(GREEN + BOLD);
                System.out.println("====================================");
                System.out.println("  CONGRATULATIONS! PLAYER " +
                    player + " WINS!");
                System.out.println("====================================");
                System.out.println(RESET);

                if (player == 'X') {
                    xWins++;
                } else {
                    oWins++;
                }

                break;
            }

            // Check draw condition
            if (isBoardFull()) {

                printBoard();

                System.out.println(YELLOW + BOLD);
                System.out.println("====================================");
                System.out.println("        MATCH DRAW! WELL PLAYED!");
                System.out.println("====================================");
                System.out.println(RESET);

                draws++;

                break;
            }

            switchPlayer();
        }
    }

    // Read and validate integer input
    private static int readInput(String message) {

        while (true) {

            System.out.print(YELLOW + message + RESET);

            if (sc.hasNextInt()) {

                int value = sc.nextInt();

                if (value >= 0 && value <= 2) {
                    return value;
                }
            } else {
                sc.next();
            }

            System.out.println(RED +
                "Invalid input! Enter a number from 0 to 2."
                + RESET);
        }
    }

    // Validate player move
    private static boolean isValidMove(int row, int col) {

        return row >= 0 && row < 3 &&
               col >= 0 && col < 3 &&
               board[row][col] == ' ';
    }

    // Switch current player
    private static void switchPlayer() {

        player = (player == 'X') ? 'O' : 'X';
    }

    // Check whether the board is full
    private static boolean isBoardFull() {

        for (int i = 0; i < board.length; i++) {

            for (int j = 0; j < board[i].length; j++) {

                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }

        return true;
    }

    // Check winning conditions
    private static boolean isPlayerWin() {

        // Check rows
        for (int i = 0; i < board.length; i++) {

            if (board[i][0] == player &&
                board[i][1] == player &&
                board[i][2] == player) {

                return true;
            }
        }

        // Check columns
        for (int i = 0; i < board.length; i++) {

            if (board[0][i] == player &&
                board[1][i] == player &&
                board[2][i] == player) {

                return true;
            }
        }

        // Check main diagonal
        if (board[0][0] == player &&
            board[1][1] == player &&
            board[2][2] == player) {

            return true;
        }

        // Check secondary diagonal
        if (board[0][2] == player &&
            board[1][1] == player &&
            board[2][0] == player) {

            return true;
        }

        return false;
    }

    // Return colored player symbol
    private static String getColoredSymbol(char symbol) {

        if (symbol == 'X') {
            return CYAN + BOLD + symbol + RESET;
        }

        if (symbol == 'O') {
            return MAGENTA + BOLD + symbol + RESET;
        }

        return " ";
    }

    // Display formatted game board
    private static void printBoard() {

        System.out.println();
        System.out.println(WHITE +
            "        0     1     2" + RESET);

        System.out.println(CYAN +
            "     ┌─────┬─────┬─────┐" + RESET);

        for (int i = 0; i < board.length; i++) {

            System.out.print("  " + i + "  ");

            System.out.print(CYAN + "│" + RESET);

            for (int j = 0; j < board[i].length; j++) {

                System.out.print("  " +
                    getColoredSymbol(board[i][j]) + "  ");

                System.out.print(CYAN + "│" + RESET);
            }

            System.out.println();

            if (i < board.length - 1) {

                System.out.println(CYAN +
                    "     ├─────┼─────┼─────┤" + RESET);
            }
        }

        System.out.println(CYAN +
            "     └─────┴─────┴─────┘" + RESET);
    }

    // Display game statistics
    private static void printScoreBoard() {

        System.out.println(YELLOW + BOLD +
            "\n╔══════════════════════════╗");
        System.out.println("║        SCORE BOARD       ║");
        System.out.println("╠══════════════════════════╣"
            + RESET);

        System.out.printf(CYAN +
            "║ Player X Wins : %-8d ║%n", xWins);

        System.out.printf(MAGENTA +
            "║ Player O Wins : %-8d ║%n", oWins);

        System.out.printf(WHITE +
            "║ Match Draws   : %-8d ║%n", draws);

        System.out.println(YELLOW +
            "╚══════════════════════════╝" + RESET);
    }
}
