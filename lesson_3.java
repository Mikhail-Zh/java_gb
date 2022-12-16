public class lesson_3 {

    public void sort(int arr[]) {
        int n = arr.length;

        // Построение максимальной кучи
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Сортировка кучи
        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    void heapify(int arr[], int n, int i) {
        // Поиск наибольшего значения среди корня, левого и правого дочерних значений
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l] > arr[largest])
            largest = l;

        if (r < n && arr[r] > arr[largest])
            largest = r;

        // Поменять местами значение и продолжить если корень не самый большой
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }

    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static void main(String args[]) {
        int arr[] = { 3, 8, 2, 9, 3, 12, 40, 73, 4, 85 };

        lesson_3 l_3 = new lesson_3();
        l_3.sort(arr);

        System.out.println("Отсортированный список:");
        printArray(arr);
    }
}
