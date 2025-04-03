package com.pragma.operationsandexecution.loansanddeposits.infrastructure.configuration;

import com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.implementation.generic.DataProviderFactory;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.interfaces.generic.IDataProvider;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.entitymanagers.entities.LoanNoSqlEntity;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.entitymanagers.entities.LoanSqlEntity;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.implementation.generic.GenericRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.pragma.operationsandexecution.crosscutting.constants.infrastructure.InfrastructureConstants.UTILITY_TYPE_REPOSITORY_NO_SQL;
import static com.pragma.operationsandexecution.crosscutting.constants.infrastructure.InfrastructureConstants.UTILITY_TYPE_REPOSITORY_SQL;

/**
 * Configuración de los repositorios de la aplicación.
 * <p>
 * Esta clase define los beans necesarios para los repositorios genéricos utilizados en la aplicación.
 * Utiliza {@link DataProviderFactory} para proporcionar las implementaciones adecuadas de {@link IDataProvider}
 * según el tipo de base de datos especificado.
 * </p>
 *
 * @Configuration indica a Spring que esta clase contiene definiciones de beans para el contexto de la aplicación.
 */
@Configuration
public class RepositoryConfiguration {

    /**
     * Crea y configura un bean de {@link GenericRepository} para la entidad {@link LoanSqlEntity}.
     * <p>
     * Utiliza {@link DataProviderFactory} para obtener el proveedor de datos adecuado (en este caso, SQL)
     * y lo pasa al constructor de {@link GenericRepository}.
     * </p>
     *
     * @param dataProviderFactory la fábrica para obtener el proveedor de datos correspondiente
     * @return una instancia configurada de {@link GenericRepository} para la entidad {@link LoanSqlEntity}
     */
    @Bean
    public GenericRepository<LoanSqlEntity, String> loanSQLRepository(DataProviderFactory dataProviderFactory) {
        return new GenericRepository<>(LoanSqlEntity.class, dataProviderFactory, UTILITY_TYPE_REPOSITORY_SQL);
    }

    @Bean
    public GenericRepository<LoanNoSqlEntity, String> loanMongoRepository(DataProviderFactory dataProviderFactory) {
        return new GenericRepository<>(LoanNoSqlEntity.class, dataProviderFactory, UTILITY_TYPE_REPOSITORY_NO_SQL);
    }

}
