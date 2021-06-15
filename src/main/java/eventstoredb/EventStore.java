package eventstoredb;

import com.eventstore.dbclient.Client;
import com.eventstore.dbclient.ClientSettings;
import com.eventstore.dbclient.ConnectionSettingsBuilder;
import com.eventstore.dbclient.ConnectionString;
import com.eventstore.dbclient.Endpoint;
import com.eventstore.dbclient.EventStoreDBClusterClient;
import com.eventstore.dbclient.NodePreference;
import com.eventstore.dbclient.Streams;
import com.eventstore.dbclient.UserCredentials;

public class EventStore {
    private static EventStore instance;
    public Client client;

    private EventStore(Client value) {
        this.client = value;
    }

    public static EventStore getInstance() {
        // The doc from evenstore.com sucks!
        //EventStoreDBClientSettings settings = EventStoreDBConnectionString.parse("esdb://localhost:2113?tls=false");
        //EventStoreDBClient client = EventStoreDBClient.create(settings);

        if (instance == null) {
            ConnectionSettingsBuilder settings = ClientSettings.builder().addHost(new Endpoint("localhost", 2113)).tls(false);
            instance = new EventStore(Client.create(settings.buildConnectionSettings()));

            //ClientSettings clientSettings = ConnectionString.parseOrThrow("esdb://localhost:2113?tls=false");
            //instance = new EventStore(Client.create(clientSettings).streams());
        }

        return instance;
    }
}
