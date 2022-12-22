package task2;

import java.io.IOException;
import java.util.Scanner;

abstract class Figure2D {
    abstract void draw();

    abstract void properties();

    abstract void area();

    abstract void perimeter();


}

abstract class Figure3D {
    abstract void draw();

    abstract void properties();

    abstract void area();

    abstract void perimeter();

}

class Square extends Figure2D {
    private final int side;

    Square(int side) {
        this.side = side;
    }

    @Override
    void draw() {
        System.out.println(""" 
                 _______
                |       |
                |       |
                |_______|
                 """);
    }

    @Override
    void properties() {
        System.out.println("Its sides are equal in length");
        System.out.println("All interior angles are equal and right angles at 90°");
        System.out.println("The sum of all the interior angles is 360°");
    }

    @Override
    void area() {
        System.out.println("Formula: a^2");
        System.out.println("Area of Square is " + Math.round(Math.pow(side, 2)));
    }

    @Override
    void perimeter() {
        System.out.println("Formula: 4 x a");
        System.out.println("Perimeter of Square is " + (4 * side));
    }
}

class Triangle extends Figure2D {
    private final int side;
    private final int height;

    public Triangle(int side, int height) {
        this.side = side;
        this.height = height;
    }

    @Override
    void draw() {
        System.out.println("""
                    _
                  / _ \\
                 / / \\ \\
                / /___\\ \\
                \\/_____\\/
                """);
    }

    @Override
    void properties() {
        System.out.println("A triangle has three sides and three angles");
        System.out.println("The sum of the angles of a triangle is always 180 degrees");
        System.out.println("The exterior angles of a triangle always add up to 360 degrees");
    }

    @Override
    void area() {
        System.out.println("Formula: 1/2 x base x height");
        System.out.println("Area of Triangle is " + Math.round(.5 * (side * height)));
    }

    @Override
    void perimeter() {
        System.out.println("Formula: a + b + c");
        System.out.println("Perimeter of Triangle is " + (side + side + side));
    }
}

class Circle extends Figure2D {
    private final int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    void draw() {
        System.out.println("""
                   ******
                **        **
                *          *
                **        **
                   ******
                 """);
    }

    @Override
    void properties() {
        System.out.println("Angles formed by the same arc on the circumference of the circle is always equal");
        System.out.println("Has a total angle of 180 degrees");
        System.out.println("Has a constant diameter");
    }

    @Override
    void area() {
        System.out.println("Formula: π x r^2");
        System.out.println("Area of Circle is " + String.format("%.2f",Math.PI * Math.pow(radius, 2)));
    }

    @Override
    void perimeter() {
        System.out.println("Formula: 2 x π x r");
        System.out.println("Perimeter of Triangle is " + String.format("%.2f",(2 * Math.PI * radius)));
    }
}

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
        Square square = new Square(2);
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
    }
}