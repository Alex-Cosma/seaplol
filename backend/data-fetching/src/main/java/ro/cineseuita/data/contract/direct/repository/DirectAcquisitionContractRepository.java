package ro.cineseuita.data.contract.direct.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ro.cineseuita.data.contract.direct.entity.DirectAcquisitionContract;

public interface DirectAcquisitionContractRepository extends MongoRepository<DirectAcquisitionContract, Long> {

    @Query("{ 'uniqueIdentificationCode' : ?0 }")
    DirectAcquisitionContract findByUniqueIdentificationCode(String uniqueId);
}
