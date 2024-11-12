package se.yrgo;

public interface ItemContainer {
    Item get(int index);
    int put(Item i);
    int size();
}
