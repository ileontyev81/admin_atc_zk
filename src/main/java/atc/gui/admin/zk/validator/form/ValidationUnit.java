package atc.gui.admin.zk.validator.form;

import lombok.Getter;

public class ValidationUnit<ValueType> {

    @Getter
    private final String name;
    @Getter
    private final ValueType value;

    public ValidationUnit(String name, ValueType value) {
        this.name = name;
        this.value = value;
    }

    public boolean isUseless() {
        return name == null;
    }

    public boolean hasValue() {
        return value != null;
    }

}
