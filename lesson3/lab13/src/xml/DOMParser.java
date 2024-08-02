package xml;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.util.*;

import student.Student;

public class DOMParser {
    public static List<Student> parseStudentsFromXML(String path) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(path);

        NodeList nodeList = doc.getElementsByTagName("student");
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Student student = new Student();

            Element studentElement = (Element) nodeList.item(i);

            student.setId(Integer.parseInt(studentElement.getElementsByTagName("id").item(0).getTextContent()));
            student.setName(studentElement.getElementsByTagName("name").item(0).getTextContent());
            student.setSurname(studentElement.getElementsByTagName("surname").item(0).getTextContent());

            student.setMarks(new HashMap<>());
            NodeList marks = studentElement.getElementsByTagName("mark");
            for (int j = 0; j < marks.getLength(); j++) {
                StringTokenizer currentMark = new StringTokenizer(marks.item(j).getTextContent(), "- ", false);
                student.getMarks().put(currentMark.nextToken(), Integer.parseInt(currentMark.nextToken()));
            }
            students.add(student);
        }

        return students;
    }

    public static void saveStudents(List<Student> students, String path) throws ParserConfigurationException, TransformerException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.newDocument();

        Element rootElement = doc.createElement("students");
        doc.appendChild(rootElement);

        for (Student student : students) {
            rootElement.appendChild(getStudentElement(doc, student.getName(), student.getSurname(), student.getId(), student.getMarks()));
        }

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(path));
    }

    private static Element getStudentElement(Document doc, String name, String surname, Integer id, Map<String, Integer> marks) {
        Element studentElement = doc.createElement("student");
        Element nameElement = doc.createElement("name");
        nameElement.appendChild(doc.createTextNode(name));
        studentElement.appendChild(nameElement);

        Element surnameElement = doc.createElement("surname");
        surnameElement.appendChild(doc.createTextNode(surname));
        studentElement.appendChild(surnameElement);

        Element idElement = doc.createElement("id");
        idElement.appendChild(doc.createTextNode(Integer.toString(id)));
        studentElement.appendChild(idElement);

        Element marksElement = doc.createElement("marks");
        marks.forEach((k, v) -> {
            Element markElement = doc.createElement("mark");
            markElement.appendChild(doc.createTextNode(k+"-"+v));
            marksElement.appendChild(markElement);
        });
        studentElement.appendChild(marksElement);

        return studentElement;
    }
}
