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
    private Node search(Node root, int x, int y, int step) {
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
    boolean isExistPoint(int x, int y){
        return search(this._root, x, y, 0) != null;
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
        if (n1 == null && n2 == null){
            return root;
        }else if (n1 == null){
            return n2;
        }else if (n2 == null){
            return n1;
        }else{
            if (ooe == 0){
                if (n1.x <= n2.x){
                    return n1;
                }else {
                    return n2;
                }
            }else{
                if (n1.y < n2.y){
                    return n1;
                }else{
                    return n2;
                }
            }
        }

    }
    void swap(Node n1, Node n2){
        Node temp = new Node(n1.object, n1.x, n1.y);
        n1.x = n2.x;
        n1.y = n2.y;
        n1.object = n2.object;
        n2.x = temp.x;
        n2.y = temp.y;
        n2.object = temp.object;

    }

    Node deleteNode(Node root, int x, int y, int step) {
        if (root == null){
            return null;
        }
        int m = step % 2;
        if (root.x == x && root.y == y) {
            if (root.right != null) {
                Node min = findMinNode(root.right, m, m+1);
                if (min != null) {
                    root.x = min.x;
                    root.y = min.y;
                    root.object = min.object;
                    root.right = deleteNode(root.right, min.x, min.y, m+1);
                }
            } else if (root.left != null) {
                Node min = findMinNode(root.left, m, m+1);
                if (min != null) {
                    root.x = min.x;
                    root.y = min.y;
                    root.object = min.object;

                    root.right = deleteNode(root.left, min.x, min.y, m+1);
                    root.left = null;
                }
            } else {
                //System.out.println(root.x + " " + root.y);
                /*System.out.println("saeed");
                System.out.println(root.y);
                Node parent = findParent(root.x, root.y);
                System.out.println(parent.x+" "+parent.y);
                if (step % 2 == 0){
                    if (parent.x >= root.x){
                        parent.left = null;
                    }else{
                        parent.right = null;
                    }
                }else{
                    if (parent.y >= root.y){
                        parent.left = null;
                    }else{
                        parent.right = null;
                    }
                }*/

                return null;
            }
            return root;
        }
        if (step % 2 == 0){
            if (x >= root.x){
                deleteNode(root.right, x,y, step+1);
            }else{
                deleteNode(root.left, x,y, step+1);
            }
        }else{
            if (y >= root.y){
                deleteNode(root.right, x,y, step+1);
            }else{
                deleteNode(root.left, x,y, step+1);
            }
        }
        return root;
    }
    Node findParent(int x, int y){
        int step = 0;
        Node parent = null;
        Node next = _root;
        while (next != null){
            if (x == next.x && y == next.y){
                    break;
            }
            parent = next;
            if (step % 2 == 0){
                if (x >= parent.x){
                    next = next.right;
                }else{
                    next = next.left;
                }
            }else {
                if (y >= parent.y){
                    next = next.right;
                }else{
                    next = next.left;
                }
            }
            step++;
        }
        return parent;
    }


    public static void main(String[] args) {
        KDTree kdTree = new KDTree();
        Node n1 = new Node(new Bank("salam", 1, 2), 1, 2);
        Node n2 = new Node(new Bank("salam2", 3, 4), 3, 4);
        Node n3 = new Node(new Bank("salam3", 5, 2), 5, 2);
        Node n4 = new Node(new Bank("salam4", 1, 7), 1, 7);
        Node n5 = new Node(new Bank("salam5", 6, 1), 6, 1);
        Node n6 = new Node(new Bank("salam4", 6, 3), 6, 3);
        kdTree._root = kdTree.insert(kdTree._root, n1, 0);
        kdTree._root = kdTree.insert(kdTree._root, n2, 0);
        kdTree._root = kdTree.insert(kdTree._root, n3, 0);
        kdTree._root = kdTree.insert(kdTree._root, n4, 0);
        kdTree._root = kdTree.insert(kdTree._root, n5, 0);
        kdTree._root = kdTree.insert(kdTree._root, n6, 0);
        kdTree.printTree(kdTree._root);
        System.out.println();
        kdTree.deleteNode(kdTree._root, 6,3,0);
        kdTree.printTree(kdTree._root);




    }
}
