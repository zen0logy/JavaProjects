import java.util.Scanner;
import java.util.Arrays;

public class Lotto {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        lottoTip(6);

        System.out.println("Play again? (ENTER)");
        String option = sc.nextLine();

        while (option.equalsIgnoreCase("")) {
            lottoTip(6); // adjust attempts here! (increases win chance)
        }
    }

    // Lotto
    public static void lottoTip(int attemptLimit) {
        Scanner sc = new Scanner(System.in);

        int correctGuess = 0;
        int[] correctInput = new int[attemptLimit];

        int[] lottoNumbers = new int[attemptLimit];
        int[] attempts = new int[attemptLimit];

        int[] inputs = new int[attemptLimit];
        int[] randoms = new int[attemptLimit];

        for (int i = 1; i <= attemptLimit; i++) {
            System.out.println("Enter your " + i + ". number: ");

            int input = sc.nextInt(); // 2
            int random = (int) (Math.random() * 5) + 1; // 1

            inputs[i - 1] = input;
            randoms[i - 1] = random;

            lottoNumbers[i - 1] = random; // 1
            attempts[i - 1] = input; // 2
        }

        for (int i = 0; i < randoms.length; i++) {
            for (int j = 0; j < inputs.length; j++) {
                if (inputs[j] == randoms[i]) {
                    correctGuess += 1;
                    correctInput[j] = inputs[j];
                }
            }
        }

        if(Arrays.asList(correctInput).contains(0)) {
            correctInput = correctInput[];
        }

        // Enhancements
        System.out.println();
        System.out.println("===================== INFO =====================");

        System.out.println("Lotto Numbers: " + Arrays.toString(lottoNumbers));
        System.out.println("Attempted Numbers: " + Arrays.toString(attempts));

        System.out.println("==================== GUESSES ===================");

        System.out.println("Guesses: " + Arrays.toString(correctInput));
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
        } if (correctGuess == 1 || correctGuess == 2) {
            System.out.println("You lose");
        }

        System.out.println("=================== NEXT ROUND? ================");
    }
}
