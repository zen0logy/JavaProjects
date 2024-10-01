import java.util.Random;

public class ShipPiece {
    public static void main(String[] args) {
        int size = 10;
        Random rand = new Random();

        char[][] field = new char[size][size];

        // Initialize the field with water
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = '~'; // Character for the water in the game field
            }
        }

        // Place the ships on the field
        printShips(field, rand);

        // Now print the field after the ships are placed
        printField(field);
    }

    // Method to print the field
    public static void printField(char[][] field) {
        int size = field.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(field[i][j] + " "); // Print each character in the row on the same line
            }
            System.out.println(); // Move to the next line after printing each row
        }
    }

    public static void printShips(char[][] field, Random rand) {
        // M = Marine, P = Pirate, C = Cruiser
        char[] ships = {'M', 'P', 'C'};

        int numberOfShips = 3;

        for (char ship : ships) {
            for (int i = 0; i < numberOfShips; i++) {
                int row, col;
                // Keep generating random positions until we find an empty spot
                do {
                    row = rand.nextInt(field.length);
                    col = rand.nextInt(field[0].length);
                } while (field[row][col] != '~'); // '~' means water (empty spot)

                field[row][col] = ship; // Place the ship
            }
        }
    }
}
