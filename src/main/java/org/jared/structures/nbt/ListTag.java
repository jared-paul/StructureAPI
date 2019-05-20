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


import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * The {@code TAG_List} tag.
 */
public final class ListTag extends Tag {

    private final Class<? extends Tag> type;
    private final List<Tag> value;

    public ListTag()
    {
        this.type = null;
        this.value = Lists.newArrayList();
    }

    /**
     * Creates the tag with an empty name.
     *
     * @param type the type of tag
     * @param value the value of the tag
     */
    public ListTag(Class<? extends Tag> type, List<? extends Tag> value) {
        super();
        this.type = type;
        this.value = Lists.newArrayList(value);
    }

    /**
     * Gets the type of item in this list.
     *
     * @return The type of item in this list.
     */
    public Class<? extends Tag> getType() {
        return type;
    }

    @Override
    public List<Tag> getValue() {
        return value;
    }

    @Override
    public int getTypeId()
    {
        return 9;
    }

    /**
     * Create a new list tag with this tag's name and type.
     *
     * @param list the new list
     * @return a new list tag
     */
    public ListTag setValue(List<Tag> list) {
        return new ListTag(getType(), list);
    }

    /**
     * Get the tag if it exists at the given index.
     *
     * @param index the index
     * @return the tag or null
     */

