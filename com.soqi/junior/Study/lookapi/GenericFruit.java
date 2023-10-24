package lookapi;

public class GenericFruit {
    class Fruit {
        @Override
        public String toString() {
            return "fruit";
        }
    }

    class Apple extends Fruit {
        @Override
        public String toString() {
            return "apple";
        }
    }

    class Person {
        @Override
        public String toString() {
            return "Person";
        }
    }

    class GenerateTest<T> {
        public void show_1(T t) {
            System.out.println(t.toString());
        }

        public <E> void show_3(E t) {
            System.out.println(t.toString());
        }

        public <T> void show_2(T t) {
            System.out.println(t.toString());
        }

    }
}
