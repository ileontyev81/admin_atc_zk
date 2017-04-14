package atc.gui.admin.domain;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public interface PersistentEntity extends HasId
{

    public default boolean isPersisted()
    {
        Object id = getId();
        if (id != null) {
            if (id instanceof Optional && ((Optional)id).isPresent()) {
                id = ((Optional)id).get();
            }
            if (id instanceof Number) {
                return ((int)id) > 0;
            }
            return !StringUtils.isBlank(String.valueOf(id));
        }
        return false;
    }

}
