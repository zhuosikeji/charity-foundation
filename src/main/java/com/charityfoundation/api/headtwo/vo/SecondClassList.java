package com.charityfoundation.api.headtwo.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SecondClassList {

    /**二级ID*/
    @JsonProperty("id")
    private String secondClassId;
    /**二级名字*/
    @JsonProperty("second_class_name")
    private String secondClassName;
    /**二级序号*/
    @JsonProperty("class_number")
    private String classNumber;

}
