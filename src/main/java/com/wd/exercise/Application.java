package com.wd.exercise;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twitter.joauth.Normalizer;
import com.twitter.joauth.OAuthParams;
import com.twitter.joauth.Signer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    /**
     * This is the main solution for the application.
     * @throws Exception
     */
    public void solution() throws Exception {
        // Get the list of reactive github projects in the rest API
        GitProject gitProject = getReactiveGithub();
        System.out.println(gitProject);
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public GitProject getReactiveGithub() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.github.com/search/repositories?q=reactive";

        ResponseEntity<GitProject> response = restTemplate.getForEntity(url, GitProject.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return null;
    }

    public GitProject getTwitter(String projectName) throws Exception {

        return null;
    }

    public static void main(String[] args) throws Exception {
        Application app = new Application();
        app.solution();
    }


}
