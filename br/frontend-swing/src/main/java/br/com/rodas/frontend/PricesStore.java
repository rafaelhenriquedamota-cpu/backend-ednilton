package br.com.rodas.frontend;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Armazena preços atuais dos combustíveis de forma simples e centralizada.
 */
public class PricesStore {
    private static final Map<String, String> prices = new LinkedHashMap<>();

    static {
        // valores padrão (você pode alterar)
        prices.put("Gasolina", "6.59");
        prices.put("Etanol",   "4.79");
        prices.put("Diesel",   "5.19");
    }

    public static Map<String, String> getAll() {
        return prices;
    }

    public static String get(String key) {
        return prices.get(key);
    }

    public static void set(String key, String value) {
        prices.put(key, value);
    }
}
