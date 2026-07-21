package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileImporter implements ImportFiles {

    @Override
    public List<String> ImportFile() {

        String filePath = "src/main/java/org/example/files/discount_day.txt";


        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println("Ошибка:" + e.getMessage());
        }
        System.out.println("Всего строк" + lines.size());

        return lines;
    }
}
