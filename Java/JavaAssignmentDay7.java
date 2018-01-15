/**
 *
 * @author Balraj Singh
 */


class SList<T> {
    private Node<T> headNode = new Node<>(null);
    SListIterator<T> iterator() {
        return new SListIterator<>(headNode);
    }

    @Override
    public String toString() {
        if(headNode.next == null) return "SList: []";
        SListIterator<T> it = this.iterator();
        StringBuilder builder = new StringBuilder("SList: [ ");
        while(it.hasNext()) {
            builder.append(it.next().getValue() + (it.hasNext() ? ", " : " "));
        }
        builder.append("]");
        return builder.toString();
    }
}

class SListIterator<T> {
    private Node<T> current;
    SListIterator(Node<T> node) {
        current = node;
    }
    public boolean hasNext() {
        return current.next != null;
    }
    public Node<T> next() {
        current = current.next;
        return current;
    }
    public void insert(T value) {
        current.next = new Node<>(value, current.next);
        current = current.next;
    }
    public void remove() {
        if(current.next != null) {
            current.next = current.next.next;
        }
        else current = null;
    }
}

class Node<T> {
    private T value;
    Node<T> next;
    Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
    }
    Node(T value) {
        this(value, null);
    }
    T getValue() {
        return this.value;
    }
}
public class JavaAssignmentDay7 {
    public static void main(String[] args) {
        SList<Integer> sList = new SList<>();
        SListIterator<Integer> iterator1 = sList.iterator();

        System.out.println("Inserting elements in SList");
        iterator1.insert(5);
        iterator1.insert(4);
        iterator1.insert(2);
        iterator1.insert(1);
        iterator1.insert(6);

        System.out.println(sList);

        SListIterator<Integer> iterator2 = sList.iterator();
        System.out.println("Removing some elements from SList");
        iterator2.remove();
        iterator2.remove();

        System.out.println(sList);
    }
}
