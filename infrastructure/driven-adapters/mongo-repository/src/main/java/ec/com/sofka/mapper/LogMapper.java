package ec.com.sofka.mapper;

import ec.com.sofka.Log;
import ec.com.sofka.data.LogEntity;

public class LogMapper {

    // Convierte de la entidad de dominio Log a la entidad persistente LogEntity
    public static LogEntity toEntity(Log log) {
        return new LogEntity(log.getMessage());
    }

    // Convierte de la entidad persistente LogEntity a la entidad de dominio Log
    public static Log toDomain(LogEntity logEntity) {
        Log log = new Log(logEntity.getId(), logEntity.getTimestamp());
        log.setTimestamp(logEntity.getTimestamp());
        return log;
    }

}
