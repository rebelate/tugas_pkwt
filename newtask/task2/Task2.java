package task2;

import java.io.IOException;
import java.util.Scanner;

class MainApplication {
    static Scanner scanner = new Scanner(System.in);

    private static void println(Object object) {
        System.out.println(object);
    }

    private static void printf(String format, Object... object) {
        System.out.printf(format, object);
    }

    private static String input() {
        return scanner.nextLine();
    }

    private static void clearScr() throws IOException, InterruptedException {
        if (System.getProperty("os.name").equals("Linux")) {
            System.out.print("\033\143");
        }
//            else new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    private static void welcomeMenu() {
        println("=======================");
        printf("%-3s%s%3s%n", "=", "APLIKASI BIODATA ", "=");
        println("=======================");
        printf("%-9s%s%9s%n", "=", "MENU ", "=");
        println("=======================");
        String[] menus = {"1. Person", "2. Pendidikan", "3. Tampilkan Data", "4. Exit"};
        for (String item : menus) {
            System.out.printf("%-2s%-20s%s%n", "=", item, "=");
        }
        println("=======================");
    }

    public static void main(String[] args) {
        Square square = new Square(4);
        Triangle triangle = new Triangle(3, 6);
        Circle circle = new Circle(4);
        square.draw();
        square.properties();
        square.area();
        square.perimeter();
        triangle.draw();
        triangle.properties();
        triangle.area();
        triangle.perimeter();
        circle.draw();
        circle.properties();
        circle.area();
        circle.perimeter();
        Square.Cube cube = square.new Cube();
        cube.draw();
        cube.area();
        cube.volume();
        Triangle.Prism prism = triangle.new Prism();
        prism.draw();
    }
}