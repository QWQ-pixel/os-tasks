package com.taskFile;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {

    static Scanner in=new Scanner(System.in);
    public static String way;

    public static void main(String[] args) throws IOException {
	int num=1;
	while(num!=0){
	    System.out.println("Enter task number(2-5) or 0 to exit: ");
	    Scanner in=new Scanner(System.in);
	    num=in.nextInt();
            switch (num) {
		    case 2:
			    NO2();
			    break;
		    case 3 : 
			    NO3();
			    break;
		    case 4 :
			    NO4();
			    break;
		    case 5: 
			    NO5();
			    break;
                default : {

                    if(num<2||num>=6)
                    System.out.println("Wrong task number ");

                    if(num==0)
                        System.out.println("Program end ");
                }
            }
        }
    }

    public static void NO2() throws IOException {

        System.out.println("\t Task №2 txt file");

        way="/Users/Documents/Hello.txt";

        fileCreate(way);
        fileReader(way);
        fileDelete(way);
    }

    public static void NO3() throws IOException {
        System.out.println("\t Task №3 JSON file");

        way="/Users/Documents/Hello.json";
	JSONParser parser = new JSONParser();
        JSONArray a = (JSONArray) parser.parse(new FileReader(way));

        jsonRead(way);
        String string;

        JSONObject customer = new JSONObject();

        System.out.println("Add new person: ");
        System.out.println("Write name: ");
        string=in.nextLine();
        customer.put("name", string);


        System.out.println("Write City: ");
        string=in.nextLine();
        customer.put("city", string);

        System.out.println("Write car: ");

        string=in.nextLine();
        customer.put("car",string);


        System.out.println("Write job: ");
        string=in.nextLine();
        customer.put("job",string);
        a.add(customer);

        try {
            FileWriter fileWriter = new FileWriter(way);        
            fileWriter.write(a.toJSONString());
            fileWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonRead(way);
        fileDelete(way);
      
    }
	private static void jsonRead(String way) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray a = (JSONArray) parser.parse(new FileReader(way));
        for (Object o : a)
        {
            JSONObject person = (JSONObject) o;

            String name = (String) person.get("name");
            System.out.println("Name: "+name);

            String city = (String) person.get("city");
            System.out.println("City: "+city);

            String job = (String) person.get("job");
            System.out.println("Job: "+job);

            String car = (String) person.get("car");
            System.out.println("Car: "+car+"");

        }
    }
    public static void NO4() throws IOException {

        System.out.println("\t Task №4 XML file");

        way="/Users/IdeaProjects/taskFile/Hello.xml";

        final File xmlFile = new File(way);
        xmlReader(xmlFile);

        Document doc;

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        doc = db.parse(xmlFile);

        Element root = doc.getDocumentElement();

        System.out.println("The root element is " + root.getNodeName() + ".\n");


        Element staffElement = doc.createElement("staff");

        Node updateText = doc.createTextNode("");
        staffElement.appendChild(updateText);

        Element firstName = doc.createElement("firstname");
        System.out.println("Add firstname: ");
        String str_firstName=in.nextLine();
        Node firstNameNode = doc.createTextNode(str_firstName);
        firstName.appendChild(firstNameNode);

        staffElement.appendChild(firstName);

        Element lastName = doc.createElement("lastname");
        System.out.println("Add lastname: ");
        String str_lastName=in.nextLine();
        Node lastNameNode = doc.createTextNode(str_lastName);
        lastName.appendChild(lastNameNode);

        staffElement.appendChild(lastName);

        Element nickName = doc.createElement("nickname");
        System.out.println("Add nickname: ");
        String str_nickName=in.nextLine();
        Node nickNameNode = doc.createTextNode(str_nickName);
        nickName.appendChild(nickNameNode);

        staffElement.appendChild(nickName);

        Element salary = doc.createElement("salary");
        System.out.println("Add salary: ");
        String str_salary=in.nextLine();
        Node salaryNode = doc.createTextNode(str_salary);
        salary.appendChild(salaryNode);
        staffElement.appendChild(salary);

        root.appendChild(staffElement);

        try{
            String outputURL = way;

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileOutputStream(outputURL));

            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer = transFactory.newTransformer();

            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        xmlReader(xmlFile);
        fileDelete(way);
      
    }
	private static void xmlReader(File file) throws ParserConfigurationException, IOException, SAXException {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);

            NodeList nodeList = doc.getElementsByTagName("staff");
            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);
                System.out.println();
                System.out.println("Current item: " + node.getNodeName());
                if (Node.ELEMENT_NODE == node.getNodeType()) {
                    Element element = (Element) node;
                    System.out.println("Name: " + element.getElementsByTagName("firstname").item(0).getTextContent());
                    System.out.println("Lastname: " + element.getElementsByTagName("lastname").item(0).getTextContent());
                    System.out.println("Nickname: " + element.getElementsByTagName("nickname").item(0).getTextContent());
                    System.out.println("Salary: " + element.getElementsByTagName("salary").item(0).getTextContent());
                }
            }
    }
    public static void NO5() throws IOException {

        System.out.println("\t Task №5 Zip archive");

        String filename = "/Users/Documents/notes.txt";
        way="/Users/Documents/output.zip";
	    
        ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(way));
        File file = new File(filename);

        doZip(file, out);
        System.out.println("File <notes.txt> add in zip archive.");
        out.close();

        zipReader(way);
        long l=new File(way).length();
        System.out.println("Zip archive size: "+l+" b");
        fileDelete(way);

        }
	private static void doZip(File dir, ZipOutputStream out) throws IOException {
            if (dir.isDirectory())
                doZip(dir, out);
            else {
                out.putNextEntry(new ZipEntry(dir.getPath()));
                write(new FileInputStream(dir), out);
            }
    }
	private static void write(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) >= 0)
            out.write(buffer, 0, len);
        out.close();
        in.close();
    }
    public static void fileDelete(String way) throws IOException {

        System.out.println("Want to delete file? yes/no? ");
        String want=in.nextLine();

        if(want.equals("yes")) {
            Files.delete(Path.of(way));
            System.out.println("File deleted ");
        }
        if(want.equals("no"))
            System.out.println("File not deleted");

    }
    public static void fileReader(String way) throws IOException {

        List<String> list = Files.readAllLines(Path.of(way));
        System.out.println("Text in file: ");
        for(String str: list)
            System.out.println(str);

    }
    public static void fileCreate(String way) throws IOException {

        Files.createFile(Path.of(way));

        System.out.println("Write words:");
        Scanner in=new Scanner(System.in);
        String inFile=in.nextLine();
        Files.writeString(Path.of(way),inFile);

        System.out.println("The file has been written");
    }
}