    public Tag getIfExists(int index) {
        try {
            return value.get(index);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    /**
     * Get a byte array named with the given index.
     *
     * <p>If the index does not exist or its value is not a byte array tag,
     * then an empty byte array will be returned.</p>
     *
     * @param index the index
     * @return a byte array
     */
    public byte[] getByteArray(int index) {
        Tag tag = getIfExists(index);
        if (tag instanceof ByteArrayTag) {
            return ((ByteArrayTag) tag).getValue();
        } else {
            return new byte[0];
        }
    }

    /**
     * Get a byte named with the given index.
     *
     * <p>If the index does not exist or its value is not a byte tag,
     * then {@code 0} will be returned.</p>
     *
     * @param index the index
     * @return a byte
     */
    public byte getByte(int index) {
        Tag tag = getIfExists(index);
        if (tag instanceof ByteTag) {
            return ((ByteTag) tag).getValue();
        } else {
            return (byte) 0;
        }
    }

    /**
     * Get a double named with the given index.
     *
     * <p>If the index does not exist or its value is not a double tag,
     * then {@code 0} will be returned.</p>
     *
     * @param index the index
     * @return a double
     */
    public double getDouble(int index) {
        Tag tag = getIfExists(index);
        if (tag instanceof DoubleTag) {
            return ((DoubleTag) tag).getValue();
        } else {
            return 0;
        }
    }

    /**
     * Get a double named with the given index, even if it's another
     * type of number.
     *
     * <p>If the index does not exist or its value is not a number,
     * then {@code 0} will be returned.</p>
     *
     * @param index the index
     * @return a double
     */
    public double asDouble(int index) {
        Tag tag = getIfExists(index);
        if (tag instanceof ByteTag) {
            return ((ByteTag) tag).getValue();

        } else if (tag instanceof ShortTag) {
            return ((ShortTag) tag).getValue();

        } else if (tag instanceof IntTag) {
            return ((IntTag) tag).getValue();

        } else if (tag instanceof LongTag) {
            return ((LongTag) tag).getValue();

        } else if (tag instanceof FloatTag) {
            return ((FloatTag) tag).getValue();

        } else if (tag instanceof DoubleTag) {
            return ((DoubleTag) tag).getValue();

        } else {
            return 0;
        }
    }

    /**
     * Get a float named with the given index.
     *
     * <p>If the index does not exist or its value is not a float tag,
     * then {@code 0} will be returned.</p>
     *
     * @param index the index
     * @return a float
     */
    public float getFloat(int index) {
        Tag tag = getIfExists(index);
        if (tag instanceof FloatTag) {
            return ((FloatTag) tag).getValue();
        } else {
            return 0;
        }
    }

    /**
     * Get a {@code int[]} named with the given index.
     *
     * <p>If the index does not exist or its value is not an int array tag,
     * then an empty array will be returned.</p>
     *
     * @param index the index
     * @return an int array
     */
    public int[] getIntArray(int index) {
        Tag tag = getIfExists(index);
        if (tag instanceof IntArrayTag) {
            return ((IntArrayTag) tag).getValue();
        } else {
            return new int[0];
        }
    }

    /**
     * Get an int named with the given index.
     *
     * <p>If the index does not exist or its value is not an int tag,
     * then {@code 0} will be returned.</p>
     *
     * @param index the index
     * @return an int
     */
    public int getInt(int index) {
        Tag tag = getIfExists(index);
        if (tag instanceof IntTag) {
            return ((IntTag) tag).getValue();
        } else {
            return 0;
        }
    }

    /**
     * Get an int named with the given index, even if it's another
     * type of number.
     *
     * <p>If the index does not exist or its value is not a number,
     * then {@code 0} will be returned.</p>
     *
     * @param index the index
     * @return an int
     */
    public int asInt(int index) {
        Tag tag = getIfExists(index);
        if (tag instanceof ByteTag) {
            return ((ByteTag) tag).getValue();

        } else if (tag instanceof ShortTag) {
            return ((ShortTag) tag).getValue();

        } else if (tag instanceof IntTag) {
            return ((IntTag) tag).getValue();

        } else if (tag instanceof LongTag) {
            return ((LongTag) tag).getValue().intValue();

        } else if (tag instanceof FloatTag) {
            return ((FloatTag) tag).getValue().intValue();

        } else if (tag instanceof DoubleTag) {
            return ((DoubleTag) tag).getValue().intValue();

        } else {
            return 0;
        }
    }

    /**
     * Get a list of tags named with the given index.
     *
     * <p>If the index does not exist or its value is not a list tag,
     * then an empty list will be returned.</p>
     *
     * @param index the index
     * @return a list of tags
     */
    public List<Tag> getList(int index) {
        Tag tag = getIfExists(index);
        if (tag instanceof ListTag) {
            return ((ListTag) tag).getValue();
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Get a {@code TagList} named with the given index.
     *
     * <p>If the index does not exist or its value is not a list tag,
     * then an empty tag list will be returned.</p>
     *
     * @param index the index
     * @return a tag list instance
     */
    public ListTag getListTag(int index) {
        Tag tag = getIfExists(index);
        if (tag instanceof ListTag) {
            return (ListTag) tag;
        } else {
            return new ListTag(StringTag.class, Collections.<Tag>emptyList());
        }
    }

    /**
     * Get a list of tags named with the given index.
     *
     * <p>If the index does not exist or its value is not a list tag,
     * then an empty list will be returned. If the given index references
     * a list but the list of of a different type, then an empty
     * list will also be returned.</p>
     *
     * @param index the index
     * @param listType the class of the contained type
     * @return a list of tags
     * @param <T> the NBT type
     */
    @SuppressWarnings("unchecked")
    public <T extends Tag> List<T> getList(int index, Class<T> listType) {
        Tag tag = getIfExists(index);
        if (tag instanceof ListTag) {
            ListTag listTag = (ListTag) tag;
            if (listTag.getType().equals(listType)) {
                return (List<T>) listTag.getValue();
            } else {
                return Collections.emptyList();
            }
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Get a long named with the given index.
     *
     * <p>If the index does not exist or its value is not a long tag,
     * then {@code 0} will be returned.</p>
     *
     * @param index the index
     * @return a long
     */
    public long getLong(int index) {
        Tag tag = getIfExists(index);
        if (tag instanceof LongTag) {
            return ((LongTag) tag).getValue();
        } else {
            return 0L;
        }
    }

    /**
     * Get a long named with the given index, even if it's another
     * type of number.
     *
     * <p>If the index does not exist or its value is not a number,
     * then {@code 0} will be returned.</p>
     *
     * @param index the index
     * @return a long
     */
    public long asLong(int index) {
        Tag tag = getIfExists(index);
        if (tag instanceof ByteTag) {
            return ((ByteTag) tag).getValue();

        } else if (tag instanceof ShortTag) {
            return ((ShortTag) tag).getValue();

        } else if (tag instanceof IntTag) {
            return ((IntTag) tag).getValue();

        } else if (tag instanceof LongTag) {
            return ((LongTag) tag).getValue();

        } else if (tag instanceof FloatTag) {
            return ((FloatTag) tag).getValue().longValue();

        } else if (tag instanceof DoubleTag) {
            return ((DoubleTag) tag).getValue().longValue();

        } else {
            return 0;
        }
    }

    /**
     * Get a short named with the given index.
     *
     * <p>If the index does not exist or its value is not a short tag,
     * then {@code 0} will be returned.</p>
     *
     * @param index the index
     * @return a short
     */
    public short getShort(int index) {
        Tag tag = getIfExists(index);
        if (tag instanceof ShortTag) {
            return ((ShortTag) tag).getValue();
        } else {
            return 0;
        }
    }

    /**
     * Get a string named with the given index.
     *
     * <p>If the index does not exist or its value is not a string tag,
     * then {@code ""} will be returned.</p>
     *
     * @param index the index
     * @return a string
     */
    public String getString(int index) {
        Tag tag = getIfExists(index);
        if (tag instanceof StringTag) {
            return ((StringTag) tag).getValue();
        } else {
            return "";
        }
    }

    @Override
    public String asString()
    {
        StringBuilder var0 = new StringBuilder("[");
        for (int var1 = 0; var1 < this.value.size(); ++var1) {
            if (var1 != 0) {
                var0.append(',');
            }
            var0.append(this.value.get(var1));
        }
        return var0.append(']').toString();
    }

    @Override
    public String toString() {
        StringBuilder bldr = new StringBuilder();
        bldr.append("TAG_List").append(": ").append(value.size()).append(" entries of type ").append(NBTUtils.getTypeName(type)).append("\r\n{\r\n");
        for (Tag t : value) {
            bldr.append("   ").append(t.toString().replaceAll("\r\n", "\r\n   ")).append("\r\n");
        }
        bldr.append("}");
        return bldr.toString();
    }

}
