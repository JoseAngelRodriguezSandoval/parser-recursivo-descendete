package writer;

import java.io.FileWriter;
import java.io.IOException;

public class Writer {
	FileWriter fw;
	public Writer(String path) {
		try {
			this.fw = new FileWriter(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
