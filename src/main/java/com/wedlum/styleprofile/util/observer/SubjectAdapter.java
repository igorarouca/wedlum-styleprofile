package com.wedlum.styleprofile.util.observer;

public class SubjectAdapter<T> implements Subject<T> {

	public void registerObserver(Observer<T> observer) { }

	public void removeObserver(Observer<T> observer) { }

	public void notifyObservers() { }

	public void notifyObservers(T info) { }

}
