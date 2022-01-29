class Node{
    Object object;
    Node right, left;
    double x, y;


    Node(Object object, double x, double y){
        this.object = object;
        right = left = null;
        this.x = x;
        this.y = y;
    }
    double distance(double x, double y){
        return (this.x - x)*(this.x - x) + (this.y - y)*(this.y - y);
    }
}
public class KDTree {
    Node _root;
    KDTree(){
        _root = null;
    }
    boolean isEmpty(){
        return _root == null;
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
    Node search(Node root, double x, double y, int step) {
        if (root == null) {
            return null;
        }
        if (root.x == x && root.y == y) {
            return root;
        }
        if (step % 2 == 0) {
            if (x >= root.x) {
                 return search(root.right, x, y, step + 1);
            } else {
                 return search(root.left, x, y, step + 1);
            }
        } else {
            if (y >= root.y) {
                 return search(root.right, x, y, step + 1);
            } else {
                 return search(root.left, x, y, step + 1);
            }
        }
    }
    boolean isExistPoint(double x, double y){
        return  search(this._root, x, y, 0) != null;
    }
    Node findMinNode(Node root, int ooe, int step){ // ooe = odd or even
        if (root == null){
            return null;
        }
        if (step % 2 == ooe){
            if (root.left == null)
                return root;
            return findMinNode(root.left, ooe, step+1);
        }
        Node n1 = findMinNode(root.left, ooe, step+1);
        Node n2 = findMinNode(root.right, ooe, step+1);
        Node[] nodes = new Node[3];
        nodes[0] = root;
        nodes[1] = n1;
        nodes[2] = n2;
        Node answer = root;
        for (Node node:nodes) {
            if (ooe == 0){
                if (node != null && node.x <= answer.x){
                    answer = node;
                }
            }else{
                if (node != null && node.y < node.y){
                    answer = node;
                }
            }
        }
        return answer;

    }
    void copy(Node n1, Node n2){
     n2.x = n1.x;
     n2.y = n1.y;
     n2.object = n1.object;
    }

    Node deleteNode(Node root, double x, double y, int step) {
        if (root == null) {
            return null;
        }
        step = step % 2;
        if (root.x == x && root.y == y) {
            if (root.right != null) {
                Node min = findMinNode(root.right, step, step + 1);
                    copy(min, root);
                    root.right = deleteNode(root.right, min.x, min.y, step + 1);
            } else if (root.left != null) {
                    Node min = findMinNode(root.left, step, step + 1);
                    copy(min, root);
                    root.right = deleteNode(root.left, min.x, min.y, step + 1);
                    root.left = null;
            } else {
                return null;
            }
                return root;
            }
        else{
            if (step % 2 == 0) {
                if (x >= root.x) {
                    root.right = deleteNode(root.right, x, y, step + 1);
                } else {
                    root.left = deleteNode(root.left, x, y, step + 1);
                }
            } else {
                if (y >= root.y) {
                    root.right = deleteNode(root.right, x, y, step + 1);
                } else {
                    root.left = deleteNode(root.left, x, y, step + 1);
                }
            }
        }
            return root;
    }

    Node getNearest(Node root, double x, double y, Node best, int step){
        Node goodSide;
        Node badSide;
        if (root == null){
            return best;
        }
        if (root.distance(x, y) < best.distance(x, y)){
            best = root;
        }
        if (step % 2 == 0){
            if (x < root.x){
                goodSide = root.left;
                badSide = root.right;
            }else{
                goodSide = root.right;
                badSide = root.left;
            }
        }else{
            if (y < root.y){
                goodSide = root.left;
                badSide = root.right;
            }else {
                badSide = root.left;
                goodSide = root.right;
            }
        }
        best = getNearest(goodSide, x, y, best, step+1);
        if (step % 2 == 0){
            if (badSide != null && badSide.x* badSide.x < best.distance(x, y)){
                best = getNearest(badSide, x, y, best, step+1);
            }
        }else{
            if (badSide != null && badSide.y* badSide.y < best.distance(x, y)){
                best = getNearest(badSide, x, y, best, step+1);
            }
        }
        return best;
    }
}
