import models.Policjant;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;
import java.net.MalformedURLException;

public class Main {

    public static void main(String[] args) throws MalformedURLException {

        HttpClient httpClient = new StdHttpClient.Builder()
                .url("http://localhost:5984").
                username("admin").
                password("admin")
                .build();

        CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
        //CouchDbConnector db = new StdCouchDbConnector("policja", dbInstance);
        CouchDbConnector connector = dbInstance.createConnector("policeman", true);

       // db.createDatabaseIfNotExists();

       // PolicjantRepository repo = new PolicjantRepository(db);

        Policjant policjant = new Policjant();
        policjant.setImie("John Doe");
        connector.create(policjant);


    }


}
