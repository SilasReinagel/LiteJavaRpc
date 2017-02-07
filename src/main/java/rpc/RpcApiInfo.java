package rpc;

public class RpcApiInfo
{
    public final String Name;
    public final String Version;
    public final String BaseUri;

    public RpcApiInfo(final String name, final String version, final IRpcUri baseUri)
    {
        this(name, version, baseUri.get());
    }

    public RpcApiInfo(final String name, final String version, final String baseUri)
    {
        Name = name;
        Version = version;
        BaseUri = baseUri;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        RpcApiInfo other = (RpcApiInfo) obj;
        if (!Name.equals(other.Name)) return false;
        if (!Version.equals(other.Version)) return false;
        return BaseUri.equals(other.BaseUri);
    }

    @Override
    public int hashCode()
    {
        int result = Name.hashCode();
        result = 31 * result + Version.hashCode();
        result = 31 * result + BaseUri.hashCode();
        return result;
    }
}
