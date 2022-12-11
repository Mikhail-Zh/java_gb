import java.util.Scanner;

public class lesson_2 {
    public static void hanoiTowers(int quantity, int from, int to, int bufPeg) {
        if (quantity != 0) {
            hanoiTowers(quantity - 1, from, bufPeg, to);
            System.out.printf("Перенести с %d на %d\n", from, to);
            hanoiTowers(quantity - 1, bufPeg, to, from);
        }
    }

    public static int quantityRing() { // Запрос числа колец от пользователя
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число колец: ");
        int number = scanner.nextInt();
        scanner.close();
        return number;
    }

    public static void main(String[] args) {
        int startPeg = 1; // Начальное расположение колец
        int destinationPeg = 3; // Конечное расположение колец
        int bufferPeg = 2; // Место для промежуточного перекладывания колец
        int plateQuantity = quantityRing();
        hanoiTowers(plateQuantity, startPeg, destinationPeg, bufferPeg);
    }
}
