import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        Config config = HConfig.getConfig();
        HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);
        IMap<UUID, Animal> animalMap = instance.getMap("animal");
        IMap<UUID, Person> personMap = instance.getMap("person");

        while (true) {

            System.out.println("1.Dodaj");
            System.out.println("2.Edytuj");
            System.out.println("3.Znajdź ID");
            System.out.println("4.Znajdź wszystkie");
            System.out.println("5.Usuń");

           Scanner scan = new Scanner(System.in);
           Integer a = scan.nextInt();
                switch (a) {
                    case 1:
                        dodajDoBazy(animalMap, personMap);
                        break;
                    case 2:
                        edytuj(personMap, animalMap);
                        break;
                    case 3:
                        znajdzId(personMap, animalMap);
                        break;
                    case 4:
                        pobierzWszystkie(personMap, animalMap);
                        break;
                    case 5:
                        usun(personMap, animalMap);
                        break;
                }
        }
    }

    private static void pobierzWszystkie(IMap<UUID, Person> persons, IMap<UUID, Animal> animals){

        System.out.println("1.Opiekunowie");
        System.out.println("2.Zwierzęta");

        Scanner scanner = new Scanner(System.in);
        Integer s = scanner.nextInt();
            switch (s) {
                case 1:
                    for (Map.Entry<UUID, Person> e : persons.entrySet()) {
                        System.out.println(e.getKey() + " " + e.getValue());
                    }
                    break;
                case 2:
                    for (Map.Entry<UUID, Animal> e : animals.entrySet()) {
                        System.out.println(e.getKey() + " " + e.getValue());
                    }
                    break;
            }
    }

    private static void znajdzId(IMap<UUID, Person> persons, IMap<UUID, Animal> animals){

        System.out.println("1.Opiekunowie");
        System.out.println("2.Zwierzęta");

        Scanner scanner = new Scanner(System.in);
        Integer s = scanner.nextInt();

            System.out.println("Podaj ID");
            switch (s) {
                case 1:
                    String personId = scanner.next();

                        Person person = persons.get(UUID.fromString(personId));
                        System.out.println(person.toString());

                    break;
                case 2:
                    String animalId = scanner.next();

                        Animal animal = animals.get(UUID.fromString(animalId));
                        System.out.println(animal.toString());

                    break;
            }

    }

    private static void edytuj(IMap<UUID, Person> persons, IMap<UUID, Animal> animals) {

        System.out.println("1.Opiekunowie");
        System.out.println("2.Zwierzęta");
        Scanner scanner = new Scanner(System.in);
        Integer s = scanner.nextInt();
        System.out.println("Podaj Id");
            switch (s) {
                case 1:
                    String personId = scanner.next();
                    System.out.println("Podaj imie");
                    String name = scanner.next();
                    System.out.println("Podaj nazwisko");
                    String surname = scanner.next();
                    System.out.println("Podaj ID gatunku");
                    String animalId = scanner.next();
                    Animal animal1 = animals.get(UUID.fromString(animalId));

                    Person person = Person.builder()
                            .name(name)
                            .surname(surname)
                            .animal(animal1)
                            .build();

                    persons.put(UUID.fromString(personId), person);
                    break;
                case 2:
                    String animallId = scanner.next();
                    System.out.println("Podaj gatunek");
                    String species = scanner.next();
                    System.out.println("Podaj ilość");
                    Integer amount = scanner.nextInt();
                    Animal animal = Animal.builder()
                            .species(species)
                            .amount(amount)
                            .build();
                    animals.put(UUID.fromString(animallId), animal);

                    break;
            }

    }

    private static void usun(IMap<UUID, Person> persons, IMap<UUID, Animal> animals) {

        System.out.println("1.Opiekunowie");
        System.out.println("2.Zwierzęta");
        Scanner scanner = new Scanner(System.in);
        Integer s = scanner.nextInt();
        System.out.println("Podaj Id");
            switch (s) {
                case 1:
                    String personId = scanner.next();

                        persons.remove(UUID.fromString(personId));

                    break;
                case 2:
                    String animalId = scanner.next();

                        animals.remove(UUID.fromString(animalId));

                    break;
            }

    }

    private final static Pattern UUID_REGEX_PATTERN =
            Pattern.compile("^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$");

    private static void dodajDoBazy(IMap<UUID, Animal> animals, IMap<UUID, Person> person) {

        System.out.println("1.Opiekunowie");
        System.out.println("2.Zwierzęta");
        Scanner scanner = new Scanner(System.in);
        Integer s = scanner.nextInt();
        switch (s) {
                case 1:
                    System.out.println("Podaj imie");
                    String name = scanner.next();
                    System.out.println("Podaj nazwisko");
                    String surname = scanner.next();
                    System.out.println("Podaj ID gatunku");
                    String animalId = scanner.next();
                    Animal animal1 = animals.get(UUID.fromString(animalId));

                    Person person1 = Person.builder()
                            .name(name)
                            .surname(surname)
                            .animal(animal1)
                            .build();

                    person.put(UUID.randomUUID(), person1);
                    break;
                case 2:
                    System.out.println("Podaj gatunek");
                    String species = scanner.next();
                    System.out.println("Podaj ilość");
                    Integer amount = scanner.nextInt();
                    Animal animal = Animal.builder()
                            .species(species)
                            .amount(amount)
                            .build();
                    animals.put(UUID.randomUUID(), animal);
                    break;
            }
    }
}