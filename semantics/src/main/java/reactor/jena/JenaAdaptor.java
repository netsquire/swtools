package reactor.jena;

import org.apache.jena.graph.Triple;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.sparql.core.Var;
import org.apache.jena.sparql.expr.E_LessThan;
import org.apache.jena.sparql.expr.Expr;
import org.apache.jena.sparql.expr.ExprVar;
import org.apache.jena.sparql.expr.nodevalue.NodeValueInteger;
import org.apache.jena.sparql.syntax.ElementFilter;
import org.apache.jena.sparql.syntax.ElementGroup;
import org.apache.jena.sparql.syntax.ElementTriplesBlock;

public class JenaAdaptor {

    public static void main(String[] args) {
        try(QueryExecution qe = QueryExecutionFactory.sparqlService(
                "http://example.org/sparql",
                "SELECT * WHERE { ?s a ?type }")) {
            qe.getQuery();
            var res = qe.execJson();
        }

        // ?s ?p ?o .
        Triple pattern = Triple.create(Var.alloc("s"), Var.alloc("p"), Var.alloc("o"));

        // ( ?s < 20 )
        Expr e = new E_LessThan(new ExprVar("s"), new NodeValueInteger(20));

        QuerySolutionMap initialBinding = new QuerySolutionMap();
        RDFNode personResource = null;
        initialBinding.add("name", personResource);
        String query = null;
        Dataset dataset = null;
        var qe = QueryExecutionFactory.create(query, dataset, initialBinding);

        ElementTriplesBlock block = new ElementTriplesBlock(); // Make a BGP
        block.addTriple(pattern);                              // Add our pattern match
        ElementFilter filter = new ElementFilter(e);           // Make a filter matching the expression
        ElementGroup body = new ElementGroup();                // Group our pattern match and filter
        body.addElement(block);
        body.addElement(filter);

        Query q = QueryFactory.make();
        q.setQueryPattern(body);                               // Set the body of the query to our group
        q.setQuerySelectType();                                // Make it a select query
        q.addResultVar("s");
    }
}
