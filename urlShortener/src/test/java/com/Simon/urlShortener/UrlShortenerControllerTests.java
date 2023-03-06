package com.Simon.urlShortener;

import com.Simon.urlShortener.controller.UrlController;
import com.Simon.urlShortener.service.UrlService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static java.nio.file.Paths.get;

@RunWith((SpringRunner.class))
@WebMvcTest(UrlController.class)
public class UrlShortenerControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UrlService service;

    //Redirect method tests
    @Test
    public void redirectionValid(){
    }

    @Test
    public void redirectionInvalid(){}

    // Handler method tests
    @Test
    public void handlerNewValidLongUrl(){}

    @Test
    public void handlerExistingValidLongUrl(){}

    @Test
    public void handlerInvalidLongUrl(){}

    @Test
    public void handlerGeneratedShortUrl(){}
}
