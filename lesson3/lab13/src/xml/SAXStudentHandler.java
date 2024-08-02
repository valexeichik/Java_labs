package xml;

import student.Student;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class SAXStudentHandler extends DefaultHandler {
    public static final String STUDENT = "student";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String MARKS = "marks";
    public static final String MARK = "mark";
    private StringBuilder elementValue;
    private List<Student> students;
    private Student currentStudent;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (elementValue == null) elementValue = new StringBuilder();
        elementValue.append(ch, start, length);
    }

    @Override
    public void startDocument() throws SAXException { students = new ArrayList<>(); }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case STUDENT: {
                currentStudent = new Student();
                break;
            }
            case NAME:
            case SURNAME:
            case MARK:
            case ID:{
                elementValue = new StringBuilder();
                break;
            }
            case MARKS: {
                currentStudent.setMarks(new HashMap<>());
                break;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case NAME: {
                currentStudent.setName(elementValue.toString());
                break;
            }
            case SURNAME: {
                currentStudent.setSurname(elementValue.toString());
                break;
            }
            case ID: {
                currentStudent.setId(Integer.parseInt(elementValue.toString()));
                break;
            }
            case MARK:  {
                StringTokenizer currentMark = new StringTokenizer(elementValue.toString(), "- ", false);
                currentStudent.getMarks().put(currentMark.nextToken(), Integer.parseInt(currentMark.nextToken()));
                break;
            }
            case STUDENT:
                students.add(currentStudent);
                break;
        }
    }
    public List<Student> getStudents() {
        return students;
    }
}
