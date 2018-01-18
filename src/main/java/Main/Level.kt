package Main

import org.bukkit.ChatColor
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLevelChangeEvent
import org.bukkit.plugin.Plugin

class Level(private val plugin: Plugin) : Listener {

        @EventHandler
        fun getNewLevel(Le: PlayerLevelChangeEvent) {
            val p = Le.player
            val c: FileConfiguration = plugin.getConfig()

            val nl = Le.newLevel
            //int OL = Le.getOldLevel();
            val u = p.uniqueId.toString()
            // val c = config
            if (nl >= 1) {
                val skill = c.getInt("uuid.$u.SkillPoint") + 2
                val skill2: Int = skill + 2
                c.set("uuid.$u.SkillPoint", skill2)
                p.sendMessage(ChatColor.BLUE.toString() + "レベルが $nl になりました")
                c.set("uuid.$u.Level", +nl)
                plugin.saveConfig()
            }
        }
    }

















