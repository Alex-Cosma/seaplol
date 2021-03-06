package ro.cineseuita.data.contract.auction.entity.detailed;


import com.fasterxml.jackson.annotation.*;
import ro.cineseuita.data.shared.entityComponents.GenericSeapData;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "isFromEu",
        "isNotFromEu",
        "country",
        "caNoticeContractId",
        "caNoticeId",
        "sectionName",
        "sectionCode",
        "noticePreviousPublication"
})
public class Section528 {

    @JsonProperty("isFromEu")
    private Boolean isFromEu;
    @JsonProperty("isNotFromEu")
    private Boolean isNotFromEu;
    @JsonProperty("country")
    private GenericSeapData country;
    @JsonProperty("caNoticeContractId")
    private Long caNoticeContractId;
    @JsonProperty("caNoticeId")
    private Long caNoticeId;
    @JsonProperty("sectionName")
    private Object sectionName;
    @JsonProperty("sectionCode")
    private Object sectionCode;
    @JsonProperty("noticePreviousPublication")
    private Object noticePreviousPublication;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("isFromEu")
    public Boolean getIsFromEu() {
        return isFromEu;
    }

    @JsonProperty("isFromEu")
    public void setIsFromEu(Boolean isFromEu) {
        this.isFromEu = isFromEu;
    }

    @JsonProperty("isNotFromEu")
    public Boolean getIsNotFromEu() {
        return isNotFromEu;
    }

    @JsonProperty("isNotFromEu")
    public void setIsNotFromEu(Boolean isNotFromEu) {
        this.isNotFromEu = isNotFromEu;
    }

    @JsonProperty("country")
    public GenericSeapData getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(GenericSeapData country) {
        this.country = country;
    }

    @JsonProperty("caNoticeContractId")
    public Long getCaNoticeContractId() {
        return caNoticeContractId;
    }

    @JsonProperty("caNoticeContractId")
    public void setCaNoticeContractId(Long caNoticeContractId) {
        this.caNoticeContractId = caNoticeContractId;
    }

    @JsonProperty("caNoticeId")
    public Long getCaNoticeId() {
        return caNoticeId;
    }

    @JsonProperty("caNoticeId")
    public void setCaNoticeId(Long caNoticeId) {
        this.caNoticeId = caNoticeId;
    }

    @JsonProperty("sectionName")
    public Object getSectionName() {
        return sectionName;
    }

    @JsonProperty("sectionName")
    public void setSectionName(Object sectionName) {
        this.sectionName = sectionName;
    }

    @JsonProperty("sectionCode")
    public Object getSectionCode() {
        return sectionCode;
    }

    @JsonProperty("sectionCode")
    public void setSectionCode(Object sectionCode) {
        this.sectionCode = sectionCode;
    }

    @JsonProperty("noticePreviousPublication")
    public Object getNoticePreviousPublication() {
        return noticePreviousPublication;
    }

    @JsonProperty("noticePreviousPublication")
    public void setNoticePreviousPublication(Object noticePreviousPublication) {
        this.noticePreviousPublication = noticePreviousPublication;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
