package major.unionfind;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 并查集实现，使用hash表实现
 */
public class T01_UnionFind01<V> {

    public static class Node<V> {
        V value;

        public Node(V value) {
            this.value = value;
        }
    }

    private final Map<V, Node<V>> nodes;
    private final Map<Node<V>, Node<V>> parents;
    private final Map<Node<V>, Integer> size;

    public T01_UnionFind01(List<V> list) {
        nodes = new HashMap<>(list.size());
        parents = new HashMap<>(list.size());
        size = new HashMap<>(list.size());
        for (V v : list) {
            Node<V> node = new Node<>(v);
            nodes.put(v, node);
            parents.put(node, node);
            size.put(node, 1);
        }
    }

    private Node<V> findParent(Node<V> v) {
        Stack<Node<V>> path = new Stack<>();
        while (v != parents.get(v)) {
            path.push(v);
            v = parents.get(v);
        }
        while (!path.isEmpty()) {
            parents.put(path.pop(), v);
        }
        return v;
    }

    boolean isSameSet(V x, V y) {
        return findParent(nodes.get(x)) == findParent(nodes.get(y));
    }

    void union(V x, V y) {
        Node<V> parent1 = findParent(nodes.get(x));
        Node<V> parent2 = findParent(nodes.get(y));
        if (parent1 != parent2) {
            Integer size1 = size.get(parent1);
            Integer size2 = size.get(parent2);
            Node<V> big = size1 >= size2 ? parent1 : parent2;
            Node<V> small = big == parent1 ? parent2 : parent1;
            parents.put(small, big);
            size.put(big, size1 + size2);
            size.remove(small);
        }
    }

    public int sets() {
        return size.size();
    }
}
