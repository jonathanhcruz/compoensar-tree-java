package Node;

import CustomSystem.Input.SystemGetInput;

import java.util.Objects;

public class NodeActions {

    public static void createTree(Node currentNode ) {
        System.out.println("Current node: " + currentNode.getValue());

        // Preguntar por nodo izquierdo
        if (currentNode.getLeft() == null) {
            SystemGetInput questionLeft = new SystemGetInput("Do you want to add a left child to node " + currentNode.getValue() + "? (y/n)");
            String leftAnswer = questionLeft.getResponse().toLowerCase();

            if (Objects.equals(leftAnswer, "y") || Objects.equals(leftAnswer, "yes")) {
                SystemGetInput questionValueNode = new SystemGetInput("What is the left child value?");
                String nodeValue = questionValueNode.getResponse();
                currentNode.setLeft(new Node(nodeValue));
            }
        }

        // Preguntar por nodo derecho
        if (currentNode.getRight() == null) {
            SystemGetInput questionRight = new SystemGetInput("Do you want to add a right child to node " + currentNode.getValue() + "? (y/n)");
            String rightAnswer = questionRight.getResponse().toLowerCase();

            if (Objects.equals(rightAnswer, "y") || Objects.equals(rightAnswer, "yes")) {
                SystemGetInput questionValueNode = new SystemGetInput("What is the right child value?");
                String nodeValue = questionValueNode.getResponse();
                currentNode.setRight(new Node(nodeValue));
            }
        }

        // Recursivamente construir subárboles
        if (currentNode.getLeft() != null) {
            createTree(currentNode.getLeft());
        }

        if (currentNode.getRight() != null) {
            createTree(currentNode.getRight());
        }
    }

    public static int heightTree (Node root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = heightTree(root.getLeft());
        int rightHeight = heightTree(root.getRight());
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void showTree(Node root) {
        if (root == null) {
            return;
        }
        System.out.println(root.getValue());

        showTree(root.getLeft());
        showTree(root.getRight());
    }

    public static Node deepFirstSearch(Node root, String dfsType, String dfsValue) {
        if (root == null) {
            return null;
        }

        if (Objects.equals(dfsType, "pre")) {
            if (Objects.equals(root.getValue(), dfsValue)) {
                return root;
            }
            Node leftResult = deepFirstSearch(root.getLeft(), dfsType, dfsValue);
            if (leftResult != null) {
                return leftResult;
            }
            return deepFirstSearch(root.getRight(), dfsType, dfsValue);
        } else if (Objects.equals(dfsType, "in")) {
            Node leftResult = deepFirstSearch(root.getLeft(), dfsType, dfsValue);
            if (leftResult != null) {
                return leftResult;
            }
            if (Objects.equals(root.getValue(), dfsValue)) {
                return root;
            }
            return deepFirstSearch(root.getRight(), dfsType, dfsValue);
        } else if (Objects.equals(dfsType, "post")) {
            Node leftResult = deepFirstSearch(root.getLeft(), dfsType, dfsValue);
            if (leftResult != null) {
                return leftResult;
            }
            Node rightResult = deepFirstSearch(root.getRight(), dfsType, dfsValue);
            if (rightResult != null) {
                return rightResult;
            }
            if (Objects.equals(root.getValue(), dfsValue)) {
                return root;
            }
        }

        return null;
    }
}
