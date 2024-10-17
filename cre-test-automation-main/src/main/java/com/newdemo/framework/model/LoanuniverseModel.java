//-----------------------------------com.newdemo.framework.pojo.LoanuniverseModel.java-----------------------------------

package com.newdemo.framework.model;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "offset",
        "limit"
})
@Generated("jsonschema2pojo")
public class LoanuniverseModel {

    @JsonProperty("offset")
    private Integer offset;
    @JsonProperty("limit")
    private String limit;

    @JsonProperty("offset")
    public Integer getOffset() {
        return offset;
    }

    @JsonProperty("offset")
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @JsonProperty("limit")
    public String getLimit() {
        return limit;
    }

    @JsonProperty("limit")
    public void setLimit(String limit) {
        this.limit = limit;
    }

}
