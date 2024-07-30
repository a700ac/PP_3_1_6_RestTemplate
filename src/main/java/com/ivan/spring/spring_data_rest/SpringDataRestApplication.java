package com.ivan.spring.spring_data_rest;

import com.ivan.spring.spring_data_rest.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class SpringDataRestApplication {
    private final String GET_USERS_ENDPOINT_URL = "http://94.198.50.185:7081/api/users";
    private final String CREATE_USERS_ENDPOINT_URL = "http://94.198.50.185:7081/api/users";
    private final String UPDATE_USERS_ENDPOINT_URL = "http://94.198.50.185:7081/api/users";
    private final String DELETE_USERS_ENDPOINT_URL = "http://94.198.50.185:7081/api/users/{3}";
    static List<String> cookies;
    static String result="";

    public static void main(String[] args) {
        SpringApplication.run(SpringDataRestApplication.class, args);
        SpringDataRestApplication springRestClient = new SpringDataRestApplication();
        springRestClient.getUsers();
        springRestClient.createUser(cookies);
        springRestClient.updateUser(cookies);
        springRestClient.deleteUser(cookies,3L);
        System.out.println(result);


    }
    private void getUsers() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> forEntity = template.getForEntity(GET_USERS_ENDPOINT_URL, String.class);
        forEntity.getHeaders().get("Set-Cookie").stream().forEach(System.out::println);
        System.out.println("+++++++++++++++++++++++++++");
        System.out.println(forEntity.getBody());
        cookies = forEntity.getHeaders().get("Set-Cookie");

    }

    public void createUser(List<String> cookies) {
        RestTemplate template = new RestTemplate();
        User user = new User(3L, (byte)33, "Brown","James");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", cookies.stream().collect(Collectors.joining(";")));

        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = template.exchange(CREATE_USERS_ENDPOINT_URL,
                HttpMethod.POST, entity, String.class);

        // Выводим результат в консоль
        System.out.println("====================================");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response: " + response.getBody());
        result=result+response.getBody();
    }

    public void updateUser(List<String> cookies) {
        RestTemplate template = new RestTemplate();
        User user = new User(3L, (byte)33, "Shelby","Thomas");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", cookies.stream().collect(Collectors.joining(";")));

        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = template.exchange(UPDATE_USERS_ENDPOINT_URL,
                HttpMethod.PUT, entity, String.class);

        // Выводим результат в консоль
        System.out.println("====================================");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response: " + response.getBody());
        result=result+response.getBody();
    }

    public void deleteUser(List<String> cookies, Long userId) {
        RestTemplate template = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", cookies.stream().collect(Collectors.joining(";")));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url = UriComponentsBuilder.fromHttpUrl(DELETE_USERS_ENDPOINT_URL)
                .buildAndExpand(userId)
                .toUriString();

        ResponseEntity<String> response = template.exchange(url, HttpMethod.DELETE, entity, String.class);

        // Выводим результат в консоль
        System.out.println("====================================");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response: " + response.getBody());
        result=result+response.getBody();
    }


}
