package com.wedlum.styleprofile.business.model;

public interface Subject<T> {

	public abstract void register(Observer<T> receiver);

}
