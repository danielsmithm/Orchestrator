package br.ufrn.dimap.orchestrator.token;


import br.ufrn.dimap.orchestrator.OrchestratorApplication;
import br.ufrn.dimap.orchestrator.configuration.GoogleCloudConfiguration;
import br.ufrn.dimap.orchestrator.domain.application.Application;
import br.ufrn.dimap.orchestrator.domain.application.ApplicationRepository;
import br.ufrn.dimap.orchestrator.domain.application.Appspot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNotSame;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:application.properties")
@ContextConfiguration(classes = {OrchestratorApplication.class})
public class ApplicationRepositoryIntegrationTests {

    @Autowired
    private ApplicationRepository repository;

    @Test
    public void testFindAllApplications(){
        List<Application> allApplications = repository.findAllApplications();

        assertNotNull(allApplications);
    }

    @Test
    public void testSaveApplication(){

        //Using the persistence constructor to initialize the application just for database integration purposes.
        Application application = new Application();

        application.setAppspot(Appspot.from("Appspot test"));
        application.setOwnerName("Ownername test");

        Application applicationAfterSave = repository.save(application);

        assertNotNull(applicationAfterSave);
        assertNotNull(application.getAppspot());
        assertNotNull(application.getOwnerName());
    }

}
