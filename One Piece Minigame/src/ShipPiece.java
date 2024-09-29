public class ShipPiece {
    public static void main(String[] args) {
        int size = 10;
        char[][] field = new char[size][size];

        // Initialize the field with water
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = '~'; // Character for the water in the game field
            }
        }

        // Print the initial field
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
}
