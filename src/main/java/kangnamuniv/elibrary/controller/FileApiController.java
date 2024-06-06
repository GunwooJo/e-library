package kangnamuniv.elibrary.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;

@RequestMapping("/pdf")
@RestController
public class FileApiController {

    private static final String BASE_DIR = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "uploads" + File.separator + "pdf" + File.separator;
    private static final String DEFAULT_FILE = "Document_Notice_English.pdf";

    @GetMapping("/{name}")
    public ResponseEntity<InputStreamResource> getTermsConditions(@PathVariable("name") String name) throws FileNotFoundException {
        // Decode the URL-encoded file name
        String decodedName = UriUtils.decode(name, StandardCharsets.UTF_8);

        // 디버깅을 위해 파일 경로와 파일 이름을 출력합니다.
        System.out.println("Decoded file name: " + decodedName);
        System.out.println("File path: " + BASE_DIR + decodedName);

        // 파일 경로에서 인코딩된 파일 이름을 사용
        String encodedFilePath = BASE_DIR + decodedName;
        File file = new File(encodedFilePath);
        if (!file.exists() || !file.isFile()) {
            System.out.println("File not found. Using default file.");
            file = new File(BASE_DIR + DEFAULT_FILE);
        }

        HttpHeaders headers = new HttpHeaders();
        String encodedFileName = UriUtils.encode(file.getName(), StandardCharsets.UTF_8);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename*=UTF-8''" + encodedFileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
}
