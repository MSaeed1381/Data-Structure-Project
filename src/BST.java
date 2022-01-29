public class BST {
    private Node root;

    BST() {
        this.root = null;
    }
    void insert(Node node) {
        root = insertNode(root, node);
    }

    Node insertNode(Node root, Node node) {
        if (root == null) {
            return node;
        }

        Bank rootBank = (Bank) root.object;
        Bank nodeBank = (Bank) node.object;
        if (nodeBank.numberOfBranches < rootBank.numberOfBranches) {
            root.left = insertNode(root.left, node);
        } else if (nodeBank.numberOfBranches > rootBank.numberOfBranches) {
            root.right = insertNode(root.right, node);
        }

        return root;
    }

    void deleteKey(Node node) {
        root = deleteRec(root, node);
    }

    Node deleteRec(Node root, Node node) {
        if (root == null){
            return null;
        }
        Bank rootBank = (Bank) root.object;
        Bank nodeBank = (Bank) node.object;
        if (nodeBank.numberOfBranches < rootBank.numberOfBranches)
            root.left = deleteRec(root.left, node);
        else if (nodeBank.numberOfBranches > rootBank.numberOfBranches)
            root.right = deleteRec(root.right, node);
        else if (rootBank.name.equals(nodeBank.name)){
            if (root.left == null){
                return root.right;
            }
            else if (root.right == null){
                return root.left;
            }

            root.object = minValue(root.right);
            root.right = deleteRec(root.right, node);
        }

        return root;
    }

    int minValue(Node root) {
        int min = ((Bank)root.object).numberOfBranches;
        while (root.left != null) {
            min = ((Bank)root.left.object).numberOfBranches;
            root = root.left;
        }
        return min;
    }
    Node getMax(){
        Node temp = this.root;
        while (temp.right != null){
            temp = temp.right;
        }
        return temp;
    }
    public Node search(Node root, Node node) {
        if (root==null || (((Bank) root.object).name.equals(((Bank) node.object).name))){
        return root;
    }
        Bank rootBank = (Bank) root.object;
        Bank nodeBank = (Bank) node.object;
        if (rootBank.numberOfBranches < nodeBank.numberOfBranches)
            return search(root.right, node);
        return search(root.left, node);
    }
    boolean isEmpty(Node node){
        return search(root, node) == null;
    }

}