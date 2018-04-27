package writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Writer {
	FileWriter fw;
	Scanner scan = new Scanner(System.in);
	public Writer(String path) {
		try {
			this.fw = new FileWriter(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createNewFile() throws IOException {
		System.out.println("Cual es el nombre del archivo?");
		String nameOfFile = scan.nextLine();
		File file = new File("../parser-recursivo-descendete/" + nameOfFile + ".c");
		file.createNewFile();
		this.fw = new FileWriter(file);
	}
	
	public void append(String content, boolean newLine) throws IOException {
		if(newLine)
			fw.write(content + "\r\n");
		else
			fw.write(content);
	}
	
	public void closeWriter() throws IOException {
		fw.close();
	}
}
