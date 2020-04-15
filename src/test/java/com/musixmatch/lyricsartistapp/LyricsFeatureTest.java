package com.musixmatch.lyricsartistapp;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class LyricsFeatureTest {

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(
            WireMockConfiguration.wireMockConfig()
                    .port(9000)
                    .withRootDirectory("src/test/resources"));

    private static final String trackSearchUrl = "/track.search?q_artist=Justin%20Bieber&q_track=Baby&apikey=apikey";
    private static final String trackSearchWithNoTracksUrl = "/track.search?q_artist=Unknown&q_track=Empty&apikey=apikey";
    private static final String trackLyricsUrl = "/track.lyrics.get?track_id=32184842&apikey=apikey";

    private String lyricsBody = "You know you love me\n" +
            "I know you care\n" +
            "Just shout whenever\n" +
            "And I'll be there\n" +
            "******* This Lyrics is NOT for Commercial use *******\n" +
            "(1409618628584)";

    @Before
    public void setUp() {
        stubEverything();

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void getLyrics_returnsLyricsWithStatusOK() throws Exception {
        mockMvc.perform(get("/lyrics")
                .queryParam("artist", "Justin Bieber")
                .queryParam("track", "Baby")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message.body.lyrics.lyrics_body", is(lyricsBody)));
    }

    @Test
    public void getLyrics_returnsErrorMessage() throws Exception {
        mockMvc.perform(get("/lyrics")
                .queryParam("artist", "Unknown")
                .queryParam("track", "Empty")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.message", is("Error")));
    }

    private static void stubEverything() {
        stubFor(WireMock.get(urlEqualTo(trackSearchUrl))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withStatus(200)
                        .withBodyFile("trackSearch.json")));

        stubFor(WireMock.get(urlEqualTo(trackSearchWithNoTracksUrl))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withStatus(500)
                        .withBodyFile("trackSearch.json")));

        stubFor(WireMock.get(urlEqualTo(trackLyricsUrl))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withStatus(200)
                        .withBodyFile("trackLyrics.json")));
    }
}
