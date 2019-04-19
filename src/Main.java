import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("numele primului jucator: ");
        String player1Name = scanner.nextLine();
        System.out.println("numele celul de-al doilea jucator: ");
        String player2Name = scanner.nextLine();

        Player player1 = new Player(player1Name);
        Player player2 = new Player(player2Name);

        MyTicTacToe myTicTacToe = new MyTicTacToe(player1, player2);

        boolean playGame = true;
        while (playGame) {
            myTicTacToe.playGame();
            System.out.println("doriti sa incepeti un joc nou ? please answer with yes or no");
            String playAnotherGame = scanner.nextLine().trim().toLowerCase();

            if (playAnotherGame.equals("yes")) {
                playGame = true;
            } else if (playAnotherGame.equals("no")) {
                playGame = false;

            } else {
                System.out.println("not a valid answer , please answer with yes or no");
                playGame = false;
            }

            // test answer

        }


    }
}
