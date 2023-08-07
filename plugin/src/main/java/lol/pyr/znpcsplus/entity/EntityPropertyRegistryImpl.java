package lol.pyr.znpcsplus.entity;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.manager.server.ServerVersion;
import com.github.retrooper.packetevents.protocol.entity.data.EntityDataTypes;
import com.github.retrooper.packetevents.protocol.player.EquipmentSlot;
import lol.pyr.znpcsplus.api.entity.EntityProperty;
import lol.pyr.znpcsplus.api.entity.EntityPropertyRegistry;
import lol.pyr.znpcsplus.api.skin.SkinDescriptor;
import lol.pyr.znpcsplus.entity.properties.*;
import lol.pyr.znpcsplus.entity.serializers.*;
import lol.pyr.znpcsplus.packets.PacketFactory;
import lol.pyr.znpcsplus.skin.cache.MojangSkinCache;
import lol.pyr.znpcsplus.util.*;
import org.bukkit.Color;
import org.bukkit.DyeColor;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 1.8  <a href="https://wiki.vg/index.php?title=Entity_metadata&oldid=7415">...</a>
 * 1.9  <a href="https://wiki.vg/index.php?title=Entity_metadata&oldid=7968">...</a>
 * 1.10 <a href="https://wiki.vg/index.php?title=Entity_metadata&oldid=8241">...</a>
 * 1.11 <a href="https://wiki.vg/index.php?title=Entity_metadata&oldid=8534">...</a>
 * 1.12 <a href="https://wiki.vg/index.php?title=Entity_metadata&oldid=14048">...</a>
 * 1.13 <a href="https://wiki.vg/index.php?title=Entity_metadata&oldid=14800">...</a>
 * 1.14 <a href="https://wiki.vg/index.php?title=Entity_metadata&oldid=15240">...</a>
 * 1.15 <a href="https://wiki.vg/index.php?title=Entity_metadata&oldid=15991">...</a>
 * 1.16 <a href="https://wiki.vg/index.php?title=Entity_metadata&oldid=16539">...</a>
 * 1.17 <a href="https://wiki.vg/index.php?title=Entity_metadata&oldid=17521">...</a>
 * 1.18-1.19 <a href="https://wiki.vg/index.php?title=Entity_metadata&oldid=18191">...</a>
 * 1.20 <a href="https://wiki.vg/index.php?title=Entity_metadata">...</a>
 */
@SuppressWarnings("unchecked")
public class EntityPropertyRegistryImpl implements EntityPropertyRegistry {
    private final Map<Class<?>, PropertySerializer<?>> serializerMap = new HashMap<>();
    private final Map<String, EntityPropertyImpl<?>> byName = new HashMap<>();

