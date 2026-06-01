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
                        int[] arrayForMerge = Arrays.copyOf(originalArray, originalArray.length);
                        int[] arrayForCounting = Arrays.copyOf(originalArray, originalArray.length);

                        long startInsertion = System.nanoTime();
                        insertionSort(arrayForInsertion);
                        long endInsertion = System.nanoTime();
                        long durationInsertion = endInsertion - startInsertion;
                        long startSelection = System.nanoTime();
                        selectionSort(arrayForSelection);
                        long endSelection = System.nanoTime();
                        long durationSelection = endSelection - startSelection;
                        long startMerge = System.nanoTime();
                        mergeSort(arrayForMerge, 0, arrayForMerge.length - 1);
                        long endMerge = System.nanoTime();
                        long durationMerge = endMerge - startMerge;
                        long startCounting = System.nanoTime();
                        countingSort(arrayForCounting);
                        long endCounting = System.nanoTime();
                        long durationCounting = endCounting - startCounting;
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

                        System.out.println("Сортування злиттям");
                        if (size <= 20) {
                            System.out.println("Відсортований масив: " + Arrays.toString(arrayForMerge));
                        }
                        System.out.printf("Час виконання: %.4f мс (%d нс) \n", durationMerge / 1000000.0, durationMerge);

                        System.out.println("Сортування підрахунками");
                        if (size <= 20) {
                            System.out.println("Відсортований масив: " + Arrays.toString(arrayForCounting));
                        }
                        System.out.printf("Час виконання: %.4f мс (%d нс) \n", durationCounting / 1000000.0, durationCounting);
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

    public static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int middle = left + (right - left) / 2;

            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);

            merge(array, left, middle, right);
        }
    }

    private static void merge(int[] array, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];
        for (int i = 0; i < n1; ++i) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            rightArray[j] = array[middle + 1 + j];
        }

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    public static void countingSort(int[] array) {
        if (array.length == 0) {
            return;
        }

        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        int[] count = new int[max + 1];
        for (int i = 0; i < array.length; i++) {
            count[array[i]]++;
        }

        int index = 0;
        for (int i = 0; i <= max; i++) {
            while (count[i] > 0) {
                array[index++] = i;
                count[i]--;
            }
        }
    }
}