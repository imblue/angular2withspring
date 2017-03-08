package com.mayobirne.angular2.model.entity;

/**
 * Created by Christian Schuhmacher  on 28.04.2016.
 */
public abstract class AbstractEntity<Key> {

    protected abstract void setId(Key id);
    protected abstract Key getId();

}
