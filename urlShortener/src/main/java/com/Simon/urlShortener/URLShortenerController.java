package com.Simon.urlShortener;

import org.springframework.web.bind.annotation.*;

@RestController
public class URLShortenerController {

    @RequestMapping("/Hello")
    public String hello() {
        return "Hello";
    }

    @RequestMapping("/hello/{NAME}")
    public @ResponseBody String helloYou(@PathVariable(value = "NAME") String NAME){
        return "Hello " + NAME;
    }
}
