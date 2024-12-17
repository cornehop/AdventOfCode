package helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHelpers {
    public Scanner getResourceFile(final String fileName) throws FileNotFoundException {
        URL url = this.getClass()
                .getClassLoader()
                .getResource(fileName);

        if(url == null) {
            throw new IllegalArgumentException(fileName + " is not found 1");
        }

        File file = new File(url.getFile());
        return new Scanner(file);
    }

    public List<String> getLinesFromFile(String fileName) throws FileNotFoundException {
        Scanner reader = getResourceFile(fileName);
        List<String> lines = new ArrayList<>();
        while (reader.hasNextLine()) {
            lines.add(reader.nextLine());
        }
        reader.close();
        return lines;
    }
}