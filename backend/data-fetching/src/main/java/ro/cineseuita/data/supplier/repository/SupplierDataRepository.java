package ro.cineseuita.data.supplier.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ro.cineseuita.data.supplier.entity.Supplier;

import java.util.List;

public interface SupplierDataRepository extends MongoRepository<Supplier, Long> {

    @Query(value = "{}", fields = "{_id : 1}")
    List<Supplier> findAllByIdsIn(List<Long> ids);

    @Query(value = "{}", fields = "{_id : 1}")
    List<Supplier> findAllJustIds();

}
