package ro.cineseuita.processing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.cineseuita.contract.service.ContractsTotalSpendingByTypeService;
import ro.cineseuita.contract.service.DirectAcquisitionContractService;
import ro.cineseuita.contractingauthority.service.ContractingAuthorityService;
import ro.cineseuita.cpv.entity.components.CpvSimpleTreeNode;
import ro.cineseuita.cpv.service.CpvCodesService;
import ro.cineseuita.cpv.service.CpvNationalDataService;
import ro.cineseuita.cpv.service.CpvTreeConstructorService;
import ro.cineseuita.shared.MissingEntitiesResolutionService;
import ro.cineseuita.shared.Period;
import ro.cineseuita.supplier.service.SupplierService;

import java.io.IOException;

@Service
public class ProcessingPipelineService {

    private static final Boolean FETCH_CONTRACTING_AUTHORITIES = false;
    private static final Boolean FETCH_SUPPLIERS = false;
    private static final Boolean FETCH_DIRECT_ACQUISITION_CONTRACTS = false;
    private static final Boolean FETCH_CPV_CODES_FROM_XLS = false;
    private static final Boolean RESOLVE_MISSING_CONTRACTING_AUTHORITIES_AND_SUPPLIERS = false;
    private static final Boolean RESOLVE_MISSING_CONTRACTS = false;
    private static final Boolean COMPUTE_ITEM_MEASUREMENT_CLASS = false;
    private static final Boolean COMPUTE_TOTAL_CONTRACTS_SPENDING_BY_TYPE = false;
    private static final Boolean COMPUTE_CONTRACTING_AUTHORITIES_TOTAL_SPENDING_BY_TYPE = false;
    private static final Boolean COMPUTE_SUPPLIERS_TOTAL_SPENDING_BY_TYPE = false;
    private static final Boolean COMPUTE_CPV_TREE = true;
    private static final Boolean COMPUTE_NATIONAL_CPV_DATA = true;


    private final DirectAcquisitionContractService directAcquisitionContractService;
    private final SupplierService supplierService;
    private final ContractingAuthorityService contractingAuthorityService;
    private final ContractsTotalSpendingByTypeService contractsTotalSpendingByTypeService;
    private final CpvCodesService cpvCodesService;
    private final MissingEntitiesResolutionService missingEntitiesResolutionService;
    private final ItemMeasurementService itemMeasurementService;
    private final CpvTreeConstructorService cpvTreeConstructorService;
    private final CpvNationalDataService cpvNationalDataService;

    @Autowired
    public ProcessingPipelineService(DirectAcquisitionContractService directAcquisitionContractService, SupplierService supplierService,
                                     ContractingAuthorityService contractingAuthorityService, ContractsTotalSpendingByTypeService contractsTotalSpendingByTypeService,
                                     CpvCodesService cpvCodesService, MissingEntitiesResolutionService missingEntitiesResolutionService,
                                     ItemMeasurementService itemMeasurementService, CpvTreeConstructorService cpvTreeConstructorService, CpvNationalDataService cpvNationalDataService) {
        this.directAcquisitionContractService = directAcquisitionContractService;

        this.supplierService = supplierService;
        this.contractingAuthorityService = contractingAuthorityService;
        this.contractsTotalSpendingByTypeService = contractsTotalSpendingByTypeService;
        this.cpvCodesService = cpvCodesService;
        this.missingEntitiesResolutionService = missingEntitiesResolutionService;
        this.itemMeasurementService = itemMeasurementService;
        this.cpvTreeConstructorService = cpvTreeConstructorService;
        this.cpvNationalDataService = cpvNationalDataService;
    }

    public void execute() throws IOException, InterruptedException {
        System.out.println("BEGINNING EXECUTION PIPELINE");
        fetchContractingAuthorities();
        fetchSuppliers();
        fetchContracts();
        fetchCpvCodesFromXls();

        resolveMissingContractingAuthoritiesAndSuppliers();
        resolveMissingContracts();

        computeItemMeasurementClass();
        computeContractsTotalSpendingByType();
        computeContractingAuthoritiesTotalSpendingByType();
        computeSuppliersTotalSpendingByType();
        CpvSimpleTreeNode root = computeCpvTree();
        computeNationalCpvData(root);
        System.out.println("EXECUTION PIPELINE COMPLETE");
    }

    private void fetchContractingAuthorities() throws IOException {
        if (FETCH_CONTRACTING_AUTHORITIES) {
            System.out.println("--- FETCHING CONTRACTING AUTHORITIES (LITE) ---");
            contractingAuthorityService.fetchAllContractingAuthoritiesLite();
            System.out.println("--- DONE FETCHING CONTRACTING AUTHORITIES (LITE) ---");

            System.out.println("--- FETCHING CONTRACTING AUTHORITIES (DETAILS) ---");
            contractingAuthorityService.fetchAllContractingAuthorityDetails();
            System.out.println("--- DONE FETCHING CONTRACTING AUTHORITIES (LITE) ---");
        }
    }

