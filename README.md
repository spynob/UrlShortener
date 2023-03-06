# UrlShortener REST API
## Url shortener service created using the Java Spring Boot framework.

This project serves as an example of a simple Spring boot application. This project was created as an exercice and a coding challenge for an interview. 
It can do the following:

* Create a unique shortened url from a valid original url
* Return the original url when given a previously generated url
* Redirect user to the original url address when the short url is added to the path
* Database survives when app is restarted

Here is a class diagram that shows how the app works:
![image](https://user-images.githubusercontent.com/98675268/223175754-1c4c7b54-3b20-4833-a6c5-0323182eba26.png)

Here is a sequence diagram that portrays a typicall run:
![image](https://user-images.githubusercontent.com/98675268/223180683-705a0143-9ae1-454b-b410-21bd18354415.png)

## How to use it?
Note: intalling MongoDb is necessary to run this app

creating short url:
  Using an api testing tool like postman, make a post request with the valid long url at address http://localhost8080/UrlShortener

getting the long url:
  Using an api testing tool like postman, make a post request with the previously generated short url at address http://localhost8080/UrlShortener
  
redirection:
  append the short url to the address http:localhost8080/

## TODO

Link the HTML page to the java app to make it look nicer
