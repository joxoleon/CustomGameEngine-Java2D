package engine.datastructures;

public class Vector3 {
    
    // Fields.
    public float x;
    public float y;
    public float z;
    
    // Constructors.
    public Vector3()
    {
        this(0.0f, 0.0f, 0.0f);
    }
    
    public Vector3(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Vector3(Vector3 v)
    {
        x = v.x;
        y = v.y;
        z = v.z;
    }
    
    // Mathematical operations.
    public static Vector3 sum(Vector3 v1, Vector3 v2)
    {
        return new Vector3(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
    }
    
    public static Vector3 dif(Vector3 v1, Vector3 v2)
    {
        return new Vector3(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
    }
    
    public Vector3 add(Vector3 v)
    {
        copyVector(this, Vector3.sum(this, v));
        return this;
    }
    
    public Vector3 sub(Vector3 v)
    {
        copyVector(this, Vector3.dif(this, v));
        return this;
    }
    
    public Vector3 mul(float f)
    {
        x *= f;
        y *= f;
        z *= f;
        
        return this;
    }
    
    public Vector3 mul(Vector3 v)
    {
    	x *= v.x;
    	y *= v.y;
    	z *= v.z;
    	
    	return this;
    }
    
    public Vector3 div(float f)
    {
        if (f != 0)
        {
            return mul(1.0f / f);
        }
        else
        {
            System.err.println("Vector3.div(float): Division by zero!");
            return this;
        }
    }
    
    public Vector3 div(Vector3 v)
    {
        if (v.x == 0 || v.y == 0 || v.z == 0)
        {
            System.err.println("Vector3.div(Vector3): Division by zero!");
            return this;
        }
        else
        {
            x /= v.x;
            y /= v.y;
            z /= v.z;
            
            return this;
        }
    }
    
    public float magnitude()
    {
        return distance(new Vector3(), this);
    }
    
    public void normalize()
    {
        if ((x != 0) || (y != 0) || (z != 0))
        {
            float scale = 1.0f / magnitude();
            
            x *= scale;
            y *= scale;
            z *= scale;
        }
    }
    
    // OVO JE ZA 2D, PREPRAVITI NA 3D!!!
    public Vector3 rotate(float angle)
    {
    	Vector3 result = new Vector3(this);
    	
    	float sin = (float)Math.sin(angle);
    	float cos = (float)Math.cos(angle);
    	
    	float tempx = result.x;
    	result.x = result.x * cos + result.y * sin;
    	result.y = - tempx * sin + result.y * cos;
    	
    	return result;
    }
    
    public static float dot(Vector3 v1, Vector3 v2)
    {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }
    
    public static Vector3 cross(Vector3 v1, Vector3 v2)
    {
        return new Vector3(
            v1.y * v2.z - v1.z * v2.y,
            v1.z * v2.x - v1.x * v2.z,
            v1.x * v2.y - v1.y * v2.x);
    }
    
    public static Vector3 clamp(Vector3 v, Vector3 min, Vector3 max)
    {
        if (min.x > max.x || min.y > max.y || min.z > max.z)
        {
            System.err.println("Vector3.clamp: min > max!");
            return v;
        }
        else
        {
            Vector3 result = new Vector3();
            
            result.x = Math.max(v.x, min.x);
            result.y = Math.max(v.y, min.y);
            result.z = Math.max(v.z, min.z);
            
            result.x = Math.min(v.x, max.x);
            result.y = Math.min(v.y, max.y);
            result.z = Math.min(v.z, max.z);
            
            return v;
        }
    }
    
    public static float distance(Vector3 v1, Vector3 v2)
    {
        return (float)Math.sqrt(Vector3.distanceSq(v1, v2));
    }
    
    public static float distanceSq(Vector3 v1, Vector3 v2)
    {
        return (float)
                (Math.pow(v2.x - v1.x, 2) +
                 Math.pow(v2.y - v1.y, 2) +
                 Math.pow(v2.z - v1.z, 2));
    }
    
    public String toString()
    {
    	return "[" + x +", " + y +", " + z + "]";
    }
    
    // Get/set and helper methods.
    public void set(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public void set(Vector3 v)
    {
        set(v.x, v.y, v.z);
    }
    
    private void copyVector(Vector3 dst, Vector3 src)
    {
        dst.x = src.x;
        dst.y = src.y;
        dst.z = src.z;
    }

}
