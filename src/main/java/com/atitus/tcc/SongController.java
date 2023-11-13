package com.atitus.tcc;

import com.atitus.tcc.data.FrontResponse;
import com.atitus.tcc.service.LyricsService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Controller
public class SongController {

    @GetMapping("/song/{songTitle}")
    public ResponseEntity<ArrayList<FrontResponse>> searchSong(@PathVariable String songTitle) throws IOException {
        ArrayList<FrontResponse> responses = LyricsService.songSearcher(songTitle);

        return ResponseEntity.ok()
                .header("Access-Control-Allow-Origin", "*")
                .body(responses);
    }

    @PostMapping("/song/{songID}")
    public ResponseEntity<String> sendSong(@PathVariable String songID) throws IOException, ExecutionException, InterruptedException {
        String response = LyricsService.LyricParcer(songID);

        return ResponseEntity.ok()
                .header("Access-Control-Allow-Origin", "*")
                .body(response);
    }

}
