package datalake;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.azure.datalake.store.ADLStoreClient;
import com.microsoft.azure.datalake.store.DirectoryEntry;
import com.microsoft.azure.datalake.store.oauth2.AccessTokenProvider;
import com.microsoft.azure.datalake.store.oauth2.ClientCredsTokenProvider;

public class GetFiles {
	public ArrayList<String> Get(String authTokenEndpoint, String clientId, String clientKey, String accountFQDN,
			String pathdir) {
		ArrayList<String> mylist = new ArrayList<String>();
		try {
			AccessTokenProvider provider = new ClientCredsTokenProvider(authTokenEndpoint, clientId, clientKey);

			ADLStoreClient client = ADLStoreClient.createClient(accountFQDN + ".azuredatalakestore.net", provider);

			List<DirectoryEntry> list = client.enumerateDirectory(pathdir, 2000);
			for (DirectoryEntry entry : list) {
				if (entry.type.toString() == "FILE") {
					mylist.add(entry.name);
					System.out.println(entry.name + ":size:" + entry.length + ":blocksize:" + entry.blocksize);
				}
			}
		} catch (Exception ex) {
			System.out.println("invalid files");
		}
		return mylist;
	}
}
