package xmlStrategy;

import student.Student;
import org.xml.sax.SAXException;
import xml.SAXStudentHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class SAXStrategy implements XMLStrategy{
    @Override
    public List<Student> parse(String path) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        SAXStudentHandler handler = new SAXStudentHandler();
        saxParser.parse(path, handler);
        return handler.getStudents();
    }
}
