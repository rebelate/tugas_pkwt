import java.util.ArrayList;

class FavoriteLanguages {
    private final String[] languages;

    public FavoriteLanguages(String[] languages) {
        this.languages = languages;
    }

    void printLanguages() {
        System.out.println("=======================");
        System.out.printf("%-2s%s%3s%n", "=", "Favorite Languages", "=");
        System.out.println("=======================");
        for (String lang : languages) {
            System.out.printf("%-2s%-20s%s%n", "=", lang, "=");
        }
        System.out.println("=======================");
    }
}

class Bio extends FavoriteLanguages {
    private final String firstName;
    private final String lastName;
    private final String birthPlace;
    private final String birthYear;

    public Bio() {
        super(new String[]{"C++/C#", "Rust", "Javascript"});
        this.firstName = "Arsil";
        this.lastName = "Qodryantha";
        this.birthPlace = "Sumenep";
        this.birthYear = "1999";
    }

    public Bio(String firstName, String lastName, String birthPlace, String birthYear, String[] languages) {
        super(languages);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthPlace = birthPlace;
        this.birthYear = birthYear;
    }

    void print() {
        System.out.println("=======================");
        System.out.printf("%-10s%s%10s%n", "=", "Bio", "=");
        System.out.println("=======================");
        System.out.printf("%-2s%-20s%s%n", "=", firstName, "=");
        System.out.printf("%-2s%-20s%s%n", "=", lastName, "=");
        System.out.printf("%-2s%-20s%s%n", "=", birthPlace, "=");
        System.out.printf("%-2s%-20s%s%n", "=", birthYear, "=");
        System.out.println("=======================");
        printLanguages();
    }

}

public class Profile {
    /**
     * <p>args[0]  : First Name</p>
     * <p>args[1]  : Last Name</p>
     * <p>args[2]  : Birth Place</p>
     * <p>args[3]  : Birth Year</p>
     * <p>args[4]  : Language</p>
     * <p>args[5]  : Language</p>
     * more language..
     */
    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                Bio myBio = new Bio();
                myBio.print();
            } else {
                String firstName = null;
                String lastName = null;
                String birthPlace = null;
                String birthYear = null;
                ArrayList<String> languages = new ArrayList<>();
                int i = 0;
                for (String arg : args) {
                    switch (i) {
                        case 0:
                            firstName = arg;
                            break;
                        case 1:
                            lastName = arg;
                            break;
                        case 2:
                            birthPlace = arg;
                            break;
                        case 3:
                            birthYear = arg;
                            break;
                    }
                    if (i <= 3) i++;
                    else languages.add(arg);
                }
                Bio bio = new Bio(firstName, lastName, birthPlace, birthYear, languages.toArray(String[]::new));
                bio.print();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
