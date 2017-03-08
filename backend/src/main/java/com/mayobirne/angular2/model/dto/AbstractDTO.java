package com.mayobirne.angular2.model.dto;

/**
 * Created by Christian Schuhmacher  on 28.04.2016.
 */
public abstract class AbstractDTO<Key> {

    private Key id;


    public void setId(Key id) {
        this.id = id;
    }

    public Key getId() {
        return id;
    }

}
