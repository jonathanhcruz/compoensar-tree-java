package Node;

public class NodeList {
    private Node head;
    private String name;

    public NodeList(Node root,String name) {
        this.head = root;
        this.name = name;
    }

    public Node getHead() {
        return head;
    }

    public String getName() {
        return name;
    }
}
