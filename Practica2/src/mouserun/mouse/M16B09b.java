package mouserun.mouse;

import mouserun.game.*;
import java.util.*;

public class M16B09b extends Mouse {

    private final HashMap<Integer, Grid> laberinto;
    private final Stack<Integer> camino;

    public M16B09b() {
        super("Fat Mickey");
        laberinto = new HashMap<>();
        camino = new Stack<>();

    }

    /**
     * Con este método devolvemos el código hasmap según la posición en X e Y
     */
    private static int hashCode(int x, int y, int opcion) {
        switch (opcion) {
            case UP:
                return x * 10000 + (y + 1);
            case DOWN:
                return x * 10000 + (y - 1);
            case LEFT:
                return (x - 1) * 10000 + y;
            case RIGHT:
                return (x + 1) * 10000 + y;
        }
        System.out.println(x * 10000 + y);
        return x * 10000 + y;
    }

    /**
     * Método Busqueda en Anchura
     */
    private int Anchura(Grid g, Cheese c) {
        int i;
        int proxMovimimiento = 0;
        boolean distanciaProxMovimiento;
        /* Si estamos en un nodo no visitado, seguiremos explorando, si no buscaremos un camino por anchura*/
        if (laberinto.get(hashCode(g.getX(), g.getY(), 6)) == null) {
            laberinto.put(hashCode(g.getX(), g.getY(), 6), g);
        }

        for (i = 1; i < 5; ++i) {
            distanciaProxMovimiento = Manhattam(g, c, i);

            if (posiblesMovimientos(g, i) && laberinto.get(this.hashCode(g.getX(), g.getY(), i)) == null && (proxMovimimiento == 0 || distanciaProxMovimiento)) {
                proxMovimimiento = i;
            }
        }

        if (proxMovimimiento != 0) {
            camino.add(proxMovimimiento);
            return proxMovimimiento;
        }

        /*Obtener del camino el siguiente movimiento*/
        switch (camino.pop()) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
        }
        return BOMB;

    }

    /**
     * Método para sacar los movimientos que podemos realizar en una casilla
     */
    private static boolean posiblesMovimientos(Grid g, int opcion) {
        switch (opcion) {
            case UP:
                return g.canGoUp();
            case DOWN:
                return g.canGoDown();
            case LEFT:
                return g.canGoLeft();
            case RIGHT:
                return g.canGoRight();
        }
        return false;
    }

    /**
     * Distancia Manhattam hacia el queso
     */
    private static boolean Manhattam(Grid g, Cheese c, int opcion) {
        int distancia = 0;
        int posActual;

        posActual = Math.abs(g.getX() - c.getX()) + Math.abs(g.getY() - c.getY());

        switch (opcion) {
            case UP:
                distancia = Math.abs(g.getX() - c.getX()) + Math.abs(g.getY() + 1 - c.getY());
            case DOWN:
                distancia = Math.abs(g.getX() - c.getX()) + Math.abs(g.getY() - 1 - c.getY());
            case LEFT:
                distancia = Math.abs(g.getX() - 1 - c.getX()) + Math.abs(g.getY() + 1 - c.getY());
            case RIGHT:
                distancia = Math.abs(g.getX() + 1 - c.getX()) + Math.abs(g.getY() + 1 - c.getY());
        }

        return (distancia < posActual);
    }

     

    /**
     * Devolvemos el movimiento, calculado con el método Anchura
     */
    public int move(Grid currentGrid, Cheese cheese) {
        return Anchura(currentGrid, cheese);
    }

    /**
     * Si aparece un queso nuevo, el camino se reinicia.
     */
    public void newCheese() {
        laberinto.clear();
        camino.clear();
    }

    /**
     * Si morimos por una bomba, el camino lo reiniciamos.
     */
    public void respawned() {
        laberinto.clear();
        camino.clear();
    }

}
