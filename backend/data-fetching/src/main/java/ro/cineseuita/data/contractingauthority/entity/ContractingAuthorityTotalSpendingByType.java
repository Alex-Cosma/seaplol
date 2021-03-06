package ro.cineseuita.data.contractingauthority.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import ro.cineseuita.data.contract.direct.entity.components.DirectAcquisitionType;
import ro.cineseuita.data.contract.shared.entity.ContractsTotalSpendingByType;

@Document(collection = "contractingAuthorityTotalSpendingByType")
public class ContractingAuthorityTotalSpendingByType extends ContractsTotalSpendingByType {

    private Long contractingAuthorityId;

    public ContractingAuthorityTotalSpendingByType(DirectAcquisitionType type, Long contractingAuthorityId) {
        super(type);
        this.contractingAuthorityId = contractingAuthorityId;
        this.id = contractingAuthorityId + " - " + type.toString();
    }

    public Long getContractingAuthorityId() {
        return contractingAuthorityId;
    }

    public void setContractingAuthorityId(Long contractingAuthorityId) {
        this.contractingAuthorityId = contractingAuthorityId;
    }


}
