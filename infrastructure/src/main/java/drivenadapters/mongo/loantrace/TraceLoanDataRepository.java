package drivenadapters.mongo.loantrace;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TraceLoanDataRepository extends MongoRepository<LoanTraceData, String>, QueryByExampleExecutor<LoanTraceData> {
}
