package lol.pyr.znpcsplus.metadata;

import com.github.retrooper.packetevents.protocol.entity.data.EntityData;
import com.github.retrooper.packetevents.protocol.entity.data.EntityDataTypes;
import com.github.retrooper.packetevents.protocol.entity.villager.VillagerData;
import lol.pyr.znpcsplus.util.CreeperState;
import lol.pyr.znpcsplus.util.ParrotVariant;

@Deprecated
public class V1_15MetadataFactory extends V1_14MetadataFactory {
    @Override
    public EntityData shoulderEntityLeft(ParrotVariant variant) {
            return createShoulderEntityLeft(18, variant);
    }

    @Override
    public EntityData shoulderEntityRight(ParrotVariant variant) {
        return createShoulderEntityRight(19, variant);
    }

    @Override
    public EntityData blazeOnFire(boolean onFire) {
        return newEntityData(15, EntityDataTypes.BYTE, (byte) (onFire ? 0x01 : 0));
    }

    @Override
    public EntityData creeperState(CreeperState state) {
        return newEntityData(15, EntityDataTypes.INT, state.getState());
    }

    @Override
    public EntityData creeperCharged(boolean charged) {
        return newEntityData(16, EntityDataTypes.BOOLEAN, charged);
    }

    @Override
    public EntityData evokerSpell(int spell) {
        return newEntityData(16, EntityDataTypes.BYTE, (byte) spell);
    }

    @Override
    public EntityData villagerData(int type, int profession, int level) {
        return newEntityData(17, EntityDataTypes.VILLAGER_DATA, new VillagerData(type, profession, level));
    }
}
