package config;

public class constants {
	public class Kafka{
		public static final String SERVER = "Server";
		public static final String PORT = "Port";
		public static final String ZOOKEEPERSERVER = "ZookeeperServer";
		public static final String ZOOKEEPERPORT ="ZookeeperPort";
	}
	public class Adls{
		public static final String KEY = "Key";
		public static final String CLIENT_ID = "ClientID";
		public static final String DIRECTORY = "Directory";
		public static final String AUTH_END_POINT = "AuthEndPoint";
		public static final String ACCOUNT_NAME = "AccountName";
	}
	
	public class EventHub{
		public static final String SHARED_ACCESS_PRIVATE_KEY="SharedAPkey";
		public static final String NAME_SPACE = "NameSpace";
		public static final String TENENT_ID = "TenentID";
		public static final String CLIENT_APP_ID ="ClientAppID";
		public static final String RESOURCE_GROUP = "ResourceGroup";
		public static final String CLIENTKEY = "ClientKey";
		public static final String SUBSCRIPTION = "Subscription";
		public static final String SHARED_ACCESS_PRIVATE_NAME ="SharedAPName";
	}
	public class Jms{
		public static final String URI = "URI";
		public static final String JNDI_CONTEXT_FACTORY="JndiContextFactory";
		public static final String PASSWORD = "Password";
		public static final String USERNAME = "UserName";
		public static final String CONNECTION_FACTORY = "ConnectionFactory";
	}
	public class Kinesis{
		public static final String ACCESS_KEY = "AccessKey";
		public static final String ACCESS_ID ="AccessID";
	}
}
