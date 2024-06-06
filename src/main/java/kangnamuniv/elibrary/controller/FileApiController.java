package kangnamuniv.elibrary.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RequestMapping("/pdf")
@RestController
public class FileApiController {

    private static final String BASE_DIR = System.getProperty("user.dir") + "\\src\\main\\resources\\uploads\\pdf\\";
    private static final String DEFAULT_FILE = "Document_Notice_English.pdf";

    @GetMapping("/{name}")
    public ResponseEntity<InputStreamResource> getTermsConditions(@PathVariable("name") String name) throws FileNotFoundException {
        File file = new File(BASE_DIR + name);
        if (!file.exists() || !file.isFile()) {
            file = new File(BASE_DIR + DEFAULT_FILE);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("content-disposition", "inline;filename=" + file.getName());
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(resource);
    }

}
