package uk.ac.ebi.pride.proteomes.pipeline.unifier.funtional.steps;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: ntoro
 * Date: 16/10/2013
 * Time: 17:49
 */

@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
@Transactional(transactionManager = "transactionManager")
@ContextConfiguration(locations = {"classpath:/META-INF/context/data-unifier-hsql-test-context.xml"})
@TestExecutionListeners(listeners = {
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class})
public class ProteinMappingUniquenessStepTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    @DirtiesContext
    public void launchStep() throws Exception {

        //Testing a individual step
        JobExecution jobExecution = jobLauncherTestUtils.launchStep("proteinMappingUniquenessStep");
        Assert.assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());

    }
}

