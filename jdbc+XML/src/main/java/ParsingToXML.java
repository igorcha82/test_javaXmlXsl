import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class ParsingToXML {

    String url = "jdbc:mysql://localhost:3306/test?serverTimezone=Europe/Moscow";
    String user = "root";
    String pass = "root";
    Connection connection = DriverManager.getConnection(url, user, pass);
    int i = 1;


    public ParsingToXML() throws SQLException {

    }


    void setIncomeList(String tableName, String columLable) throws SQLException, IOException {
        List<Integer> count = new ArrayList<Integer>();
        Statement statement = connection.createStatement();
     try {
        ResultSet resultSet = statement.executeQuery("SELECT " + columLable +" FROM " + tableName);

        while (resultSet.next()) {
            this.i = resultSet.getInt("FIELD");
            count.add(i);
        }

    } catch (Exception ex) {ex.getMessage();}

       writeToXML(count);

    }

    private void writeToXML(List<Integer> strForWrite) throws IOException {
        File file = new File("D://1.xml");

        if (file.createNewFile()) {
            System.out.println("Файл создан");}
        else
        { System.out.println("Файл пересоздан");
            file.delete();
            file.createNewFile();}

        Document doc = new Document();

        doc.setRootElement(new Element("entries"));


        for (Integer item : strForWrite) {
            Element itemElement = new Element("entry");
            itemElement.addContent(new Element("field").addContent(item.toString()));
            doc.getRootElement().addContent(itemElement);
        }

        XMLOutputter xmlWriter = new XMLOutputter(Format.getPrettyFormat());
        xmlWriter.output(doc, new FileOutputStream("D://1.xml"));

    }

}
