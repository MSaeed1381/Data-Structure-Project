class Node{
    Object object;
    Node right, left;
    int x, y;
    Node(Object object, int x, int y){
        this.object = object;
        right = left = null;
        this.x = x;
        this.y = y;
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
            if (node.x >= root.x){
                root.right =  insert(root.right, node, step+1);
            }else{
                root.left = insert(root.left, node, step+1);
            }
        }else{
            if (node.y >= root.y){
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
        System.out.print(root.object + " ");
        printTree(root.left);
        printTree(root.right);
    }

}
