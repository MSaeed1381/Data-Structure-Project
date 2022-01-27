 class TNode {
    Object object;
    TNode[] children = new TNode[150];
    boolean isEnd;
    TNode(){
        this.isEnd = false;
    }
}
public class TrieTree {
    TNode root;
    void add(String name, Object object) {
        TNode tNode = root;
        for (int i = 0; i < name.length(); i++) {
            int index = name.charAt(i) - '0';
            if (tNode.children[index] == null)
                tNode.children[index] = new TNode();
            tNode = tNode.children[index];
        }
        tNode.isEnd = true;
        tNode.object = object;
    }

        TNode search(String name) {
        int level;
        int length = name.length();
        int index;
        TNode tNode = root;
        for (level = 0; level < length; level++) {
            index = name.charAt(level) - '0';
            if (tNode.children[index] == null)
                return null;
            tNode = tNode.children[index];
        }
        if (tNode.isEnd){
            return tNode;
        }
        return null;
    }
}
