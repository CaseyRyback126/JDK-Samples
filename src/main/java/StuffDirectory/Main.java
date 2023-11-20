package StuffDirectory;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Создаем объект справочника сотрудников
        EmployeeDirectory directory = new EmployeeDirectory();

        // Добавляем несколько сотрудников
        directory.addEmployee(new Employee(1, "1234567890", "Иванов", 5));
        directory.addEmployee(new Employee(2, "0987654321", "Петров", 3));
        directory.addEmployee(new Employee(3, "9876543210", "Сидоров", 7));

        // Поиск сотрудника по стажу
        int experienceToSearch = 5;
        List<Employee> employeesByExperience = directory.searchByExperience(experienceToSearch);
        System.out.println("Сотрудники со стажем " + experienceToSearch + ":");
        for (Employee employee : employeesByExperience) {
            System.out.println("Табельный номер: " + employee.getEmployeeId() +
                    ", Имя: " + employee.getName() +
                    ", Номер телефона: " + employee.getPhoneNumber());
        }

        // Поиск номера телефона по имени
        String nameToSearch = "Петров";
        List<String> phoneNumbers = directory.searchPhoneNumberByName(nameToSearch);
        System.out.println("Номера телефонов сотрудника с именем " + nameToSearch + ":");
        for (String phoneNumber : phoneNumbers) {
            System.out.println(phoneNumber);
        }

        // Поиск сотрудника по табельному номеру
        int employeeIdToSearch = 3;
        Employee employee = directory.searchByEmployeeId(employeeIdToSearch);
        if (employee != null) {
            System.out.println("Сотрудник с табельным номером " + employeeIdToSearch + ":");
            System.out.println("Имя: " + employee.getName() +
                    ", Номер телефона: " + employee.getPhoneNumber() +
                    ", Стаж: " + employee.getExperience());
        } else {
            System.out.println("Сотрудник с табельным номером " + employeeIdToSearch + " не найден.");
        }
    }
}
