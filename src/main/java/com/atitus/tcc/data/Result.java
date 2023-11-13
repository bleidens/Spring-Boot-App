package com.atitus.tcc.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@ToString
public class Result {
    private String _type;
    private int annotation_count;
    private String api_path;
    private String artist_names;
    private String full_title;
    private String header_image_thumbnail_url;
    private String header_image_url;
    private int id;
    private boolean instrumental;
    private int lyrics_owner_id;
    private String lyrics_state;
    private int lyrics_updated_at;
    private String path;
    private int pyongs_count;
    private String relationships_index_url;
    private Object release_date_components;
    private String release_date_for_display;
    private String release_date_with_abbreviated_month_for_display;
    private String song_art_image_thumbnail_url;
    private String song_art_image_url;
    private Object stats;
    private String title;
    private String title_with_featured;
    private int updated_by_human_at;
    private String url;
    private ArrayList<Object> featured_artists;
    private Object primary_artist;
}

