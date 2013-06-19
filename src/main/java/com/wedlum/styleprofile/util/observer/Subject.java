package com.wedlum.styleprofile.util.observer;


public interface Subject<T> {

	public abstract void registerObserver(Observer<T> observer);

	public abstract void removeObserver(Observer<T> observer);

	public abstract void notifyObservers();

	public abstract void notifyObservers(T info);

}
