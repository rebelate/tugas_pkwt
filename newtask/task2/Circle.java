package task2;

class Circle extends Figure2D {
    private int radius = -1;

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
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

class Ball extends Circle implements Figure3D {
    @Override
    public void draw() {
        System.out.println("""
                   ******
                **        **
                *          *
                **        **
                   ******
                 """);
    }

    @Override
    public void properties() {
        System.out.println("Has a single side");
        System.out.println("Has a single centre point");
        System.out.println("Has no corner point");
    }

    @Override
    public void areaOfSurface() {
        System.out.println("Formula: 4 x π x r^2");
        System.out.println("Area of surface is " + String.format("%.2f", 4 * Math.PI * Math.pow(getRadius(), 2)));
    }

    @Override
    public void volume() {
        System.out.println("Formula: 4/3 x π x r^3");
        System.out.println("Volume is " + String.format("%.2f", (4 / 3 * Math.PI * Math.pow(getRadius(), 3))));
    }
}

