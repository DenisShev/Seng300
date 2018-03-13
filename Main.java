package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
 

public class Main {

	// Driver for program
	public static void main(String[] args) throws IOException {
		String fileDirectory = "D:\\eclipse-workspace\\File Reader\\test.java";
		ReadFileToCharArray(fileDirectory);
	}
	
	// Main function
	// Responsible for taking a java file and converting it to a string then to a charArray
	public static char[] ReadFileToCharArray(String filePath) throws IOException {
		StringBuilder fileData = new StringBuilder(1000);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
 
		char[] buf = new char[10];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			System.out.println(numRead);
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}
 
		reader.close();
		
		char[] returnStr = fileData.toString().toCharArray();
		System.out.println(returnStr); 				//Print test
 
		return  fileData.toString().toCharArray();	
	}
	
	
}
