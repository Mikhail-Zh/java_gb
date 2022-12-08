import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

/**
 * Составить частотный словарь элементов одномерного массива
 * Частотный словарь содержит информацию о том, сколько раз встречается элемент
 * входных данных.
 */
public class java_lesson_1 {

    public static void main(String[] args) {
        int[] listInt = { 1, 9, 9, 0, 2, 8, 0, 9 }; // Задание списка чисел. Если список чисел пустой, далее программа
        // запрашивает данные от пользователя
        int choiceInput = choiceIn(listInt);
        if (choiceInput == 0) {
            String[] lst = scan();
            int[] userListInt = parse(lst);
            Map<Integer, Integer> dictionaryValues;
            dictionaryValues = dictData(userListInt);
            outData(dictionaryValues);
        } else {
            Map<Integer, Integer> dictionaryValues;
            dictionaryValues = dictData(listInt);
            outData(dictionaryValues);
        }

    }

    static int choiceIn(int[] list) {
        // Определение длины массива заданного в программе
        int choice = list.length;
        return choice;
    }

    static String[] scan() {
        // Получение данных от пользователя
        System.out.println("Введите числа через пробел: ");
        Scanner scn = new Scanner(System.in);
        String[] data = scn.nextLine().split(" ");
        scn.close();
        return data;
    }

    static int[] parse(String[] str) {
        // Преобразование строк в массиве в числа
        int[] listInt = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            listInt[i] = Integer.parseInt(str[i]);
        }
        return listInt;
    }

    static <dictionary> Map<Integer, Integer> dictData(int[] intList) {
        // Создание словаря. Ключ - число из списка. Значение - сколько раз встречается
        // число.
        Map<Integer, Integer> dictQuantityElement = new HashMap<Integer, Integer>();
        for (int i = 0; i < intList.length; i++) {
            if (dictQuantityElement.containsKey(intList[i]) != true) {
                dictQuantityElement.put(intList[i], 1);
            } else {
                dictQuantityElement.put(intList[i], dictQuantityElement.get(intList[i]) + 1);
            }
        }
        return dictQuantityElement;
    }

    static void outData(Object dictionaryValues) {
        // Вывод данных пользователю
        for (Map.Entry<Integer, Integer> set : ((Map<Integer, Integer>) dictionaryValues).entrySet()) {
            System.out.printf("%d встречается %d раз(а)\n", set.getKey(), set.getValue());
        }
    }
}
