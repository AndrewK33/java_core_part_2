package Lesson_3;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class workForArray {

    public static void printUniqueWords(List<String> arrayList) {
        Set<String> tempArray = new LinkedHashSet<>(arrayList);
        for (String tempArr : tempArray) {
            int count = 0;
            for (String arr : arrayList) {
                if(tempArr.equals(arr)) count++;
            }
            System.out.println("Объектов " + "\"" + tempArr + "\"" + " повторяющихся в листе: " + count);
        }
        System.out.println();
    }



}
