package by.academy.it;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;

public class ServletLogger {

    private final String root = System.getenv("CATALINA_HOME") +
            File.separator + "webapps" +
            File.separator + "task2-12";

    private String initLogger(String nameLogger, String nameDirLogFiles) {
        File fileDir = new File(root + File.separator + nameDirLogFiles);
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
        String pathFile = root + File.separator + nameDirLogFiles + File.separator + nameLogger;
        return pathFile;
    }

    public void setLogger(String pathFile, String text) {
        try {
            FileWriter fileWriter = new FileWriter(pathFile, true);
            fileWriter.write(text + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getLogger(String nameLogger, String nameDirLogFiles) {

        return initLogger(nameLogger, nameDirLogFiles);
    }

    public int getCounterValue(String pathFile) {
        int lines = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathFile));

            while (reader.readLine() != null) {
                lines++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;

    }

}
