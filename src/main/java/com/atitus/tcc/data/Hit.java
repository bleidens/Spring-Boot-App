package com.atitus.tcc.data;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@ToString
public class Hit {
    private ArrayList<Object> highlights;
    private String index;
    private String type;
    private Result result;

}
