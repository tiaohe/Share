import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Test {

    static abstract class Shape{
        abstract double getPerimeter();
        abstract double getArea();
    }

    static class Rectangle extends Shape {
        private double length;
        private double width;

        public Rectangle(double length, double width) {
            this.length = length;
            this.width = width;
        }

        @Override
        double getPerimeter() {
            return 2 * (length + width);
        }

        @Override
        double getArea() {
            return length * width;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    static class Circle extends Shape {
        private double radius;

        public Circle(double radius) {
            this.radius = radius;
        }

        @Override
        double getPerimeter() {
            return 2 * Math.PI * radius;
        }

        @Override
        double getArea() {
            return Math.PI * Math.pow(radius, 2);
        }
    }Scanner sc  = new Scanner(new InputStream() {
        @Override
        public int read() throws IOException {
            return 0;
        }
    });
    public static void main(String[] args) {

        Shape shape = new Circle(5.0);
        System.out.println(shape.getArea());
        shape = new Rectangle(2,3);
        System.out.println(shape);
    }
}
