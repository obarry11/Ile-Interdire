package com.company;

import java.util.ArrayList;

abstract class Observable {

    private ArrayList<Observer> observers;
    public Observable() {
	this.observers = new ArrayList<Observer>();
    }
    public void addObserver(Observer o) {
	observers.add(o);
    }


    public void notifyObservers() {
	for(Observer o : observers)
        {
            o.update();
        }
    }
}
