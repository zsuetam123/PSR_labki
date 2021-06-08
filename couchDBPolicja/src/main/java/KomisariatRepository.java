import models.Komisariat;
import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;

public class KomisariatRepository extends CouchDbRepositorySupport<Komisariat> {

    public KomisariatRepository(CouchDbConnector db) {
        super(Komisariat.class, db);
    }

}
