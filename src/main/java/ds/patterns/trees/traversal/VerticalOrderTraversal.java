package ds.patterns.trees.traversal;

import java.util.*;

public class VerticalOrderTraversal {

        class Pair<T1, T2> {
            T1 key;
            T2 value;

            Pair(T1 key, T2 value){
                this.key = key;
                this.value = value;
            }

            T2 getKey(){
                return (T2) key;
            }
            T1 getValue(){
                return (T1) value;
            }
        }

        class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            TreeNode() {}
            TreeNode(int val) { this.val = val; }
            TreeNode(int val, TreeNode left, TreeNode right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }
        }

        public List<List<Integer>> verticalOrder(TreeNode root) {
            List<List<Integer>> resultList = new ArrayList<>();

            Map<Integer, List<Pair<Integer, Integer>> > result = new TreeMap<>();
            verticalOrderTraversal(root, 0, 0, result);

            for ( Map.Entry<Integer, List<Pair<Integer, Integer>> > entry : result.entrySet() ){
                System.out.println(entry.getKey() +" " +entry.getValue());
                if (entry.getKey() < 0){
                    List<Pair<Integer, Integer>> listt = entry.getValue();
                    Comparator<Pair<Integer, Integer>> c = java.util.Collections.reverseOrder(java.util.Comparator.comparing(Pair::getKey));
                    Collections.sort(listt, c);
                    System.out.println(listt);
                    List<Integer> listtt = new ArrayList<>();
                    for(Pair<Integer, Integer> pair:  listt) {
                        listtt.add(pair.getValue());
                    }
                    //Collections.sort(listt);
                    resultList.add(0,listtt);
                }else{
                    List<Pair<Integer, Integer>> listt = entry.getValue();
                    Comparator<Pair<Integer, Integer>> c = java.util.Collections.reverseOrder(java.util.Comparator.comparing(Pair::getKey));
                    Collections.sort(listt, c);
                    System.out.println(listt);
                    List<Integer> listtt = new ArrayList<>();
                    for(Pair<Integer, Integer> pair:  listt) {
                        listtt.add(pair.getValue());
                    }
                    //Collections.sort(listt);
                    resultList.add(0,listtt);

                }
            }
            System.out.println(resultList);
            return resultList;
        }

        private void verticalOrderTraversal(TreeNode node, int row, int col, Map<Integer, List<Pair<Integer, Integer>> > result){
            if (node == null){
                return;
            }
            System.out.println(node.val + " row,col ("+row +", " +col+")");
            if (!result.containsKey(col)){
                List<Pair<Integer, Integer>> list = new ArrayList<>();
                list.add(new Pair(row, node.val));
                result.put(col, list);
            }else{
                result.get(col).add(new Pair(row, node.val));
            }
            verticalOrderTraversal(node.left, row+1, col+1 , result);
            verticalOrderTraversal(node.right, row+1, col-1, result);

        }

}
