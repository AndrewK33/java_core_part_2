package Lesson_1;

public class Cat implements SportParams {

    int maxHeight;
    int maxLength;
    String nickname;

    public Cat(int maxHeight, int maxLength, String nickname) {
        this.maxHeight = maxHeight;
        this.maxLength = maxLength;
        this.nickname = nickname;
    }

    @Override
    public int getMaxHeight() {
        return maxHeight;
    }

    @Override
    public void jump() {
        System.out.println(getClass().getSimpleName() + " прыгнул");
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
        return getClass().getSimpleName() + " по кличке " + nickname;
    }

}

