package rpc.validation;

import rpc.RpcRequest;
import rpc.exceptions.RpcInvalidRequestException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public final class RpcRequestValidator
{
    private boolean _requiresRequestId = true;

    public void validate(final RpcRequest request)
    {
        validate(request, request.getClass());
    }

    public void validate(final RpcRequest request, final Class requestType)
    {
        List<String> missingFieldNames = new ArrayList<>();
        for (Field field : getRequestFields(requestType))
            if (isNullOrIsEmptyString(getFieldValue(request, field)))
                missingFieldNames.add("'" + field.getName() + "'");
        if (!_requiresRequestId)
            missingFieldNames.remove("'RequestId'");
        if (missingFieldNames.size() > 0)
            throw new RpcInvalidRequestException("Request Invalid: " + String.join(", ", missingFieldNames) + " value(s) required.");
    }

    public void setRequiresRequestId(final boolean requiresRequestId)
    {
        _requiresRequestId = requiresRequestId;
    }

    private boolean isNullOrIsEmptyString(final Object fieldValue)
    {
        return fieldValue == null || fieldValue.equals("");
    }

    private Object getFieldValue(final RpcRequest request, final Field field)
    {
        try
        {
            return request.getClass().getField(field.getName()).get(request);
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
