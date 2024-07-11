package models;


import lombok.Getter;

@Getter
public class EnvData {

    private String protocol;
    private String host;
    private String domain;
    private int wait;


    public String getHost(){
        return protocol + "://" + domain;
    }
}
