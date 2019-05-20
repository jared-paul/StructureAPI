package org.jared.structures;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.EntityType;
import org.bukkit.util.Vector;
import org.jared.structures.nbt.*;
import org.jared.structures.nbt.util.NBTUpdater;
import org.jared.structures.util.VectorUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Structure
{
    private int dimensions[];
    private Map<Vector, BlockData> blockMap = Maps.newHashMap();
    private List<EntityInfo> entities = Lists.newArrayList();

    private NBTDataExtractor dataExtractor;

    public Structure(File file) throws IOException
    {
        this();
        loadFromFile(file);
    }

    public Structure()
    {
        this.dataExtractor = new NBTDataExtractor();
    }

    public void place(Location base)
    {
        for (Map.Entry<Vector, BlockData> blockEntry : blockMap.entrySet())
        {
            Vector position = blockEntry.getKey();
            BlockData data = blockEntry.getValue();

            base.clone().add(position).getBlock().setBlockData(data);
        }

        for (EntityInfo entityInfo : entities)
        {
            String entityID = entityInfo.getNBT().getString("id");
            EntityType entityType = EntityType.valueOf(entityID.replace("minecraft:", "").toUpperCase());

            base.getWorld().spawnEntity(base.clone().add(entityInfo.getBlockPosition()), entityType);
        }
    }

    public void populateData(CompoundTag data)
    {
        ListTag sizeTag = data.getListTag("size");
        this.dimensions = new int[]{sizeTag.getInt(0), sizeTag.getInt(1), sizeTag.getInt(2)};

        ListTag blockTags = data.getListTag("blocks");
        ListTag paletteTags;

        if (data.containsKey("palette"))
        {
            paletteTags = data.getListTag("palette");

            populateBlockStates(paletteTags, blockTags);
        }
        else
        {
            paletteTags = data.getListTag("palettes");

            for (int i = 0; i < paletteTags.getValue().size(); i++)
            {
                populateBlockStates(paletteTags.getListTag(i), blockTags);
            }
        }

        ListTag entities = data.getListTag("entities");
        populateEntities(entities);
    }

    private void populateBlockStates(ListTag paletteTags, ListTag blockTags)
    {
        for (int i = 0; i < blockTags.getValue().size(); i++)
        {
            CompoundTag blockTag = (CompoundTag) blockTags.getIfExists(i);
            ListTag positionTags = blockTag.getListTag("pos");
            CompoundTag stateTag = (CompoundTag) paletteTags.getIfExists(blockTag.getInt("state"));

            Vector position = new Vector(positionTags.getInt(0), positionTags.getInt(1), positionTags.getInt(2));
            NBTDataExtractor.BlockInfo blockInfo = dataExtractor.getBlockInfo(stateTag);

            this.blockMap.put(position, blockInfo.getData());
        }
    }

    private void populateEntities(ListTag entities)
    {
        for (int i = 0; i < entities.getValue().size(); i++)
        {
            CompoundTag entity = (CompoundTag) entities.getValue().get(i);

            ListTag positionTags = entity.getListTag("pos");
            Vector position = new Vector(positionTags.getDouble(0), positionTags.getDouble(1), positionTags.getDouble(2));

            ListTag blockPosTags = entity.getListTag("blockPos");
            Vector blockPosition = new Vector(blockPosTags.getInt(0), blockPosTags.getInt(1), blockPosTags.getInt(2));

            if (entity.containsKey("nbt"))
            {
                CompoundTag nbt = (CompoundTag) entity.getValue().get("nbt");
                this.entities.add(new EntityInfo(position, blockPosition, nbt));
            }
        }
    }

//    public void populateData(NBTTagCompound data)
//    {
//        NBTTagList sizeTag = data.getList("size", 3);
//        this.dimensions = new int[]{sizeTag.getInt(0), sizeTag.getInt(1), sizeTag.getInt(2)};
//
//        NBTTagList blockTags = data.getList("blocks", 10);
//        NBTTagList paletteTags;
//
//        if (data.contains("palette", 9))
//        {
//            paletteTags = data.getList("palette", 10);
//
//            for (int i = 0; i < blockTags.size(); i++)
//            {
//                NBTTagCompound blockTag = blockTags.getCompound(i);
//                NBTTagList positionTags = blockTag.getList("pos", 3);
//                NBTTagCompound stateTag = paletteTags.getCompound(blockTag.getInt("state"));
//
//                Vector position = new Vector(positionTags.getInt(0), positionTags.getInt(1), positionTags.getInt(2));
//                NBTDataExtractor.BlockInfo blockInfo = NBTDataExtractor.getBlockInfo(stateTag);
//
//                this.blockMap.put(position, blockInfo.getData());
//            }
//        }
//    }

    public void loadFromFile(File file) throws IOException
    {
        FileInputStream fileInputStream = new FileInputStream(file);

        NBTInputStream inputStream = new NBTInputStream(fileInputStream, true);
        CompoundTag data = (CompoundTag) inputStream.readNamedTag().getTag();
        data = NBTUpdater.updateData(data);
        populateData(data);


//        NBTTagCompound data = CompressedStreamTools.readCompressed(fileInputStream);
//        data = NBTUpdater.updateData(data);
//
//        populateData(data);

        //populateData(NBTUpdater.updateData(CompressedStreamTools.readCompressed(new FileInputStream(file))));
    }

    //TODO jeez this is bad, can't use iterator, produces CME exception, temp fix
    public void rotate(int angle)
    {
        Map<Vector, BlockData> blockMapCopy = Maps.newHashMap();

        for (Map.Entry<Vector, BlockData> blockEntry : blockMap.entrySet())
        {
            Vector vector = blockEntry.getKey();
            BlockData blockData = blockEntry.getValue();

            if (blockData instanceof Directional)
            {
                Directional directional = (Directional) blockData;
                Vector directionVector = VectorUtil.toVector(directional.getFacing());
                directionVector = VectorUtil.rotateVector(directionVector, angle);
                ((Directional) blockData).setFacing(VectorUtil.fromVector(directionVector));
            }

            Vector offset = VectorUtil.rotateVector(vector, angle);

            blockMapCopy.put(offset, blockData);
        }

        this.blockMap = blockMapCopy;

//        Iterator<Vector> blockEntryIterator = blockMap.keySet().iterator();
//        while (blockEntryIterator.hasNext())
//        {
//            Vector position = blockEntryIterator.next();
//            BlockData blockData = blockMap.get(position);
//
//            if (blockData instanceof Directional)
//            {
//                Directional directional = (Directional) blockData;
//                BlockFace facing = directional.getFacing();
//
//                Vector directionVector = VectorUtil.toVector(facing);
//                directionVector = VectorUtil.rotateVector(directionVector, angle);
//                ((Directional) blockData).setFacing(VectorUtil.fromVector(directionVector));
//            }
//
//            Vector rotated = VectorUtil.rotateVector(position, angle);
//
//            this.blockMap.put(rotated, blockData);
//
//            blockEntryIterator.remove();
//        }
    }

    private class EntityInfo
    {
        private Vector position;
        private Vector blockPosition;
        private CompoundTag nbt;

        public EntityInfo(Vector position, Vector blockPosition, CompoundTag nbt)
        {
            this.position = position;
            this.blockPosition = blockPosition;
            this.nbt = nbt;
        }

        public Vector getPosition()
        {
            return position;
        }

        public Vector getBlockPosition()
        {
            return blockPosition;
        }

        public CompoundTag getNBT()
        {
            return nbt;
        }
    }

    private class NBTDataExtractor
    {
        public BlockInfo getBlockInfo(CompoundTag data)
        {
            BlockInfo blockInfo;

            if (!data.containsKey("Name"))
            {
                blockInfo = new BlockInfo(Material.AIR, Material.AIR.createBlockData());
            }
            else
            {
                String materialName = data.getString("Name").replace("minecraft:", "").toUpperCase();
                Material material = Material.getMaterial(materialName);

                blockInfo = new BlockInfo(material, material.createBlockData());

                if (data.containsKey("Properties"))
                {
                    CompoundTag propertyTag = (CompoundTag) data.getValue().get("Properties");

                    String blockDataString = toBlockData(propertyTag.getValue());

                    blockInfo = new BlockInfo(material, material.createBlockData(blockDataString));
                }
            }

            return blockInfo;
        }

        private String toBlockData(Map<String, Tag> properties)
        {
            StringBuilder stringBuilder = new StringBuilder("[");

            Iterator<Map.Entry<String, Tag>> entryIterator = properties.entrySet().iterator();
            while (entryIterator.hasNext())
            {
                Map.Entry<String, Tag> entry = entryIterator.next();
                String key = entry.getKey();
                StringTag value = (StringTag) entry.getValue();

                stringBuilder.append(key).append("=").append(value.asString());

                if (entryIterator.hasNext())
                {
                    stringBuilder.append(",");
                }
            }

            return stringBuilder.append("]").toString();
        }


//    public static BlockInfo getBlockInfo(NBTTagCompound data)
//    {
//        BlockInfo blockInfo;
//
//        if (!data.contains("Name", 8))
//        {
//            blockInfo = new BlockInfo(Material.AIR, Material.AIR.createBlockData());
//        }
//        else
//        {
//            String materialName = data.getString("Name").replace("minecraft:", "").toUpperCase();
//            Material material = Material.getMaterial(materialName);
//
//            blockInfo = new BlockInfo(material, material.createBlockData());
//
//            if (data.contains("Properties", 10))
//            {
//                NBTTagCompound propertyTag = data.getCompound("Properties");
//
//                String blockDataString = toBlockData(propertyTag.getTagMap());
//
//                blockInfo = new BlockInfo(material, material.createBlockData(blockDataString));
//            }
//        }
//
//        return blockInfo;
//    }
//
//    private static String toBlockData(Map<String, INBTBase> properties)
//    {
//        StringBuilder stringBuilder = new StringBuilder("[");
//
//        Iterator<Map.Entry<String, INBTBase>> entryIterator = properties.entrySet().iterator();
//        while (entryIterator.hasNext())
//        {
//            Map.Entry<String, INBTBase> entry = entryIterator.next();
//            String key = entry.getKey();
//            NBTTagString value = (NBTTagString) entry.getValue();
//
//            stringBuilder.append(key).append("=").append(value.asString());
//
//            if (entryIterator.hasNext())
//            {
//                stringBuilder.append(",");
//            }
//        }
//
//        return stringBuilder.append("]").toString();
//    }

        public class BlockInfo
        {
            private Material material;
            private BlockData data;

            public BlockInfo(Material material, BlockData data)
            {
                this.material = material;
                this.data = data;
            }

            public Material getMaterial()
            {
                return material;
            }

            public BlockData getData()
            {
                return data;
            }
        }
    }
}
