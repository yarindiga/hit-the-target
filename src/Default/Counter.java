package Default;

public class Counter {
    private int counter;
    public Counter(int c) {
        this.counter = c;
    }

    public void increase(int number) {
        this.counter += number;
    }
    public void decrease(int number) {
        this.counter -= number;
    }

    public int getValue() {
        return counter;
    }
}