package com.atitus.tcc.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class FrontResponse {
    private int id;
    private String title;
    private String artist;
}