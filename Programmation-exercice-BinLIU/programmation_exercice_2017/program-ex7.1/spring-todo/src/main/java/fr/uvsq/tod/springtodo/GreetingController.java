package fr.uvsq.tod.springtodo;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private static final String template1 = "%s speak to %s \'%s\'";;
    
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @RequestMapping(value="/message", method=RequestMethod.POST)
    public Greeting setterMessage1(@RequestBody Message message) {
      return new Greeting(counter.incrementAndGet(), String.format(template1, message.getFrom(), message.getTo(), message.getContent()));
    }

    
    @RequestMapping(value = "/greetings", method = RequestMethod.GET)
    public String greetings(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greetings";
    }
}
