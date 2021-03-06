/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.machinelearningservices.v2019_05_01;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.microsoft.azure.management.machinelearningservices.v2019_05_01.implementation.ComputeSecretsInner;

/**
 * Secrets related to a Machine Learning compute based on Databricks.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "computeType")
@JsonTypeName("Databricks")
public class DatabricksComputeSecrets extends ComputeSecretsInner {
    /**
     * access token for databricks account.
     */
    @JsonProperty(value = "databricksAccessToken")
    private String databricksAccessToken;

    /**
     * Get access token for databricks account.
     *
     * @return the databricksAccessToken value
     */
    public String databricksAccessToken() {
        return this.databricksAccessToken;
    }

    /**
     * Set access token for databricks account.
     *
     * @param databricksAccessToken the databricksAccessToken value to set
     * @return the DatabricksComputeSecrets object itself.
     */
    public DatabricksComputeSecrets withDatabricksAccessToken(String databricksAccessToken) {
        this.databricksAccessToken = databricksAccessToken;
        return this;
    }

}
