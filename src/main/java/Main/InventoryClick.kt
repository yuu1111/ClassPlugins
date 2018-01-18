package Main

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.Plugin

class InventoryClick (private val plugin: Plugin) : Listener {



    @EventHandler
    fun inventoryClick(e: InventoryClickEvent) {
        val gui2: Inventory? = e.inventory
        val c: FileConfiguration = plugin.getConfig()
        val p = e.whoClicked as Player
        val inv: Inventory
        val u = p.uniqueId.toString()
        val close = ItemStack(Material.STAINED_GLASS_PANE, 1, 14.toShort())
        val closeim = close.itemMeta
        closeim.displayName = "習得済み"
        close.itemMeta = closeim


        val skilltest = ItemStack(Material.WOOL, 1, 2.toShort())
        val skilltestim = skilltest.itemMeta
        skilltestim.displayName = "Skill test"
        skilltest.itemMeta = skilltestim
        val invslot: Int? = e.slot
        val gui: String? = e.clickedInventory.name

        val firemagic = ItemStack(Material.BOOK,1)
        val firemagicim = firemagic.itemMeta
        firemagicim.displayName = ChatColor.DARK_PURPLE.toString()+"攻撃魔法スキル"+ChatColor.DARK_RED.toString()+"[火]"
        firemagicim.addItemFlags(ItemFlag.HIDE_ENCHANTS)
        firemagic.itemMeta = firemagicim
        firemagic.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1)

        val grassmagic = ItemStack(Material.BOOK,1)
        val grassmagicim = firemagic.itemMeta
        grassmagicim.displayName = ChatColor.DARK_PURPLE.toString()+"攻撃魔法スキル"+ChatColor.GREEN.toString()+"[草]"
        grassmagicim.addItemFlags(ItemFlag.HIDE_ENCHANTS)
        grassmagic.itemMeta = grassmagicim
        grassmagic.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1)

        val watermagic = ItemStack(Material.BOOK,1)
        val watermagicim = firemagic.itemMeta
        watermagicim.displayName = ChatColor.DARK_PURPLE.toString()+"攻撃魔法スキル"+ChatColor.BLUE.toString()+"[水]"
        watermagicim.addItemFlags(ItemFlag.HIDE_ENCHANTS)
        watermagic.itemMeta = watermagicim
        watermagic.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1)

        when (invslot) {

            10 ->

                if (gui === "魔法") {
                    e.isCancelled = true
                    val skill2 = c.getString("uuid.$u.Skill1")
                    if (skill2 != "test") {
                        val point = c.getInt("uuid.$u.SkillPoint")
                        val point2 = point - 2
                        c.set("uuid.$u.Skill1", "test")
                        c.set("uuid.$u.SkillPoint", point2)

                        val jobskill = c.getInt("uuid.$u.SkillPoint")
                        gui2!!.setItem(10, close)
                        p.sendMessage(ChatColor.BLUE.toString() + "testを取得しました残りスキルポイント" + jobskill)
                                plugin.saveConfig()
                    }

                } else if (gui === "スキル") {
                    e.isCancelled = true
                    inv = Bukkit.createInventory(null, 27, "魔法")
                    p.openInventory(inv)
                    inv.setItem(11,firemagic)

                    inv.setItem(13,grassmagic)

                    inv.setItem(15, watermagic)
                }
            11 -> if(gui == "魔法") {
                e.isCancelled = true
            }
            12 -> if (gui == "スキル") {
                e.isCancelled = true
                inv = Bukkit.createInventory(null, 27, "攻撃")
                p.openInventory(inv)
            }
            13 -> if(gui == "魔法") {
                e.isCancelled = true
            }
            14 -> if (gui == "スキル") {
                e.isCancelled = true
                inv = Bukkit.createInventory(null, 27, "耐久")
                p.openInventory(inv)
            }
            15 -> if(gui == "魔法") {
                e.isCancelled = true
            }
            16 -> if (gui === "スキル") {
                e.isCancelled = true
                inv = Bukkit.createInventory(null, 27, "未実装")
                p.openInventory(inv)
            }
           /* 49 -> if (gui === "魔法" || gui === "攻撃" || gui === "耐久" || gui === "未実装") {
                e.isCancelled = true
            }
            */
        }
    }
}




