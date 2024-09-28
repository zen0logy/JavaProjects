import java.util.Scanner;
import java.util.Arrays;

public class Lotto {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        lottoTip(6);

        System.out.println("Play again? (Y/N)");
        String option = sc.nextLine();

        while (option.equalsIgnoreCase("y")) {
            lottoTip(6); // adjust attempts here! (increases win chance)
        }
    }

    // Lotto
    public static void lottoTip(int attemptLimit) {
        Scanner sc = new Scanner(System.in);

        int correctGuess = 0;
        int correctInput = 0;

        int[] lottoNumbers = new int[attemptLimit];
        int[] attempts = new int[attemptLimit];

        for (int i = 1; i <= attemptLimit; i++) {
            System.out.println("Enter your " + i + ". number: ");

            int input = sc.nextInt();
            int random = (int) (Math.random() * 45) + 1; // adjust lotto-range here! (increases win chance)

            lottoNumbers[i - 1] = random;
            attempts[i - 1] = input;

            if (input == random) {
                correctGuess++;
                correctInput = input;
            }
        }

        // Enhancements
        System.out.println();
        System.out.println("===================== INFO =====================");

        System.out.println("Lotto Numbers: " + Arrays.toString(lottoNumbers));
        System.out.println("Attempted Numbers: " + Arrays.toString(attempts));

        System.out.println("==================== GUESSES ===================");

        System.out.println("Guesses: " + correctInput);
        System.out.println(correctGuess + " / " + attemptLimit + " guessed correctly");

        System.out.println("====================== WIN =====================");

        // Win Logic
        if (correctGuess == 3) {
            System.out.println("You win € 5,-");
        }
        if (correctGuess == 4) {
            System.out.println("You win € 15,-");
        }
        if (correctGuess == 5) {
            System.out.println("You win € 500,-");
        }
        if (correctGuess == 6) {
            System.out.println("!!- JACKPOT -!!");
            System.out.println("YOU WON € 100.000,-");
        } else if (correctGuess == 1 || correctGuess == 2) {
            System.out.println("You lose");
        }

        System.out.println("=================== NEXT ROUND? ================");
    }
}
