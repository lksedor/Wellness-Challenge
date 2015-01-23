package com.ritcheydevelopers.wellnesstracker.model;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "bmi", "date", "logId", "time", "weight" })
public class Weight_ {

	@JsonProperty("bmi")
	private Double bmi;
	@JsonProperty("date")
	private String date;
	@JsonProperty("logId")
	private Long logId;
	@JsonProperty("time")
	private String time;
	@JsonProperty("weight")
	private Double weight;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The bmi
	 */
	@JsonProperty("bmi")
	public Double getBmi() {
		return bmi;
	}

	/**
	 * 
	 * @param bmi
	 *            The bmi
	 */
	@JsonProperty("bmi")
	public void setBmi(Double bmi) {
		this.bmi = bmi;
	}

	/**
	 * 
	 * @return The date
	 */
	@JsonProperty("date")
	public String getDate() {
		return date;
	}

	/**
	 * 
	 * @param date
	 *            The date
	 */
	@JsonProperty("date")
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * 
	 * @return The logId
	 */
	@JsonProperty("logId")
	public Long getLogId() {
		return logId;
	}

	/**
	 * 
	 * @param logId
	 *            The logId
	 */
	@JsonProperty("logId")
	public void setLogId(Long logId) {
		this.logId = logId;
	}

	/**
	 * 
	 * @return The time
	 */
	@JsonProperty("time")
	public String getTime() {
		return time;
	}

	/**
	 * 
	 * @param time
	 *            The time
	 */
	@JsonProperty("time")
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * 
	 * @return The weight
	 */
	@JsonProperty("weight")
	public Double getWeight() {
		return weight;
	}

	/**
	 * 
	 * @param weight
	 *            The weight
	 */
	@JsonProperty("weight")
	public void setWeight(Double weight) {
		this.weight = weight;
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
