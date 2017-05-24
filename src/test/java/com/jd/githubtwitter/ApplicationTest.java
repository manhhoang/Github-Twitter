package com.jd.githubtwitter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(TwitterManager.class)
public class ApplicationTest {

    Application app;

    @Before
    public void setupMock() {
        app = new Application();
    }

    @Test
    public void testBuildGitTweet() throws Exception {
        String name = "Reactive";
        Application app = spy(Application.class);
        PowerMockito.mockStatic(TwitterManager.class);
        TwitterManager twitterManager = mock(TwitterManager.class);
        when(TwitterManager.getInstance()).thenReturn(twitterManager);
        GitTweet gitTweet = new GitTweet();
        when(twitterManager.getTwitter(name)).thenReturn(gitTweet);
        GitProject gitProject = new GitProject();
        List<Item> items = new ArrayList<>();
        Item item = new Item();
        item.setFullName(name);
        items.add(item);
        gitProject.setItems(items);
        String json = app.buildGitTweet(gitProject);
        assert (json != null);
    }
}
