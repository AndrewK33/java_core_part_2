package Lesson_3;

import java.util.*;

public class Phonebook {


    Map <String, String> phonebook = new HashMap<>();


    public void addNote (String secondName, String number) {
        phonebook.put(secondName, number);
    }





    public void getNote(String secondName){
        if(phonebook.containsValue(secondName)) {
            System.out.println(phonebook.get(secondName));
        } else {
            System.out.println("Указанной фамилии нет в списке");
        }
    }

}
