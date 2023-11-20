package CollectionsExs;

import java.util.ArrayList;
import java.util.List;

public class NamesCollection {
    public static void main(String[] args) {
        List<String> namesList = new ArrayList<>();
        namesList.add("John");
        namesList.add("David");
        namesList.add("John");
        namesList.add("Adam");
        namesList.add("Alice");
        namesList.add("Alice");

        // Определение наименьшего элемента (алфавитный порядок)
        String minName = namesList.stream().min(String::compareTo).orElse(null);
        System.out.println("Наименьший элемент: " + minName);
    }

}
