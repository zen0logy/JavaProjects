public class LuhnAlgorithm {
    public static void main(String[] args) {
        String cardNumber = "4539704354706391";
        String cardNumber2 = "327537590396793";
        boolean isValidLuhn = isValidLuhn(cardNumber);

        if (isValidLuhn) {
            System.out.println("Provided credit card number: " + cardNumber + " is valid");
        } else {
            System.out.println("Provided credit card number: " + cardNumber + " is not valid");
        }
    }

    public static boolean isValidLuhn(String number) {
        int sum = 0;
        boolean alternate = false;

        for (int i = number.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(String.valueOf(number.charAt(i)));

            if(alternate) {
                n *= 2;
                if(n > 9) {
                    n -= 9;
                }
            }

            sum += n;
            alternate = !alternate;
        }

        // Valid luhn number is dividible by 10
        return sum % 10 == 0;
    }
}