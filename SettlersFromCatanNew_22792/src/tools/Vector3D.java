package tools;

/**
 * Simulates a 3D vector.
 */
public class Vector3D<T> {
    /**
     * X Coordinate value
     */
    public T x;
    /**
     * Y Coordinate value
     */
    public T y;
    /**
     * Y Coordinate value
     */
    public T z;

    /**
     * Constructor
     *
     * @param x X-Coordinate
     * @param y Y-Coordinate
     * @param z Z-Coordinate
     */
    public Vector3D(T x, T y, T z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Casts a 3D vector into a 2D vector (z component gets lost!)
     * @return A vector 2D withotu the z component.
     */
    public Vector2D<T> castTo2D(){
        return new Vector2D<T>(x,y);
    }
    /**
     * Redefines the toString method
     * @return
     */
    @Override
    public String toString(){
        return "("+x+","+y+","+z+")";
    }

    /**
     * Checks if two vectors are the same.
     *
     * @param vec The vector with want to check against.
     * @return True, if vector components are equal.
     */
    public boolean isEqualTo(Vector3D<T> vec) {
        return x == vec.x && y == vec.y && z == vec.z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector3D<?> vector3D = (Vector3D<?>) o;

        if (x != null ? !x.equals(vector3D.x) : vector3D.x != null) return false;
        if (y != null ? !y.equals(vector3D.y) : vector3D.y != null) return false;
        return z != null ? z.equals(vector3D.z) : vector3D.z == null;
    }

    @Override
    public int hashCode() {
        int result = x != null ? x.hashCode() : 0;
        result = 31 * result + (y != null ? y.hashCode() : 0);
        result = 31 * result + (z != null ? z.hashCode() : 0);
        return result;
    }
}
