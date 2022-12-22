package task2;

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
        System.out.println("Area of Circle is " + String.format("%.2f", Math.PI * Math.pow(radius, 2)));
    }

    @Override
    void perimeter() {
        System.out.println("Formula: 2 x π x r");
        System.out.println("Perimeter of Circle is " + String.format("%.2f", (2 * Math.PI * radius)));
    }
}
