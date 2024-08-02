package xmlStrategy;

import student.Student;
import org.xml.sax.SAXException;
import xml.DOMParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class DOMStrategy implements XMLStrategy{
    @Override
    public List<Student> parse(String path) throws IOException, ParserConfigurationException, SAXException {
        return DOMParser.parseStudentsFromXML(path);
    }
}
