/*
 * JNBT License
 *
 * Copyright (c) 2010 Graham Edgecombe
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright notice,
 *       this list of conditions and the following disclaimer.
 *
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 *     * Neither the name of the JNBT team nor the names of its
 *       contributors may be used to endorse or promote products derived from
 *       this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package org.jared.structures.nbt;

import com.google.common.collect.Maps;

import java.util.*;
import java.util.regex.Pattern;

/**
 * The {@code TAG_Compound} tag.
 */
public final class CompoundTag extends Tag
{

    private final Map<String, Tag> value;


    public CompoundTag()
    {
        this.value = Maps.newHashMap();
    }

    /**
     * Creates the tag with an empty name.
     *
     * @param value the value of the tag
     */
    public CompoundTag(Map<String, Tag> value)
    {
        super();
        this.value = Collections.unmodifiableMap(value);
    }

    /**
     * Returns whether this compound tag contains the given key.
     *
     * @param key the given key
     * @return true if the tag contains the given key
     */
    public boolean containsKey(String key)
    {
        return value.containsKey(key);
    }

    @Override
    public Map<String, Tag> getValue()
    {
        return value;
    }

    @Override
    public int getTypeId()
    {
        return 10;
    }

    public void set(String key, Tag tag)
    {
        this.value.put(key, tag);
    }

    /**
     * Return a new compound tag with the given values.
     *
     * @param value the value
     * @return the new compound tag
     */
    public CompoundTag setValue(Map<String, Tag> value)
    {
        return new CompoundTag(value);
    }

    /**
     * Create a compound tag builder.
     *
     * @return the builder
     */
    public CompoundTagBuilder createBuilder()
    {
        return new CompoundTagBuilder(new HashMap<String, Tag>(value));
    }

    /**
     * Get a byte array named with the given key.
     *
     * <p>If the key does not exist or its value is not a byte array tag,
     * then an empty byte array will be returned.</p>
     *
     * @param key the key
     * @return a byte array
     */
    public byte[] getByteArray(String key)
    {
        Tag tag = value.get(key);
        if (tag instanceof ByteArrayTag)
        {
            return ((ByteArrayTag) tag).getValue();
        }
        else
        {
            return new byte[0];
        }
    }

    /**
     * Get a byte named with the given key.
     *
     * <p>If the key does not exist or its value is not a byte tag,
     * then {@code 0} will be returned.</p>
     *
     * @param key the key
     * @return a byte
     */
    public byte getByte(String key)
    {
        Tag tag = value.get(key);
        if (tag instanceof ByteTag)
        {
            return ((ByteTag) tag).getValue();
        }
        else
        {
            return (byte) 0;
        }
    }

    /**
     * Get a double named with the given key.
     *
     * <p>If the key does not exist or its value is not a double tag,
     * then {@code 0} will be returned.</p>
     *
     * @param key the key
     * @return a double
     */
    public double getDouble(String key)
    {
        Tag tag = value.get(key);
        if (tag instanceof DoubleTag)
        {
            return ((DoubleTag) tag).getValue();
        }
        else
        {
            return 0;
        }
    }

    /**
     * Get a double named with the given key, even if it's another
     * type of number.
     *
     * <p>If the key does not exist or its value is not a number,
     * then {@code 0} will be returned.</p>
     *
     * @param key the key
     * @return a double
     */
    public double asDouble(String key)
    {
        Tag tag = value.get(key);
        if (tag instanceof ByteTag)
        {
            return ((ByteTag) tag).getValue();

        }
        else if (tag instanceof ShortTag)
        {
            return ((ShortTag) tag).getValue();

        }
        else if (tag instanceof IntTag)
        {
            return ((IntTag) tag).getValue();

        }
        else if (tag instanceof LongTag)
        {
            return ((LongTag) tag).getValue();

        }
        else if (tag instanceof FloatTag)
        {
            return ((FloatTag) tag).getValue();

        }
        else if (tag instanceof DoubleTag)
        {
            return ((DoubleTag) tag).getValue();

        }

        else
        {
            return 0;
        }
    }

