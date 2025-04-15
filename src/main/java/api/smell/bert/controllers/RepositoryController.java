package api.smell.bert.controllers;

import api.smell.bert.services.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1")
public class RepositoryController {

    final RepositoryService repositoryService;

    RepositoryController(@Autowired RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }
    @GetMapping("/clone-repository")
    String cloneRepository(@RequestParam String url) {
        String repoDirectory = this.repositoryService.cloneRepository(url);
        String startPoint = repoDirectory + "/src/main/java";
        return url;
    }
}
