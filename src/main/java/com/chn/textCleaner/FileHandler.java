package com.chn.textCleaner;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.stream.Streams;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;




public class FileHandler {




    public static List<File> listDirectoryContents(String folderPath) throws FileNotFoundException {

        List<File> folderContents = (List<File>) FileUtils.listFiles(new File(folderPath), null, false);
        List<File> allFiles = new ArrayList<File>();
        for (File fileName : folderContents) {
            allFiles.add(fileName);
            System.out.println(fileName);
            /* ArrayList<String> fileContent = new ArrayList<String>();
            Scanner sc = new Scanner(fileName);
            while (sc.hasNextLine())
            {
                fileContent.add(sc.nextLine());
            }
            sc.close();
            System.out.println(fileContent);*/
        }
        return allFiles;
    }

    // returns a list of strings for a file
    public static List<String> getFileContents(File file)
    {
        List <String> lines =new ArrayList<String>();

        try {
            lines = FileUtils.readLines(file, Charset.defaultCharset()).stream().filter( line ->{
                if(StringUtils.trimToEmpty(line)== "")
                    return false;

                return true;
            }).collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }






    //putting the list contents into a file
    public void makeFile(List<String> finalText)
    {
        File file = new File("/Users/attrighosh/IdeaProjects/CHN/cleaned.txt");
        try
        {
            FileUtils.writeLines(file, finalText);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

