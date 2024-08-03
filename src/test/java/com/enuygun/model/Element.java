package com.enuygun.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Element {
    private String key;
    private String androidValue;
    private String androidType;
    private String iosValue;
    private String iosType;

    public String getValue(String platform) {
        return platform.equalsIgnoreCase("android") ? androidValue : iosValue;
    }

    public String getType(String platform) {
        return platform.equalsIgnoreCase("android") ? androidType : iosType;
    }

}