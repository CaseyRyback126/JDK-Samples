package CalculatorEtc.Pair;

public class Pair<T, U> {
    private final T first;
    private final U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }

    public static void main(String[] args) {
        Pair<String, Integer> pair = new Pair<>("Hello", 123);
        String firstValue = pair.getFirst();
        Integer secondValue = pair.getSecond();
        System.out.println(firstValue);
        System.out.println(secondValue);
        System.out.println(pair);
    }
}
