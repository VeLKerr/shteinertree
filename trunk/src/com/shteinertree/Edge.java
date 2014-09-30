
package com.shteinertree;

import java.util.Arrays;

/**
 *
 * @author Ivchenko Oleg (Kirius VeLKerr)
 */
public class Edge implements Comparable<Edge>{
    private final int[] vertices;
    private final int weigt;

    public Edge(int vert1, int vert2, int weigt) {
        this.vertices = new int[]{vert1, vert2};
        Arrays.sort(vertices);
        this.weigt = weigt;
    }

    public int getWeigt() {
        return weigt;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compareUnsigned(weigt, o.weigt);
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("(").append(vertices[0]).append(", ").append(vertices[1]).append(")");
        sb.append(", w = ").append(weigt);
        return sb.toString();
    }
}
