package Lesson_1;

public class Robot implements SportParams {

    int maxHeight;
    int maxLength;
    int serialNumber;

    public Robot(int maxHeight, int maxLength, int serialNumber) {
        this.maxHeight = maxHeight;
        this.maxLength = maxLength;
        this.serialNumber = serialNumber;
    }

    @Override
    public int getMaxHeight() {
        return maxHeight;
    }

    @Override
    public void jump() {
        System.out.println(getClass().getSimpleName() +" прыгнул");
    }

    @Override
    public int getMaxLength() {
        return maxLength;
    }

    @Override
    public void run() {
        System.out.println(getClass().getSimpleName() + " пробежал");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " с серийным номером " + serialNumber;
    }


}


