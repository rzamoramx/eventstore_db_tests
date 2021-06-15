package testscases;

import com.eventstore.dbclient.EventData;
import com.eventstore.dbclient.ExpectedRevision;
import eventstoredb.EventStore;
import model.TestEventModel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class FirstTest {
    public void appendSomeData() throws ExecutionException, InterruptedException {
        TestEventModel event = new TestEventModel();
        event.setSome_id(UUID.randomUUID().toString());
        event.setSome_data("Hello World from EventStoreDB");

        List<EventData> events = Collections.singletonList(EventData
                .builderAsJson("TestEventModel", event)
                .build());
        java.util.Iterator<EventData> preparedEvents = events.iterator();

        EventStore.getInstance().client.streams().appendStream("some_stream")
                .expectedRevision(ExpectedRevision.NO_STREAM)
                .addEvents(preparedEvents).execute().get();
        //EventStore.getInstance().client.streams().appendStream("some_stream");
    }
}
