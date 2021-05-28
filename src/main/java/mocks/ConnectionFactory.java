package mocks;

import java.io.InputStream;

/**
 * Class factory refactoring technique, replace subclasses with collaborator
 * The role of classes implementing this factory is
 * to return an InputStream from a connection, whatever the connection might be
 */
public interface ConnectionFactory {
    InputStream getData() throws Exception;
}
