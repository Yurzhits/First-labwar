//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
interface ArrayProcessor {
    void process(int[] A, int[] B);
}

class DataEngine {
    private String version;

    public DataEngine(String version) {
        this.version = version;
    }

    public void start() {
        System.out.println("Движок компании версии " + version + " запущен!");
        System.out.println("Ураааааа!");
    }
}

class Employee {
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public void work() {
        System.out.println("Сотрудник " + name + " начал анализ данных...");
    }
}

class Project {
    private String title;

    public Project(String title) {
        this.title = title;
    }

    public void showInfo() {
        System.out.println("Проект: " + title);
    }
}

class ArrayService implements ArrayProcessor {
    @Override
    public void process(int[] A, int[] B) {
        int n = A.length;
        int[] C = new int[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            C[i] = Math.min(4 * A[i], B[i] * B[i]);
            if (C[i] == B[i] * B[i]) count++;
        }

        System.out.print("Массив A: ");
        for (int x : A) System.out.print(x + " ");
        System.out.print("\nМассив B: ");
        for (int x : B) System.out.print(x + " ");
        System.out.print("\nМассив C: ");
        for (int x : C) System.out.print(x + " ");
        System.out.println("\nСовпадений с B^2: " + count);
    }
}

class ITCompany {
    private DataEngine engine;       // композиция
    private Employee employee;       // ассоциация
    private ArrayProcessor service;  // зависимость через интерфейс
    private Project[] projects;      // агрегация

    public ITCompany(String version, Employee employee, ArrayProcessor service, Project[] projects) {
        this.engine = new DataEngine(version); // композиция
        this.employee = employee;              // ассоциация
        this.service = service;                // зависимость
        this.projects = projects;              // агрегация
    }

    public void startWork(int[] A, int[] B) {
        System.out.println("\n=== Запуск системы компании ===");
        engine.start();
        employee.work();
        System.out.println("Список проектов компании:");
        for (Project p : projects) p.showInfo();
        service.process(A, B);
    }
}

public class Main {
    public static void main(String[] args) {
        int[] A = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] B = {2, 3, 4, 5, 6, 7, 8, 9};

        Employee dev = new Employee("Алексей");
        ArrayProcessor processor = new ArrayService();
        Project[] projects = {
                new Project("CRM-система"),
                new Project("Аналитическая платформа")
        };

        ITCompany company = new ITCompany("1.0", dev, processor, projects);
        company.startWork(A, B);

        System.out.println("\nРабочая сессия завершена!");
    }
}
