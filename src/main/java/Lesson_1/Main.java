package Lesson_1;

public class Main {
    public static void main(String[] args) {

        SportParams[] members = {
                new Human(20, 20, "Vasya"),
                new Human(1, 2, "Maria"),
                new Cat(3, 4, "MegaCat"),
                new Cat(1, 1, "Mashka"),
                new Robot(0, 10, 10),
                new Robot(4,7,12)
        };

        DemensionObstacles[] obstacles = {
                new Treadmill(4),
                new Wall(3)
        };

        for (SportParams member : members) {
            boolean winner = true;
            System.out.println("К прохождению препятствий приступает " + member);
            for (DemensionObstacles obstacle : obstacles) {
                System.out.println(member + " пробует пройти " + obstacle.getClass().getSimpleName());
                if (obstacle.toJump(member.getMaxHeight()) ||
                        obstacle.toRun(member.getMaxLength())) {
                    System.out.println("И у него получается!");
                } else {
                    winner = false;
                    System.out.println("И у него не получается.");
                    break;
                }
            }

            if(winner) {
                System.out.println(member + " прошёл дистанцию!");
            } else {
                System.out.println(member + " проиграл.");
            }
            System.out.println();


        }

    }
}
