package com.wedlum.styleprofile.business.model;


public interface Observer<T> {

	void notify(T arg);

}
