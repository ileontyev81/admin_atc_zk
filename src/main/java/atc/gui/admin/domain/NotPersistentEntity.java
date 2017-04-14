package atc.gui.admin.domain;

public interface NotPersistentEntity extends PersistentEntity
{

    public default Object getId() {
        return null;
    }

    public default boolean isPersisted()
    {
        return false;
    }

}