    public EntityPropertyRegistryImpl(MojangSkinCache skinCache) {
        registerSerializer(new BooleanPropertySerializer());
        registerSerializer(new ComponentPropertySerializer());
        registerSerializer(new NamedTextColorPropertySerializer());
        registerSerializer(new SkinDescriptorSerializer(skinCache));
        registerSerializer(new ItemStackPropertySerializer());
        registerSerializer(new ColorPropertySerializer());
        registerSerializer(new Vector3fPropertySerializer());
        registerSerializer(new BlockStatePropertySerializer());

        registerEnumSerializer(NpcPose.class);
        registerEnumSerializer(DyeColor.class);
        registerEnumSerializer(CatVariant.class);
        registerEnumSerializer(CreeperState.class);
        registerEnumSerializer(ParrotVariant.class);
        registerEnumSerializer(SpellType.class);
        registerEnumSerializer(FoxVariant.class);
        registerEnumSerializer(FrogVariant.class);
        registerEnumSerializer(VillagerType.class);
        registerEnumSerializer(VillagerProfession.class);
        registerEnumSerializer(VillagerLevel.class);
        /*
        registerType("using_item", false); // TODO: fix it for 1.8 and add new property to use offhand item and riptide animation

        registerType("baby", false); // TODO
        registerType("pose", NpcPose.STANDING);

        // Player
        registerType("shoulder_entity_left", ParrotVariant.NONE);
        registerType("shoulder_entity_right", ParrotVariant.NONE);

        // End Crystal
        registerType("beam_target", null); // TODO: Make a block pos class for this
        registerType("show_base", true); // TODO

        // Axolotl
        registerType("axolotl_variant", 0);
        registerType("playing_dead", false); // TODO fix disabling

        // Blaze
        registerType("blaze_on_fire", false);

        // Creeper
        registerType("creeper_state", CreeperState.IDLE);
        registerType("creeper_charged", false);

        // Enderman
        registerType("enderman_held_block", new BlockState(0)); // TODO: figure out the type on this
        registerType("enderman_screaming", false); // TODO
        registerType("enderman_staring", false); // TODO

        // Evoker
        registerType("evoker_spell", SpellType.NONE);

        // Frog
        registerType("frog_variant", FrogVariant.TEMPERATE);

        // Guardian
        registerType("is_elder", false); // TODO: ensure it only works till 1.10. Note: index is wrong on wiki.vg

        // Pufferfish
        registerType("puff_state", null); // TODO: Make a puff state enum class

        // Tropical Fish
        registerType("tropical_fish_variant", null); // TODO: Maybe make an enum class for this? its just an int on wiki.vg

        // Sniffer
        registerType("sniffer_state", null); // TODO: Nothing on wiki.vg, look in mc source

        // Horse
        registerType("horse_style", 0); // TODO: Figure this out
        registerType("horse_chest", false); // TODO
        registerType("horse_saddle", false); // TODO

        // LLama
        registerType("carpet_color", DyeColor.class); // TODO
        registerType("llama_variant", 0); // TODO

        // Panda
        registerType("panda_sneezing", false); // TODO
        registerType("panda_rolling", false); // TODO
        registerType("panda_sitting", false); // TODO
        registerType("panda_on_back", false); // TODO

        // Pig
        registerType("pig_saddle", false); // TODO

        // Rabbit
        registerType("rabbit_type", 0); // TODO: Figure this out

        // Polar Bear
        registerType("polar_bear_standing", false); // TODO

        // Sheep
        registerType("sheep_color", DyeColor.WHITE); // TODO: Figure this out
        registerType("sheep_sheared", false); // TODO

        // Strider
        registerType("strider_shaking", false); // TODO
        registerType("strider_saddle", false); // TODO

        // Wolf
        registerType("wolf_collar_color", DyeColor.RED); // TODO
        registerType("wolf_angry", false); // TODO

        // Parrot
        registerType("parrot_variant", 0); // TODO

        // Villager
        registerType("villager_type", VillagerType.PLAINS);
        registerType("villager_profession", VillagerProfession.NONE);
        registerType("villager_level", VillagerLevel.STONE);

        // Show Golem
        registerType("pumpkin", true); // TODO

        // Shulker
        registerType("attach_direction", null); // TODO: make a direction enum
        registerType("shield_height", 0); // TODO: figure this out
        registerType("shulker_color", DyeColor.RED); // TODO

        // Piglin
        registerType("piglin_dancing", false); // TODO
        registerType("piglin_charging_crossbow", false); // TODO

        // Vindicator
        registerType("celebrating", false); // TODO

        // Wither
        registerType("invulnerable_time", 0); // TODO

        // Phantom
        registerType("phantom_size", 0); // TODO

        // Slime
        registerType("slime_size", 0); // TODO
         */
    }

