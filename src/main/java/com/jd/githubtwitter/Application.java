package com.jd.githubtwitter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class Application {

    private static final String GIT_URL = "https://api.github.com/search/repositories?q=";
    private static final int NUMBER_OF_TWEET = 2;
    private static final String SEARCH_NAME = "reactive";

    public void solution() throws Exception {
        // Get the list of reactive github projects in the rest API
        GitProject gitProject = getGitProjects(SEARCH_NAME);

        System.out.println(buildGitTweet(gitProject));
    }

    /**
     *
     * @param gitProject
     * @return
     * @throws Exception
     */
    public String buildGitTweet(GitProject gitProject) throws Exception {
        List<GitTweet> gitTweets = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_TWEET; i++) {
            if(i < gitProject.getItems().size()) {
                Item item = gitProject.getItems().get(i);
                gitTweets.add(TwitterManager.getInstance().getTwitter(item.getFullName()));
            }
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(gitTweets);
    }

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    public GitProject getGitProjects(String name) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String url = GIT_URL + name;
        ResponseEntity<GitProject> response = restTemplate.getForEntity(url, GitProject.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        Application app = new Application();
        app.solution();
    }
}
