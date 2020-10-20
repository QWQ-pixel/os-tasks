package com.taskFile;

import java.io.File;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.zip.*;

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
                case 2 -> NO2();
                case 3 -> NO3();
                case 4 -> NO4();
                case 5 -> NO5();
                default -> {

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

        fileCreate(way);
        fileReader(way);
        fileDelete(way);

    }
    public static void NO4() throws IOException {

        System.out.println("\t Task №4 XML file");

        way="/Users/Documents/Hello.xml";

        fileCreate(way);
        fileReader(way);
        fileDelete(way);
    }
    public static void NO5() throws IOException {

        System.out.println("\t Task №5 Zip archive");

        String filename = "/Users/Documents/notes.txt";
        way="/Users/Documents/output.zip";
            ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(way));
            FileInputStream fis= new FileInputStream(filename);
            ZipEntry entry1=new ZipEntry("notes.txt");
            zout.putNextEntry(entry1);

            byte[] buffer = new byte[fis.available()];

            zout.write(buffer);
            System.out.println("File <notes.txt> add in zip archive.");
                                                           
            zout.closeEntry();

           long l=new File(way).length();
           System.out.println("Zip archive size: "+l+" b");

           fileReader(filename);
           fileDelete(way);

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
