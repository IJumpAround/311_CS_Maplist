package objectBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

public class GsonHelpers {

    private static GsonBuilder gsonbuilder = new GsonBuilder().setPrettyPrinting().serializeNulls();
    public static void registerType (RuntimeTypeAdapterFactory<?> adapterFactory) {
        gsonbuilder.registerTypeAdapterFactory(adapterFactory);
    }

    public static Gson getGson() {
        return gsonbuilder.create();
    }
}
