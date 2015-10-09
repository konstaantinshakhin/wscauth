package ru.shakhin.wscauth.model;

import java.util.List;
import java.util.Map;

/**
 * Created by kshahin on 10/8/2015.
 */
public class TokenMessage {
    private String type;
    private String sequenceId;
    private Map<String,String> data;

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

    public Map<String,String> getData() {
        return data;
    }

    public void setData(Map<String,String> data) {
        this.data = data;
    }

    public  String getString(){
        StringBuilder sdata = new StringBuilder();
        for(Map.Entry<String, String> entry : data.entrySet()) {
            sdata.append(entry.getKey()+" ").append(entry.getValue()+"\n");
            // Json.createObjectBuilder().add(entry.getKey(), entry.getValue());
        }
        sdata.append(type+"\n").append(sequenceId+"\n");
        return sdata.toString();
    }
}
