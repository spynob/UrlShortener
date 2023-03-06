package com.Simon.urlShortener;

import com.Simon.urlShortener.database.UrlRepository;
import javafx.application.Application;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest()
class UrlShortenerApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private UrlRepository repository;

	@Test
	void contextLoads() {
	}

	@Test
	void redirectionValid(){

	}

}
