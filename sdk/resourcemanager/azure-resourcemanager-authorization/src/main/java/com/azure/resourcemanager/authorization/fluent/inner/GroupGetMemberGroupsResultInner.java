// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.authorization.fluent.inner;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.logging.ClientLogger;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** The GroupGetMemberGroupsResult model. */
@Fluent
public final class GroupGetMemberGroupsResultInner {
    @JsonIgnore private final ClientLogger logger = new ClientLogger(GroupGetMemberGroupsResultInner.class);

    /*
     * A collection of group IDs of which the group is a member.
     */
    @JsonProperty(value = "value")
    private List<String> value;

    /**
     * Get the value property: A collection of group IDs of which the group is a member.
     *
     * @return the value value.
     */
    public List<String> value() {
        return this.value;
    }

    /**
     * Set the value property: A collection of group IDs of which the group is a member.
     *
     * @param value the value value to set.
     * @return the GroupGetMemberGroupsResultInner object itself.
     */
    public GroupGetMemberGroupsResultInner withValue(List<String> value) {
        this.value = value;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
    }
}
