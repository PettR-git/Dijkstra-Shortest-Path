//Petter Rignell, Reem Mohamed - LinkSimulering
//2022-10-25

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DijkstraAlgorithm {

    private int[] prevNode;
    private int start;
    private int end;

    public String calcDijkstra(int source, int destination, int[][] topologyMatrix){
        int[] minDistance;
        int matrixSize = topologyMatrix.length;
        minDistance = new int[matrixSize];
        prevNode = new int[matrixSize];
        start = source-1;
        end = destination-1;

        Set<Integer> unvisitedNodes = new HashSet<>();

        if(topologyMatrix[start][start] == -1)
            return "Fire!";

        for(int i = 0; i<matrixSize; i++){
            unvisitedNodes.add(i);
            if(i!=start)
                minDistance[i] = Integer.MAX_VALUE;
        }
        prevNode[start] = start;

        while (!unvisitedNodes.isEmpty()){
            System.out.println(unvisitedNodes.toString());
            int currentNode = getNextNode(minDistance, unvisitedNodes);
            if(currentNode == -1)
                break;
// Men snabbaste vägen arrayen borde väll kunna se ut såhär {1,4,6,0,0.......} med node_size som length?
            unvisitedNodes.remove(currentNode);

            for(int i = 0; i< matrixSize; i++){
                if(unvisitedNodes.contains(i) && topologyMatrix[currentNode][i]>0){

                    int newDistance = minDistance[currentNode] + topologyMatrix[currentNode][i];

                    if(newDistance < minDistance[i]){
                        minDistance[i] = newDistance;
                        prevNode[i] = currentNode;
                    }
                }
            }
        }
        //System.out.println(Arrays.toString(prevNode));
        return getShortestPathString(matrixSize, topologyMatrix);
    }

    private int getNextNode(int[] minDistance, Set<Integer> nodeOrder){
        int min = Integer.MAX_VALUE;
        int newNode = -1;

        for(int i: nodeOrder){
            if(minDistance[i]<min){
                newNode = i;
                min = minDistance[i];
            }
        }
        return newNode;
    }

    private String getShortestPathString(int len, int[][] topMatrix){
        int source = start+1;
        int destination = end+1;
        int i = end;
        int itr = 1;
        int[] pathArr = new int[len];
        String reversedPath = destination + "R";
        String path = "";

        while(true){
            if(prevNode[i]==i){
                break;
            }

            pathArr[itr] = prevNode[i] +1;

            reversedPath += " >-- " + (prevNode[i] +1) + "R";
            i = prevNode[i];
            itr++;
        }
        pathArr[0] = destination;
        //System.out.println(Arrays.toString(pathArr));

        if(source != destination){
            if(!(topMatrix[end][pathArr[1]-1]!= -1))
                return "No escaperoute";
        }

        for(int j = reversedPath.length()-1; j >= 0; j--){
            path += reversedPath.charAt(j) + "";
        }

        return path;
    }

}

