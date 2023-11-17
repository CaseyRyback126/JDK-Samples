package CollectionsExs;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PhoneDirectory {
    public static void main(String[] args) {
        Map<String, String> phoneBook = new HashMap<>();

        // Добавляем записи в телефонный справочник
        phoneBook.put("1234567890", "Иванов");
        phoneBook.put("9876543210", "Петров");
        phoneBook.put("5555555555", "Сидоров");
        phoneBook.put("9999999999", "Андреев");

        // Находим человека с самым маленьким номером телефона
        String minPhoneNumber = Collections.min(phoneBook.keySet());
        String personWithMinPhoneNumber = phoneBook.get(minPhoneNumber);
        System.out.println("Человек с самым маленьким номером телефона: " + personWithMinPhoneNumber);

        // Находим номер телефона человека с именем, наибольшим в алфавитном порядке
        String maxName = Collections.max(phoneBook.values());
        String phoneNumberWithMaxName = null;

        // Ищем номер телефона по имени
        for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
            if (entry.getValue().equals(maxName)) {
                phoneNumberWithMaxName = entry.getKey();
                break;
            }
        }

        System.out.println("Номер телефона человека с самым большим именем в алфавитном порядке: "
                + phoneNumberWithMaxName);
    }

}
