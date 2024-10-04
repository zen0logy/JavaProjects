import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class Player {
    private int hp;
    private int stamina;
    private boolean doubleMovement;
    private int row, col; // Player's position on the field

    public Player(int startRow, int startCol) {
        this.hp = 100;
        this.stamina = 50;
        this.doubleMovement = false;
        this.row = startRow;
        this.col = startCol;
    }

    public void restoreHp(int amount) {
        this.hp += amount;
        if (this.hp > 100) this.hp = 100;
        System.out.println("Your HP has been restored by: " + amount + ". Current HP: " + this.hp);
    }

    public void reduceHp(int amount) {
        this.hp -= amount;
        if (this.hp < 0) this.hp = 0;
        System.out.println("You took " + amount + " damage! Current HP: " + this.hp);
        if (this.hp == 0) {
            System.out.println("You have died. Game over.");
            System.exit(0); // End the game if HP reaches 0
        }
    }

    public void restoreStamina(int amount) {
        this.stamina += amount;
        if (this.stamina > 50) this.stamina = 50;
        System.out.println("Your Stamina has been restored by: " + amount + ". Current Stamina: " + this.stamina);
    }

    public void setDoubleMovementTrue() {
        this.doubleMovement = true;
        System.out.println("Double Movement is active. You'll move 2 steps with the next input.");
    }

    public boolean isDoubleMovementActive() {
        return doubleMovement;
    }

    public void deactivateDoubleMovement() {
        this.doubleMovement = false;
    }

    public void printPlayer() {
        System.out.println("Player Stats:");
        System.out.println("HP: " + hp);
        System.out.println("Stamina: " + stamina);
        System.out.println("Double Movement Active: " + (doubleMovement ? "Yes" : "No"));
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    // Move the player on the field
    public void move(char direction, int fieldSize) {
        switch (direction) {
            case 'W': // Move up
                if (row > 0) row--;
                break;
            case 'S': // Move down
                if (row < fieldSize - 1) row++;
                break;
            case 'A': // Move left
                if (col > 0) col--;
                break;
            case 'D': // Move right
                if (col < fieldSize - 1) col++;
                break;
            default:
                System.out.println("Invalid move. Use W, A, S, D for movement.");
        }
        System.out.println("Player moved to position: (" + row + ", " + col + ")");
    }
}

class Game {
    private final Player player;
    private final char[][] field;
    private final Random random;

    public Game(int size) {
        this.field = new char[size][size];
        this.random = new Random();
        this.player = new Player(size / 2, size / 2); // Start player at center of the field
        initializeField();
        printShips();
        printBarrels();
    }

    // Initialize the field with water
    public void initializeField() {
        for (char[] chars : field) {
            // Character for water (empty space)
            Arrays.fill(chars, '~');
        }
    }

    // Print the field
    public void printField() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                // Check if the current position matches the player's position
                if (i == player.getRow() && j == player.getCol()) {
                    System.out.print("X "); // Mark player's position on the field
                } else {
                    System.out.print(field[i][j] + " ");
                }
            }
            System.out.println(); // Move to the next line after printing each row
        }
    }

    // Place ships on the field
    public void printShips() {
        char[] ships = {'M', 'P', 'C'};
        for (char ship : ships) {
            placeItem(ship, 3); // Place 3 ships of each type
        }
    }

    // Place barrels on the field
    public void printBarrels() {
        placeItem('b', 8); // Place 8 barrels on the field
    }

    // Place an item on the field in random empty locations
    private void placeItem(char item, int count) {
        for (int i = 0; i < count; i++) {
            int row, col;
            do {
                row = random.nextInt(field.length);
                col = random.nextInt(field[0].length);
            } while (field[row][col] != '~'); // Place only on empty spots (water)
            field[row][col] = item;
        }
    }

    // Handle barrel interaction
    public void interactWithBarrel() {
        int row = player.getRow();
        int col = player.getCol();
        if (field[row][col] == 'b') {
            field[row][col] = '~'; // Remove the barrel after interaction
            giveRandomItem();
        }
    }

    // Give the player a random item when they find a barrel
    public void giveRandomItem() {
        int randomItem = random.nextInt(4);  // Generate a number between 0 and 3

        switch (randomItem) {
            case 0:
                System.out.println("You found some supplies! No immediate effect.");
                break;
            case 1:
                player.restoreHp(20);  // Restores 20 HP
                break;
            case 2:
                player.restoreStamina(20);  // Restores 20 Stamina
                break;
            case 3:
                player.setDoubleMovementTrue();  // Activates double movement
                break;
        }
    }

    public void moveTwoSteps(char direction, int fieldSize) {
        for (int i = 0; i < 2; i++) {
            player.move(direction, fieldSize); // Move the same direction twice
            attackShip();  // Check for ship interaction after each move
        }
        player.deactivateDoubleMovement(); // Deactivate double movement after use
    }

    // Attack logic for ships
    public void attackShip() {
        int row = player.getRow();
        int col = player.getCol();
        char position = field[row][col];

        switch (position) {
            case 'M': // Military Ship
                System.out.println("================================================");
                System.out.println("You attacked a Military ship and took 15 damage!");
                player.reduceHp(15);
                field[row][col] = '~'; // Remove the ship
                break;
            case 'P': // Pirate Ship
                System.out.println("You attacked a Pirate ship and took 25 damage!");
                player.reduceHp(25);
                field[row][col] = '~'; // Remove the ship
                break;
            case 'C': // Civilian Ship
                System.out.println("You attacked a Civilian ship but took no damage.");
                field[row][col] = '~'; // Remove the ship
                break;
        }
    }

    // Run the game loop
    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printField();
            player.printPlayer(); // Display player stats

            System.out.println("Move (WASD): ");
            char move = scanner.next().charAt(0);

            if (player.isDoubleMovementActive()) {
                moveTwoSteps(move, field.length); // Move twice if double movement is active
            } else {
                player.move(move, field.length); // Regular Movement
                attackShip();  // Check for ship interaction after each move
            }

            interactWithBarrel(); // Check for barrels after each move
        }
    }

    public static void main(String[] args) {
        Game game = new Game(10); // Create a 10x10 game field
        game.startGame();
    }
}
