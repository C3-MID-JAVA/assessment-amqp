package ec.com.sofka.generic.interfaces;

import ec.com.sofka.generic.util.Request;

public interface UseCaseExecute<T extends Request, R> {
    R execute(T request);
}