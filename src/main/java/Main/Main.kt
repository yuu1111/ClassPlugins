package Main

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerLevelChangeEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin


class Main : JavaPlugin(), Listener {


    override fun onEnable() {
        //getServer().getPluginManager().registerEvents(new ClassLevel(), this);
        server.pluginManager.registerEvents(this, this)
        saveDefaultConfig()
        getCommand("Jobset").executor = this

    }

    override fun onDisable() {


    }

    override fun onCommand(sender: CommandSender?, command: Command?, label: String?, args: Array<String>?): Boolean {
        val p: Player = sender as Player
        val u: String = sender.uniqueId.toString()
        val lev: Int = p.level
        val c: FileConfiguration = config
        //val RED = ChatColor.RED.toString()
        //val BLUE = ChatColor.BLUE.toString()
        when (args!![0]) {
            "Mage" -> {
                p.sendMessage(ChatColor.BLUE.toString() + "Mageになりました")
                c.set("uuid.$u.Class", "Mage");
                c.set("uuid.$u.Level", lev)
                saveConfig()
            }
            "Warrior" -> {
                p.sendMessage(ChatColor.BLUE.toString() + "Warriorになりました")
                c.set("uuid.$u.Class", "Warrior");
                c.set("uuid.$u.Level", lev)
                        saveConfig()
            }
            "Healer" -> {
                p.sendMessage(ChatColor.BLUE.toString() + "Healerになりました")
                c.set("uuid.$u.Class", "Healer");
                c.set("uuid.$u.Level", lev)
                saveConfig();
            }
            "Tank" -> {
                p.sendMessage(ChatColor.BLUE.toString() + "Tankになりました")
                c.set("uuid.$u.Class", "Tank");
                c.set("uuid.$u.Level", lev)
                saveConfig();
            }
            "Check" -> {
                p.sendMessage(ChatColor.BLUE.toString() + "ステータス")
                p.sendMessage(ChatColor.BLUE.toString() + "Class " + c.getString("uuid.$u.Class"))
                        p.sendMessage(ChatColor.BLUE.toString() + "Level " + c.getInt("uuid.$u.Level"))
            }
            "Skill" -> {
                val inv: Inventory
                inv = Bukkit.createInventory(null, 27, "Skilltree")

                val MageWool = ItemStack(Material.WOOL, 1, 2.toShort())
                val MageWoolim = MageWool.itemMeta
                MageWoolim.displayName = "Mage"
                MageWool.itemMeta = MageWoolim

                val WarriorWool = ItemStack(Material.WOOL, 1, 14.toShort())
                val WarriorWoolim = WarriorWool.itemMeta
                WarriorWoolim.displayName = "Warrior"
                WarriorWool.itemMeta = WarriorWoolim

                val HealerWool = ItemStack(Material.WOOL, 1, 5.toShort())
                val HealerWoollim = HealerWool.itemMeta
                HealerWoollim.displayName = "Healer"
                HealerWool.itemMeta = HealerWoollim

                val TankWool = ItemStack(Material.WOOL, 1, 15.toShort())
                val TankWoollim = TankWool.itemMeta
                TankWoollim.displayName = "Tank"
                TankWool.itemMeta = TankWoollim

                inv.setItem(10, MageWool)
                inv.setItem(12, WarriorWool)
                inv.setItem(14, HealerWool)
                inv.setItem(16, TankWool)
                p.openInventory(inv)

                saveConfig()
            }


            "Reset" -> {
                c.set("uuid.$u.Level", "")
                c.set("uuid.$u.SkillPoint", "")
                p.level = 0
                p.exp = 0f
                saveConfig()
                p.sendMessage(ChatColor.RED.toString() + "デバッグ リセットしました")
            }

            else -> p.sendMessage(ChatColor.RED.toString() + "エラー コマンドが違います")


        }


        return false
    }

    @EventHandler
    fun InventoryClick(e: InventoryClickEvent) {
        val GUI = e.clickedInventory.name
        val p = e.whoClicked as Player
        val inv: Inventory
        val c = config
        val u = p.uniqueId.toString()
        val Close = ItemStack(Material.STAINED_GLASS_PANE, 1, 14.toShort())
        val Closeim = Close.itemMeta
        Closeim.displayName = "閉じる"
        Close.itemMeta = Closeim

        val Skilltest = ItemStack(Material.WOOL, 1, 2.toShort())
        val Skilltestim = Skilltest.itemMeta
        Skilltestim.displayName = "Skill test"
        Skilltest.itemMeta = Skilltestim


        val JobSkillp = c.getInt("uuid.$u.SkillPoint")
        when (e.slot) {

            10 -> if (GUI === "Mage") {
                e.isCancelled = true

                val Point = c.getInt("uuid.$u.SkillPoint")
                val Point2 = Point - 2
                c.set("uuid.$u.SkillPoint", Point)
                c.set("uuid.$u.SkillPoint.Skill1", "test")
                val JobSkill = c.getInt("uuid.$u.SkillPoint")
                p.sendMessage(ChatColor.BLUE.toString() + "testを取得しました残りスキルポイント" + JobSkill)
                saveConfig()
            } else if (GUI === "Skilltree") {
                e.isCancelled = true
                inv = Bukkit.createInventory(null, 54, "Mage")
                p.openInventory(inv)
                inv.setItem(49, Close)
                inv.setItem(10, Skilltest)



            }
            12 -> if (GUI === "Skilltree") {
                e.isCancelled = true
                inv = Bukkit.createInventory(null, 54, "Warrior")
                p.openInventory(inv)
                inv.setItem(49, Close)
            }
            14 -> if (GUI === "Skilltree") {
                e.isCancelled = true
                inv = Bukkit.createInventory(null, 54, "Healer")
                p.openInventory(inv)
                inv.setItem(49, Close)
            }
            16 -> if (GUI === "Skilltree") {
                e.isCancelled = true
                inv = Bukkit.createInventory(null, 54, "Tank")
                p.openInventory(inv)
                inv.setItem(49, Close)
            }
            49 -> if (GUI === "Mage") {
                e.isCancelled = true
            } else if (GUI === "Warrior") {
                e.isCancelled = true
            } else if (GUI === "Healer") {
                e.isCancelled = true
            } else if (GUI === "Tank") {
                e.isCancelled = true
            }
        }
    }

    @EventHandler
    fun getNewLevel(Le: PlayerLevelChangeEvent) {
        val p = Le.player
        val NL = Le.newLevel
        //int OL = Le.getOldLevel();
        val u = p.uniqueId.toString()
        val c = config
        if (NL >= 1) {
            val Job = c.getString("uuid.$u.Class")
            val JobSkill = c.getInt("uuid.$u.SkillPoint") +2
            p.sendMessage(ChatColor.BLUE.toString() +"$Job のレベルが $NL になりました")
            c.set("uuid.$u.Level", NL)
            c.set("uuid.$u.SkillPoint", JobSkill)
            saveConfig()
        }
    }
}



