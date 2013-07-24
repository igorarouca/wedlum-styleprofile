package com.wedlum.styleprofile.util.observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenericSubject<T> implements Subject<T> {

	private boolean changed = false;
	private List<Observer<T>> observers = Collections.synchronizedList(new ArrayList<Observer<T>>());

	public void registerObserver(Observer<T> observer) {
		if (observer == null)
			throw new NullPointerException();

		if (!observers.contains(observer))
			observers.add(observer);
	}

	public void removeObserver(Observer<T> observer) {
		observers.remove(observer);
	}

	public void notifyObservers() {
		this.notifyObservers(null);
	}

	public void notifyObservers(T info) {
		if (!changed) return;

		clearChanged();
		for (Observer<T> observer : observers)
			observer.update(info);
	}

    public void notifyObserversRemove(T info) {
        for (Observer<T> observer : observers)
            observer.remove(info);
    }


	public void setChanged() {
        changed = true;
    }

	public void clearChanged() {
		changed = false;
	}

    public boolean hasChanged() {
        return changed;
    }

    public int countObservers() {
        return observers.size();
    }
}
