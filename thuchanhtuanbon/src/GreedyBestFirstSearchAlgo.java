import java.util.ArrayList;
import java.util.PriorityQueue;

public class GreedyBestFirstSearchAlgo implements IInformedSearchAlgo {
    @Override
    public Node execute(Node root, String goal) {
        PriorityQueue<Node> frontiers = new PriorityQueue<>(new NodeComparatorByHn());
        ArrayList<Node> explored = new ArrayList<>();
        frontiers.offer(root);
        while (!frontiers.isEmpty()) {
            Node current = frontiers.poll();
            if (current.getLabel().equals(goal)) return current;
            explored.add(current);
            for (Edge edge
                    : current.getChildren()) {
                Node child = edge.getEnd();
                if (!explored.contains(child) && !frontiers.contains(child)) {
                    frontiers.offer(child);
                    child.setParent(current);
                }
            }
        }
        return null;
    }

    @Override
    public Node execute(Node root, String start, String goal) {
        return null;
    }


}
