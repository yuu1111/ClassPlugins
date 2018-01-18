package Main

import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin


class Main : JavaPlugin(), Listener {


    override fun onEnable() {
        server.pluginManager.registerEvents(this, this)
        server.pluginManager.registerEvents(Level(this), this)
        server.pluginManager.registerEvents(InventoryClick(this), this)
        server.pluginManager.registerEvents(Firemagic(this), this)
        getCommand("Skill").executor = Skill(this)
        //Level(this)
        //server.pluginManager.registerEvents(Level,this)
        saveDefaultConfig()

    }


    override fun onDisable() {


    }

}
