class Node{
    Bank bank;
    Node right, left;
    Node(Bank bank){
        this.bank = bank;
        right = left = null;
    }
}
public class KDTree {
    Node _root;
    KDTree(){
        _root = null;
    }

    Node insert(Node root, Node node, int step){
        if (root == null){
            return node;
        }
        if (step % 2 == 0){
            if (node.bank.x >= root.bank.x){
                root.right =  insert(root.right, node, step+1);
            }else{
                root.left = insert(root.left, node, step+1);
            }
        }else{
            if (node.bank.y >= root.bank.y){
                root.right =  insert(root.right, node, step+1);
            }else{
                root.left = insert(root.left, node, step+1);
            }
        }
        return root;
    }
    void printTree(Node root){
        if (root == null){
            return;
        }
        System.out.print(root.bank + " ");
        printTree(root.left);
        printTree(root.right);
    }

}
