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
    void add(String key, Object object) {
        TNode tNode = root;
        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - '0';
            if (tNode.children[index] == null)
                tNode.children[index] = new TNode();
            tNode = tNode.children[index];
        }
        tNode.isEnd = true;
        tNode.object = object;
    }

        TNode search(String key) {
        int level;
        int length = key.length();
        int index;
        TNode pCrawl = root;
        for (level = 0; level < length; level++) {
            index = key.charAt(level) - '0';
            if (pCrawl.children[index] == null)
                return null;
            pCrawl = pCrawl.children[index];
        }
        if (pCrawl.isEnd){
            return pCrawl;
        }
        return null;
    }
}