    private void fetchSuppliers() throws IOException {
        if (FETCH_SUPPLIERS) {
            System.out.println("--- FETCHING SUPPLIERS (LITE) ---");
            supplierService.getAllSuppliersLite();
            System.out.println("--- DONE FETCHING SUPPLIERS (LITE) ---");

            System.out.println("--- FETCHING SUPPLIERS (DETAILS) ---");
            supplierService.getAllSupplierDetails();
            System.out.println("--- DONE FETCHING SUPPLIERS (DETAILS) ---");
        }
    }

    private void fetchContracts() throws IOException, InterruptedException {
        if (FETCH_DIRECT_ACQUISITION_CONTRACTS) {
            for (Period period : Period.getYears()) {
                int year = period.getNumVal();
                System.out.println("--- FETCHING CONTRACTS FOR " + year + "  ---");
                directAcquisitionContractService.fullFetch(year);
                System.out.println("--- DONE FETCHING CONTRACTS FOR " + year + "  ---");
            }
        }
    }

    private void fetchCpvCodesFromXls() throws IOException {
        if (FETCH_CPV_CODES_FROM_XLS) {
            System.out.println("--- FETCHING CPV CODES FROM XLS ---");
            cpvCodesService.parseCpvCodesXls();
            cpvCodesService.assignParentCodes();
            System.out.println("--- DONE FETCHING CPV CODES FROM XLS  ---");
        }
    }

    private void resolveMissingContractingAuthoritiesAndSuppliers() {
        if (RESOLVE_MISSING_CONTRACTING_AUTHORITIES_AND_SUPPLIERS) {
            System.out.println("--- RESOLVING MISSING CONTRACTING AUTHORITIES AND SUPPLIERS ---");
            missingEntitiesResolutionService.resolveMissingContractingAuthoritiesAndSuppliers();
            System.out.println("--- DONE RESOLVING MISSING CONTRACTING AUTHORITIES AND SUPPLIERS ---");
        }
    }

    private void resolveMissingContracts() {
        if (RESOLVE_MISSING_CONTRACTS) {
            System.out.println("--- RESOLVING MISSING CONTRACTS ---");
            missingEntitiesResolutionService.resolveMissingContractDetails();
            System.out.println("--- DONE RESOLVING MISSING CONTRACTS ---");
        }
    }

    private void computeItemMeasurementClass() {
        if (COMPUTE_ITEM_MEASUREMENT_CLASS) {
            System.out.println("--- COMPUTING ITEM MEASUREMENT CLASSES ---");
            itemMeasurementService.addItemMeasurementClassToAllContractItems();
            System.out.println("--- DONE COMPUTING ITEM MEASUREMENT CLASSES ---");
        }
    }

    private void computeContractsTotalSpendingByType() {
        if (COMPUTE_TOTAL_CONTRACTS_SPENDING_BY_TYPE) {
            System.out.println("--- COMPUTING CONTRACTS TOTAL SPENDING BY TYPE ---");
            contractsTotalSpendingByTypeService.computeContractsTotalSpendingByType();
            System.out.println("--- DONE COMPUTING CONTRACTS TOTAL SPENDING BY TYPE ---");
        }
    }

    private void computeContractingAuthoritiesTotalSpendingByType() {
        if (COMPUTE_CONTRACTING_AUTHORITIES_TOTAL_SPENDING_BY_TYPE) {
            System.out.println("--- COMPUTING CONTRACTING AUTHORITY SPENDING BY TYPE ---");
            contractsTotalSpendingByTypeService.computeContractsTotalSpendingByTypeForContractingAuthorities();
            System.out.println("--- DONE COMPUTING CONTRACTING AUTHORITY SPENDING BY TYPE ---");
        }
    }

    private void computeSuppliersTotalSpendingByType() {
        if (COMPUTE_SUPPLIERS_TOTAL_SPENDING_BY_TYPE) {
            System.out.println("--- COMPUTING SUPPLIER SPENDING BY TYPE ---");
            contractsTotalSpendingByTypeService.computeContractsTotalSpendingByTypeForSuppliers();
            System.out.println("--- DONE COMPUTING SUPPLIER SPENDING BY TYPE ---");
        }
    }

    private CpvSimpleTreeNode computeCpvTree() {
        if (COMPUTE_CPV_TREE) {
            System.out.println("--- COMPUTING CPV TREE ---");
            CpvSimpleTreeNode cpvSimpleTreeNode = cpvTreeConstructorService.constructCpvCodesTree();
            System.out.println("--- DONE COMPUTING CPV TREE ---");
            return cpvSimpleTreeNode;
        }
        return null;
    }

    private void computeNationalCpvData(CpvSimpleTreeNode root) {
        if (COMPUTE_NATIONAL_CPV_DATA) {
            System.out.println("--- COMPUTING NATIONAL CPV DATA TREE ---");
            cpvNationalDataService.computeNationalWideCpvData(root);
            System.out.println("--- DONE COMPUTING NATIONAL CPV DATA TREE ---");
        }
    }
}