    /**
     * Get a float named with the given key.
     *
     * <p>If the key does not exist or its value is not a float tag,
     * then {@code 0} will be returned.</p>
     *
     * @param key the key
     * @return a float
     */
    public float getFloat(String key)
    {
        Tag tag = value.get(key);
        if (tag instanceof FloatTag)
        {
            return ((FloatTag) tag).getValue();
        }
        else
        {
            return 0;
        }
    }

    /**
     * Get a {@code int[]} named with the given key.
     *
     * <p>If the key does not exist or its value is not an int array tag,
     * then an empty array will be returned.</p>
     *
     * @param key the key
     * @return an int array
     */
    public int[] getIntArray(String key)
    {
        Tag tag = value.get(key);
        if (tag instanceof IntArrayTag)
        {
            return ((IntArrayTag) tag).getValue();
        }
        else
        {
            return new int[0];
        }
    }

    /**
     * Get an int named with the given key.
     *
     * <p>If the key does not exist or its value is not an int tag,
     * then {@code 0} will be returned.</p>
     *
     * @param key the key
     * @return an int
     */
    public int getInt(String key)
    {
        Tag tag = value.get(key);
        if (tag instanceof IntTag)
        {
            return ((IntTag) tag).getValue();
        }
        else
        {
            return 0;
        }
    }

    /**
     * Get an int named with the given key, even if it's another
     * type of number.
     *
     * <p>If the key does not exist or its value is not a number,
     * then {@code 0} will be returned.</p>
     *
     * @param key the key
     * @return an int
     */
    public int asInt(String key)
    {
        Tag tag = value.get(key);
        if (tag instanceof ByteTag)
        {
            return ((ByteTag) tag).getValue();

        }
        else if (tag instanceof ShortTag)
        {
            return ((ShortTag) tag).getValue();

        }
        else if (tag instanceof IntTag)
        {
            return ((IntTag) tag).getValue();

        }
        else if (tag instanceof LongTag)
        {
            return ((LongTag) tag).getValue().intValue();

        }
        else if (tag instanceof FloatTag)
        {
            return ((FloatTag) tag).getValue().intValue();

        }
        else if (tag instanceof DoubleTag)
        {
            return ((DoubleTag) tag).getValue().intValue();

        }
        else
        {
            return 0;
        }
    }

    /**
     * Get a list of tags named with the given key.
     *
     * <p>If the key does not exist or its value is not a list tag,
     * then an empty list will be returned.</p>
     *
     * @param key the key
     * @return a list of tags
     */
    public List<Tag> getList(String key)
    {
        Tag tag = value.get(key);
        if (tag instanceof ListTag)
        {
            return ((ListTag) tag).getValue();
        }
        else
        {
            return Collections.emptyList();
        }
    }

    /**
     * Get a {@code TagList} named with the given key.
     *
     * <p>If the key does not exist or its value is not a list tag,
     * then an empty tag list will be returned.</p>
     *
     * @param key the key
     * @return a tag list instance
     */
    public ListTag getListTag(String key)
    {
        Tag tag = value.get(key);
        if (tag instanceof ListTag)
        {
            return (ListTag) tag;
        }
        else
        {
            return new ListTag(StringTag.class, Collections.<Tag>emptyList());
        }
    }

    /**
     * Get a list of tags named with the given key.
     *
     * <p>If the key does not exist or its value is not a list tag,
     * then an empty list will be returned. If the given key references
     * a list but the list of of a different type, then an empty
     * list will also be returned.</p>
     *
     * @param key      the key
     * @param listType the class of the contained type
     * @param <T>      the type of list
     * @return a list of tags
     */
    @SuppressWarnings("unchecked")
    public <T extends Tag> List<T> getList(String key, Class<T> listType)
    {
        Tag tag = value.get(key);
        if (tag instanceof ListTag)
        {
            ListTag listTag = (ListTag) tag;
            if (listTag.getType().equals(listType))
            {
                return (List<T>) listTag.getValue();
            }
            else
            {
                return Collections.emptyList();
            }
        }
        else
        {
            return Collections.emptyList();
        }
    }

