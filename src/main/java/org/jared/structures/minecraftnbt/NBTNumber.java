package org.jared.structures.minecraftnbt;

public abstract class NBTNumber implements INBTBase
{
    protected NBTNumber() {
    }

    public abstract long asLong();

    public abstract int asInt();

    public abstract short asShort();

    public abstract byte asByte();

    public abstract double asDouble();

    public abstract float asFloat();

    public abstract Number asNumber();
}
