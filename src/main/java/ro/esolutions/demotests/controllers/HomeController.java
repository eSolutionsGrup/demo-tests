package ro.esolutions.demotests.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {

    @GetMapping
    public String view() {
        return Redirect.to(ClientController.VIEW_PATH);
    }
}
