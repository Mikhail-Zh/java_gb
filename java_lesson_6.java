import java.util.ArrayList;
import java.util.Scanner;

public class java_lesson_6 {
    static ArrayList<Integer> xQueue = new ArrayList<Integer>(); // Очередь для "x"
    static ArrayList<Integer> yQueue = new ArrayList<Integer>(); // Очередь для "y"
    static int[] inPoint; // Переменная для хранения координаты точки входа
    static int[] outPoint; // Переменная для хранения координаты точки выхода
    static ArrayList<int[]> path = new ArrayList<int[]>(); // Переменная для хранения пути выхода

    public static void main(String[] args) {
        int[][] copyMap = myMap(); // Присвоение карты переменной
        printMap(copyMap); // Печать карты в консоли
        Scanner scan = new Scanner(System.in); // Запуск сканера
        System.out.println("Введите координаты входа. Начало координат - верхний левый угол: ");
        boolean flagIn = false; // Флаг цикла получения координат точки входа
        while (flagIn == false) { // Получение координат точки входа и проверка возможности установки
            inPoint = Points(scan);
            if (inPoint[0] >= copyMap.length || inPoint[1] >= copyMap[0].length) {
                System.out.println("Точка лежит за пределами поля. Выберете другую координату.");
            } else if (copyMap[inPoint[0]][inPoint[1]] != 0) {
                System.out.println("На данной координате препятствие. Выберете другую координату.");
            } else {
                flagIn = true;
                addQueue(inPoint[1], inPoint[0]); // Добавление точки входа в очередь
            }
        }
        System.out.println("Введите координаты выхода. Начало координат - верхний левый угол: ");
        boolean flagOut = false; // Флаг цикла получения координат точки выхода
        while (flagOut == false) { // Получение координат точки выхода и проверка возможности установки
            outPoint = Points(scan);
            if (outPoint[0] >= copyMap.length || outPoint[1] >= copyMap[0].length) {
                System.out.println("Точка лежит за пределами поля. Выберете другую координату.");
            } else if (copyMap[outPoint[0]][outPoint[1]] != 0) {
                System.out.println("На данной координате препятствие. Выберете другую координату.");
            } else {
                flagOut = true;
            }
        }
        scan.close(); // Отключение сканера
        while (xQueue.size() != 0) { // Заполнение карты шагами до точки выхода
            if (xQueue.get(0) == outPoint[1] && yQueue.get(0) == outPoint[0]) {
                xQueue.clear();
                yQueue.clear();
            } else {
                markupMap(outPoint, copyMap);
            }
        }
        path.add(outPoint); // Запись точки выхода в переменную для хранения пути выхода
        boolean flagPath = true; // Флаг цикла получения пути
        while (flagPath == true) { // Построение пути от точки выхода к точке входа
            if (copyMap[path.get(path.size() - 1)[0]][path.get(path.size() - 1)[1]] == 1) {
                flagPath = false;
            } else {
                findingPath(path.get(path.size() - 1), copyMap);
            }
        }
        pathMap(copyMap); // Запись пути в карту
        printMap(copyMap); // Вывод карты в консоль
    }

    public static void pathMap(int[][] map) {
        // Метод для записи пути в карту
        for (int i = 1; i < path.size(); i++) {
            map[path.get(i)[0]][path.get(i)[1]] = -11;
        }
    }

    public static void findingPath(int[] finish, int[][] map) {
        // Метод получения координа соседней клеточки где значение меньше на 1 для
        // построения пути
        int xstart = finish[1];
        int ystart = finish[0];

        if (map[ystart][xstart] - map[ystart - 1][xstart] == 1) { // Проверка клеточки выше
            int[] nextPoint = { ystart - 1, xstart };
            path.add(nextPoint); // Добавление координат клеточки в путь
        } else if (map[ystart][xstart] - map[ystart][xstart + 1] == 1) { // Проверка клеточки справа
            int[] nextPoint = { ystart, xstart + 1 };
            path.add(nextPoint); // Добавление координат клеточки в путь
        } else if (map[ystart][xstart] - map[ystart + 1][xstart] == 1) { // Проверка клеточки ниже
            int[] nextPoint = { ystart + 1, xstart };
            path.add(nextPoint); // Добавление координат клеточки в путь
        } else if (map[ystart][xstart] - map[ystart][xstart - 1] == 1) { // Проверка клеточки слева
            int[] nextPoint = { ystart, xstart - 1 };
            path.add(nextPoint); // Добавление координат клеточки в путь
        }
    }

