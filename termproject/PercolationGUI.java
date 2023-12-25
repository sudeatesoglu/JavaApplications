import edu.princeton.cs.algs4.StdRandom;

import javax.swing.*;
import java.awt.*;

/*
The PercolationGUI class provides a graphical user interface for visualizing Percolation problems.
It uses Java's Swing library to create a simple GUI application with a grid of Percolation instances,
each represented by a panel. The GUI allows users to generate new Percolation instances and visualize
the state of each site, along with the probability of percolation and other relevant information.

The class includes methods to create the main GUI components, update the panels with new Percolation problems,
and handle the visualization of the percolation state using buttons and colors.

The Percolation class, an internal class within PercolationGUI, represents the logic of the Percolation problem.
It includes methods for opening sites, connecting adjacent open sites, checking if a site is open or full,
calculating the probability of percolation, and determining if the system percolates.

To run the GUI, the main method launches the application using SwingUtilities.invokeLater.
*/

public class PercolationGUI extends JFrame {
    private static final int GRID_SIZE = 9;
    private static final int ROWS = 2;
    private static final int COLS = 5;
    private static final int MARGIN = 10;
    private final JPanel mainPanel;

    // Constructor for the GUI
    public PercolationGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Percolation Problems");

        mainPanel = new JPanel(new GridLayout(ROWS, COLS, MARGIN, MARGIN));

        // Create initial problem panels
        createProblems();

        // Create "Next Problems" button
        JButton nextProblemButton = new JButton("NEXT PROBLEMS");
        nextProblemButton.addActionListener(e -> {
            updateMainPanel();
            revalidate();
            repaint();
        });

        // Set layout and add components to the JFrame
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(nextProblemButton, BorderLayout.SOUTH);

        // Pack and display the JFrame
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Create a new Percolation object with random open sites
    private Percolation createPercolation() {
        Percolation percolation = new Percolation(GRID_SIZE);
        double p = StdRandom.uniformDouble(0.1, 0.8);
        percolation.openAllSites(p);
        return percolation;
    }

    // Update the main panel with new problem panels
    private void updateMainPanel() {
        mainPanel.removeAll();
        createProblems();
    }

    // Create the problems to be displayed
    private void createProblems() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Percolation percolation = createPercolation();
                JPanel problemPanel = createProblemPanel(percolation);
                mainPanel.add(problemPanel);
            }
        }
    }

    // Create a panel for a specific Percolation problem
    private JPanel createProblemPanel(Percolation percolation) {
        JPanel problemPanel = new JPanel(new BorderLayout());
        int buttonSize = 40;

        JPanel gridPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        int openSiteCount = 0; // Counter for open sites

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(buttonSize, buttonSize));
                setButtonBackground(button, percolation, i, j);
                button.setEnabled(false);
                gridPanel.add(button);

                // Increment the open site count
                if (percolation.isOpen(i, j)) {
                    openSiteCount++;
                }
            }
        }

        JLabel resultLabel = new JLabel(String.format("p=%.2f, Open Sites: %d, %s",
                percolation.getProbability(), openSiteCount,
                percolation.percolationCheck() ? "Percolates" : "Does not percolate"),
                SwingConstants.CENTER);

        // Add grid panel and result label to the problem panel
        problemPanel.add(gridPanel, BorderLayout.CENTER);
        problemPanel.add(resultLabel, BorderLayout.SOUTH);

        return problemPanel;
    }

    // Set the colors to visualize results based on the Percolation state
    private void setButtonBackground(JButton button, Percolation percolation, int i, int j) {
        if (percolation.isOpen(i, j)) {
            if (percolation.isFull(i, j)) {
                button.setBackground(new Color(135, 209, 250));
            } else {
                button.setBackground(Color.WHITE);
            }
        } else {
            button.setBackground(Color.BLACK);
        }
    }

    // Main method to launch the GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(PercolationGUI::new);
    }

    // Internal class representing the Percolation problem
    private static class Percolation {
        private final boolean[][] grid;
        private final int gridSize;
        private final int gridSquared;
        private final int[] parent;
        private final int[] size;
        private final int virtualTop;
        private final int virtualBottom;
        private double probability;

        // Constructor for the Percolation problem
        public Percolation(int n) {
            gridSize = n;
            gridSquared = n * n;
            grid = new boolean[n][n];
            parent = new int[gridSquared + 2];
            size = new int[gridSquared + 2];
            virtualTop = gridSquared;
            virtualBottom = gridSquared + 1;

            // Initialize parent and size arrays
            for (int i = 0; i < gridSquared + 2; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        // Get the 1D index for a 2D grid
        private int getIndex(int row, int col) {
            return row * gridSize + col;
        }

        // Find the root of the given element using path compression
        private int find(int p) {
            while (p != parent[p]) {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        // Union operation for weighted quick-union with path compression
        private void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;

            if (size[rootP] < size[rootQ]) {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            } else {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }
        }

        // Open a site and connect adjacent open sites
        private void openSite(int row, int col) {
            if (!grid[row][col]) {
                grid[row][col] = true;
                int siteIndex = getIndex(row, col);
                connectAdjacentSites(row, col, siteIndex);

                if (row == 0) {
                    union(siteIndex, virtualTop);
                }
                if (row == gridSize - 1) {
                    union(siteIndex, virtualBottom);
                }
            }
        }

        // Connect adjacent open sites
        private void connectAdjacentSites(int row, int col, int siteIndex) {
            if (row > 0 && grid[row - 1][col]) {
                union(siteIndex, getIndex(row - 1, col));
            }
            if (row < gridSize - 1 && grid[row + 1][col]) {
                union(siteIndex, getIndex(row + 1, col));
            }
            if (col > 0 && grid[row][col - 1]) {
                union(siteIndex, getIndex(row, col - 1));
            }
            if (col < gridSize - 1 && grid[row][col + 1]) {
                union(siteIndex, getIndex(row, col + 1));
            }
        }

        // Check if a site is open
        private boolean isOpen(int row, int col) {
            return grid[row][col];
        }

        // Check if a site is full
        private boolean isFull(int row, int col) {
            int siteIndex = getIndex(row, col);
            return find(siteIndex) == find(virtualTop);
        }

        // Open sites randomly with probability p
        private void openAllSites(double p) {
            for (int i = 0; i < gridSize; i++) {
                for (int j = 0; j < gridSize; j++) {
                    if (StdRandom.uniformDouble() < p) {
                        openSite(i, j);
                    }
                }
            }
            probability = p;
        }

        // Check if the system percolates
        private boolean percolationCheck() {
            return find(virtualTop) == find(virtualBottom);
        }

        // Get the probability of the system percolating
        private double getProbability() {
            return probability;
        }
    }
}
