package task2;

import java.io.IOException;
import java.util.Scanner;

class MainApplication {
    static Scanner scanner = new Scanner(System.in);

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
        System.out.println("=======================");
        System.out.printf("%-3s%s%3s%n", "=", "APLIKASI GEOMETRI ", "=");
        System.out.println("=======================");
        System.out.printf("%-9s%s%9s%n", "=", "MENU ", "=");
        System.out.println("=======================");
        String[] menus = {
                "1. Persegi",
                "2. Segitiga",
                "3. Lingkaran",
                "4. Kubus",
                "5. Prisma",
                "6. Bola",
                "7. Exit"
        };
        for (String item : menus) {
            System.out.printf("%-2s%-20s%s%n", "=", item, "=");
        }
        System.out.println("=======================");
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Square square = new Square();
        Triangle triangle = new Triangle();
        Circle circle = new Circle();
        int input;
        do {
            clearScr();
            welcomeMenu();
            System.out.printf("%s", "Menu: ");
            input = Integer.parseInt(input());
            switch (input) {
                case 1 -> {
                    System.out.println("Persegi");
                    square.draw();
                    square.properties();
                    System.out.print("Input sisi: ");
                    square.setSide(Integer.parseInt(input()));
                    square.area();
                    square.perimeter();
                    input();
                }
                case 2 -> {
                    System.out.println("Segitiga");
                    triangle.draw();
                    triangle.properties();
                    System.out.print("Input alas: ");
                    triangle.setBase(Integer.parseInt(input()));
                    System.out.print("Input tinggi: ");
                    triangle.setHeight(Integer.parseInt(input()));
                    triangle.area();
                    triangle.perimeter();
                    input();
                }
                case 3 -> {
                    System.out.println("Lingkaran");
                    circle.draw();
                    circle.properties();
                    System.out.print("Input radius: ");
                    circle.setRadius(Integer.parseInt(input()));
                    circle.area();
                    circle.perimeter();
                    input();
                }
                case 4 -> {
                    System.out.println("Kubus");
                    Square.Cube cube = square.new Cube();
                    cube.draw();
                    cube.properties();
                    if (cube.checkValue()) {
                        System.out.println("sisi dari parent: " + cube.getSide()); // experiment
                        System.out.print("Input sisi baru ? (enter to skip): ");
                        String newSide = input();
                        if (!newSide.isBlank()) cube.setSide(Integer.parseInt(newSide));
                    } else {
                        System.out.print("Input sisi: ");
                        cube.setSide(Integer.parseInt(input()));
                    }
                    cube.areaOfSurface();
                    cube.volume();
                    input();
                }
                case 5 -> {
                    System.out.println("Prisma");
                    Prism prism = new Prism();
                    prism.draw();
                    prism.properties();
                    System.out.print("Input panjang: ");
                    prism.setLength(Integer.parseInt(input()));
                    prism.areaOfSurface();
                    prism.volume();
                    input();
                }
                case 6 -> {
                    System.out.println("Bola");
                    Ball ball = new Ball();
                    ball.draw();
                    ball.properties();
                    System.out.print("Input radius: ");
                    ball.setRadius(Integer.parseInt(input()));
                    ball.areaOfSurface();
                    ball.volume();
                    input();
                }
            }
        } while (input != 7);

    }
}