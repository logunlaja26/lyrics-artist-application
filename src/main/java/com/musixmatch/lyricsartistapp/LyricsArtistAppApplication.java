package com.musixmatch.lyricsartistapp;

import com.musixmatch.lyricsartistapp.config.MusicMatchConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(MusicMatchConfig.class)
public class LyricsArtistAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(LyricsArtistAppApplication.class, args);
	}

}
