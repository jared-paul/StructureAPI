package org.jared.structures.nbt;

import java.util.HashMap;
import java.util.Map;


/**
 * Helps create compound tags.
 */
public class CompoundTagBuilder {

    private final Map<String, Tag> entries;

    /**
     * Create a new instance.
     */
    CompoundTagBuilder() {
        this.entries = new HashMap<String, Tag>();
    }

    /**
     * Create a new instance and use the given map (which will be modified).
     *
     * @param value the value
     */
    CompoundTagBuilder(Map<String, Tag> value) {
        this.entries = value;
    }

    /**
     * Put the given key and tag into the compound tag.
     *
     * @param key they key
     * @param value the value
     * @return this object
     */
    public CompoundTagBuilder put(String key, Tag value) {
        entries.put(key, value);
        return this;
    }

    /**
     * Put the given key and value into the compound tag as a
     * {@code ByteArrayTag}.
     *
     * @param key they key
     * @param value the value
     * @return this object
     */
    public CompoundTagBuilder putByteArray(String key, byte[] value) {
        return put(key, new ByteArrayTag(value));
    }

    /**
     * Put the given key and value into the compound tag as a
     * {@code ByteTag}.
     *
     * @param key they key
     * @param value the value
     * @return this object
     */
    public CompoundTagBuilder putByte(String key, byte value) {
        return put(key, new ByteTag(value));
    }

    /**
     * Put the given key and value into the compound tag as a
     * {@code DoubleTag}.
     *
     * @param key they key
     * @param value the value
     * @return this object
     */
    public CompoundTagBuilder putDouble(String key, double value) {
        return put(key, new DoubleTag(value));
    }

    /**
     * Put the given key and value into the compound tag as a
     * {@code FloatTag}.
     *
     * @param key they key
     * @param value the value
     * @return this object
     */
    public CompoundTagBuilder putFloat(String key, float value) {
        return put(key, new FloatTag(value));
    }

    /**
     * Put the given key and value into the compound tag as a
     * {@code IntArrayTag}.
     *
     * @param key they key
     * @param value the value
     * @return this object
     */
    public CompoundTagBuilder putIntArray(String key, int[] value) {
        return put(key, new IntArrayTag(value));
    }

    /**
     * Put the given key and value into the compound tag as an {@code IntTag}.
     *
     * @param key they key
     * @param value the value
     * @return this object
     */
    public CompoundTagBuilder putInt(String key, int value) {
        return put(key, new IntTag(value));
    }

    /**
     * Put the given key and value into the compound tag as a
     * {@code LongTag}.
     *
     * @param key they key
     * @param value the value
     * @return this object
     */
    public CompoundTagBuilder putLong(String key, long value) {
        return put(key, new LongTag(value));
    }

    /**
     * Put the given key and value into the compound tag as a
     * {@code ShortTag}.
     *
     * @param key they key
     * @param value the value
     * @return this object
     */
    public CompoundTagBuilder putShort(String key, short value) {
        return put(key, new ShortTag(value));
    }

    /**
     * Put the given key and value into the compound tag as a
     * {@code StringTag}.
     *
     * @param key they key
     * @param value the value
     * @return this object
     */
    public CompoundTagBuilder putString(String key, String value) {
        return put(key, new StringTag(value));
    }

    /**
     * Put all the entries from the given map into this map.
     *
     * @param value the map of tags
     * @return this object
     */
    public CompoundTagBuilder putAll(Map<String, ? extends Tag> value) {
        for (Map.Entry<String, ? extends Tag> entry : value.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
        return this;
    }

    /**
     * Build an unnamed compound tag with this builder's entries.
     *
     * @return the new compound tag
     */
    public CompoundTag build() {
        return new CompoundTag(new HashMap<String, Tag>(entries));
    }

    /**
     * Create a new builder instance.
     *
     * @return a new builder
     */
    public static CompoundTagBuilder create() {
        return new CompoundTagBuilder();
    }

}
