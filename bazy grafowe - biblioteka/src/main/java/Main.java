import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.search.SearchHit;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        ClientConfiguration clientConfiguration =
                ClientConfiguration.builder().connectedTo("localhost:9200").build();
        RestHighLevelClient client = RestClients.create(clientConfiguration).rest();

        while(true){

            System.out.println("1. Dodaj");
            System.out.println("2. Edytuj");
            System.out.println("3. Pobierz po ID");
            System.out.println("4. Pobierz wszystko");
            System.out.println("5. Usuń");
            Scanner scan = new Scanner(System.in);
            Integer a = scan.nextInt();
            XContentBuilder builder = null;
            switch (a) {
                case 1:
                    System.out.println("1. Ksiazka");
                    System.out.println("2. Autor");
                    Integer b = scan.nextInt();
                    switch (b) {
                        case 1:
                            System.out.println("Tytul:");
                            String tytul = scan.next();
                            System.out.println("Ocena:");
                            Integer ocena = scan.nextInt();
                            Ksiazka ksiazka =  Ksiazka.builder()
                                    .tytul(tytul)
                                    .ocena(ocena)
                                    .build();
                            builder = XContentFactory.jsonBuilder()
                                    .startObject()
                                    .field("tytul", ksiazka.getTytul())
                                    .field("ocena", ksiazka.getOcena())
                                    .endObject();
                            IndexRequest indexRequest = new IndexRequest("ksiazka");
                            indexRequest.source(builder);
                            client.index(indexRequest, RequestOptions.DEFAULT);
                            break;
                        case 2:
                            System.out.println("Imie:");
                            String imie = scan.next();
                            System.out.println("Nazwisko:");
                            String nazwisko = scan.next();
                            Autor autor =  Autor.builder()
                                    .imie(imie)
                                    .nazwisko(nazwisko)
                                    .build();
                            builder = XContentFactory.jsonBuilder()
                                    .startObject()
                                    .field("imie", autor.getImie())
                                    .field("nazwisko", autor.getNazwisko())
                                    .endObject();
                            IndexRequest indexRequest1 = new IndexRequest("autor");
                            indexRequest1.source(builder);
                            client.index(indexRequest1, RequestOptions.DEFAULT);
                            break;
                    }
                    break;
                case 2:
                    System.out.println("1. Ksiazka");
                    System.out.println("2. Autor");
                    Integer c = scan.nextInt();
                    System.out.println("ID:");
                    String id = scan.next();
                    switch (c) {
                        case 1:
                            System.out.println("Tytul:");
                            String tytul = scan.next();
                            System.out.println("Ocena:");
                            Integer ocena = scan.nextInt();
                            Ksiazka ksiazka =  Ksiazka.builder()
                                    .tytul(tytul)
                                    .ocena(ocena)
                                    .build();
                            builder = XContentFactory.jsonBuilder()
                                    .startObject()
                                    .field("tytul", ksiazka.getTytul())
                                    .field("ocena", ksiazka.getOcena())
                                    .endObject();
                            UpdateRequest updateRequest = new UpdateRequest("ksiazka", id);
                            updateRequest.doc(builder);
                            client.update(updateRequest, RequestOptions.DEFAULT);
                            break;
                        case 2:
                            System.out.println("Imie:");
                            String imie = scan.next();
                            System.out.println("Nazwisko:");
                            String nazwisko = scan.next();
                            Autor autor =  Autor.builder()
                                    .imie(imie)
                                    .nazwisko(nazwisko)
                                    .build();
                            builder = XContentFactory.jsonBuilder()
                                    .startObject()
                                    .field("imie", autor.getImie())
                                    .field("nazwisko", autor.getNazwisko())
                                    .endObject();
                            UpdateRequest updateRequest1 = new UpdateRequest("autor", id);
                            updateRequest1.doc(builder);
                            client.update(updateRequest1, RequestOptions.DEFAULT);
                            break;
                    }
                    break;
                case 3:
                    System.out.println("1. Ksiazka");
                    System.out.println("2. Autor");
                    Integer f = scan.nextInt();
                    System.out.println("ID:");
                    String id2 = scan.next();
                    switch (f){
                        case 1:
                            GetRequest getRequest = new GetRequest("ksiazka");
                            getRequest.id(id2);

                            GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
                            System.out.println(getResponse);
                            break;
                        case 2:
                            GetRequest getRequest1 = new GetRequest("autor");
                            getRequest1.id(id2);

                            GetResponse getResponse1 = client.get(getRequest1, RequestOptions.DEFAULT);
                            System.out.println(getResponse1);
                            break;
                    }
                    break;
                case 4:
                    System.out.println("1. Ksiazka");
                    System.out.println("2. Autor");
                    Integer e = scan.nextInt();
                    switch (e){
                        case 1:
                            SearchRequest searchRequest = new SearchRequest("ksiazka");
                            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
                            SearchHit[] searchHits = response.getHits().getHits();
                            Arrays.stream(searchHits)
                                    .map(hit -> {
                                        Ksiazka ksiazka = JSON.parseObject(hit.getSourceAsString(), Ksiazka.class);
                                        ksiazka.setId(hit.getId());
                                        return ksiazka;
                                    }).collect(Collectors.toList())
                                    .forEach(System.out::println);
                            break;
                        case 2:
                            SearchRequest searchRequest1 = new SearchRequest("autor");
                            SearchResponse response1 = client.search(searchRequest1, RequestOptions.DEFAULT);
                            SearchHit[] searchHits1 = response1.getHits().getHits();
                            Arrays.stream(searchHits1)
                                    .map(hit -> {
                                        Autor autor = JSON.parseObject(hit.getSourceAsString(), Autor.class);
                                        autor.setId(hit.getId());
                                        return autor;
                                    }).collect(Collectors.toList())
                                    .forEach(System.out::println);
                            break;
                    }
                    break;
                case 5:
                    System.out.println("1. Ksiazka");
                    System.out.println("2. Autor");
                    Integer d = scan.nextInt();
                    System.out.println("ID:");
                    String id1 = scan.next();
                    switch (d){
                        case 1:
                            DeleteRequest deleteRequest = new DeleteRequest("ksiazka");
                            deleteRequest.id(id1);
                            client.delete(deleteRequest, RequestOptions.DEFAULT);
                            break;
                        case 2:
                            DeleteRequest deleteRequest1 = new DeleteRequest("autor");
                            deleteRequest1.id(id1);
                            client.delete(deleteRequest1, RequestOptions.DEFAULT);
                            break;
                    }
                    break;
            }
        }
    }
}
