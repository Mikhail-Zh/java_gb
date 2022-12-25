import java.util.HashMap;

class java_lesson_4 {

    public static void main(String[] args) {
        System.out.println(N(2, 25000));
        System.out.println(N(2, 12));
        System.out.println(F(2, 12));
    }

    static long N(int a, int b) {
        HashMap<Integer, Long> map = new HashMap<>();
        int begin = a;
        long count = 1;
        map.put(a++, count);
        while (a <= b) {
            if (a % 2 == 0 && a / 2 >= begin) {
                count = map.get(a - 1) + map.get(a / 2);
                map.put(a, count);
                a++;
            } else if (a % 2 == 0 && a / 2 < begin) {
                map.put(a, count);
                a++;
            } else if (a % 2 != 0) {
                map.put(a, count);
                a++;
            }
        }
        return map.get(b);
    }

    // Разобрано на уроке №4.
    // int заменил на long - проверял на больших числах, что количество маршрутов
    // совпадает
    static long F(int a, int b) {
        // System.out.println(b);
        // new Scanner(System.in).nextLine();
        if (b == a)
            return 1;
        else if (b < a)
            return 0;
        else if (b % 2 != 0)
            return F(a, b - 1);
        else
            return F(a, b - 1) + F(a, b / 2);
    }
}
