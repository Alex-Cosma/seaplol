package ro.cineseuita.data.service;

import org.junit.Before;
import org.junit.Test;
import ro.cineseuita.data.contractingauthority.entity.ContractingAuthorityDetails;
import ro.cineseuita.data.essentials.contractingauthority.entity.ContractingAuthorityEssentials;
import ro.cineseuita.data.essentials.mappers.ContractingAuthorityEssentialsMapperService;
import ro.cineseuita.data.shared.ObjectMapperService;
import ro.cineseuita.data.shared.requests.seap.FetchContractingAuthorityDetails;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ContractingAuthorityForDirectAcquisitionEssentialsMapperServiceTest {

    private ObjectMapperService objectMapperService;
    private ContractingAuthorityEssentialsMapperService service;

    @Before
    public void setUp() {
        objectMapperService = new ObjectMapperService();
        service = new ContractingAuthorityEssentialsMapperService();
    }

    @Test
    public void mapToContractingAuthorityEssentials() throws IOException {
        ContractingAuthorityDetails contractingAuthorityDetails = objectMapperService.mapToContractingAuthorityDetails(new FetchContractingAuthorityDetails(-1L).getMockResponse());

        ContractingAuthorityEssentials contractingAuthorityForDirectAcquisitionEssentials = service.mapToContractingAuthorityWithContractsEssentials(contractingAuthorityDetails);

        assertEquals(contractingAuthorityForDirectAcquisitionEssentials.get_id(), contractingAuthorityDetails.getId());
        assertEquals(contractingAuthorityForDirectAcquisitionEssentials.getName(), contractingAuthorityDetails.getName());
        assertEquals(contractingAuthorityForDirectAcquisitionEssentials.getCui(), contractingAuthorityDetails.getCui());
        assertEquals(contractingAuthorityForDirectAcquisitionEssentials.getLegalRepresentative(), contractingAuthorityDetails.getRegistration().getLegalRepresentative());
        assertEquals(contractingAuthorityForDirectAcquisitionEssentials.getAddress(), contractingAuthorityDetails.getAddress());
    }
}
