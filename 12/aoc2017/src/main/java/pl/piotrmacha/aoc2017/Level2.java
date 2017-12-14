package pl.piotrmacha.aoc2017;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.Graphs;
import com.google.common.graph.MutableGraph;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Level2
{
    public static void main(String[] args)
    {
        try {


            Map<Integer, Program> programs = DataLoader.load("data");

            MutableGraph<Program> graph = GraphBuilder.undirected().allowsSelfLoops(true).build();

            for (Program program : programs.values()) {
                graph.addNode(program);
                for (Integer connId : program.connections) {
                    graph.putEdge(program, programs.get(connId));
                }
            }

            Set<Program> alreadyChecked = new HashSet<>();
            int groups = 0;
            for (Program program : programs.values()) {
                if (alreadyChecked.contains(program)) {
                    continue;
                }

                alreadyChecked.add(program);
                Set<Program> connected = Graphs.reachableNodes(graph, program);
                alreadyChecked.addAll(connected);
                groups++;
            }
            System.out.println(groups);


        } catch (Exception e) {
            // Not a good practice, but hey! It's algorithms compo
            e.printStackTrace();
        }

    }
}
