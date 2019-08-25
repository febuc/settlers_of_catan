package tools;

/**
 * Simulates a 2D vector.
 */
public class Vector2D<T> {


    /**
     * X Coordinate value
     */
    public T x;
    /**
     * Y Coordinate value
     */
    public T y;

    /**
     * Constructor
     *
     * @param x X-Coordinate
     * @param y Y-Coordinate
     */
    public Vector2D(T x, T y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Redefines the toString method
     * @return
     */
    @Override
    public String toString(){
        return "("+x+","+y+")";
    }
    /**
     * Checks if two vectors are the same.
     *
     * @param vec The vector with want to check against.
     * @return True, if vector components are equal.
     */
    public boolean isEqualTo(tools.Vector3D<T> vec) {
        return x == vec.x && y == vec.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector2D<?> vector2D = (Vector2D<?>) o;

        if (x != null ? !x.equals(vector2D.x) : vector2D.x != null) return false;
        return y != null ? y.equals(vector2D.y) : vector2D.y == null;
    }

    @Override
    public int hashCode() {
        int result = x != null ? x.hashCode() : 0;
        result = 31 * result + (y != null ? y.hashCode() : 0);
        return result;
    }
}
