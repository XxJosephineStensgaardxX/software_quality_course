package com.jabberpoint.prototype;

import java.util.HashMap;
import java.util.Map;

public class PrototypeRegistry<T extends Cloneable<T>>
{
    private Map<String, T> prototypes;

    public PrototypeRegistry()
    {
        this.prototypes = new HashMap<>();
    }

    public PrototypeRegistry(Map<String, T> prototypes)
    {
        this.prototypes = new HashMap<>(prototypes);
    }

    public void addPrototype(String id, T prototype)
    {
        this.prototypes.put(id, prototype);
    }

    public T removePrototype(String id)
    {
        return this.prototypes.remove(id);
    }

    public T getPrototype(String id)
    {
        T prototype = this.prototypes.get(id);

        if (prototype != null)
        {
            return prototype.clone(); // Return a clone, not the original
        }

        return null; // Return null if prototype not found
    }
}
