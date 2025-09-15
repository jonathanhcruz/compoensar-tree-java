import CustomSystem.Input.SystemGetInput;
import CustomSystem.SystemProgram;
import CustomSystem.menu.MenuItems;
import Node.Node;
import Node.NodeActions;
import Node.NodeList;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        NodeActions nodeActions = new NodeActions();
        final ArrayList<MenuItems> menu = new ArrayList<MenuItems>();
        final ArrayList<NodeList> trees = new ArrayList<NodeList>();

        menu.add(new MenuItems("Create New Tree", true, () -> {
            SystemGetInput questioRootValue = new SystemGetInput("witch is the root Node Value?");
            String rootValue = questioRootValue.getResponse();
            Node root = new Node(rootValue);
            nodeActions.createTree(root);

            SystemGetInput questionTreeName = new SystemGetInput("Enter a name for this tree:");
            String treeName = questionTreeName.getResponse().trim();

            trees.add(new NodeList(root, treeName));
        }));
        menu.add(new MenuItems("Show the trees root list", true, () -> {
            main.printTreeList(trees);
        }));
        menu.add(new MenuItems("Show a selected Tree", true, () -> {
            if (trees.isEmpty()) {
                System.out.println("There are no trees created yet.");
                return;
            }

            main.printTreeList(trees);

           Node treeIndex = main.selectTree(trees, "Select the tree number to show:");
           nodeActions.showTree(treeIndex);
        }));
        menu.add(new MenuItems("Get Tree Height", true, () -> {
            if (trees.isEmpty()) {
                System.out.println("There are no trees created yet.");
                return;
            }

            main.printTreeList(trees);
            Node treeIndex = main.selectTree(trees, "Select the tree number to get its height:");

            int heightTree = nodeActions.heightTree(treeIndex);
            System.out.println("The height of the tree is: " + heightTree);
        }));
        menu.add(new MenuItems("Deep first search", true, () -> {
            if (trees.isEmpty()) {
                System.out.println("There are no trees created yet.");
                return;
            }

            main.printTreeList(trees);
            Node selectTree = main.selectTree(trees, "Select the tree number to perform DFS:");
            String dfsType = main.chooseDFSType();

            SystemGetInput questionDFSValue = new SystemGetInput("Enter the value to search for:");
            String dfsValue = questionDFSValue.getResponse();

            System.out.println("Depth-First Search (DFS) Traversal:");
            Node element = nodeActions.deepFirstSearch(selectTree, dfsType, dfsValue);

            if (element != null) {
                System.out.println("Element found: " + element.getValue() + " at connected to" + (element.getLeft() != null ? " left: " + element.getLeft().getValue() : " left: null") + (element.getRight() != null ? " right: " + element.getRight().getValue() : " right: null"));
            } else {
                System.out.println("Node with value '" + dfsValue + "' not found in the tree.");
            }
        }));

        final SystemProgram program = new SystemProgram(menu);
        program.run();
    }

    private void printTreeList(ArrayList<NodeList> trees) {
        if (trees.isEmpty()) {
            System.out.println("There are no trees created yet.");
        }

        for (int i = 0; i < trees.size(); i++) {
            String nameTree = trees.get(i).getName();
            System.out.println((i + 1) + ". " + nameTree);
        }
    }

    private Node selectTree (ArrayList<NodeList> trees, String labelAction) {
        SystemGetInput questionTreeIndex = new SystemGetInput(labelAction);
        String treeIndexStr = questionTreeIndex.getResponse();
        int treeIndex;

        NodeList selectedTree = null;

        try {
            treeIndexStr = treeIndexStr.trim();
            treeIndex = Integer.parseInt(treeIndexStr) - 1;

            if (treeIndex < 0 || treeIndex >= trees.size()) {
                System.out.println("Invalid tree number. Please try again.");
                return null;
            }

            selectedTree = trees.get(treeIndex);

            if (selectedTree == null) {
                System.out.println("Tree not found. Please try again.");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return null;
        }

        System.out.println("You selected the tree: " + selectedTree.getName());

        return selectedTree.getHead();
    }

    private String chooseDFSType() {
        SystemGetInput questionDFS = new SystemGetInput("Choose DFS type (pre-order 'pre', in-order, 'in', post-order 'post'):");
        String dfsType = questionDFS.getResponse().toLowerCase();

        if (!dfsType.equals("pre") && !dfsType.equals("in") && !dfsType.equals("post")) {
            System.out.println("Invalid DFS type. Please try again.");
            chooseDFSType();

            return null;
        }

        return dfsType;
    }
}