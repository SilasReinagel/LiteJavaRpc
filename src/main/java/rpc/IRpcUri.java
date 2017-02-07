package rpc;

import java.util.function.Supplier;

public interface IRpcUri extends Supplier<String>
{
    final class Simple implements IRpcUri
    {
        private final String _uri;

        public Simple(final String uri)
        {
            _uri = uri;
        }

        @Override
        public String get()
        {
            return _uri;
        }
    }
}
