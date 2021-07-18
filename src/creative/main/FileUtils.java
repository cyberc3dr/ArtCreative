package creative.main;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileUtils {
	public String getContent(File file) throws Exception {
		FileReader reader = new FileReader(file);
        Scanner scan = new Scanner(reader);
        int i = 0;
        String result = "";
        while(scan.hasNextLine()) {
        	if(i==0){
        		result = result + scan.nextLine();
        	}else{
        		result = result + "\n" + scan.nextLine();
        	}
            i++;
        }
        reader.close();
        return result;
    }
	public static void setContent(File file, String content) throws Exception {
		FileWriter nFile = new FileWriter(file);
        nFile.write(content);
        nFile.close();
    }
}
