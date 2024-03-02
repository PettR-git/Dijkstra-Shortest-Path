//Petter Rignell - LinkSimulering
//2022-10-25

import java.util.Arrays;

public class RoutingTopology {

    /*
     Topologin exempel med fem routrar och "cost"

     R1 -------3------- R3
      |  -              -|
      |    2-       -7   |
      1        R2        |
      |    3-       -5   |
      | -              - |
     R4 -------4--------R5
     */

    //0 = egen router
    //-1 = ingen väg, dvs router har inte max antal grannar
  /*  private int[][] topology = {
    //       R1, R2, R3, R4, R5, R6
            { 0,  2,  3,  1, -1, -1}, //nod1 förhållande till alla andra
            { 2,  0,  7,  3,  8, -1},
            { 3,  7,  0, -1,  6, -1},
            { 1,  3, -1,  0,  4,  3},
            {-1,  8,  6,  4,  0,  2},
            {-1, -1, -1,  3,  2,  0},
    }; */


    private int[][] topology = {
            {0, 1, 1, 1, -1, -1},
            {1, 0, 1, 1, 1, -1},
            {1, 1, 0, -1, 1, -1},
            {1, 1, -1, 0, 1, 1},
            {-1, 1, 1, 1, 0, 1},
            {-1, -1, -1, 1, 1, 0}
    };

   /* private int[][] topology = {
            {0,1,1,1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},  //1
            {1,0,-1,1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}, //2
            {1,-1,0,1,1,1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},   //3
            {1,1,1,0,-1,1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},   //4
            {-1,-1,1,-1,0,1,1,1,-1,-1,-1,-1,-1,-1,-1,-1},   //5
            {-1,-1,1,1,1,0,-1,1,-1,-1,-1,-1,-1,-1,-1,-1},   //6
            {-1,-1,-1,-1,1,-1,0,1,1,-1,-1,-1,-1,-1,-1,-1},  //7
            {-1,-1,-1,-1,1,1,1,0,-1,-1,-1,-1,-1,-1,-1,-1},  //8
            {-1,-1,-1,-1,-1,-1,1,-1,0,1,-1,-1,-1,-1,-1,-1}, //9
            {-1,-1,-1,-1,-1,-1,-1,-1,1,0,1,1,1,-1,-1,-1},   //10
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,1,0,-1,1,-1,-1,-1}, //11
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,0,1,1,1,-1},   //12
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,1,1,1,0,-1,1,-1},   //13
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,0,1,1},  //14
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1,1,1,0,1},   //15
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1,1,0}, //16
    };*/

    public int getMatrixSize(){
        return topology.length;
    }

    //För att få cost för en routers länkar
    public int[] GetLinksCostAt(int index){
        int len = topology.length;
        int[] routerNeighbours = new int[len];
        try{
            for(int row = 0; row< len; row++){
                if(index==row){
                    for(int col = 0; col<len; col++){
                        routerNeighbours[col] = topology[index][col];
                    }
                }
            }
        }catch(Exception e){
            System.out.println(e + " links cost could not be added!");
        }

        return routerNeighbours;
    }

    public void UpdateTopology(int routerNmbr){
        for(int i = 0; i<topology.length; i++){
            if(i == routerNmbr-1){
                for(int j = 0; j< topology[0].length; j++){
                    topology[i][j] = -1;
                    topology[j][routerNmbr-1] = -1;
                }
            }
        }
    }

    public int[][] getTopology(){
        return topology;
    }

    public void setTopology(int[][] topology){
        this.topology=topology;
    }
}
