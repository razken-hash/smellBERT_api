package api.smell.bert.controllers;

import api.smell.bert.models.JavaFile;
import api.smell.bert.services.RepositoryService;
import api.smell.bert.utils.FilesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1")
public class RepositoryController {
    final RepositoryService repositoryService;

    RepositoryController(@Autowired RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }
    @GetMapping("/clone-repository")
    List<JavaFile> cloneRepository(@RequestParam String url) {
        String repoDirectory = this.repositoryService.cloneRepository(url);
        return FilesUtils.traverseRepository(repoDirectory);
    }
}
