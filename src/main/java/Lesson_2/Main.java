package Lesson_2;




public class Main {
    public static void main(String[] args) {
        String[][] array = new String[4][4];

        try {
            try {
                int result = arrayCheck(array);
                System.out.println(result);
            } catch (MyArraySizeException e) {
                System.out.println("Размер массива превышен!");
            }
        }
        catch (MyArrayDataException e) {
            System.out.println("Неправильное значение массива!");
            //System.out.println("Ошибка в ячейке: " + array.i + array.j);
        }

    }


    public static int arrayCheck(String[][] a) throws MyArraySizeException, MyArrayDataException {
        int count = 0;

        if (a.length != 4) {
            throw new MyArraySizeException("Метод arrayCheck работает только с 2-х мерными массивами 4х4");
        } else {
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a[i].length; j++) {
                    try {
                    count = count + Integer.parseInt(a[i][j]);
                    } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                    }
                }
            }
        }
        return count;
    }

}

