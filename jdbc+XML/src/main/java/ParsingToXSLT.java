import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;


public class ParsingToXSLT {

    public void parserClass(String fileIn, String fileOut, String xslFile) {

        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer(new StreamSource(xslFile));
            transformer.transform(
                    new StreamSource(fileIn),
                    new StreamResult(fileOut));
            System.out.println("Обработка завершена");

        } catch(TransformerException e) {
            e.printStackTrace();
        }

    }

}