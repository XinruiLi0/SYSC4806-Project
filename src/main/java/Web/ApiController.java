package Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ApiController {

    @GetMapping("/Hello")
    public String hello(){
        return "Hello Word!";
    }
}
