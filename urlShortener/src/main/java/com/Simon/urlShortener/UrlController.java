package com.Simon.urlShortener;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UrlController {

    @PostMapping("create-short")
    public String convertToShort(String)
}