    public static void markupMap(int[] finish, int[][] map) {
        // Построение шагов в соседних клеточках
        int xstart = xQueue.get(0);
        int ystart = yQueue.get(0);
        removeQueue(); // удаление 1 элемента из очереди

        if (map[ystart - 1][xstart] == 0) { // Проверка клеточки выше
            map[ystart - 1][xstart] = map[ystart][xstart] + 1;
            addQueue(xstart, ystart - 1); // добавление элемента в конец очереди
        }
        if (map[ystart][xstart + 1] == 0) { // Проверка клеточки справа
            map[ystart][xstart + 1] = map[ystart][xstart] + 1;
            addQueue(xstart + 1, ystart); // добавление элемента в конец очереди
        }
        if (map[ystart + 1][xstart] == 0) { // Проверка клеточки ниже
            map[ystart + 1][xstart] = map[ystart][xstart] + 1;
            addQueue(xstart, ystart + 1); // добавление элемента в конец очереди
        }
        if (map[ystart][xstart - 1] == 0) { // Проверка клеточки слева
            map[ystart][xstart - 1] = map[ystart][xstart] + 1;
            addQueue(xstart - 1, ystart); // добавление элемента в конец очереди
        }

    }

    private static void addQueue(int x, int y) {
        // Добавление координат в конец очереди
        xQueue.add(x);
        yQueue.add(y);
    }

    private static void removeQueue() {
        // Удаление первого элемента из очереди
        xQueue.remove(0);
        yQueue.remove(0);
    }

    public static int[] Points(Scanner scan) {
        // Получение координат точек
        System.out.print("Координата 'x': ");
        int xCoord = scan.nextInt();
        System.out.print("Координата 'y': ");
        int yCoord = scan.nextInt();
        int[] Coord = { yCoord, xCoord };
        return Coord;
    }

    public static int[][] myMap() {
        // Карта с препятствиями
        int[][] mapList = new int[][] {
                { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                { -1, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, -1 },
                { -1, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, -1, 0, 0, 0, 0, -1 },
                { -1, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, 0, 0, -1, -1, -1, -1, 0, 0, -1 },
                { -1, 0, 0, -1, 0, 0, 0, -1, -1, -1, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1 },
                { -1, 0, -1, -1, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, -1, 0, 0, -1 },
                { -1, 0, 0, 0, 0, -1, 0, 0, 0, -1, -1, 0, -1, -1, 0, -1, 0, 0, 0, -1 },
                { -1, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1 },
                { -1, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, -1, 0, 0, -1, 0, 0, 0, 0, -1 },
                { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }
        };
        return mapList;
    }

    public static void printMap(int[][] m) {
        // Отрисовка карты в консоли
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] == -1) {
                    System.out.print("[=]"); // Препятствие и рамка
                } else if (m[i][j] == -11) {
                    System.out.print("-x-"); // Отметка пути
                } else if (inPoint != null && i == inPoint[0] && j == inPoint[1]) {
                    System.out.print("-S-"); // Точка входа
                } else if (inPoint != null && i == outPoint[0] && j == outPoint[1]) {
                    System.out.print("-F-"); // Точка выхода
                } else {
                    System.out.print(" - ");
                    // Печать шагов вместо " - "
                    // if (m[i][j] >= 0 && m[i][j] < 10) {
                    // System.out.print("-" + m[i][j] + "-");
                    // } else {
                    // System.out.print("-" + m[i][j]);
                    // }
                }
            }
            System.out.println();
        }
    }
}
