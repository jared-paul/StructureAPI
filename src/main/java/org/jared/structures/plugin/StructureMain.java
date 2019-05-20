package org.jared.structures.plugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.jared.structures.StructureTest;

public class StructureMain extends JavaPlugin
{
    private static StructureMain instance = null;

    @Override
    public void onEnable()
    {
        instance = this;

        registerListeners();
        //commmit test
    }

    @Override
    public void onDisable()
    {
        instance = null;
    }

    public void registerListeners()
    {
        getServer().getPluginManager().registerEvents(new StructureTest(), this);
    }

    public static StructureMain getInstance()
    {
        return instance;
    }
}
