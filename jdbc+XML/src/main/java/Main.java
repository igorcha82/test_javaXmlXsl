import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

   private static int countRecord;

    public static void main(String[] args) throws SQLException, IOException, TransformerException, SAXException, ParserConfigurationException {

        String url = "jdbc:mysql://localhost:3306/test?serverTimezone=Europe/Moscow";
        String user = "root";
        String pass = "root";
        Connection connection = DriverManager.getConnection(url, user, pass);
        Statement statement = connection.createStatement();
        boolean str = statement.execute("Select * FROM TEST");

        setCountRecord();

        if (str) {
            statement.executeUpdate("TRUNCATE TABLE TEST");
        }

        for (int i = 1; i <= getCountRecord(); i++) {
            statement.executeUpdate("INSERT INTO test" + " VALUES (" + i + ");");
        }

        ParsingToXML parsingToXML = new ParsingToXML();
        parsingToXML.setIncomeList("Test", "FIELD");

        statement.close();
        connection.close();

        ParsingToXSLT parsingToXSLT = new ParsingToXSLT();
        parsingToXSLT.parserClass("D://1.xml", "D://2.xml", "D://1.xsl");

        ParsingFile2 parsingFile2 = new ParsingFile2();
        parsingFile2.parsing("D://2.xml");

    }

    private static void setCountRecord() {
        Scanner monitor = new Scanner(System.in);
        System.out.print("Введите количество добавляемых записей в БД: ");
        String monitorInput = monitor.next();
        countRecord = Integer.valueOf(monitorInput.replaceAll("[^0-9]", ""));
    }

    private static Integer getCountRecord() {
        return countRecord;
    }
}

