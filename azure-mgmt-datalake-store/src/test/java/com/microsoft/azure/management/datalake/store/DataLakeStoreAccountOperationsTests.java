package com.microsoft.azure.management.datalake.store;

import com.microsoft.azure.management.datalake.store.models.DataLakeStoreAccount;
import com.microsoft.azure.management.datalake.store.models.DataLakeStoreAccountProperties;
import com.microsoft.azure.management.resources.models.ResourceGroup;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataLakeStoreAccountOperationsTests extends DataLakeStoreManagementTestBase {
    private static String rgName = generateName("javaadlsrg");
    private static String location = "eastus2";
    private static String adlsAcct = generateName("javaadlsacct");

    @BeforeClass
    public static void setup() throws Exception {
        createClients();
        ResourceGroup group = new ResourceGroup();
        group.setLocation(location);
        resourceManagementClient.getResourceGroupsOperations().createOrUpdate(rgName, group);
    }

    @AfterClass
    public static void cleanup() throws Exception {
        try {
            resourceManagementClient.getResourceGroupsOperations().delete(rgName);
        }
        catch (Exception e) {
            // ignore failures during cleanup, as it is best effort
        }
    }
    @Test
    public void canCreateGetUpdateDeleteAdlsAccount() throws Exception {
        // Create
        DataLakeStoreAccountProperties createProperties = new DataLakeStoreAccountProperties();
        
        DataLakeStoreAccount createParams = new DataLakeStoreAccount();
        createParams.setLocation(location);
        createParams.setName(adlsAcct);
        createParams.setProperties(createProperties);
        createParams.setTags(new HashMap<String, String>());
        createParams.getTags().put("testkey", "testvalue");

        DataLakeStoreAccount createResponse = dataLakeStoreAccountManagementClient.getAccountOperations().create(rgName, adlsAcct, createParams).getBody();
        Assert.assertEquals(location, createResponse.getLocation());
        Assert.assertEquals("Microsoft.DataLakeStore/accounts", createResponse.getType());
        Assert.assertNotNull(createResponse.getId());
        Assert.assertTrue(createResponse.getId().contains(adlsAcct));
        Assert.assertEquals(1, createResponse.getTags().size());

        // update the tags
        createParams.getTags().put("testkey2", "testvalue2");
        createParams.setProperties(null);
        DataLakeStoreAccount updateResponse = dataLakeStoreAccountManagementClient.getAccountOperations().update(rgName, adlsAcct, createParams).getBody();
        Assert.assertEquals(location, updateResponse.getLocation());
        Assert.assertEquals("Microsoft.DataLakeStore/accounts", updateResponse.getType());
        Assert.assertNotNull(updateResponse.getId());
        Assert.assertTrue(updateResponse.getId().contains(adlsAcct));
        Assert.assertEquals(2, updateResponse.getTags().size());

        // get the account
        DataLakeStoreAccount getResponse = dataLakeStoreAccountManagementClient.getAccountOperations().get(rgName, adlsAcct).getBody();
        Assert.assertEquals(location, getResponse.getLocation());
        Assert.assertEquals("Microsoft.DataLakeStore/accounts", getResponse.getType());
        Assert.assertNotNull(getResponse.getId());
        Assert.assertTrue(getResponse.getId().contains(adlsAcct));
        Assert.assertEquals(2, getResponse.getTags().size());

        // list all accounts and make sure there is one.
        List<DataLakeStoreAccount> listResult = dataLakeStoreAccountManagementClient.getAccountOperations().list().getBody();
        DataLakeStoreAccount discoveredAcct = null;
        for (DataLakeStoreAccount acct : listResult) {
            if (acct.getName().equals(adlsAcct)) {
                discoveredAcct = acct;
                break;
            }
        }

        Assert.assertNotNull(discoveredAcct);
        Assert.assertEquals(location, discoveredAcct.getLocation());
        Assert.assertEquals("Microsoft.DataLakeStore/accounts", discoveredAcct.getType());
        Assert.assertNotNull(discoveredAcct.getId());
        Assert.assertTrue(discoveredAcct.getId().contains(adlsAcct));
        Assert.assertEquals(2, discoveredAcct.getTags().size());

        // the properties should be empty when we do list calls
        Assert.assertNull(discoveredAcct.getProperties().getDefaultGroup());

        // list within a resource group
        listResult = dataLakeStoreAccountManagementClient.getAccountOperations().listByResourceGroup(rgName).getBody();
        discoveredAcct = null;
        for (DataLakeStoreAccount acct : listResult) {
            if (acct.getName().equals(adlsAcct)) {
                discoveredAcct = acct;
                break;
            }
        }

        Assert.assertNotNull(discoveredAcct);
        Assert.assertEquals(location, discoveredAcct.getLocation());
        Assert.assertEquals("Microsoft.DataLakeStore/accounts", discoveredAcct.getType());
        Assert.assertNotNull(discoveredAcct.getId());
        Assert.assertTrue(discoveredAcct.getId().contains(adlsAcct));
        Assert.assertEquals(2, discoveredAcct.getTags().size());

        // the properties should be empty when we do list calls
        Assert.assertNull(discoveredAcct.getProperties().getDefaultGroup());

        // Delete the ADLS account
        dataLakeStoreAccountManagementClient.getAccountOperations().delete(rgName, adlsAcct);

        // Do it again, it should not throw
        dataLakeStoreAccountManagementClient.getAccountOperations().delete(rgName, adlsAcct);
    }
}
