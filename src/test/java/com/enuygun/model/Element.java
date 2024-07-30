package com.enuygun.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Element {
    private String key;
    private String value;
    private String type;

    public Element(String key, String value, String type) {
        this.key = key;
        this.value = value;
        this.type = type;
    }


}
