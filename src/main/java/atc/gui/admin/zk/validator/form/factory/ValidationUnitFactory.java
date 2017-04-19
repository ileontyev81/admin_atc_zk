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

package atc.gui.admin.zk.validator.form.factory;

import atc.gui.admin.zk.validator.form.ValidationUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;
import org.zkoss.bind.proxy.FormProxyHandler;
import org.zkoss.bind.proxy.ProxyNode;
import org.zkoss.bind.sys.SavePropertyBinding;
import org.zkoss.util.Pair;

import java.lang.reflect.Field;
import java.util.Optional;

@Slf4j
public class ValidationUnitFactory {

    public static <ValueType> ValidationUnit construct(String rawPropertyName, Object rawPropertyValue) {
        String resultPropertyName = null;
        Object resultPropertyValue = null;
        try {
            if (rawPropertyValue != null && rawPropertyValue instanceof org.zkoss.bind.Form) {
                Optional<Pair> pair = getPropertyNameValuePair(rawPropertyValue);
                if (pair.isPresent()) {
                    resultPropertyName = getPropertyName(pair.get());
                    resultPropertyValue = getPropertyValue(pair.get());
                }
            }
            else {
                resultPropertyName = rawPropertyName;
                resultPropertyValue = rawPropertyValue;
            }
        } catch (IllegalAccessException | NoSuchFieldException e) {
            log.error("while creating property container", e);
        }
        return new ValidationUnit(resultPropertyName, (ValueType)resultPropertyValue);
    }

    /**
     * Hack to get name and value of org.zkoss.bind.Form property
     */
    private static Optional<Pair> getPropertyNameValuePair(Object propertyValue) throws IllegalAccessException, NoSuchFieldException {
        Field handlerField = propertyValue.getClass().getDeclaredField("handler");
        handlerField.setAccessible(true);
        FormProxyHandler proxyHandler = (FormProxyHandler)handlerField.get(propertyValue);
        Field nodeField = ReflectionUtils.findField(proxyHandler.getClass(), "_node");
        nodeField.setAccessible(true);
        ProxyNode node = (ProxyNode)nodeField.get(proxyHandler);
        Object[] objects = node.getCachedSavePropertyBinding().toArray();
        return (objects.length > 0) ? Optional.of((Pair) objects[0]) : Optional.empty();
    }

    private static String getPropertyName(Pair pair) throws IllegalAccessException, NoSuchFieldException {
        return pair.getX().toString();
    }

    private static String getPropertyValue(Pair pair) throws IllegalAccessException, NoSuchFieldException {
        return ((org.zkoss.zul.impl.InputElement)((SavePropertyBinding)pair.getY()).getComponent()).getRawText();
    }

}
