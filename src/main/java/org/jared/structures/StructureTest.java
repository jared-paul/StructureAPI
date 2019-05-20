package org.jared.structures;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.jared.structures.plugin.StructureMain;

import java.io.File;
import java.io.IOException;

public class StructureTest implements Listener
{
    @EventHandler
    public void onBucketFill(PlayerBucketFillEvent fillEvent)
    {
        placeStructure(fillEvent.getPlayer().getLocation());
    }

    public void placeStructure(Location base)
    {
        try
        {
            File file = new File(StructureMain.getInstance().getDataFolder() + "\\entitytest.nbt");
            Structure structure = new Structure(file);
            structure.rotate(90); //only hard angles are allowed (90, 180, 270, 360, 0)

            structure.place(base);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
