package rpc.validation;

import rpc.RpcResponse;
import rpc.exceptions.RpcInvalidResponseException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public final class RpcResponseValidator
{
    public void validate(final RpcResponse response)
    {
        validate(response, response.getClass());
    }

    public void validate(final RpcResponse response, final Class responseType)
    {
        List<String> missingFieldNames = new ArrayList<>();
        for (Field field : getRequestFields(responseType))
            if (isNull(getFieldValue(response, field)))
                missingFieldNames.add("'" + field.getName() + "'");
        if (missingFieldNames.size() > 0)
            throw new RpcInvalidResponseException("Response Incomplete: " + String.join(", ", missingFieldNames) + " value(s) missing.");
    }

    private boolean isNull(final Object fieldValue)
    {
        return fieldValue == null;
    }

    private Object getFieldValue(final Object obj, final Field field)
    {
        try
        {
            return obj.getClass().getField(field.getName()).get(obj);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    private Field[] getRequestFields(final Class requestType)
    {
        return requestType.getFields();
    }
}
