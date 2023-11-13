package com.atitus.tcc.service;

import com.atitus.tcc.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Service
public class LyricsService {

    static FirebaseMessagingService firebaseMessagingService;

    @Autowired
    private LyricsService(FirebaseMessagingService firebase){
        LyricsService.firebaseMessagingService = firebase;
    }

    public static ArrayList<FrontResponse> songSearcher(String songTitle) throws IOException{
        GeniusResponse response = GeniusService.SongSearch(songTitle);

        ArrayList<Hit> hits = response.getResponse().getSections().get(0).getHits();

        ArrayList<FrontResponse> res = new ArrayList<>();
        hits.forEach(hit -> {
            if (res.size() < 3){
                Result datas = hit.getResult();
                FrontResponse fr = new FrontResponse(datas.getId(), datas.getTitle(), datas.getArtist_names());
                res.add(fr);
            }
        });
        return res;
    }

    public static String LyricParcer(String songID) throws IOException, ExecutionException, InterruptedException {
        String response = GeniusService.GetLyric(songID);

        assert response != null;
        String title = getTitle(response);
        String lyric = getReadable(response);

        return firebaseMessagingService.sendNotificationByToken(messageBuilder(title, lyric));
    }

    private static NotificationMessage messageBuilder(String songName, String lyric) throws ExecutionException, InterruptedException {
        NotificationMessage notificationMessage = new NotificationMessage();

        notificationMessage.setRecipientToken(FirestoreService.getToken());
        notificationMessage.setTitle(songName);
        notificationMessage.setBody(lyric);

        return notificationMessage;
    }

    private static String getReadable(String rawLyrics) {
        rawLyrics = rawLyrics.replaceAll("[\\S\\s]*<div class=\\\\\\\\\\\\\"rg_embed_body\\\\\\\\\\\\\">[ (\\\\\\\\n)]*", "");
        rawLyrics = rawLyrics.replaceAll("[ (\\\\\\\\n)]*<\\\\/div>[\\S\\s]*", "");
        rawLyrics = rawLyrics.replaceAll("<[^<>]*>", "");
        rawLyrics = rawLyrics.replaceAll("\\\\\\\\n","\n");
        rawLyrics = rawLyrics.replaceAll("\\\\'", "'");
        rawLyrics = rawLyrics.replaceAll("\\\\\\\\\\\\\"", "\"");
        return rawLyrics;
    }

    public static String getTitle(String entrada) {
        String marcador = "<div class=\\\\\\\"song_title\\\\\\\">";
        int indiceInicio = entrada.indexOf(marcador);

        if (indiceInicio != -1) {
            int indiceFim = entrada.indexOf("<", indiceInicio + marcador.length());
            if (indiceFim != -1) {
                return entrada.substring(indiceInicio + marcador.length(), indiceFim);
            }
        }
        return "null_title";
    }

}
