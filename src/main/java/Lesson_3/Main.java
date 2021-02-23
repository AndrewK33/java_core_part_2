package Lesson_3;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {


        //Задание 1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
        //Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
        //Посчитать сколько раз встречается каждое слово.

        List<String> arrayList = new ArrayList<String>();
        arrayList.add("WordA");
        arrayList.add("WordB");
        arrayList.add("WordA");
        arrayList.add("WordB");
        arrayList.add("WordC");
        arrayList.add("WordD");
        arrayList.add("WordE");
        arrayList.add("WordF");
        arrayList.add("WordG");
        arrayList.add("WordG");
        arrayList.add("WordG");
        arrayList.add("WordH");

        workForArray.printUniqueWords(arrayList);




        //Задание 2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
        //В этот телефонный справочник с помощью метода add() можно добавлять записи.
        //С помощью метода get() искать номер телефона по фамилии.
        //Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев), тогда при запросе такой фамилии должны выводиться все телефоны

        Phonebook phonebook = new Phonebook();

        phonebook.addNote("IVANOV", "+7(999)363-71-71");
        phonebook.addNote("PETROV", "+7(888)888-77-11");
        phonebook.addNote("VASIN", "+3(222)111-88-77");
        phonebook.addNote("VASIN", "+7(333)555-11-22");
        phonebook.addNote("IVANOV", "999-99-8989");

        phonebook.getNote("IVANOV");
        phonebook.getNote("VASIN");



    }
}
