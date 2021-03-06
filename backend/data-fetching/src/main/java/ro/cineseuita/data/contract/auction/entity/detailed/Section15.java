package ro.cineseuita.data.contract.auction.entity.detailed;

import com.fasterxml.jackson.annotation.*;
import ro.cineseuita.data.shared.entityComponents.GenericSeapData;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "mainActivityId",
        "mainActivity",
        "ifOthersThenSpecify",
        "caNoticeId",
        "sectionName",
        "sectionCode",
        "noticePreviousPublication"
})
public class Section15 {

    @JsonProperty("mainActivityId")
    private Long mainActivityId;
    @JsonProperty("mainActivity")
    private GenericSeapData mainActivity;
    @JsonProperty("ifOthersThenSpecify")
    private Object ifOthersThenSpecify;
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

    @JsonProperty("mainActivityId")
    public Long getMainActivityId() {
        return mainActivityId;
    }

    @JsonProperty("mainActivityId")
    public void setMainActivityId(Long mainActivityId) {
        this.mainActivityId = mainActivityId;
    }

    @JsonProperty("mainActivity")
    public GenericSeapData getMainActivity() {
        return mainActivity;
    }

    @JsonProperty("mainActivity")
    public void setMainActivity(GenericSeapData mainActivity) {
        this.mainActivity = mainActivity;
    }

    @JsonProperty("ifOthersThenSpecify")
    public Object getIfOthersThenSpecify() {
        return ifOthersThenSpecify;
    }

    @JsonProperty("ifOthersThenSpecify")
    public void setIfOthersThenSpecify(Object ifOthersThenSpecify) {
        this.ifOthersThenSpecify = ifOthersThenSpecify;
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
