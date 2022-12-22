package task1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Pendidikan {
    private final ArrayList<String> items = new ArrayList<>();

    void addEducation(String name) {
        items.add(name);
    }

    void printEducations() {
        System.out.println("=======================");
        System.out.printf("%-2s%s%3s%n", "=", "Riwayat Pendidikan", "=");
        System.out.println("=======================");
        for (String item : items) {
            System.out.printf("%-2s%-20s%s%n", "=", item, "=");
        }
        System.out.println("=======================");
    }
}

class Person extends Pendidikan {
    private String firstName;
    private String lastName;
    private String domicile;
    private String birthYear;

    public Person() {
        this.firstName = "";
        this.lastName = "";
        this.domicile = "";
        this.birthYear = "";
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    boolean isDone() {
        if (this.firstName.isEmpty() || this.lastName.isEmpty() || this.domicile.isEmpty() || this.birthYear.isEmpty()) {
            System.out.println("You left one or more field(s) empty");
            return false;
        } else return true;
    }

    void print() {
        System.out.println("=======================");
        System.out.printf("%-10s%s%10s%n", "=", "Bio", "=");
        System.out.println("=======================");
        System.out.printf("%-2s%-20s%s%n", "=", firstName, "=");
        System.out.printf("%-2s%-20s%s%n", "=", lastName, "=");
        System.out.printf("%-2s%-20s%s%n", "=", domicile, "=");
        System.out.printf("%-2s%-20s%s%n", "=", birthYear, "=");
        System.out.println("=======================");
        printEducations();
    }

}

class MainApplication {
    static Scanner scanner = new Scanner(System.in);

    private static String input() {
        return scanner.nextLine();
    }

    private static void clearScr() throws IOException, InterruptedException {
        if (System.getProperty("os.name").equals("Linux")) {
            System.out.print("\033\143");
        }
//            else new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    private static void welcomeMenu() {
        System.out.println("=======================");
        System.out.printf("%-3s%s%3s%n", "=", "APLIKASI BIODATA ", "=");
        System.out.println("=======================");
        System.out.printf("%-9s%s%9s%n", "=", "MENU ", "=");
        System.out.println("=======================");
        String[] menus = {"1. Person", "2. Pendidikan", "3. Tampilkan Data", "4. Exit"};
        for (String item : menus) {
            System.out.printf("%-2s%-20s%s%n", "=", item, "=");
        }
        System.out.println("=======================");
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Person person = new Person();
        int input = 0;
        do {
            clearScr();
            welcomeMenu();
            System.out.printf("%s", "Menu: ");
            input = Integer.parseInt(input());
            switch (input) {
                case 1:
                    System.out.printf("%s", "First Name: ");
                    person.setFirstName(input());
                    System.out.printf("%s", "Last Name: ");
                    person.setLastName(input());
                    System.out.printf("%s", "Domicile: ");
                    person.setDomicile(input());
                    System.out.printf("%s", "Birth Year: ");
                    person.setBirthYear(input());
                    System.out.println("Saved!");
                    Thread.sleep(1000);
                    break;
                case 2:
                    System.out.println("Enter your education, press enter twice to finish");
                    boolean done = false;
                    while (!done) {
                        System.out.printf("%s", "Add Education: ");
                        String education = input();
                        if (!education.isBlank())
                            person.addEducation(education);
                        else done = true;
                    }
                    System.out.println("Saved!");
                    Thread.sleep(1000);
                    break;
                case 3:
                    if (person.isDone()) {
                        person.print();
                        input();
                    } else {
                        Thread.sleep(2000);
                    }
            }
        } while (input != 4);
        System.out.println("Thanks for using this app");
    }
}