import models.Komisariat;
import models.Policjant;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;
import java.net.MalformedURLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws MalformedURLException {

        HttpClient httpClient = new StdHttpClient.Builder()
                .url("http://localhost:5984").
                username("admin").
                password("admin")
                .build();

        CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
        CouchDbConnector connector = dbInstance.createConnector("policeman", true);

        PolicjantRepository policjantRepo = new PolicjantRepository(connector);
        KomisariatRepository komisariatRepository = new KomisariatRepository(connector);

while(true){

    System.out.println("1. Dodaj");
    System.out.println("2. Edytuj");
    System.out.println("3. Pobierz po ID");
    System.out.println("4. Pobierz wszystko");
    System.out.println("5. Usuń");
    Scanner scan = new Scanner(System.in);
    Integer a = scan.nextInt();
    switch(a){
        case 1:
            System.out.println("1. Policjanci");
            System.out.println("2. Komisariaty");
            Integer b = scan.nextInt();
            switch (b){
                case 1:
                    System.out.println("Imie:");
                    String imie = scan.next();
                    System.out.println("Nazwisko:");
                    String nazwisko = scan.next();
                    System.out.println("Stopien:");
                    String stopien = scan.next();
                    Policjant policjant =  Policjant.builder()
                            .imie(imie)
                            .nazwisko(nazwisko)
                            .stopien(stopien)
                            .build();
                    policjantRepo.add(policjant);
                    break;
                case 2:
                    System.out.println("Miejscowosc:");
                    String miasto = scan.next();
                    Komisariat komisariat1 = Komisariat.builder()
                            .miejscowosc(miasto)
                            .build();
                    komisariatRepository.add(komisariat1);
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
                    Policjant policjant = connector.get(Policjant.class, id);
                    System.out.println("Imie:");
                    String imie = scan.next();
                    System.out.println("Nazwisko:");
                    String nazwisko = scan.next();
                    System.out.println("Stopien:");
                    String stopien = scan.next();
                    policjant.setImie(imie);
                    policjant.setNazwisko(nazwisko);
                    policjant.setStopien(stopien);
                    connector.update(policjant);
                    break;
                case 2:
                    Komisariat komisariat1 = connector.get(Komisariat.class, id);
                    System.out.println("Miejscowosc:");
                    String miasto = scan.next();
                    komisariat1.setMiejscowosc(miasto);
                    connector.update(komisariat1);
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
                    System.out.println(policjantRepo.get(ajDi));
                    break;
                case 2:
                    System.out.println(komisariatRepository.get(ajDi));
                    break;
            }
            break;
        case 4:

            System.out.println("1. Policjanci");
            System.out.println("2. Komisariaty");
            Integer e = scan.nextInt();
            switch (e){
                case 1:
                    System.out.println(policjantRepo.getAll());
                    break;
                case 2:
                    System.out.println(komisariatRepository.getAll());
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
                    Policjant policjant = connector.get(Policjant.class, ejczDi);
                    connector.delete(policjant);
                    break;
                case 2:
                    Komisariat komisariat1 = connector.get(Komisariat.class, ejczDi);
                    connector.delete(komisariat1);
                    break;
            }
            break;
    }
}
    }
}
