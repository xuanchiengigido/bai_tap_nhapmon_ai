import java.util.ArrayList;
import java.util.PriorityQueue;

public class UniformCostSearchAlgo implements ISearchAlgo {
    @Override
    public Node execute(Node root, String goal) {
        PriorityQueue<Node> frontiers = new PriorityQueue<Node>(new NodeComparator());
        ArrayList<Node> explored = new ArrayList<>();
        frontiers.offer(root);
        root.setPathCost(0);
        while (!frontiers.isEmpty()) {
            Node current = frontiers.poll();
            if (current.getLabel().equals(goal)) return current;
            explored.add(current);
            for (Edge e :
                    current.getChildren()) {
                Node n = e.getEnd();
                if (!frontiers.contains(n) && !explored.contains(n)) {
                    n.setParent(current);
                    n.setPathCost(current.getPathCost() + e.getWeight());
                    frontiers.add(n);
                } else if (frontiers.contains(n) && n.getPathCost() > (current.getPathCost() + e.getWeight())) {
                    n.setPathCost(current.getPathCost() + e.getWeight());
                    n.setParent(current);
                }
            }
        }
        return null;
    }

    @Override
    public Node execute(Node root, String start, String goal) {
        PriorityQueue<Node> frontiers = new PriorityQueue<Node>(new NodeComparator());
        ArrayList<Node> explored = new ArrayList<>();
        frontiers.offer(root);
        boolean flag = false;
        root.setPathCost(0);
        while (!frontiers.isEmpty()) {
            Node current = frontiers.poll();
            if (current.getLabel().equals(start) && !flag) {
                flag = true;
                for (Node e :
                        frontiers) {
                    e.setPathCost(0);
                }
                frontiers.clear();
                for (Node e :
                        explored) {
                    e.setPathCost(0);
                }
                explored.clear();
                current.setParent(null);
                frontiers.offer(current);
            }
            if (current.getLabel().equals(goal)) return current;
            explored.add(current);
            for (Edge e :
                    current.getChildren()) {
                Node n = e.getEnd();
                if (!frontiers.contains(n) && !explored.contains(n)) {
                    n.setParent(current);
                    n.setPathCost(current.getPathCost() + e.getWeight());
                    frontiers.add(n);
                } else if (frontiers.contains(n) && n.getPathCost() > (current.getPathCost() + e.getWeight())) {
                    n.setPathCost(current.getPathCost() + e.getWeight());
                    n.setParent(current);
                }
            }
        }
        return null;
    }

    public Node execute(Node root, String goal, int limitedDepth) {
        if (root.getLabel().equals(goal)) return root;
        else if (limitedDepth == 0) return null;
        else {
            if (limitedDepth != 0) {
                for (Node child :
                        root.getChildrenNodes()) {
                    child.setParent(root);
                    Node result = execute(child, goal, limitedDepth - 1);
                     if (result != null) {
                        return result;
                    }
                }
            }
            return null;
        }
    }

    public static void main(String[] args) {
        Node nodeS = new Node("S");
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");
        Node nodeG = new Node("G");
        Node nodeH = new Node("H");
        nodeS.addEdge(nodeA, 5);
        nodeS.addEdge(nodeB, 2);
        nodeS.addEdge(nodeC, 4);
        nodeA.addEdge(nodeD, 9);
        nodeA.addEdge(nodeE, 4);
        nodeB.addEdge(nodeG, 6);
        nodeC.addEdge(nodeF, 2);
        nodeD.addEdge(nodeH, 7);
        nodeE.addEdge(nodeG, 6);
        nodeF.addEdge(nodeG, 1);
        UniformCostSearchAlgo algo1 = new UniformCostSearchAlgo();
        Node result = algo1.execute(nodeS, "G", 3);
        System.out.println("UCS: " + NodeUtils.printPath(result));
    }
}
