public class BST {
     Node root;

    BST() {
        this.root = null;
    }
    void insert(Node node) {
        root = insertNode(root, node);
    }
    boolean equal(Node n1, Node n2){
        return n1.x == n2.x && n1.y == n2.y;
    }

    Node insertNode(Node root, Node node) {
        if (root == null) {
            return node;
        }

        Bank rootBank = (Bank) root.object;
        Bank nodeBank = (Bank) node.object;
        if (equal(root, node)){
            return root;
        }
        else if (nodeBank.numberOfBranches < rootBank.numberOfBranches) {
            root.left = insertNode(root.left, node);
        } else {
            root.right = insertNode(root.right, node);
        }
    return root;
    }

    void delete(Node node) {
        root = deleteNode(root, node);
    }

    Node deleteNode(Node root, Node node) {
        if (root == null){
            return null;
        }
        Bank rootBank = (Bank) root.object;
        Bank nodeBank = (Bank) node.object;
        if (equal(root, node)){
            if (root.left == null){
                return root.right;
            }
            else if (root.right == null){
                return root.left;
            }

            root.object = getRightMin(root.right);
            root.right = deleteNode(root.right, node);
        }
        else if (nodeBank.numberOfBranches < rootBank.numberOfBranches){
            root.left = deleteNode(root.left, node);
        } else {
            root.right = deleteNode(root.right, node);
        }
        return root;
    }

    Bank getRightMin(Node root) {
        Bank minBank = ((Bank)(root.object));
        while (root.left != null) {
            minBank = ((Bank)root.left.object);
            root = root.left;
        }
        return minBank;
    }
    Node getMax(){
        Node temp = this.root;
        while (temp.right != null){
            temp = temp.right;
        }
        return temp;
    }
}