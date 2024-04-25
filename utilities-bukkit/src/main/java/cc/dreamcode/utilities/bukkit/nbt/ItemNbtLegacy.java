package cc.dreamcode.utilities.bukkit.nbt;

import cc.dreamcode.utilities.ClassUtil;
import cc.dreamcode.utilities.builder.MapBuilder;
import cc.dreamcode.utilities.bukkit.VersionUtil;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class ItemNbtLegacy implements ItemNbt {

    private final Method asNMSCopyMethod;
    private final Method asBukkitCopyMethod;

    private final Method hasItemTagMethod;
    private final Method getItemTagMethod;
    private final Method setItemTagMethod;
    private final Method getItemKeySet;
    private final Method getItemStringMethod;
    private final Method setItemStringMethod;

    private final Constructor<?> nbtCompoundConstructor;

    public ItemNbtLegacy() {
        final String version = VersionUtil.getStringVersion()
                .orElseThrow(() -> new RuntimeException("Cannot resolve nms version"));

        final Class<?> craftItemStackClass = ClassUtil.getClass("org.bukkit.craftbukkit." + version + ".inventory.CraftItemStack")
                .orElseThrow(() -> new RuntimeException("Cannot resolve craftbukkit CraftItemStack class"));
        final Class<?> itemStackClass = ClassUtil.getClass("net.minecraft.server." + version + ".ItemStack")
                .orElseThrow(() -> new RuntimeException("Cannot resolve minecraft-server ItemStack class"));
        final Class<?> nbtTagCompoundClass = ClassUtil.getClass("net.minecraft.server." + version + ".NBTTagCompound")
                .orElseThrow(() -> new RuntimeException("Cannot resolve minecraft-server NBTTagCompound class"));

        try {
            this.asNMSCopyMethod = craftItemStackClass.getMethod("asNMSCopy", ItemStack.class);
            this.asBukkitCopyMethod = craftItemStackClass.getMethod("asBukkitCopy", itemStackClass);

            this.hasItemTagMethod = itemStackClass.getMethod("hasTag");
            this.getItemTagMethod = itemStackClass.getMethod("getTag");
            this.setItemTagMethod = itemStackClass.getMethod("setTag", nbtTagCompoundClass);

            this.getItemKeySet = nbtTagCompoundClass.getMethod("c");
            this.getItemStringMethod = nbtTagCompoundClass.getMethod("getString", String.class);
            this.setItemStringMethod = nbtTagCompoundClass.getMethod("setString", String.class, String.class);

            this.nbtCompoundConstructor = nbtTagCompoundClass.getDeclaredConstructor();
        }
        catch (NoSuchMethodException e) {
            throw new RuntimeException("Cannot find method/constuctor", e);
        }
    }

    @Override
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public Map<String, String> getValues(@NonNull Plugin plugin, @NonNull ItemStack itemStack) {

        final Object nmsCopy = this.asNMSCopyMethod.invoke(null, itemStack);
        final Object itemCompound = this.getItemCompound(nmsCopy);

        final Set<String> keys = (Set<String>) this.getItemKeySet.invoke(itemCompound);
        final MapBuilder<String, String> mapBuilder = new MapBuilder<>();

        for (String key : keys) {
            Optional.ofNullable((String) this.getItemStringMethod.invoke(itemCompound, key))
                    .ifPresent(s -> mapBuilder.put(key, s));
        }

        return mapBuilder.build();
    }

    @Override
    @SneakyThrows
    public Optional<String> getValue(@NonNull Plugin plugin, @NonNull ItemStack itemStack, @NonNull String key) {

        final Object nmsCopy = this.asNMSCopyMethod.invoke(null, itemStack);
        final Object itemCompound = this.getItemCompound(nmsCopy);

        return Optional.ofNullable((String) this.getItemStringMethod.invoke(itemCompound, key))
                .map(text -> {
                    if (text.isEmpty()) {
                        return null;
                    }

                    return text;
                });
    }

    @Override
    @SneakyThrows
    public ItemStack setValue(@NonNull Plugin plugin, @NonNull ItemStack itemStack, @NonNull String key, @NonNull String value) {

        final Object nmsCopy = this.asNMSCopyMethod.invoke(null, itemStack);
        final Object itemCompound = this.getItemCompound(nmsCopy);

        this.setItemStringMethod.invoke(itemCompound, key, value);
        this.setItemTagMethod.invoke(nmsCopy, itemCompound);

        return (ItemStack) this.asBukkitCopyMethod.invoke(null, nmsCopy);
    }

    @SneakyThrows
    private Object getItemCompound(@NonNull Object nmsCopy) {
        return (boolean) this.hasItemTagMethod.invoke(nmsCopy)
                ? this.getItemTagMethod.invoke(nmsCopy)
                : this.nbtCompoundConstructor.newInstance();
    }
}
