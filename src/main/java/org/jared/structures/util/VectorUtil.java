package org.jared.structures.util;

import org.bukkit.block.BlockFace;
import org.bukkit.util.Vector;

public class VectorUtil
{
    public static Vector toVector(BlockFace blockFace)
    {
        return new Vector(blockFace.getModX(), blockFace.getModY(), blockFace.getModZ());
    }

    public static BlockFace fromVector(Vector vector)
    {
        for (BlockFace blockFace : BlockFace.values())
        {
            if (vector.equals(new Vector(blockFace.getModX(), blockFace.getModY(), blockFace.getModZ())))
                return blockFace;
        }

        return null;
    }


    public static Vector rotateVector(Vector vector, int angle)
    {
        double rad = Math.toRadians(angle);

        double currentX = vector.getX();
        double currentZ = vector.getZ();

        double cosine = Math.cos(rad);
        double sine = Math.sin(rad);

        return new Vector((cosine * currentX - sine * currentZ), vector.getY(), (sine * currentX + cosine * currentZ));
    }
}