    public void registerTypes(PacketFactory packetFactory) {
        ServerVersion ver = PacketEvents.getAPI().getServerManager().getVersion();
        boolean legacyBooleans = ver.isOlderThan(ServerVersion.V_1_9);
        boolean legacyNames = ver.isOlderThan(ServerVersion.V_1_9);
        boolean optionalComponents = ver.isNewerThanOrEquals(ServerVersion.V_1_13);

        register(new EquipmentProperty(packetFactory, "helmet", EquipmentSlot.HELMET));
        register(new EquipmentProperty(packetFactory, "chestplate", EquipmentSlot.CHEST_PLATE));
        register(new EquipmentProperty(packetFactory, "leggings", EquipmentSlot.LEGGINGS));
        register(new EquipmentProperty(packetFactory, "boots", EquipmentSlot.BOOTS));
        register(new EquipmentProperty(packetFactory, "hand", EquipmentSlot.MAIN_HAND));
        register(new EquipmentProperty(packetFactory, "offhand", EquipmentSlot.OFF_HAND));

        register(new NameProperty(legacyNames, optionalComponents));
        register(new DinnerboneProperty(legacyNames, optionalComponents));

        register(new DummyProperty<>("look", false));
        register(new GlowProperty(packetFactory));
        register(new BitsetProperty("fire", 0, 0x01));
        register(new BitsetProperty("invisible", 0, 0x20));
        register(new HologramItemProperty());
        linkProperties("glow", "fire", "invisible");
        register(new BooleanProperty("silent", 4, false, legacyBooleans));

        final int tamedIndex;
        if (ver.isNewerThanOrEquals(ServerVersion.V_1_17)) tamedIndex = 17;
        else if (ver.isNewerThanOrEquals(ServerVersion.V_1_15)) tamedIndex = 16;
        else if (ver.isNewerThanOrEquals(ServerVersion.V_1_14)) tamedIndex = 15;
        else if (ver.isNewerThanOrEquals(ServerVersion.V_1_10)) tamedIndex = 13;
        else if (ver.isNewerThanOrEquals(ServerVersion.V_1_9)) tamedIndex = 12;
        else tamedIndex = 16;
        register(new BitsetProperty("tamed", tamedIndex, 0x04));

        int potionIndex;
        if (ver.isNewerThanOrEquals(ServerVersion.V_1_17)) potionIndex = 10;
        else if (ver.isNewerThanOrEquals(ServerVersion.V_1_14)) potionIndex = 9;
        else if (ver.isNewerThanOrEquals(ServerVersion.V_1_10)) potionIndex = 8;
        else potionIndex = 7;
        register(new EncodedIntegerProperty<>("potion_color", Color.class, potionIndex++, Color::asRGB));
        register(new BooleanProperty("potion_ambient", potionIndex, false, legacyBooleans));

        // Player
        register(new DummyProperty<>("skin", SkinDescriptor.class, false));
        final int skinLayersIndex;
        if (ver.isNewerThanOrEquals(ServerVersion.V_1_17)) skinLayersIndex = 17;
        else if (ver.isNewerThanOrEquals(ServerVersion.V_1_16)) skinLayersIndex = 16;
        else if (ver.isNewerThanOrEquals(ServerVersion.V_1_14)) skinLayersIndex = 15;
        else if (ver.isNewerThanOrEquals(ServerVersion.V_1_10)) skinLayersIndex = 13;
        else if (ver.isNewerThanOrEquals(ServerVersion.V_1_9)) skinLayersIndex = 12;
        else skinLayersIndex = 10;
        register(new BitsetProperty("skin_cape", skinLayersIndex, 0x01));
        register(new BitsetProperty("skin_jacket", skinLayersIndex, 0x02));
        register(new BitsetProperty("skin_left_sleeve", skinLayersIndex, 0x04));
        register(new BitsetProperty("skin_right_sleeve", skinLayersIndex, 0x08));
        register(new BitsetProperty("skin_left_leg", skinLayersIndex, 0x10));
        register(new BitsetProperty("skin_right_leg", skinLayersIndex, 0x20));
        register(new BitsetProperty("skin_hat", skinLayersIndex, 0x40));
        linkProperties("skin_cape", "skin_jacket", "skin_left_sleeve", "skin_right_sleeve", "skin_left_leg", "skin_right_leg", "skin_hat");

        // Armor Stand
        int armorStandIndex;
        if (ver.isNewerThanOrEquals(ServerVersion.V_1_17)) armorStandIndex = 15;
        else if (ver.isNewerThanOrEquals(ServerVersion.V_1_15)) armorStandIndex = 14;
        else if (ver.isNewerThanOrEquals(ServerVersion.V_1_14)) armorStandIndex = 13;
        else if (ver.isNewerThanOrEquals(ServerVersion.V_1_10)) armorStandIndex = 11;
        else armorStandIndex = 10;
        register(new BitsetProperty("small", armorStandIndex, 0x01));
        register(new BitsetProperty("arms", armorStandIndex, 0x04));
        register(new BitsetProperty("base_plate", armorStandIndex++, 0x08, true));
        linkProperties("small", "arms", "base_plate");
        register(new RotationProperty("head_rotation", armorStandIndex++, Vector3f.zero()));
        register(new RotationProperty("body_rotation", armorStandIndex++, Vector3f.zero()));
        register(new RotationProperty("left_arm_rotation", armorStandIndex++, new Vector3f(-10, 0, -10)));
        register(new RotationProperty("right_arm_rotation", armorStandIndex++, new Vector3f(-15, 0, 10)));
        register(new RotationProperty("left_leg_rotation", armorStandIndex++, new Vector3f(-1, 0, -1)));
        register(new RotationProperty("right_leg_rotation", armorStandIndex, new Vector3f(1, 0, 1)));

        // Ghast
        final int ghastAttackingIndex;
        if (ver.isNewerThanOrEquals(ServerVersion.V_1_17)) ghastAttackingIndex = 16;
        else if (ver.isNewerThanOrEquals(ServerVersion.V_1_15)) ghastAttackingIndex = 15;
        else if (ver.isNewerThanOrEquals(ServerVersion.V_1_14)) ghastAttackingIndex = 14;
        else if (ver.isNewerThanOrEquals(ServerVersion.V_1_10)) ghastAttackingIndex = 12;
        else if (ver.isNewerThanOrEquals(ServerVersion.V_1_9)) ghastAttackingIndex = 11;
        else ghastAttackingIndex = 16;
        register(new BooleanProperty("attacking", ghastAttackingIndex, false, legacyBooleans));

        // Bat
        final int batIndex;
        if (ver.isNewerThanOrEquals(ServerVersion.V_1_17)) batIndex = 16;
        else if (ver.isNewerThanOrEquals(ServerVersion.V_1_15)) batIndex = 15;
        else if (ver.isNewerThanOrEquals(ServerVersion.V_1_14)) batIndex = 14;
        else if (ver.isNewerThanOrEquals(ServerVersion.V_1_10)) batIndex = 12;
        else if (ver.isNewerThanOrEquals(ServerVersion.V_1_9)) batIndex = 11;
        else batIndex = 16;
        register(new BooleanProperty("hanging", batIndex, false, true /* This isnt a mistake */));

        if (!ver.isNewerThanOrEquals(ServerVersion.V_1_14)) return;

        // Cat
        int catIndex;
        if (ver.isNewerThanOrEquals(ServerVersion.V_1_17)) catIndex = 19;
        else if (ver.isNewerThanOrEquals(ServerVersion.V_1_15)) catIndex = 18;
        else catIndex = 17;
        register(new EncodedIntegerProperty<>("cat_variant", CatVariant.BLACK, catIndex++, Enum::ordinal, EntityDataTypes.CAT_VARIANT));
        register(new BooleanProperty("cat_laying", catIndex++, false, legacyBooleans));
        register(new BooleanProperty("cat_relaxed", catIndex++, false, legacyBooleans));
        register(new EncodedIntegerProperty<>("cat_collar", DyeColor.RED, catIndex, Enum::ordinal));

        // Fox
        int foxIndex;
        if (ver.isNewerThanOrEquals(ServerVersion.V_1_17)) foxIndex = 17;
        else if (ver.isNewerThanOrEquals(ServerVersion.V_1_15)) foxIndex = 16;
        else foxIndex = 15;
        register(new EncodedIntegerProperty<>("fox_variant", FoxVariant.RED, foxIndex++, Enum::ordinal));
        register(new BitsetProperty("fox_sitting", foxIndex, 0x01));
        register(new BitsetProperty("fox_crouching", foxIndex, 0x04));
        register(new BitsetProperty("fox_sleeping", foxIndex, 0x20));
        linkProperties("fox_sitting", "fox_crouching", "fox_sleeping");

        if (!ver.isNewerThanOrEquals(ServerVersion.V_1_15)) return;

        register(new BitsetProperty("fox_faceplanted", foxIndex, 0x40));
        linkProperties("fox_sitting", "fox_crouching", "fox_sleeping", "fox_faceplanted");

        int beeIndex;
        if (ver.isNewerThanOrEquals(ServerVersion.V_1_17)) beeIndex = 17;
        else beeIndex = 18;
        register(new BitsetProperty("has_nectar", beeIndex++, 0x08));
        register(new EncodedIntegerProperty<>("angry", false, beeIndex, enabled -> enabled ? 1 : 0));

        if (!ver.isNewerThanOrEquals(ServerVersion.V_1_16)) return;

        final int zombificationIndex;
        if (ver.isNewerThanOrEquals(ServerVersion.V_1_17)) zombificationIndex = 17;
        else zombificationIndex = 16;
        register(new BooleanProperty("immune_to_zombification", zombificationIndex, false, legacyBooleans));

        if (!ver.isNewerThanOrEquals(ServerVersion.V_1_17)) return;

        // Goat
        register(new BooleanProperty("has_left_horn", 18, true, legacyBooleans));
        register(new BooleanProperty("has_right_horn", 19, true, legacyBooleans));

        register(new EncodedIntegerProperty<>("shaking", false,7, enabled -> enabled ? 140 : 0));
    }

