/*
 * (C) Copyright 2019-2019, by Dimitrios Michail and Contributors.
 *
 * JGraphT : a free Java graph-theory library
 *
 * See the CONTRIBUTORS.md file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0, or the
 * GNU Lesser General Public License v2.1 or later
 * which is available at
 * http://www.gnu.org/licenses/old-licenses/lgpl-2.1-standalone.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR LGPL-2.1-or-later
 */
package org.jgrapht.alg.clustering;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ClusteringAlgorithm.Clustering;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.builder.GraphTypeBuilder;
import org.jgrapht.util.SupplierUtil;
import org.junit.Test;

/**
 * Tests
 *
 * @author Dimitrios Michail
 */
public class KSpanningTreeClusteringTest
{
    @Test
    public void test1()
    {
        Graph<Integer,
            DefaultWeightedEdge> g = GraphTypeBuilder
                .undirected().allowingMultipleEdges(true).allowingSelfLoops(true).weighted(true)
                .edgeSupplier(SupplierUtil.DEFAULT_WEIGHTED_EDGE_SUPPLIER)
                .vertexSupplier(SupplierUtil.createIntegerSupplier()).buildGraph();

        for (int i = 0; i < 9; i++)
            g.addVertex();
        g.setEdgeWeight(g.addEdge(0, 1), 2d);
        g.setEdgeWeight(g.addEdge(0, 5), 1d);
        g.setEdgeWeight(g.addEdge(1, 2), 3d);
        g.setEdgeWeight(g.addEdge(1, 4), 11d);
        g.setEdgeWeight(g.addEdge(2, 3), 4d);
        g.setEdgeWeight(g.addEdge(3, 4), 5d);
        g.setEdgeWeight(g.addEdge(3, 8), 10d);
        g.setEdgeWeight(g.addEdge(4, 5), 6d);
        g.setEdgeWeight(g.addEdge(4, 7), 12d);
        g.setEdgeWeight(g.addEdge(5, 6), 7d);
        g.setEdgeWeight(g.addEdge(6, 7), 8d);
        g.setEdgeWeight(g.addEdge(7, 8), 9d);

        final int k = 3;
        KSpanningTreeClustering<Integer, DefaultWeightedEdge> alg =
            new KSpanningTreeClustering<>(g, k);
        Clustering<Integer> clustering = alg.getClustering();

        assertEquals(clustering.getNumberClusters(), k);
        List<Set<Integer>> clusters = clustering.getClusters();

        assertEquals(new HashSet<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6)), clusters.get(0));
        assertEquals(new HashSet<>(Arrays.asList(7)), clusters.get(1));
        assertEquals(new HashSet<>(Arrays.asList(8)), clusters.get(2));
    }

    @Test
    public void test2()
    {
        Graph<Integer,
            DefaultWeightedEdge> g = GraphTypeBuilder
                .undirected().allowingMultipleEdges(true).allowingSelfLoops(true).weighted(true)
                .edgeSupplier(SupplierUtil.DEFAULT_WEIGHTED_EDGE_SUPPLIER)
                .vertexSupplier(SupplierUtil.createIntegerSupplier()).buildGraph();

        for (int i = 0; i < 9; i++)
            g.addVertex();
        g.setEdgeWeight(g.addEdge(0, 1), 2d);
        g.setEdgeWeight(g.addEdge(0, 5), 1d);
        g.setEdgeWeight(g.addEdge(1, 2), 9d);
        g.setEdgeWeight(g.addEdge(1, 4), 11d);
        g.setEdgeWeight(g.addEdge(2, 3), 4d);
        g.setEdgeWeight(g.addEdge(3, 4), 5d);
        g.setEdgeWeight(g.addEdge(3, 8), 10d);
        g.setEdgeWeight(g.addEdge(4, 5), 6d);
        g.setEdgeWeight(g.addEdge(4, 7), 12d);
        g.setEdgeWeight(g.addEdge(5, 6), 7d);
        g.setEdgeWeight(g.addEdge(6, 7), 8d);
        g.setEdgeWeight(g.addEdge(7, 8), 3d);

        final int k = 4;
        KSpanningTreeClustering<Integer, DefaultWeightedEdge> alg =
            new KSpanningTreeClustering<>(g, k);
        Clustering<Integer> clustering = alg.getClustering();

        assertEquals(clustering.getNumberClusters(), k);
        List<Set<Integer>> clusters = clustering.getClusters();

        assertEquals(new HashSet<>(Arrays.asList(0, 1, 5)), clusters.get(0));
        assertEquals(new HashSet<>(Arrays.asList(2, 3, 4)), clusters.get(1));
        assertEquals(new HashSet<>(Arrays.asList(6)), clusters.get(2));
        assertEquals(new HashSet<>(Arrays.asList(7, 8)), clusters.get(3));
    }

    @Test
    public void testOneCluster()
    {
        Graph<Integer,
            DefaultWeightedEdge> g = GraphTypeBuilder
                .undirected().allowingMultipleEdges(true).allowingSelfLoops(true).weighted(true)
                .edgeSupplier(SupplierUtil.DEFAULT_WEIGHTED_EDGE_SUPPLIER)
                .vertexSupplier(SupplierUtil.createIntegerSupplier()).buildGraph();

        for (int i = 0; i < 9; i++)
            g.addVertex();
        g.setEdgeWeight(g.addEdge(0, 1), 2d);
        g.setEdgeWeight(g.addEdge(0, 5), 1d);
        g.setEdgeWeight(g.addEdge(1, 2), 3d);
        g.setEdgeWeight(g.addEdge(1, 4), 11d);
        g.setEdgeWeight(g.addEdge(2, 3), 4d);
        g.setEdgeWeight(g.addEdge(3, 4), 5d);
        g.setEdgeWeight(g.addEdge(3, 8), 10d);
        g.setEdgeWeight(g.addEdge(4, 5), 6d);
        g.setEdgeWeight(g.addEdge(4, 7), 12d);
        g.setEdgeWeight(g.addEdge(5, 6), 7d);
        g.setEdgeWeight(g.addEdge(6, 7), 8d);
        g.setEdgeWeight(g.addEdge(7, 8), 9d);

        final int k = 1;
        KSpanningTreeClustering<Integer, DefaultWeightedEdge> alg =
            new KSpanningTreeClustering<>(g, k);
        Clustering<Integer> clustering = alg.getClustering();

        assertEquals(clustering.getNumberClusters(), k);
    }

    @Test
    public void testNClusters()
    {
        Graph<Integer,
            DefaultWeightedEdge> g = GraphTypeBuilder
                .undirected().allowingMultipleEdges(true).allowingSelfLoops(true).weighted(true)
                .edgeSupplier(SupplierUtil.DEFAULT_WEIGHTED_EDGE_SUPPLIER)
                .vertexSupplier(SupplierUtil.createIntegerSupplier()).buildGraph();

        for (int i = 0; i < 9; i++)
            g.addVertex();
        g.setEdgeWeight(g.addEdge(0, 1), 2d);
        g.setEdgeWeight(g.addEdge(0, 5), 1d);
        g.setEdgeWeight(g.addEdge(1, 2), 3d);
        g.setEdgeWeight(g.addEdge(1, 4), 11d);
        g.setEdgeWeight(g.addEdge(2, 3), 4d);
        g.setEdgeWeight(g.addEdge(3, 4), 5d);
        g.setEdgeWeight(g.addEdge(3, 8), 10d);
        g.setEdgeWeight(g.addEdge(4, 5), 6d);
        g.setEdgeWeight(g.addEdge(4, 7), 12d);
        g.setEdgeWeight(g.addEdge(5, 6), 7d);
        g.setEdgeWeight(g.addEdge(6, 7), 8d);
        g.setEdgeWeight(g.addEdge(7, 8), 9d);

        final int k = 9;
        KSpanningTreeClustering<Integer, DefaultWeightedEdge> alg =
            new KSpanningTreeClustering<>(g, k);
        Clustering<Integer> clustering = alg.getClustering();
        assertEquals(clustering.getNumberClusters(), k);
    }

}