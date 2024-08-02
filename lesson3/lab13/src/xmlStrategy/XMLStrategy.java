package xmlStrategy;

import student.Student;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public interface XMLStrategy {
    List<Student> parse(String path) throws IOException, ParserConfigurationException, SAXException;
}
