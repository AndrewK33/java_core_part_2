package Lesson_4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        int[] arr = new int [5];
        arr[0] = 4;
        arr[1] = 2;
        arr[2] = 4;
        arr[3] = 6;
        arr[4] = 1;



        FirstInterface c = (t, y) -> {
            for (int i = 0; i < y.length; i++) {
                if (y[i] == t) {
                    return i;
                }
            }
            return -1;
        };
        int x = 2;
        foo(c, x, arr);





        SecondInterface d = (p) -> {
            return new StringBuilder(p).reverse().toString();

        };
        String result = reverseString(d, "Результирующий");
        System.out.println(result);


        List<Integer> integerArrayListist = new ArrayList<>();
        integerArrayListist.add(12);
        integerArrayListist.add(21);
        integerArrayListist.add(111);
        integerArrayListist.add(0);

        ThirdInterface thInt = (w) -> {
            return Collections.max(w);
        };
        System.out.println(researchMax(thInt, integerArrayListist));




        List<Integer> integerArrayList = new ArrayList<>();
        integerArrayListist.add(12);
        integerArrayListist.add(21);
        integerArrayListist.add(111);
        integerArrayListist.add(0);

        FourInterface frInt = (j) -> {
            double sum = 0;
            for (int i = 0; i < j.size(); i++) {
                sum += j.get(i);
            }
            return sum / j.size();
        };
        System.out.println(average(frInt, integerArrayListist));







        List<String> stringList = new ArrayList<>();
        stringList.add("авокадо");
        stringList.add("пертурбация");
        stringList.add("анх");
        stringList.add("акр");
        List<String> voidList = new ArrayList<>();

        FiveInterface fvInt = (q, e) -> {

            for (int i = 0; i < q.size(); i++) {
                if (q.get(i).charAt(0) == 'а'
                        &&
                        q.get(i).length() == 3) {
                        e.add(q.get(i));
                }

            }
            return e;
        };
        System.out.println(aWords(fvInt, stringList, voidList));
        
        
        
        

    }


    public static void foo(FirstInterface c, int a, int[] b) {
        c.searchReturn(a, b);
    }


    public static String reverseString(SecondInterface secInt, String str) {
        String a = secInt.reverseString(str);
        return a;
    }


    public static Integer researchMax (ThirdInterface thInt, List<Integer> integerList) {
        int b = thInt.searchMax(integerList);
        return b;

    }
    
    public static double average (FourInterface frInt, List<Integer> integerList) {
        double z = frInt.average(integerList);
        return z;
    }


    public static List<String> aWords (FiveInterface fvInt, List<String> stringList, List<String> voidList) {
        List<String> t = fvInt.aWordsAndThreeLetters(stringList, voidList);
        return t;
    }
    
}
















