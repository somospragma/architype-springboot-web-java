package drivenadapters.mongo.loantrace;


import drivenadapters.mongo.loantrace.generic.MongoAdapterOperations;
import drivenadapters.mongo.loantrace.mapper.TraceLoanDataMapper;
import com.pragma.loansanddeposits.model.LoanTrace;
import com.pragma.loansanddeposits.port.out.ITraceLoanPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class TraceLoanDataAdapter extends MongoAdapterOperations<LoanTrace, LoanTraceData, String,
        TraceLoanDataRepository, TraceLoanDataMapper> implements ITraceLoanPort {


    protected TraceLoanDataAdapter(TraceLoanDataRepository repository, TraceLoanDataMapper mapper) {
        super(repository, mapper);
    }

    public void saveTraceLoan(LoanTrace loanTrace) {
        repository.save(toData(loanTrace));
    }

}
