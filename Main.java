import java.util.Scanner;

public class Main {
    public static Cell[][] conway = new Cell[12][12]; // you can modify the size here
    public static Cell[][] bufferGrid = new Cell[12][12]; // storage when switching living status between generations KEEP IT AT SAME DIMENSIONS
    public static int population = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        initGrid();
        System.out.println("Conway's game of life but Pixelverse Recreated it.");
        System.out.print("How many Generations?: ");
        int maxGeneration = input.nextInt();
        int generation = 0;

        input.close();
        conway[0][0].birth();
        conway[1][1].birth();
        conway[1][2].birth();
        conway[2][0].birth();
        conway[2][1].birth();
        bufferSetup();
        while (generation <= maxGeneration) {
            System.out.println("Generation: " + generation);
            System.out.println("Population: " + population);
            showGrid();
            gameLoop();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            generation++;
        }
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void gameLoop() {
        for (int row = 0; row < conway.length; row++) {
            for (int col = 0; col < conway[0].length; col++) { // checks every tile
                checkForNeighbors(row, col);
            }
        }
        for (int r = 0; r < conway.length; r++) {
            for (int c = 0; c < conway[0].length; c++) {
                conway[r][c].setAlive(bufferGrid[r][c].getAlive());
            }
        }
    }

    public static void showGrid() {
        for (Cell[] row : conway) {
            for (Cell c : row) {
                System.out.print(c.toString());
            }
            System.out.print("\n");
        }
    }

    public static void initGrid() {
        for (int r = 0; r < conway.length; r++) {
            for (int c = 0; c < conway[0].length; c++) {
                conway[r][c] = new Cell();
                bufferGrid[r][c] = new Cell();
            }
        }
    }

    public static void bufferSetup() {
        for (int r = 0; r < conway.length; r++) {
            for (int c = 0; c < conway[0].length; c++) {
                bufferGrid[r][c].setAlive(conway[r][c].getAlive());
                 if (bufferGrid[r][c].getAlive()){
                    population++;
                }
            }
        }
    }

    public static void checkForNeighbors(int r, int c) {
        int neighbors = 0;
        if (r > 0){ // not the first row
            if (conway[r-1][c].getAlive()) {
                neighbors++;
            }
        }
        if (c > 0){ // not the first column
            if (conway[r][c-1].getAlive()) {
                neighbors++;
            }
        }
        if (r < conway.length-1){ // not the last row
            if (conway[r+1][c].getAlive()) {
                neighbors++;
            }
        }
        if (c < conway[0].length-1){ // not the last column
            if (conway[r][c+1].getAlive()) {
                neighbors++;
            }
        }
        if (r > 0 && c > 0){ 
            if (conway[r-1][c-1].getAlive()) {
                neighbors++;
            }
        }
        if (r < conway.length-1 && c < conway[0].length-1){ 
            if (conway[r+1][c+1].getAlive()) {
                neighbors++;
            }
        }
        if (r < conway.length-1 && c > 0){ 
            if (conway[r+1][c-1].getAlive()) {
                neighbors++;
            }
        }
        if (r > 0 && c < conway[0].length-1){ 
            if (conway[r-1][c+1].getAlive()) {
                neighbors++;
            }
        }
        if (conway[r][c].getAlive() && (neighbors > 3 || neighbors < 2)) {
            bufferGrid[r][c].kill(); // Cell dies if there are 0-1 or 4+ living neighbors
            population--;
        }

        if (!conway[r][c].getAlive() && neighbors == 3) {
            bufferGrid[r][c].birth(); // Cell comes back to life if there are 3 living neighbors
            population++;
        }
    }
}
