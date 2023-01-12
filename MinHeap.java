public class MinHeap<E extends Comparable<E>> {

    private DLList<E> myList = new DLList<E>();

    public String toString() {
        String returnString = "";
        int level = 0;
        int nextLine = 0;
        for (int i = 0; i < myList.size(); i++) {
            if (nextLine == i) {
                returnString += myList.get(i) + "\n";
                level++;
                nextLine = nextLine + (int) Math.pow(2, level);
            } else {
                returnString += myList.get(i) + " ";
            }

        }
        return returnString;

    }

    public void add(E data) {
        myList.add(data);
        if (myList.size() != 1) {
            swapUp(myList.size() - 1);
        }
    }

    private void swapUp(int index) {
        if (index == 0) {
            return;
        }
        E data = myList.get(index);
        int parentIndex = (index - 1) / 2;
        E parent = myList.get(parentIndex);
        if (parent.compareTo(data) > 0) {
            myList.set(index, parent);
            myList.set(parentIndex, data);
            swapUp(parentIndex);
        }
    }

    private void swapDown(int index) {
        if(index >= myList.size()){
            return;
        }
        E data = myList.get(index);

        int childIndex1 = (index * 2) + 1;
        int childIndex2 = (index * 2) + 2;

        int smallIndex;
        E smallChild;
        if (childIndex1 >= myList.size()) {
            return;
        }
        if (childIndex2 < myList.size()) {
            // both are valid
            if (myList.get(childIndex1).compareTo(myList.get(childIndex2)) < 0) {
                smallIndex = childIndex1;
                smallChild = myList.get(childIndex1);
            } else {
                smallIndex = childIndex2;
                smallChild = myList.get(childIndex2);
            }
        } else {
            smallIndex = childIndex1;
            smallChild = myList.get(childIndex1);
        }

        if (data.compareTo(smallChild) > 0) {
            myList.set(smallIndex, data);
            myList.set(index, smallChild);
            swapDown(smallIndex);
        }
    }

    public int size() {
        return myList.size();
    }

    private void remove(int i) {
        myList.set(i, myList.get(myList.size()-1));
        myList.remove(myList.size()-1);
        swapDown(i);
    }

    public void remove(E data) {
        for (int i = 0; i < myList.size(); i++) {
            if (myList.get(i).equals(data)) {
                remove(i);
                return;
            }
        }
    }

    public E poll() {
        E store = myList.get(0);
        remove(0);
        return store;

    }

    public E peek(){
        return myList.get(0);
    }
}
