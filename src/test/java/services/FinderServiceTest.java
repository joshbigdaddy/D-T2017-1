package services;

import domain.Tenant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import utilities.AbstractTest;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml"
})
@Transactional
public class FinderServiceTest extends AbstractTest {

    @Autowired
    ActorService actorService;

    @Autowired
    FinderService finderService;

    @Test
    public void testFinderService() {

        super.authenticate("ozumas");
        Tenant tenant = (Tenant) actorService.findActorByPrincipal();
        System.out.println(finderService.executeFinder(tenant.getFinder()));
    }

}
