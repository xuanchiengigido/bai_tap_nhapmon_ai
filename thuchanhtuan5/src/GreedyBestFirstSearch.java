import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class GreedyBestFirstSearch implements IPuzzleAlgo {
    @Override
    public Node execute(Puzzle model) {

        PriorityQueue<Node> frontiers = new PriorityQueue<>();
        List<Node> exploredSet = new ArrayList<>();
        frontiers.offer(model.getInitialState());
        while (!frontiers.isEmpty()) {
            Node current = frontiers.poll();
            if (current.getH() == 0) return current;
            else {
                exploredSet.add(current);
                for (Node child :
                        model.getSuccessors(current)) {
                    if (!exploredSet.contains(child) && !frontiers.contains(child)) {
                        child.setG(current.getG() + 1);
                        frontiers.add(child);
                    }
                }
            }
        }


        return null;
    }
}
