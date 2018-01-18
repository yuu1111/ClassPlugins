package Main

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.Plugin

class Skill (private val plugin: Plugin) : CommandExecutor {

    override fun onCommand(sender: CommandSender?, command: Command?, label: String?, args: Array<String>?): Boolean {
        val p: Player = sender as Player
        val u: String = sender.uniqueId.toString()
        // val lev: Int = p.level
        val c: FileConfiguration = plugin.getConfig()
        //val RED = ChatColor.RED.toString()
        //val BLUE = ChatColor.BLUE.toString()

        val inv: Inventory = Bukkit.createInventory(null, 27, "スキル")

        val magic = ItemStack(Material.BOOK, 1)
        val magicim = magic.itemMeta
        magicim.displayName = ChatColor.DARK_PURPLE.toString() + "攻撃魔法"
        magicim.addItemFlags(ItemFlag.HIDE_ENCHANTS)
        magic.itemMeta = magicim
        magic.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1)


        val sword = ItemStack(Material.STONE_SWORD, 1)
        val swordim = sword.itemMeta
        swordim.displayName = ChatColor.BLUE.toString() + "近接攻撃"
        swordim.addItemFlags(ItemFlag.HIDE_ENCHANTS)
        sword.itemMeta = swordim
        sword.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1)


        val armor = ItemStack(Material.LEATHER_CHESTPLATE, 1)
        val armorim = armor.itemMeta
        armorim.displayName = ChatColor.GREEN.toString() + "耐久"
        armorim.addItemFlags(ItemFlag.HIDE_ENCHANTS)
        armor.itemMeta = armorim
        armor.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1)


        val healerWool = ItemStack(Material.BARRIER, 1)
        val healerWoolim = healerWool.itemMeta
        healerWoolim.displayName = ChatColor.RED.toString() + "未実装"
        healerWoolim.addItemFlags(ItemFlag.HIDE_ENCHANTS)
        healerWool.itemMeta = healerWoolim
        healerWool.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1)



        inv.setItem(10, magic)
        inv.setItem(12, sword)
        inv.setItem(14, armor)
        inv.setItem(16, healerWool)
        p.openInventory(inv)

        plugin.saveConfig()






        return false
    }
}

