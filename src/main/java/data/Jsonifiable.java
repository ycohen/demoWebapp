package data;

import java.util.Collection;

public interface Jsonifiable {
    String toJson();

    static StringBuilder jsonifyCollection(Collection<? extends Jsonifiable> collection) {
        StringBuilder jsonResponse = new StringBuilder("[");

        for (Jsonifiable jsonifiable: collection) {
            jsonResponse.append(jsonifiable.toJson()).append(", ");
        }

        if (jsonResponse.length() > 2) {
            jsonResponse.delete(jsonResponse.length() - 2, jsonResponse.length());
        }
        jsonResponse.append("]");
        return jsonResponse;
    }
}
