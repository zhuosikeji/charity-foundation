package com.charityfoundation.api.headone.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FirstClassList {

    /**ID*/
    @JsonProperty("id")
    private String id;
    /**分类名*/
    @JsonProperty("first_class_name")
    private String firstClassName;

}
