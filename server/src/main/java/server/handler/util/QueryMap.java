package server.handler.util;

import java.util.HashMap;
import java.util.Map;

public class QueryMap {

    private Map<String, String> map = new HashMap<>();

    public QueryMap(String query) {
        String[] params = query.split("&");
        for (String param : params) {
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            map.put(name, value);
        }
    }

    public boolean containsKey(String key) {
        return map.containsKey(key);
    }

    public Long getLong(String key) {
        return Long.valueOf(map.get(key));
    }

    public boolean isTrue(String key) {
        return map.get(key).equals("true");
    }

    public boolean isFalse(String key) {
        return map.get(key).equals("false");
    }

}
