package ro.cineseuita.contract.direct.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ro.cineseuita.contract.direct.api.dto.DirectContractDto;
import ro.cineseuita.contract.direct.api.dto.DirectContractExpandedItemDto;
import ro.cineseuita.contract.direct.api.dto.DirectContractListDto;
import ro.cineseuita.contract.direct.service.DirectContractService;
import ro.cineseuita.data.essentials.directcontract.repository.DirectAcquisitionContractFilter;

import java.util.List;

@RestController
@RequestMapping("/api/directContracts")
public class DirectContractController {
    private final DirectContractService directContractService;

    @Autowired
    public DirectContractController(final DirectContractService supplierService) {
        this.directContractService = supplierService;
    }

    @GetMapping("/{id}")
    public DirectContractDto get(@PathVariable Long id) {
        return directContractService.get(id);
    }

    @GetMapping("/supplier/{supplierId}")
    public Page<DirectContractListDto> getAllForSupplier(@PathVariable Long supplierId, DirectAcquisitionContractFilter directAcquisitionContractFilter) {
        return directContractService.getAllForSupplier(supplierId, directAcquisitionContractFilter);
    }

    @GetMapping("/contractingAuthority/{contractingAuthorityId}")
    public Page<DirectContractListDto> getAllForContractingAuthority(@PathVariable Long contractingAuthorityId, DirectAcquisitionContractFilter directAcquisitionContractFilter) {
        return directContractService.getAllForContractingAuthority(contractingAuthorityId, directAcquisitionContractFilter);
    }

    @PostMapping("/all")
    public Page<DirectContractListDto> getAll(@RequestBody DirectAcquisitionContractFilter directAcquisitionContractFilter) {
        return directContractService.findAll(directAcquisitionContractFilter);
    }

    @GetMapping("/detailsForExpandedRow/{contractId}")
    public List<DirectContractExpandedItemDto> detailsForExpandedRow(@PathVariable Long contractId) {
        return directContractService.detailsForExpandedRow(contractId);
    }


}
