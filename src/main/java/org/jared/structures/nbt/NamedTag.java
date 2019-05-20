package org.jared.structures.nbt;


/**
 * A tag that has a name.
 */
public class NamedTag {

    private final String name;
    private final Tag tag;

    /**
     * Create a new named tag.
     *
     * @param name the name
     * @param tag the tag
     */
    public NamedTag(String name, Tag tag) {
        this.name = name;
        this.tag = tag;
    }

    /**
     * Get the name of the tag.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the tag.
     *
     * @return the tag
     */
    public Tag getTag() {
        return tag;
    }

}
