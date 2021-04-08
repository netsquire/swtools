package up;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.VCARD;

public class JenaAdapter {

    static String personURI    = "http://somewhere/JohnSmith";
    static String fullName     = "John Smith";

    public static void main(String[] args) {
        Model model = ModelFactory.createDefaultModel();
        Resource johnSmith = model.createResource(personURI);
        johnSmith.addProperty(VCARD.FN, fullName);

        System.out.println(johnSmith.asResource());
    }

}