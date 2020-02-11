package com.hsn.mall.core.express.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Traces {

    @JsonProperty("AcceptStation")
    private String AcceptStation;
    @JsonProperty("AcceptTime")
    private String AcceptTime;

}