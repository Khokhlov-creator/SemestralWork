package cz.cvut.fel.ts1.storage;

public class NoItemInStorage extends Exception{
    public NoItemInStorage(String s) {
        super("No item in storage");
    }
}
