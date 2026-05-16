import java.util.Scanner;
import java.util.InputMismatchException;

public class Bubble {
    public static void main(String[] args) {
        CustomList<String> list = new CustomList<>();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("1. Додати елемент в кінець");
            System.out.println("2. Вставити елемент за індексом");
            System.out.println("3. Видалити елемент за індексом");
            System.out.println("4. Отримати елемент за індексом");
            System.out.println("5. Вивести розмір переліку");
            System.out.println("6. Вивести розмір буфера (ємність)");
            System.out.println("7. Показати всі елементи переліку");
            System.out.println("0. Вийти");
            System.out.print("Ваш вибір: ");
            int choice = -1;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Будь ласка, введіть числове значення.");
                scanner.nextLine();
                continue;
            }

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Введіть значення елемента: ");
                        String itemToAdd = scanner.nextLine();
                        list.add(itemToAdd);
                        System.out.println("Елемент додано в кінець.");
                        break;
                    case 2:
                        System.out.print("Введіть індекс: ");
                        int insertIndex = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Введіть значення елемента: ");
                        String itemToInsert = scanner.nextLine();
                        list.insertAt(insertIndex, itemToInsert);
                        System.out.println("Елемент успішно вставлено.");
                        break;
                    case 3:
                        System.out.print("Введіть індекс для видалення: ");
                        int removeIndex = scanner.nextInt();
                        list.removeAt(removeIndex);
                        System.out.println("Елемент за індексом " + removeIndex + " видалено.");
                        break;
                    case 4:
                        System.out.print("Введіть індекс: ");
                        int getIndex = scanner.nextInt();
                        String item = list.get(getIndex);
                        System.out.println("Значення елемента: " + item);
                        break;
                    case 5:
                        System.out.println("Поточна кількість елементів: " + list.getSize());
                        break;
                    case 6:
                        System.out.println("Поточний розмір буфера: " + list.getCapacity());
                        break;
                    case 7:
                        System.out.println("Вміст переліку");
                        if (list.getSize() == 0) {
                            System.out.println("Перелік порожній.");
                        } else {
                            for (int i = 0; i < list.getSize(); i++) {
                                System.out.println("Індекс " + i + ": " + list.get(i));
                            }
                        }
                        break;

                    case 0:
                        running = false;
                        break;

                    default:
                        System.out.println("Невідома команда. Спробуйте ще раз.");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Сталася помилка" + e.getMessage());
            } catch (Exception e) {
                System.out.println("Сталася непередбачена помилка: " + e.getMessage());
            }
        }

        scanner.close();
    }
}