import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Arrays;
import java.util.Random;

public class Bubble {
    public static void main(String[] args) {
        CustomList<String> list = new CustomList<>();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("1.Додати елемент в кінець");
            System.out.println("2.Вставити елемент за індексом");
            System.out.println("3.Видалити елемент за індексом");
            System.out.println("4.Отримати елемент за індексом");
            System.out.println("5.Вивести розмір переліку");
            System.out.println("6.Вивести розмір буфера");
            System.out.println("7.Показати всі елементи переліку");
            System.out.println("8.Згенерувати масив та порівняти сортування");
            System.out.println("0.Вийти");
            System.out.print("Ваш вибір: ");
            int choice = -1;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Введіть числове значення.");
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

                    case 8:
                        System.out.print("Введіть розмір масиву для генерації: ");
                        int size = scanner.nextInt();
                        scanner.nextLine();
                        int[] originalArray = new int[size];
                        Random random = new Random();
                        for (int i = 0; i < size; i++) {
                            originalArray[i] = random.nextInt(10000);
                        }

                        System.out.println("Масив успішно згенеровано.");
                        if (size <= 20) {
                            System.out.println("Початковий масив: " + Arrays.toString(originalArray));
                        }

                        int[] arrayForInsertion = Arrays.copyOf(originalArray, originalArray.length);
                        int[] arrayForSelection = Arrays.copyOf(originalArray, originalArray.length);
                        long startInsertion = System.nanoTime();
                        insertionSort(arrayForInsertion);
                        long endInsertion = System.nanoTime();
                        long durationInsertion = endInsertion - startInsertion;
                        long startSelection = System.nanoTime();
                        selectionSort(arrayForSelection);
                        long endSelection = System.nanoTime();
                        long durationSelection = endSelection - startSelection;
                        System.out.println("Сортування вставками");
                        if (size <= 20) {
                            System.out.println("Відсортований масив: " + Arrays.toString(arrayForInsertion));
                        }
                        System.out.printf("Час виконання: %.4f мс (%d нс) \n", durationInsertion / 1000000.0, durationInsertion);

                        System.out.println("Сортування вибіркою");
                        if (size <= 20) {
                            System.out.println("Відсортований масив: " + Arrays.toString(arrayForSelection));
                        }
                        System.out.printf("Час виконання: %.4f мс (%d нс) \n", durationSelection / 1000000.0, durationSelection);
                        break;

                    case 0:
                        running = false;
                        break;

                    default:
                        System.out.println("Невідома команда.");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Сталася помилка: " + e.getMessage());
            }
        }

        scanner.close();
    }

    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }
}