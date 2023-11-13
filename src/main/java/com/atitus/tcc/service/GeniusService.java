package com.atitus.tcc.service;

import com.atitus.tcc.data.GeniusResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

@Service
public class GeniusService {

    public static GeniusResponse SongSearch(String songTitle) throws IOException {
        String queryEncoded = URLEncoder.encode(songTitle, "UTF-8");

        URLConnection connection = new URL("https://genius.com/api/search/song?page=1&q=" + queryEncoded).openConnection();
        Scanner scanner = new Scanner(connection.getInputStream());
        scanner.useDelimiter("\\A");
        String raw = "";
        while (scanner.hasNext()) {
            raw += scanner.next();
        }
        if (raw.equals("")) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(raw, GeniusResponse.class);
    }

    public static String GetLyric(String id) throws IOException {
        URLConnection connection = new URL("https://genius.com/songs/" + id + "/embed.js").openConnection();
        Scanner scanner = new Scanner(connection.getInputStream());
        scanner.useDelimiter("\\A");
        String raw = "";
        while (scanner.hasNext()) {
            raw += scanner.next();
        }
        if (raw.equals("")) {
            return null;
        }
        return raw;
    }
}
