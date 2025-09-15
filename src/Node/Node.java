package Node;

public class Node {
    private String value;
    private Node left;
    private Node right;

    public Node(String root) {
        this.value = root;
    }

    // Getters
    public String getValue() {
        return value;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    // Setters
    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
