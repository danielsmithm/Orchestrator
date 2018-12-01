package br.ufrn.dimap.orchestrator.configuration;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class GoogleCloudConfiguration {

    @Value("${google.cloud.projectId}")
    private String googleProjectId;

    @Bean
    public Datastore createDatastore(){
        return DatastoreOptions.newBuilder()
                .setProjectId(googleProjectId)
                .build()
                .getService();
    }

}
