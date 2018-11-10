package datalake;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.microsoft.azure.datalake.store.ADLStoreClient;
import com.microsoft.azure.datalake.store.oauth2.AccessTokenProvider;
import com.microsoft.azure.datalake.store.oauth2.ClientCredsTokenProvider;

public class GetData {
	public String Data(String authTokenEndpoint, String clientId, String clientKey, String accountFQDN, String pathdir,
			String fname) {
		StringBuilder sb = new StringBuilder();
		try {

			AccessTokenProvider provider = new ClientCredsTokenProvider(authTokenEndpoint, clientId, clientKey);

			ADLStoreClient client = ADLStoreClient.createClient(accountFQDN + ".azuredatalakestore.net", provider);

			// client.get
			// client.createDirectory("/test");
			// client.createf

			// Read File

			InputStream in = client.getReadStream(pathdir + "/" + fname);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;

			while ((line = reader.readLine()) != null) {
				line = line.replaceAll("\\\\n", System.getProperty("line.separator"));
				sb.append(line + "\n");
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
}
