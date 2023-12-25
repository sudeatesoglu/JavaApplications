import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStdDraw {
    private static final int GRID_SIZE = 9;
    private static final int NUM_ROWS = 2;
    private static final int NUM_COLS = 5;
    private static final double MARGIN = 0.8;

    // Create a percolation instance with random open sites
    private static Percolation createPercolation() {
        Percolation percolation = new Percolation(GRID_SIZE);
        double p = StdRandom.uniformDouble(0.1, 0.8);
        percolation.openAllSites(p);
        return percolation;
    }

    // Visualize an array of percolation instances
    private static void visualizePercolation(Percolation[][] problems) {
        double cellSize = 1.0;
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                drawProblem(problems[row][col], row, col, cellSize);
            }
        }
        // Display the visualization
        StdDraw.show();
    }

    // Draw a single percolation instance
    private static void drawProblem(Percolation percolation, int row, int col, double cellSize) {
        double xOffset = col * (GRID_SIZE + MARGIN);
        double yOffset = row * (GRID_SIZE + MARGIN);

        // Reverse the order of drawing rows
        for (int i = GRID_SIZE - 1; i >= 0; i--) {
            for (int j = 0; j < GRID_SIZE; j++) {
                drawCell(i, j, percolation, xOffset, yOffset, cellSize);
            }
        }

        // Adjust the position for displaying the result
        double resultXOffset = xOffset + (GRID_SIZE + MARGIN) / 2;
        double resultYOffset = yOffset + GRID_SIZE * cellSize + MARGIN / 2;

        // Display the percolation result including open site count
        StdDraw.text(resultXOffset, resultYOffset, percolation.getResult());
    }

    // Draw a single cell based on the percolation state
    private static void drawCell(int row, int col, Percolation percolation,
                                 double xOffset, double yOffset, double cellSize) {
        double x = col * cellSize + xOffset;
        double y = row * cellSize + yOffset;

        // Set the color based on the state of the site
        if (percolation.isOpen(row, col)) {
            if (percolation.isFull(row, col)) {
                StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
            } else {
                StdDraw.setPenColor(StdDraw.WHITE);
            }
        } else {
            StdDraw.setPenColor(StdDraw.BLACK);
        }

        // Draw a filled square for the cell
        StdDraw.filledSquare(x + cellSize / 2, y + cellSize / 2, cellSize / 2);
    }

    public static void main(String[] args) {
        // Set up the drawing canvas
        StdDraw.setCanvasSize(1530, 555);
        StdDraw.setXscale(0, NUM_COLS * (GRID_SIZE + MARGIN));
        StdDraw.setYscale(0, NUM_ROWS * (GRID_SIZE + MARGIN));

        // Create a 2D array to store Percolation instances
        Percolation[][] problems = new Percolation[NUM_ROWS][NUM_COLS];

        // Initialize each element in the array with a Percolation instance
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                problems[row][col] = createPercolation();
            }
        }

        // Visualize the Percolation instances
        visualizePercolation(problems);
    }

    private static class Percolation {
        private final boolean[][] grid;
        private final int gridSize;
        private final int gridSquared;
        private final int[] parent;
        private final int[] size;
        private final int virtualTop;
        private final int virtualBottom;
        private int openSiteCount; // Counter for open sites
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

                // Increment the open site count
                openSiteCount++;
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

        // Get the count of open sites
        private int getOpenSiteCount() {
            return openSiteCount;
        }

        // Get the result string including open site count
        private String getResult() {
            return String.format("p=%.2f, Open Sites: %d, %s",
                    getProbability(),
                    getOpenSiteCount(),
                    percolationCheck() ? "Percolates" : "Does not percolate");
        }
    }
}
