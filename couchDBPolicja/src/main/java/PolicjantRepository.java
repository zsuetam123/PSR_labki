import models.Policjant;
import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;

public class PolicjantRepository extends CouchDbRepositorySupport<Policjant> {

    public PolicjantRepository(CouchDbConnector db) {
        super(Policjant.class, db);
    }

}
