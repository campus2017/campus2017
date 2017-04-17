package FileManager;

/**
 * Created by Administrator on 2017/2/18.
 */
public class MinHeap <T> {

    private Entry<T> root;
    private Entry<T> lastNode;
    private Entry<T> last;
    private int size;

    public MinHeap() {
        this.root = null;
        this.size = 0;
        this.last = root;
        this.lastNode = root;
    }

    public boolean Add(T item) {
        if (this.root == null) {
            Entry<T> nowNode = new Entry<T>(item, null, null);
            this.root = this.last = this.lastNode = nowNode;
            return true;
        }
        if (this.last.right != null) {
            this.last = this.last.next;
        }
        Entry<T> nowNode = new Entry<T>(item, this.last, this.lastNode);

        this.lastNode.next = nowNode;
        this.lastNode = nowNode;

        if (this.last.left == null) {
            this.last.left = nowNode;
        } else {
            this.last.right = nowNode;
        }
        this.SiftUp(nowNode);
        this.size++;
        return true;
    }

    public int size() {
        return this.size;
    }

    public T poll() {
        if (root == null) {
            return null;
        }
        T out = root.value;
        root.value = this.lastNode.value;

        Entry<T> tlast = this.lastNode;
        if (tlast == root) {
            this.last = null;
            this.lastNode = null;
            this.root = null;
            return out;
        }

        this.lastNode = this.lastNode.before;
        this.lastNode.next = null;

        if (tlast == tlast.parent.left) {
            tlast.parent.left = null;
            this.last = this.last.before;
        } else {
            tlast.parent.right = null;
        }

        Entry<T> node = this.last;
        node = root;


        while (node != null) {
            this.SiftDown(node);
            node = node.before;
        }
        this.size--;
        return out;
    }

    public T Top() {
        if (root == null) {
            return null;
        }
        return root.value;
    }

    private void SiftDown(Entry<T> node) {
        if (node == null) {
            return;
        }
        while (node.left != null) {
            Comparable now = (Comparable) node.value;
            Comparable child = null;
            Entry<T> childNode;

            if (node.right != null) {
                Comparable child1 = (Comparable) node.left.value;
                Comparable child2 = (Comparable) node.right.value;

                if (child1.compareTo(child2) < 0) {
                    child = child1;
                    childNode = node.left;
                } else {
                    child = child2;
                    childNode = node.right;
                }

            } else {
                child = (Comparable) node.left.value;
                childNode = node.left;

            }
            if (now.compareTo(child) > 0) {
                node.value = (T) child;
                childNode.value = (T) now;
                node = childNode;
                continue;
            }

            break;
        }
    }

    private void SiftUp(Entry<T> node) {
        if (node == null) {
            return;
        }
        while (node.parent != null) {
            Comparable now = (Comparable) node.value;
            Comparable parent = (Comparable) node.parent.value;
            if (now.compareTo(parent) < 0) {
                node.value = (T) parent;
                node.parent.value = (T) now;
                node = node.parent;
                continue;
            }
            break;
        }
    }

    private final class Entry<T> {
        Entry<T> parent;
        Entry<T> left;
        Entry<T> right;

        Entry<T> next;
        Entry<T> before;

        T value;

        public Entry(T value, Entry<T> parent, Entry<T> before) {
            this.value = value;
            this.parent = parent;
            this.before = before;
        }
    }
}