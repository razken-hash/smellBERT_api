package api.smell.bert.utils;

import api.smell.bert.models.JavaFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FilesUtils {

    public static List<JavaFile> traverseRepository(String repositoryPath) {
        List<JavaFile> repositoryJavaFiles = new ArrayList<>();

        Path basePath = Paths.get(repositoryPath);
        try {
            Files.walk(basePath)
                    .filter(p -> p.toString().endsWith(".java"))
                    .forEach(filePath -> {
                        try {
                            String code = Files.readString(filePath);
                            String relativePath = basePath.relativize(filePath).toString();
                            String fileName = filePath.getFileName().toString();
                            repositoryJavaFiles.add(new JavaFile(fileName, relativePath, code));
                        } catch (IOException e) {
                            System.out.println(e.toString());
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return repositoryJavaFiles;
    }
}