import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class TunnelerPlugin extends JavaPlugin {

    private static final String COMMAND_NAME = "tunneler";
    private static final String ALLOWED_USER = "HKFireBlaster";

    @Override
    public void onEnable() {
        getLogger().info("Tunneler Plugin Enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Tunneler Plugin Disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase(COMMAND_NAME)) {
            
            if (!(sender instanceof Player) || !((Player) sender).getName().equals(ALLOWED_USER)) {
                sender.sendMessage("You do not have permission to use this command.");
                return true;
            }

            Player player = (Player) sender;

            
            if (args.length != 1) {
                player.sendMessage("Usage: /tunneler <size>");
                return true;
            }

            
            int size;
            try {
                size = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                player.sendMessage("Invalid size. Please specify a number.");
                return true;
            }

            
            ItemStack item = player.getInventory().getItemInMainHand();

            
            if (!isTunnelerPickaxe(item)) {
                player.sendMessage("You must be holding a Tunneler pickaxe to use this command.");
                return true;
            }

            
            setMiningSize(item, size);
            player.sendMessage("Tunneler pickaxe size set to " + size + "x" + size + ".");

            return true;
        }
        return false;
    }

    
    private boolean isTunnelerPickaxe(ItemStack item) {
        if (item != null && item.getType() == Material.DIAMOND_PICKAXE) {
            ItemMeta meta = item.getItemMeta();
            return meta != null && meta.hasDisplayName() && meta.getDisplayName().contains("Tunneler") && meta.getPersistentDataContainer().has(new NamespacedKey(this, "tunneler"), PersistentDataType.STRING);
        }
        return false;
    }

    
    private void setMiningSize(ItemStack item, int size) {
        item.addUnsafeEnchantment(Enchantment.DIG_SPEED, size);
    }
}