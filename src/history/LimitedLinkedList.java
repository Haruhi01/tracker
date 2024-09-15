package history;

import java.util.LinkedList;

public class LimitedLinkedList<E> extends LinkedList<E> {
    private int maxSize;

    public LimitedLinkedList(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public boolean add(E element) {
        if(contains(element)){
            remove(element);
        } else if (size() >= maxSize) {
            removeFirst();
        }
        return super.add(element);
    }
}
