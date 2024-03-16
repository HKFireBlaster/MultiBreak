private void registerTunnelerRecipe() {

    NamespacedKey key = new NamespacedKey(this, "tunneler_recipe");


    ItemStack tunnelerPickaxe = new ItemStack(Material.DIAMOND_PICKAXE);

    ItemMeta itemMeta = tunnelerPickaxe.getItemMeta();
    itemMeta.setDisplayName("§9Tunn§celer Pickaxe");

    itemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "tunneler");
    tunnelerPickaxe.setItemMeta(itemMeta);


    tunnelerPickaxe.addUnsafeEnchantment(Enchantment.DIG_SPEED, 3);


    ShapedRecipe recipe = new ShapedRecipe(key, tunnelerPickaxe);
    recipe.shape("DDD", "ODO", "ODO"); // D = Diamond Block, O = Oak Log
    recipe.setIngredient('D', Material.DIAMOND_BLOCK);
    recipe.setIngredient('O', Material.OAK_LOG);


    getServer().addRecipe(recipe);
}