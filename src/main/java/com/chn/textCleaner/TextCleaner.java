package com.chn.textCleaner;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextCleaner {

    private static Pattern vsPattern = Pattern.compile(" *v. *");
    private static Pattern dashPattern = Pattern.compile("-{4,}");
    private static Pattern noSpacePattern = Pattern.compile("(\\S+)");

    public void display()
    {
        System.out.println("Hello World");
    }


    public void startCleaningAllFilesInFolder(String folderPath) throws FileNotFoundException {
         List<File> files = FileHandler.listDirectoryContents(folderPath);

         List<List<String>> filesAsList = new ArrayList<>();
         for (File file : files){
             filesAsList.add(FileHandler.getFileContents(file));
         }
         for( List<String> file : filesAsList )
         {
             file.stream().forEach(line->{
                 if(Pattern.compile("-{4,}").matcher(line).find())
                     System.out.println(line+" - FOUND");
                 else System.out.println(line+" - NOT FOUND");
             });
             //modifyList(file);
         }



    }

    //iterates the arraylist and deletes
    private List<String> modifyList(List<String> fileContent)
    {

        //Iterator<String> fileIterator = fileContent.iterator();

        List<String> extractedLines = new ArrayList<String>();

        boolean reachedDash = false;
        for (int index = 0 ; index<fileContent.size() ; index++){


            //while(fileIterator.hasNext()) {
            String line = fileContent.get(index);
            //Matcher matcher = dashPattern.matcher(line);
            boolean dashFound = dashPattern.matcher(line).find();
            if (reachedDash && !dashFound){
                extractedLines.add(line);
            }

            else if( !reachedDash && dashFound){
                reachedDash = true;
            }

            else if ( reachedDash && dashFound){
                break;
            }

        }
        // for the 2nd matching
        int i;
        for(i=0; i<extractedLines.size(); i++) {
            Matcher matcher = vsPattern.matcher(extractedLines.get(i));
            if (matcher.find()) {

                if (StringUtils.isNumeric(extractedLines.get(i - 1)))
                    extractedLines.set(i - 1, "");
                extractedLines.set(i + 1, "");
                extractedLines.set(i + 1, "");
            }
        }

        // removing spaces
        //List<String> finalExtractedText = new ArrayList<String>();

       /* for(int index=0; index<extractedLines.size(); index++)
        {
            String line = extractedLines.get(index);
            if(! noSpacePattern.matcher(line).find())
            {
                finalExtractedText.add(line);
            }
        }*/
        List<String> finalExtractedText=  extractedLines.stream().filter(line ->StringUtils.trimToEmpty(line)!= "").collect(Collectors.toList());

        finalExtractedText.stream().forEach(line -> System.out.println(line));
        return finalExtractedText;


    }// end of modifyList


}
