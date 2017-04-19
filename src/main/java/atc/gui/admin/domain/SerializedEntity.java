/*
 * Copyright (C) 2017 i.leontyev81@gmail.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package atc.gui.admin.domain;

import atc.gui.admin.domain.model.appobjects.SelectEntity;
import com.impossibl.postgres.api.jdbc.PGSQLInput;
import com.impossibl.postgres.api.jdbc.PGSQLOutput;
import com.impossibl.postgres.types.CompositeType;
import com.impossibl.postgres.types.CompositeType.Attribute;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;
import org.zkoss.bind.proxy.ProxyHelper;

import javax.persistence.Transient;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;

@Slf4j
public abstract class SerializedEntity implements SQLData, HasInit {

    public static Integer DEFAULT_NUMBER_ID = 0;// TODO: может быть сделать -1 ?
    public static String DEFAULT_STRING_VALUE = "";
    public static Boolean DEFAULT_BOOLEAN_VALUE = Boolean.FALSE;

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    // TODO: rebuild by using standart validator
    private static class FieldsStateRepair {

        private void fillNotNullFields(SerializedEntity entity) {
            for (Field field : entity.getClassFields()) {
                String name = field.getName();
                Object value = entity.getData(field);
                try {
                    Set<? extends ConstraintViolation<? extends SerializedEntity>> constraintViolations = validator.validateValue(entity.getClass(), name, value);
                    if (!constraintViolations.isEmpty())
                    {
                        Optional<Object> defaultValue = getDefaultValueForType(field.getType());
                        if (defaultValue.isPresent()) {
                            entity.setData(field, defaultValue.get());
                        }
                    }
                } catch (Exception e) {
                    log.error("name: " + name + " value: " + value, e);
                }
            }
        }

        private Optional<Object> getDefaultValueForType(Class<?> type) {
            if (String.class.isAssignableFrom(type)) {
                return Optional.of(DEFAULT_STRING_VALUE);
            }
            if (Integer.class.isAssignableFrom(type)) {
                return Optional.of(DEFAULT_NUMBER_ID);
            }
            if (Long.class.isAssignableFrom(type)) {
                return Optional.of(Long.valueOf(DEFAULT_NUMBER_ID));
            }
            if (Boolean.class.isAssignableFrom(type)) {
                return Optional.of(DEFAULT_BOOLEAN_VALUE);
            }
            return Optional.empty();
        }
    }

    private final static FieldsStateRepair fieldsStateRepair = new FieldsStateRepair();

    public SerializedEntity() {
        log.debug("SerializedEntity: " + getClass());
        if (fillWithDefaultValues()) {
            fieldsStateRepair.fillNotNullFields(this);
        }
    }

    private boolean fillWithDefaultValues() {
        return (!getClass().isAnnotationPresent(NoDefaultOnCreation.class));
    }
    public void init(ResultSet rs) {
        try {
            for (int index = 1; index <= rs.getMetaData().getColumnCount(); index++) {
                String columnName = rs.getMetaData().getColumnName(index);
                log.debug("assign data to field " + columnName);
                setData(findField(columnName), rs.getObject(index));
            }
        } catch (SQLException exception) {
            log.error("", exception);
        }
    }

    private String converted(String columnName) {
        StringBuffer result = new StringBuffer();
        StringTokenizer tokenizer = new StringTokenizer(columnName, "_");
        int tokenIndex = 0;
        while (tokenizer.hasMoreTokens()) {
            String namePart = tokenizer.nextToken();
            if (tokenIndex > 0) {
                namePart = StringUtils.capitalize(namePart);
            }
            result.append(namePart);
            tokenIndex++;
        }
        log.debug("converted field " + result.toString());
        return result.toString();
    }

    /**
     * Read data got from db UDT into POJO
     */
    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        PGSQLInput in = (PGSQLInput) stream;
        for (Field field : getClassFields()) {
            setData(field, in.readObject());
        }
    }

    /**
     * Write data from current POJO into db UDT
     */
    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        PGSQLOutput out = (PGSQLOutput) stream;
        Field typeField = ReflectionUtils.findField(stream.getClass(), "type");
        ReflectionUtils.makeAccessible(typeField);
        CompositeType type = (CompositeType) ReflectionUtils.getField(typeField, stream);
        recreateAttributesIndexes(type);
        ReflectionUtils.setField(typeField, stream, type);
        try {
            for (Attribute dbAttribute : type.getAttributes())// the same order as in db
            {
                Field attributeField = findField(dbAttribute.name);
                if (attributeField != null) {
                    out.writeObject(getData(attributeField));
                } else {
                    out.writeObject(null);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // TODO: driver hack
    private void recreateAttributesIndexes(CompositeType type) {
        List<Attribute> attributes = type.getAttributes();
        for (int i = 1; i <= attributes.size(); i++) {
            Attribute dbAttribute = attributes.get(i - 1);
            dbAttribute.number = i;
        }
    }

    private void setData(Field field, Object object) {
        if (field != null && object != null) {
            if (object != null && object.getClass().isArray() && Collection.class.isAssignableFrom(field.getType())) {
                object = convertArrayToCollection(field.getType(), (Object[]) object);
            }
            ReflectionUtils.makeAccessible(field);
            ReflectionUtils.setField(field, this, object);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private Collection convertArrayToCollection(Class fieldType, Object[] data) {
        if (Set.class.isAssignableFrom(fieldType)) {
            return new HashSet(Arrays.asList(data));
        }
        return new ArrayList(Arrays.asList(data));
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private Object getData(Field field) {
        ReflectionUtils.makeAccessible(field);
        Object fieldData = ReflectionUtils.getField(field, this);
        if (fieldData != null && Collection.class.isAssignableFrom(fieldData.getClass())) {
            return ((Collection) fieldData).toArray(new SelectEntity[]{});
        }
        return fieldData;
    }

    @Override
    public String getSQLTypeName() throws SQLException {
        return getClass().getAnnotation(UdtSqlType.class).value();
    }

    private Field findField(String columnName) {
        for (Field field : getClassFields()) {
            if (field.getName().equals(columnName)) {
                return field;
            }
            if (field.getName().equals(converted(columnName))) {
                return field;
            }
            if (field.isAnnotationPresent(DbField.class)) {
                DbField dbField = field.getAnnotation(DbField.class);
                if (dbField.value().equals(columnName)) {
                    return field;
                }
            }
        }
        return null;
    }

    private List<Field> getClassFields() {
        return getClassFields(getUnwrappedEntityClass().getDeclaredFields(),
                Collections.emptyList(), Collections.singletonList(Transient.class));
    }

    public List<Field> getUIClassFields() {
        return getClassFields(getUnwrappedEntityClass().getDeclaredFields(),
                Collections.singletonList(FormField.class), Collections.singletonList(Transient.class));
    }

    private Class<? extends SerializedEntity> getUnwrappedEntityClass() {
        try {
            Method handlerField = ProxyHelper.class.getDeclaredMethod("getTargetClassIfProxied", Class.class);
            handlerField.setAccessible(true);
            return (Class<? extends SerializedEntity>) ReflectionUtils.invokeMethod(handlerField, ProxyHelper.class, this.getClass());
        } catch (NoSuchMethodException e) {
            log.error("", e);
        }
        return this.getClass();
    }

    private List<Field> getClassFields(Field[] rawFields, List<Class> neededMarks, List<Class> uselessMarks) {
        List<Field> filteredFields = new ArrayList<Field>();
        for (Field field : rawFields) {
            boolean containsAllNeeded = neededMarks.isEmpty() || neededMarks.stream().allMatch(annotation -> field.isAnnotationPresent(annotation));
            boolean uselessContains = !uselessMarks.isEmpty() && uselessMarks.stream().anyMatch(annotation -> field.isAnnotationPresent(annotation));
            if (containsAllNeeded && !uselessContains) {
                filteredFields.add(field);
            }
        }
        return filteredFields;
    }

}