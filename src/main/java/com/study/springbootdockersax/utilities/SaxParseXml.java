package com.study.springbootdockersax.utilities;

import java.util.ArrayList;
import java.util.List;
import com.study.springbootdockersax.entities.Student;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParseXml extends DefaultHandler{
    private List<Student> list;
    private Student student;
    private String tagName;

    public List<Student> getList() {
        return list;
    }


    public void setList(List<Student> list) {
        this.list = list;
    }


    public Student getStudent() {
        return student;
    }


    public void setStudent(Student student) {
        this.student = student;
    }


    public String getTagName() {
        return tagName;
    }


    public void setTagName(String tagName) {
        this.tagName = tagName;
    }


    @Override
    public void startDocument() throws SAXException {
        list=new ArrayList<Student>();
    }


    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        if(qName.equals("student")){
            student=new Student();
            //获取student节点上的id属性值
            student.setId(Integer.parseInt(attributes.getValue(0)));
            //获取student节点上的group属性值
            student.setGroup(Integer.parseInt(attributes.getValue(1)));
        }
        this.tagName=qName;
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if(qName.equals("student")){
            this.list.add(this.student);
        }
        this.tagName=null;
    }


    @Override
    public void endDocument() throws SAXException {
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        if(this.tagName!=null){
            String date=new String(ch,start,length);
            if(this.tagName.equals("name")){
                this.student.setName(date);
            }
            else if(this.tagName.equals("sex")){
                this.student.setSex(date);
            }
            else if(this.tagName.equals("age")){
                this.student.setAge(Integer.parseInt(date));
            }
            else if(this.tagName.equals("email")){
                this.student.setEmail(date);
            }
            else if(this.tagName.equals("birthday")){
                this.student.setBirthday(date);
            }
            else if(this.tagName.equals("memo")){
                this.student.setMemo(date);
            }
        }
    }
}
