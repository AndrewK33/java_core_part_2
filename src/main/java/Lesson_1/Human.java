package Lesson_1;

public class Human implements SportParams {

    int maxHeight;
    int maxLength;
    String name;

    public Human(int maxHeight, int maxLength, String name) {
        this.maxHeight = maxHeight;
        this.maxLength = maxLength;
        this.name = name;
    }

    @Override
    public int getMaxHeight() {
        return maxHeight;
    }

    @Override
    public void jump() {
        System.out.println(getClass().getSimpleName() + " run");
    }

    @Override
    public int getMaxLength() {
        return maxLength;
    }

    @Override
    public void run() {
        System.out.println(getClass().getSimpleName() + " jump");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " по имени " + name;
    }

}

