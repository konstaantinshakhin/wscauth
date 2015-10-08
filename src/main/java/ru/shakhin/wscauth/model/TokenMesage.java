package ru.shakhin.wscauth.model;

import java.util.List;

/**
 * Created by kshahin on 10/8/2015.
 */
public class TokenMesage {
    private String type;
    private String sequenceId;
    private List<String> data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(String sequenceId) {
        this.sequenceId = sequenceId;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
