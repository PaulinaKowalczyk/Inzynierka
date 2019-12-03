package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    @Qualifier("helloServicePython")
    private HelloService service;
    String text;
    @RequestMapping("/paulina")
    public List index() {
        return service.get_result(text);
    }


}
