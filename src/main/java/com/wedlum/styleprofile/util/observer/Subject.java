package com.wedlum.styleprofile.util.observer;


public interface Subject<T> {

	void registerObserver(Observer<T> observer);

	void removeObserver(Observer<T> observer);

	void notifyObservers();

	void notifyObservers(T info);

}
