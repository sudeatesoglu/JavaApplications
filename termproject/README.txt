Overview

The project includes two files, PercolationStdDraw and PercolationGUI, each designed to visualize percolation problems. These classes adopt distinct visualization methods and address specific functionalities.

Important

PercolationGUI Description

PercolationGUI is a graphical user interface implementation for percolation problems. It uses Swing components to create a JFrame with a layout containing multiple panels, each representing a different percolation problem. This GUI allows users to generate new sets of percolation problems by clicking the "NEXT PROBLEMS" button.

PercolationStdDraw Description

PercolationStdDraw utilizes the StdDraw library to visualize percolation problems in a graphical format. It creates a 2D array of Percolation instances, each representing a percolation problem. The class also attempts to visualize the open sites in the grid, but there may be limitations in accurately displaying them unlike PercolationGUI.
