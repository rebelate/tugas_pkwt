package task2;

class Triangle extends Figure2D {
    private static int side;
    private static int height;

    public Triangle(int side, int height) {
        Triangle.side = side;
        Triangle.height = height;
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

    class Prism extends Figure3D {

        @Override
        void draw() {
            System.out.println("""
                          /\\.
                         /__\\`.
                        /____\\/`.
                       /______\\/ `.
                      /________\\/ /`.
                     /__________\\/ /
                    /____________\\/
                    """);
        }

        @Override
        void properties() {
            System.out.println("A prism may be a sort of three-dimensional (3D) shape with flat sides");
            System.out.println("Has two ends that are an equivalent shape and size (and appear as if a 2D shape)");
            System.out.println("Has an equivalent cross-section right along with the form from end to end");
        }

        @Override
        void area() {
            System.out.println("Formula: 1/2 x base x height");
            System.out.println("Area of Triangle is " + Math.round(.5 * (side * height)));
        }

        @Override
        void volume() {
            System.out.println("Formula: a + b + c");
            System.out.println("Perimeter of Triangle is " + (side + side + side));
        }
    }
}