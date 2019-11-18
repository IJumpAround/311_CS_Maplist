package objectBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import records.Record;
import records.Top10;
import records.WR;
import zoning.TeleportZone;
import zoning.TimerZone;
import zoning.Zone;


/**
 * Big thanks to the Gson package and the answers at https://stackoverflow.com/questions/19588020/gson-serialize-a-list-of-polymorphic-objects/22081826#22081826
 * Creates a static GsonBuilder and registers all subclass types
 */
public class GsonHelpers {

    private static GsonBuilder gsonbuilder = new GsonBuilder().setPrettyPrinting().serializeNulls();

    public static Gson getGson() {
        return gsonbuilder.create();
    }

    /**
     * Register Record and its subtypes as well as Zone and its subtypes.
     */
    public static void registerAllTypes() {

        RuntimeTypeAdapterFactory<Record> recordAdapter = RuntimeTypeAdapterFactory.of(Record.class)
                .registerSubtype(Record.class)
                .registerSubtype(Top10.class)
                .registerSubtype(WR.class);

        RuntimeTypeAdapterFactory<Zone> zoneAdapter = RuntimeTypeAdapterFactory.of(Zone.class)
                .registerSubtype(TeleportZone.class)
                .registerSubtype(TimerZone.class);

        gsonbuilder.registerTypeAdapterFactory(zoneAdapter);
        gsonbuilder.registerTypeAdapterFactory(recordAdapter);
    }
}
