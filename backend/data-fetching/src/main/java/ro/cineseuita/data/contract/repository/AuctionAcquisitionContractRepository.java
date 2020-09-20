package ro.cineseuita.data.contract.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.cineseuita.data.contract.entity.auction.AuctionAcquisitionContract;

public interface AuctionAcquisitionContractRepository extends MongoRepository<AuctionAcquisitionContract, Long> {

}