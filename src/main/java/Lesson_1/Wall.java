package Lesson_1;

public class Wall implements DemensionObstacles {

    int height;

    public Wall(int height) {
        this.height = height;
    }


    @Override
    public boolean toRun(int maxLength) {
        return false;
    }

    @Override
    public boolean toJump(int maxHeight) {
        return (maxHeight >= height);
    }
}
