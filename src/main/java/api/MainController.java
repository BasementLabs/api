package api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @RequestMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<String>("Hello World!", HttpStatus.OK);
    }
}
