package com.study.springbootdockersax.controllers;

import com.study.springbootdockersax.entities.Student;
import com.study.springbootdockersax.utilities.SaxParseXml;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
public class SaxController {

    @RequestMapping("/sax.html")
    public String parseXml() {
        SAXParser parser = null;
        String content = null;
        try {
            ApplicationContext appContext = new ClassPathXmlApplicationContext();
//            Resource resource = appContext.getResource("classpath:student.xml");
//            Resource resource = appContext.getResource("url:https://nateliu.github.io/student.xml");
//            Resource resource = appContext.getResource(
//                    "file:/Users/jinlliu/github/springboot-docker-sax/src/main/resources/student.xml");
            Resource resource = appContext.getResource("file:/tmp/student.xml");

            parser = SAXParserFactory.newInstance().newSAXParser();
            SaxParseXml parseXml = new SaxParseXml();

            //InputStream stream = SaxParseXml.class.getClassLoader().getResourceAsStream("student.xml");
            InputStream stream = resource.getInputStream();

            parser.parse(stream, parseXml);

            List<Student> list = parseXml.getList();
            for (Student student : list) {
                content += ("id:" + student.getId() + "\tgroup:" + student.getGroup() + "\tname:" + student.getName() + "\tsex:" + student.getSex() + "\tage:" + student.getAge() + "\temail:" + student.getEmail() + "\tbirthday:" + student.getBirthday() + "\tmemo:" + student.getMemo());
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
