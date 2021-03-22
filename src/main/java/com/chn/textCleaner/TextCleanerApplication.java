package com.chn.textCleaner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;

@SpringBootApplication
public class TextCleanerApplication {

	public static void main(String[] args) throws FileNotFoundException {
		SpringApplication.run(TextCleanerApplication.class, args);
		new TextCleaner().startCleaningAllFilesInFolder("/Users/attrighosh/Desktop/CHN ONLINE");

	}

}
