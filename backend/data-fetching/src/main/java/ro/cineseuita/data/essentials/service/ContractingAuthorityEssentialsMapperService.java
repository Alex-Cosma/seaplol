package ro.cineseuita.data.essentials.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.cineseuita.data.contractingauthority.entity.ContractingAuthorityDetails;
import ro.cineseuita.data.essentials.contractingauthority.entity.ContractingAuthorityEssentials;
import ro.cineseuita.data.essentials.directcontract.entity.ContractingAuthorityForDirectAcquisitionEssentials;

@Service
public class ContractingAuthorityEssentialsMapperService {

    @Autowired
    public ContractingAuthorityEssentialsMapperService() {
    }

    //TODO: maybe refactor this using proper mappers not custom ones

    public ContractingAuthorityForDirectAcquisitionEssentials mapToContractingAuthorityEssentials(ContractingAuthorityDetails contractingAuthorityDetails) {
        ContractingAuthorityForDirectAcquisitionEssentials contractingAuthorityForDirectAcquisitionEssentials = new ContractingAuthorityForDirectAcquisitionEssentials();

        contractingAuthorityForDirectAcquisitionEssentials.set_id(Long.valueOf(contractingAuthorityDetails.getId()));
        contractingAuthorityForDirectAcquisitionEssentials.setName(contractingAuthorityDetails.getName());
        contractingAuthorityForDirectAcquisitionEssentials.setCui(contractingAuthorityDetails.getCui());
        contractingAuthorityForDirectAcquisitionEssentials.setLegalRepresentative(contractingAuthorityDetails.getRegistration().getLegalRepresentative());
        contractingAuthorityForDirectAcquisitionEssentials.setAddress(contractingAuthorityDetails.getAddress());

        return contractingAuthorityForDirectAcquisitionEssentials;
    }

    public ContractingAuthorityEssentials mapToContractingAuthorityWithContractsEssentials(ContractingAuthorityDetails contractingAuthorityDetails) {
        ContractingAuthorityEssentials contractingAuthorityEssentials = new ContractingAuthorityEssentials();

        contractingAuthorityEssentials.set_id(Long.valueOf(contractingAuthorityDetails.getId()));
        contractingAuthorityEssentials.setName(contractingAuthorityDetails.getName());
        contractingAuthorityEssentials.setCui(contractingAuthorityDetails.getCui());
        contractingAuthorityEssentials.setLegalRepresentative(contractingAuthorityDetails.getRegistration().getLegalRepresentative());
        contractingAuthorityEssentials.setAddress(contractingAuthorityDetails.getAddress());

        return contractingAuthorityEssentials;
    }

}
