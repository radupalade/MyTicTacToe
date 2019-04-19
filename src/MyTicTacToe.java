import java.util.Scanner;

public class MyTicTacToe {

    public static final char SYMBOL_X = 'X';
    public static final char SYMBOL_0 = '0';

    public static final int GAME_SIZE = 3;

    char[][] game = new char[GAME_SIZE][GAME_SIZE];

    Player player1;
    Player player2;

    public MyTicTacToe(Player player1, Player player2) {

        this.player1 = player1;
        this.player2 = player2;
    }

    public void showGame() {
        for (int i = 0; i < GAME_SIZE; i++) {
            for (int j = 0; j < GAME_SIZE; j++) {
                System.out.print(game[i][j] + " ");
            }
            System.out.println();
        }

    }

    public void initBoard() {
        for (int i = 0; i < GAME_SIZE; i++) {
            for (int j = 0; j < GAME_SIZE; j++) {
                game[i][j] = '.';

            }
        }
    }


    public Move readMove() {
        Scanner s = new Scanner(System.in);
        System.out.println("make move");
        String myMove = s.nextLine();
        String[] split = myMove.split("-");
        int myLine = Integer.valueOf(split[0]);
        int myCol = Integer.valueOf(split[1]);
        Move move = new Move(myLine, myCol);

        return move;
    }

    /*
     * trebuie sa returneze true daca mutarea e valida si false daca mutarea e invalida
     * */
    public boolean validateMove(Move move) {
        if (move.col >= 3 && move.line >= 3) {
            return false;

        }

        if (game[move.line][move.col] == SYMBOL_X || game[move.line][move.col] == SYMBOL_0) {
            return false;
        }

        // testez daca in joc la linia si coloana respectiva exista deja un simbol

        return true;
    }


    public void makeMove(Move move, char symbol) {
        game[move.line][move.col] = symbol;


    }


    public boolean isWinLine(int line, char symbol) {
        boolean isWin = true;
        int i = 0;
        while (i < GAME_SIZE && isWin == true) {
            if (game[line][i] != symbol) {
                isWin = false;
            }
            i++;
        }
        return isWin;
    }

    public boolean isWinCol(int col, char symbol) {
        boolean isWin = true;
        int i = 0;
        while (i < GAME_SIZE && isWin == true) {
            if (game[i][col] != symbol) {
                isWin = false;
            }
            i++;
        }
        return isWin;
    }

    public boolean isWinDiag1(char symbol) {
        boolean isWin = true;
        int i = 0;
        while (i < GAME_SIZE && isWin == true) {
            if (game[i][i] != symbol) {
                isWin = false;
            }
            i++;
        }
        return isWin;
    }

    public boolean isWinDiag2(char symbol) {
        boolean isWin = true;
        int i = 0;
        while (i < GAME_SIZE && isWin == true) {
            if (game[i][GAME_SIZE - i - 1] != symbol) {
                isWin = false;
            }
            i++;
        }
        return isWin;
    }


    public boolean isWin(Move move, char symbol) {
        boolean isWin = false;
        //testez linii
        isWin = isWinLine(move.line, symbol);
        if (isWin == false)
            isWin = isWinCol(move.col, symbol);

        //testez coloane
        //testez diag1
        if (isWin == false && move.line == move.col) {
            isWin = isWinDiag1(symbol);
        }
        //testez diag2
        if (isWin == false && move.line == GAME_SIZE - move.col - 1) {
            isWin = isWinDiag2(symbol);
        }

        return isWin;
    }


    public void playGame() {

        initBoard();
        System.out.println("incepe jocul. ");
        showGame();

        Player currentPlayer = player1;
        char currentSymbol = SYMBOL_X;
        int nrMoves = 0;
        boolean isWin = false;

        while (isWin == false && nrMoves < GAME_SIZE * GAME_SIZE) {

            Move move = readMove();
            //validez mutare
            boolean moveIsValid = validateMove(move);

            while (!moveIsValid) {
                // read move
                System.out.println("mutare invalida");

                move = readMove();

                System.out.println(move.line);
                System.out.println(move.col);

                moveIsValid = validateMove(move);

            }

            //efectuez mutare
            makeMove(move, currentSymbol);
            showGame();
            //numar mutare
            nrMoves++;
            if (nrMoves >= 2 * GAME_SIZE - 1) {
                isWin = isWin(move, currentSymbol);

            }
            //testez daca avem stare de WIN
            //daca nu e win sau mai am mutari -- schimb jucatorul
            if (!isWin) {
                if (currentPlayer == player1) {
                    currentPlayer = player2;
                    currentSymbol = SYMBOL_0;
                } else {
                    currentPlayer = player1;
                    currentSymbol = SYMBOL_X;
                }
            }
        }

        //afisare mesaj corespunzator
        if (isWin == true) {
            System.out.println(currentPlayer.name + " " + "este castigator");
        } else {
            System.out.println("nici un castigator");

        }

    }
}