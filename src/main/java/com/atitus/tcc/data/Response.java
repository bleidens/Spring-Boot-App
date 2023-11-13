package com.atitus.tcc.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@ToString
public class Response {
    private ArrayList<Section> sections;
    private int next_page;
}
