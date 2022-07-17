/**
 * 基於路徑壓縮的優化(以UnionFind4為基礎)
 */

public class UnionFind5 implements UF {

    private int[] parent;
    private int[] rank; // 判斷節點數量

    public UnionFind5(int size) {
        parent = new int[size];
        rank = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 合併時進行判斷
     * @param p
     * @param q
     */
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        // 根據兩個元素所在樹的rank不同判斷合併方向
        if(rank[pRoot] < rank[qRoot]){
            parent[pRoot] = qRoot;
        }else if(rank[pRoot] > rank[qRoot]){
            parent[qRoot] = pRoot;
        }else{
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }
    }

    private int find(int p) {
        if (p < 0 || p > getSize()) {
            throw new IllegalArgumentException("invalid index!");
        }

        while (p != parent[p]) {
            parent[p] = parent[parent[p]]; // 指向父親的父親節點
            p = parent[p];
        }

        return p;
    }
}