package com.bridgelabz.observer;

public interface IParkingLotSubject {
    public void register(IParkingLotObserver observers);

    public void unRegister(IParkingLotObserver observers);

    public void notifyObservers();
}
