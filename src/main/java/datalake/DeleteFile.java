package datalake;

import java.io.IOException;
import com.microsoft.azure.datalake.store.ADLStoreClient;
import com.microsoft.azure.datalake.store.oauth2.AccessTokenProvider;
import com.microsoft.azure.datalake.store.oauth2.ClientCredsTokenProvider;

public class DeleteFile {
	public boolean delete(String authTokenEndpoint, String clientId, String clientKey, String accountFQDN,
			String pathdir, String fname) {
		try {
			AccessTokenProvider provider = new ClientCredsTokenProvider(authTokenEndpoint, clientId, clientKey);
			ADLStoreClient client = ADLStoreClient.createClient(accountFQDN + ".azuredatalakestore.net", provider);
			return client.delete(pathdir + "/" + fname);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
