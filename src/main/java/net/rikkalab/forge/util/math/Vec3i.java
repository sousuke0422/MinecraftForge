package net.rikkalab.forge.util.math;

import com.google.common.base.Objects;

import net.minecraft.util.MathHelper;

public class Vec3i implements Comparable<Vec3i> {

    private final int x, y, z;

    public Vec3i(int xIn, int yIn, int zIn) {
        x = xIn;
        y = yIn;
        z = zIn;
    }

    public Vec3i(double xIn, double yIn, double zIn) {
        this(MathHelper.floor_double(xIn), MathHelper.floor_double(yIn), MathHelper.floor_double(zIn));
    }

    @Override
    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_)
            return true;
        else if (!(p_equals_1_ instanceof Vec3i))
            return false;
        else {
            Vec3i vec3i = (Vec3i) p_equals_1_;
            return getX() != vec3i.getX() ? false : getY() != vec3i.getY() ? false : getZ() == vec3i.getZ();
        }
    }

    @Override
    public int hashCode() {
        return (getY() + getZ() * 31) * 31 + getX();
    }

    @Override
    public int compareTo(Vec3i vec) {
        return getY() == vec.getY() ? getZ() == vec.getZ() ? getX() - vec.getX() : getZ() - vec.getZ()
                : getY() - vec.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public Vec3i crossProduct(Vec3i vec) {
        return new Vec3i(getY() * vec.getZ() - getZ() * vec.getY(), getZ() * vec.getX() - getX() * vec.getZ(),
                getX() * vec.getY() - getY() * vec.getX());
    }

    public boolean isWithinDistance(Vec3i vec, double distance) {
        return this.getSquaredDistance((double) vec.getX(), (double) vec.getY(), (double) vec.getZ(), false) < distance
                * distance;
    }

    public double getSquaredDistance(Vec3i vec) {
        return this.getSquaredDistance((double) vec.getX(), (double) vec.getY(), (double) vec.getZ(), true);
    }

    public double getSquaredDistance(Vec3i vec, boolean treatAsBlockPos) {
        return this.getSquaredDistance((double) vec.x, (double) vec.y, (double) vec.z, treatAsBlockPos);
    }

    public double getSquaredDistance(double x, double y, double z, boolean treatAsBlockPos) {
        double d = treatAsBlockPos ? 0.5D : 0.0D;
        double e = (double) this.getX() + d - x;
        double f = (double) this.getY() + d - y;
        double g = (double) this.getZ() + d - z;
        return e * e + f * f + g * g;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("x", getX()).add("y", getY()).add("z", getZ()).toString();
    }
}