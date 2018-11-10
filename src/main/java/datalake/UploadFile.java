package datalake;

import java.io.IOException;

import com.microsoft.azure.datalake.store.ADLStoreClient;
import com.microsoft.azure.datalake.store.IfExists;
import com.microsoft.azure.datalake.store.oauth2.AccessTokenProvider;
import com.microsoft.azure.datalake.store.oauth2.ClientCredsTokenProvider;

import datalake.helper;

public class UploadFile {
	public boolean update(String authTokenEndpoint, String clientId, String clientKey, String accountFQDN,
			String pathdir, String fileName, String localfile) {
		AccessTokenProvider provider = new ClientCredsTokenProvider(authTokenEndpoint, clientId, clientKey);
		ADLStoreClient client = ADLStoreClient.createClient(accountFQDN + ".azuredatalakestore.net", provider);
		helper ut = new helper(client);
		try {
			ut.upload(pathdir + "/" + fileName, localfile, IfExists.OVERWRITE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
}
