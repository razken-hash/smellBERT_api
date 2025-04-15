package api.smell.bert.services;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class RepositoryService {
    public String cloneRepository(String url) {

        if (url.contains("git@github.com:")) {
            url = url.replace("git@github.com:", "https://github.com/");
        }

        if (url.contains("git@gitlab.com:")) {
            url = url.replace("git@gitlab.com:", "https://gitlab.com/");
        }

        String repoId = UUID.randomUUID().toString();

        File cloneDirectoryPath = new File(System.getProperty("user.dir") + "/temp-repos/" + repoId);

        try {
            Files.createDirectories(cloneDirectoryPath.toPath());
        } catch (IOException e) {
            System.out.println("IOException");
        }
        try {
            Git.cloneRepository()
                    .setURI(url)
                    .setDirectory(Paths.get(cloneDirectoryPath.toString()).toFile())
                    .call();
        } catch (GitAPIException e) {
            throw new RuntimeException(e);
        }
        return cloneDirectoryPath.toString();
    }
}