    /**
     * Get a {@code long[]} named with the given key.
     *
     * <p>If the key does not exist or its value is not a long array tag,
     * then an empty array will be returned.</p>
     *
     * @param key the key
     * @return an long array
     */
    public long[] getLongArray(String key)
    {
        Tag tag = value.get(key);
        if (tag instanceof IntArrayTag)
        {
            return ((LongArrayTag) tag).getValue();
        }
        else
        {
            return new long[0];
        }
    }

    /**
     * Get a long named with the given key.
     *
     * <p>If the key does not exist or its value is not a long tag,
     * then {@code 0} will be returned.</p>
     *
     * @param key the key
     * @return a long
     */
    public long getLong(String key)
    {
        Tag tag = value.get(key);
        if (tag instanceof LongTag)
        {
            return ((LongTag) tag).getValue();
        }
        else
        {
            return 0L;
        }
    }

    /**
     * Get a long named with the given key, even if it's another
     * type of number.
     *
     * <p>If the key does not exist or its value is not a number,
     * then {@code 0} will be returned.</p>
     *
     * @param key the key
     * @return a long
     */
    public long asLong(String key)
    {
        Tag tag = value.get(key);
        if (tag instanceof ByteTag)
        {
            return ((ByteTag) tag).getValue();

        }
        else if (tag instanceof ShortTag)
        {
            return ((ShortTag) tag).getValue();

        }
        else if (tag instanceof IntTag)
        {
            return ((IntTag) tag).getValue();

        }
        else if (tag instanceof LongTag)
        {
            return ((LongTag) tag).getValue();

        }
        else if (tag instanceof FloatTag)
        {
            return ((FloatTag) tag).getValue().longValue();

        }
        else if (tag instanceof DoubleTag)
        {
            return ((DoubleTag) tag).getValue().longValue();

        }
        else
        {
            return 0L;
        }
    }

    /**
     * Get a short named with the given key.
     *
     * <p>If the key does not exist or its value is not a short tag,
     * then {@code 0} will be returned.</p>
     *
     * @param key the key
     * @return a short
     */
    public short getShort(String key)
    {
        Tag tag = value.get(key);
        if (tag instanceof ShortTag)
        {
            return ((ShortTag) tag).getValue();
        }
        else
        {
            return 0;
        }
    }

    /**
     * Get a string named with the given key.
     *
     * <p>If the key does not exist or its value is not a string tag,
     * then {@code ""} will be returned.</p>
     *
     * @param key the key
     * @return a string
     */
    public String getString(String key)
    {
        Tag tag = value.get(key);
        if (tag instanceof StringTag)
        {
            return ((StringTag) tag).getValue();
        }
        else
        {
            return "";
        }
    }

    public void setInt(String key, int value)
    {
        this.value.put(key, new IntTag(value));
    }

    @Override
    public String asString()
    {
        Iterator var2;
        StringBuilder var0 = new StringBuilder("{");
        Set<String> var1 = this.value.keySet();
        var2 = var1.iterator();
        while (var2.hasNext())
        {
            String var3 = (String) var2.next();
            if (var0.length() != 1)
            {
                var0.append(',');
            }
            var0.append(s(var3)).append(':').append(this.value.get(var3));
        }
        return var0.append('}').toString();
    }

    @Override
    public String toString()
    {
        StringBuilder bldr = new StringBuilder();
        bldr.append("TAG_Compound").append(": ").append(value.size()).append(" entries\r\n{\r\n");
        for (Map.Entry<String, Tag> entry : value.entrySet())
        {
            bldr.append("   ").append(entry.getValue().toString().replaceAll("\r\n", "\r\n   ")).append("\r\n");
        }
        bldr.append("}");
        return bldr.toString();
    }

    protected static String s(String var0)
    {
        return c.matcher(var0).matches() ? var0 : StringTag.a(var0);
    }

    private static final Pattern c = Pattern.compile("[A-Za-z0-9._+-]+");
}
