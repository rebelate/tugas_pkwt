package task2;

class Square extends Figure2D {
    private int side = -1;

    public void setSide(int side) {
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

    class Cube extends Square implements Figure3D {

        public int getSide() {
            return side;
        }

        protected boolean checkValue() {
            return side != -1;
        }

        @Override
        public void draw() {
            System.out.println("""
                       .+------+
                     .' |    .'|
                    +---+--+'  |
                    |   |  |   |
                    |  ,+--+---+
                    |.'    | .'
                    +------+'
                    """);

        }

        @Override
        public void properties() {
            System.out.println("It is a three-dimensional, square-shaped figure");
            System.out.println("It has 6 faces, 12 edges, and 8 vertices");
            System.out.println("All faces are in the shape of a square");
        }

        @Override
        public void areaOfSurface() {
            System.out.println("Formula: 6 x a^2");
            System.out.println("Area of surface is " + Math.round(6 * Math.pow(side, 2)));
        }

        @Override
        public void volume() {
            System.out.println("Formula: a^3");
            System.out.println("Volume is " + Math.round(Math.pow(side, 3)));
        }
    }
}