    private void registerSerializer(PropertySerializer<?> serializer) {
        serializerMap.put(serializer.getTypeClass(), serializer);
    }

    private <T extends Enum<T>> void registerEnumSerializer(Class<T> clazz) {
        serializerMap.put(clazz, new EnumPropertySerializer<>(clazz));
    }

    private <T> void register(EntityPropertyImpl<?> property) {
        if (byName.containsKey(property.getName()))
            throw new IllegalArgumentException("Duplicate property name: " + property.getName());
        byName.put(property.getName(), property);
    }

    private void linkProperties(String... names) {
        linkProperties(Arrays.stream(names)
                .map(this::getByName)
                .collect(Collectors.toSet()));
    }

    private void linkProperties(Collection<EntityPropertyImpl<?>> properties) {
        for (EntityPropertyImpl<?> property : properties) for (EntityPropertyImpl<?> dependency : properties) {
            if (property.equals(dependency)) continue;
            property.addDependency(dependency);
        }
    }

    public <V> PropertySerializer<V> getSerializer(Class<V> type) {
        return (PropertySerializer<V>) serializerMap.get(type);
    }

    @Override
    public Collection<EntityProperty<?>> getAll() {
        return Collections.unmodifiableCollection(
                byName.values().stream()
                        .map(property -> (EntityProperty<?>) property)
                        .collect(Collectors.toSet()));
    }

    public <T> EntityPropertyImpl<T> getByName(String name, Class<T> type) {
        return (EntityPropertyImpl<T>) getByName(name);
    }

    public EntityPropertyImpl<?> getByName(String name) {
        return byName.get(name.toLowerCase());
    }
}
