package com.wedlum.styleprofile.util.observer;


public interface Observer<T> {

	void update(T info);
    void remove(T info);
}
