import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import models.Policjant;
import models.Komisariat;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import java.util.Scanner;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Main {

    public static void main(String[] args) {

        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(), fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoClient mongoClient = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
        MongoDatabase database = mongoClient.getDatabase("policja").withCodecRegistry(pojoCodecRegistry);

        database.getCollection("policjanci").drop();
        database.getCollection("komisariaty").drop();
        database.createCollection("policjanci");
        database.createCollection("komisariaty");
        MongoCollection<Policjant> policjantMap = database.getCollection("policjanci", Policjant.class);
        MongoCollection<Komisariat> komisariatMap = database.getCollection("komisariaty", Komisariat.class);

        while(true){

            System.out.println("1. Dodaj");
            System.out.println("2. Edytuj");
            System.out.println("3. Pobierz po ID");
            System.out.println("4. Pobierz wszystko");
            System.out.println("5. Usuń");
            Scanner scan = new Scanner(System.in);
            Integer a = scan.nextInt();

            switch (a) {
                case 1:
                    System.out.println("1. Policjanci");
                    System.out.println("2. Komisariaty");
                    Integer b = scan.nextInt();
                        switch (b) {
                            case 1:
                                System.out.println("Imie:");
                                String imie = scan.next();
                                System.out.println("Nazwisko:");
                                String nazwisko = scan.next();
                                System.out.println("Stopien:");
                                String stopien = scan.next();
                                Komisariat komisariat = null;
                                Policjant policjant =  Policjant.builder()
                                        .imie(imie)
                                        .nazwisko(nazwisko)
                                        .stopien(stopien)
                                        .komisariat(komisariat)
                                        .build();
                                policjantMap.insertOne(policjant);
                                break;
                            case 2:
                                System.out.println("Miejscowosc:");
                                String miasto = scan.next();
                                Komisariat komisariat1 = Komisariat.builder()
                                        .miejscowosc(miasto)
                                        .build();
                                komisariatMap.insertOne(komisariat1);
                                break;
                        }

                    break;
                case 2:
                    System.out.println("1. Policjanci");
                    System.out.println("2. Komisariaty");
                    Integer c = scan.nextInt();
                    System.out.println("Podaj ID: ");
                    String id = scan.next();
                    switch (c) {
                        case 1:
                            System.out.println("Imie:");
                            String imie = scan.next();
                            System.out.println("Nazwisko:");
                            String nazwisko = scan.next();
                            System.out.println("Stopien:");
                            String stopien = scan.next();
                            Komisariat komisariat = null;
                            Policjant policjant = Policjant.builder()
                                    .imie(imie)
                                    .nazwisko(nazwisko)
                                    .stopien(stopien)
                                    .komisariat(komisariat)
                                    .build();
                            policjantMap.updateOne(eq("_id", new ObjectId(id)), combine(
                                    set("imie", policjant.getImie()),
                                    set("nazwisko", policjant.getNazwisko()),
                                    set("stopien", policjant.getStopien()),
                                    set("komisariat", policjant.getKomisariat())));
                            break;
                        case 2:
                            System.out.println("Miejscowosc:");
                            String miasto = scan.next();
                            Komisariat komisariat1 = Komisariat.builder()
                                    .miejscowosc(miasto)
                                    .build();
                            komisariatMap.updateOne(eq("_id", new ObjectId(id)), combine(
                                    set("miejscowosc", komisariat1.getMiejscowosc())));
                            break;
                    }
                    break;
                case 3:
                    System.out.println("1. Policjanci");
                    System.out.println("2. Komisariaty");
                    Integer d = scan.nextInt();
                    System.out.println("Podaj ID: ");
                    String ajDi = scan.next();
                    switch (d){
                        case 1:
                            Block<Policjant> policjantPrintBlock = System.out::println;
                            policjantMap.find(eq("_id", new ObjectId(ajDi))).forEach(policjantPrintBlock);
                            break;
                        case 2:
                            Block<Komisariat> komisariatPrintBlock = System.out::println;
                            komisariatMap.find(eq("_id", new ObjectId(ajDi))).forEach(komisariatPrintBlock);
                            break;
                    }
                    break;
                case 4:
                    System.out.println("1. Policjanci");
                    System.out.println("2. Komisariaty");
                    Integer e = scan.nextInt();
                    switch (e){
                        case 1:
                            Block<Policjant> policjantPrintBlock = System.out::println;
                            policjantMap.find().forEach(policjantPrintBlock);
                            break;
                        case 2:
                            Block<Komisariat> komisariatPrintBlock = System.out::println;
                            komisariatMap.find().forEach(komisariatPrintBlock);
                            break;
                    }
                    break;
                case 5:
                    System.out.println("1. Policjanci");
                    System.out.println("2. Komisariaty");
                    Integer f = scan.nextInt();
                    System.out.println("Podaj ID: ");
                    String ejczDi = scan.next();
                    switch (f){
                        case 1:
                            policjantMap.deleteOne(eq("_id", new ObjectId(ejczDi)));
                            break;
                        case 2:
                            komisariatMap.deleteOne(eq("_id", new ObjectId(ejczDi)));
                            break;
                    }
                    break;

                    }
                    }
            }

        }

