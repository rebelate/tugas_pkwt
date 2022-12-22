package task2;

class Triangle extends Figure2D {
    private int base = -1;

    public int getBase() {
        return base;
    }

    public int getHeight() {
        return height;
    }

    private int height = -1;
    private long computedArea = -1;

    public long getComputedArea() {
        return computedArea;
    }

    public int getComputedPerimeter() {
        return computedPerimeter;
    }

    private int computedPerimeter = -1;

    public void setBase(int base) {
        this.base = base;
    }

    public void setHeight(int height) {
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
        computedArea = Math.round(.5 * (base * height));
        System.out.println("Formula: 1/2 x base x height");
        System.out.println("Area of Triangle is " + computedArea);
    }

    @Override
    void perimeter() {
        computedPerimeter = base * 3;
        System.out.println("Formula: a + b + c");
        System.out.println("Perimeter of Triangle is " + computedPerimeter);
    }
}

class Prism extends Triangle implements Figure3D {
    private int length;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public void draw() {
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
    public void properties() {
        System.out.println("A prism may be a sort of three-dimensional (3D) shape with flat sides");
        System.out.println("Has two ends that are an equivalent shape and size (and appear as if a 2D shape)");
        System.out.println("Has an equivalent cross-section right along with the form from end to end");
    }

    @Override
    public void areaOfSurface() {
        System.out.println("Formula: 2 x base area + perimeter");
        System.out.println("Area of Triangle is " + (2 * getComputedArea()) + getComputedPerimeter());
    }

    @Override
    public void volume() {
        System.out.println("Formula: 1/2 x base x height x length");
        System.out.println("Volume of Triangle is " + Math.round(.5 * (getBase() * getHeight() * getLength())));
    }
}