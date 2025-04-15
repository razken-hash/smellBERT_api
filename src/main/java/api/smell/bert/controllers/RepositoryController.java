package api.smell.bert.controllers;

import api.smell.bert.models.JavaFile;
import api.smell.bert.services.RepositoryService;
import api.smell.bert.utils.FilesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

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


    @GetMapping("/scan-repository")
    List<JavaFile> scanRepository(@RequestParam String url) {
        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

        String cloneRepositoryEndpoint = UriComponentsBuilder.fromUriString(baseUrl + "/api/v1/clone-repository").queryParam("url", url).toUriString();

        ResponseEntity<List<JavaFile>> response = new RestTemplate().exchange(
                cloneRepositoryEndpoint,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<JavaFile>>() {}
        );

        List<JavaFile> repositoryJavaFiles = response.getBody();

        for (JavaFile javaFile : repositoryJavaFiles) {
            System.out.println(javaFile.toString());
        }

        return repositoryJavaFiles;
    }
}
