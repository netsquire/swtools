package reactor.rdf4j;

import org.apache.jena.fuseki.main.FusekiServer;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.system.Txn;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.tdb2.DatabaseMgr;
import org.apache.jena.tdb2.TDB2Factory;

public class TdbAdapter {

    public static void main(String[] args) {
        DatasetGraph graph = DatabaseMgr.createDatasetGraph();
        String directory = "rdfDbs/Dataset1";
//        Dataset dataset = TDBFactory.createDataset(directory);
        String assemblerFile = "Store/tdb-assembler.ttl";
        Dataset dataset = TDBFactory.assembleDataset(assemblerFile);
        dataset.begin(ReadWrite.READ) ;
        // Get model inside the transaction
        Model model = dataset.getDefaultModel() ;
        dataset.end() ;

        model.listSubjects();

        dataset.begin(ReadWrite.READ);
        dataset.end();
    }

    public static void FusekiServerTdb2() {
        Dataset ds = TDB2Factory.createDataset() ;
        Txn.executeWrite(ds, ()-> RDFDataMgr.read(ds, "SomeData.ttl")) ;
        Txn.executeRead(ds, ()-> RDFDataMgr.write(System.out, ds, Lang.TRIG)) ;

        FusekiServer server = FusekiServer.create()
                .add("/dataset", ds)
                .build() ;
        server.start() ;
    }
}