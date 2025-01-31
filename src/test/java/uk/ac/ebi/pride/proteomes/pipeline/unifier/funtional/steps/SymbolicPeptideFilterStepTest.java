package uk.ac.ebi.pride.proteomes.pipeline.unifier.funtional.steps;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

/**
 * User: ntoro
 * Date: 11/10/2013
 * Time: 16:50
 */

/**
 * This test can only be executed under the data-unifier-oracle-test-context.xml context configuration because the query to
 * find filter the symbolic peptides is oracle dependent nowadays. Maybe in the future it can be generalized to a standard sql one.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/META-INF/context/data-unifier-oracle-test-context.xml"})
@TestExecutionListeners(listeners = {
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class})
public class SymbolicPeptideFilterStepTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Before
    public void setUp() throws Exception {

//
//        jdbcTemplate.update( "DELETE FROM PRIDEPROT.PEP_MOD WHERE PEPTIDE_ID='888888881' AND MOD_ID='MOD:01214' AND POSITION='0.0' ");
//        jdbcTemplate.update( "DELETE FROM PRIDEPROT.PEP_MOD WHERE PEPTIDE_ID='45482309000' AND MOD_ID='MOD:01214' AND POSITION='0.0' ");
//        jdbcTemplate.update( "DELETE FROM PRIDEPROT.PEP_MOD WHERE PEPTIDE_ID='45482309013' AND MOD_ID='MOD:01214' AND POSITION='13.0' ");
//        jdbcTemplate.update( "DELETE FROM PRIDEPROT.PEP_MOD WHERE PEPTIDE_ID='45482309001' AND MOD_ID='MOD:01214' AND POSITION='0.0' ");
//        jdbcTemplate.update( "DELETE FROM PRIDEPROT.PEP_MOD WHERE PEPTIDE_ID='45482309012' AND MOD_ID='MOD:01214' AND POSITION='9.0' ");
//
//        jdbcTemplate.update( "DELETE FROM PRIDEPROT.PEPTIDE WHERE PEPTIDE_ID='888888881' ");
//        jdbcTemplate.update( "DELETE FROM PRIDEPROT.PEPTIDE WHERE PEPTIDE_ID='45482309000' ");
//        jdbcTemplate.update( "DELETE FROM PRIDEPROT.PEPTIDE WHERE PEPTIDE_ID='45482309013' ");
//        jdbcTemplate.update( "DELETE FROM PRIDEPROT.PEPTIDE WHERE PEPTIDE_ID='45482309001' ");
//        jdbcTemplate.update( "DELETE FROM PRIDEPROT.PEPTIDE WHERE PEPTIDE_ID='45482309012' ");
//        jdbcTemplate.update( "DELETE FROM PRIDEPROT.PEPTIDE WHERE PEPTIDE_ID='32' ");
//        jdbcTemplate.update( "DELETE FROM PRIDEPROT.PEPTIDE WHERE PEPTIDE_ID='1' ");
//        jdbcTemplate.update( "DELETE FROM PRIDEPROT.PEPTIDE WHERE PEPTIDE_ID='45482301001' ");
//
//
//
//        //The two first need to be deleted
//        //The third one should stay
//
//
//        //Peptide with only one modification in the n-term position
//        jdbcTemplate.update( "INSERT INTO PRIDEPROT.PEPTIDE (PEPTIDE_ID, SEQUENCE, REPRESENTATION, DESCRIPTION, SCORE_ID, SYMBOLIC, TAXID) "
//                + "VALUES (888888881, N'EWKSNVYLAR', N'[EWKSNVYLAR|9606|(0,MOD:01214)]', N'', 1.0, N'FALSE', 9606.0) ");
//        jdbcTemplate.update( " INSERT INTO PRIDEPROT.PEP_MOD (PEPTIDE_ID, MOD_ID, POSITION) "
//                + "VALUES (888888881, N'MOD:01214', 0.0) ");
//
//        jdbcTemplate.update( "INSERT INTO PRIDEPROT.PEPTIDE (PEPTIDE_ID, SEQUENCE, REPRESENTATION, DESCRIPTION, SCORE_ID, SYMBOLIC, TAXID) "
//                + "VALUES (32, N'EWKSNVYLAR', N'[EWKSNVYLAR|9606]', N'', 1.0, N'TRUE', 9606.0) ");
//
//        //Peptide with two modifications in the n-term and in the c-term positions
//        jdbcTemplate.update( "INSERT INTO PRIDEPROT.PEPTIDE (PEPTIDE_ID, SEQUENCE, REPRESENTATION, DESCRIPTION, SCORE_ID, SYMBOLIC, TAXID) "
//                + "VALUES (45482309000, N'PPCEDEIDEFLK', N'[PPCEDEIDEFLK|9606|(0,MOD:01214)]', N'', 1.0, N'FALSE', 9606.0) ");
//        jdbcTemplate.update( " INSERT INTO PRIDEPROT.PEP_MOD (PEPTIDE_ID, MOD_ID, POSITION) "
//                + "VALUES (45482309000, N'MOD:01214', 0.0) ");
//        jdbcTemplate.update( "INSERT INTO PRIDEPROT.PEPTIDE (PEPTIDE_ID, SEQUENCE, REPRESENTATION, DESCRIPTION, SCORE_ID, SYMBOLIC, TAXID) "
//                + "VALUES (45482309013, N'PPCEDEIDEFLK', N'[PPCEDEIDEFLK|9606|(13,MOD:01214)]', N'', 1.0, N'FALSE', 9606.0) ");
//        jdbcTemplate.update( " INSERT INTO PRIDEPROT.PEP_MOD (PEPTIDE_ID, MOD_ID, POSITION) "
//                + "VALUES (45482309013, N'MOD:01214', 13.0) ");
//
//        jdbcTemplate.update( "INSERT INTO PRIDEPROT.PEPTIDE (PEPTIDE_ID, SEQUENCE, REPRESENTATION, DESCRIPTION, SCORE_ID, SYMBOLIC, TAXID) "
//                + "VALUES (1, N'PPCEDEIDEFLK', N'[PPCEDEIDEFLK|9606]', N'', 1.0, N'TRUE', 9606.0) ");
//
//
//        //Peptide with two modifications in the n-term and in the c-term positions
//        jdbcTemplate.update( "INSERT INTO PRIDEPROT.PEPTIDE (PEPTIDE_ID, SEQUENCE, REPRESENTATION, DESCRIPTION, SCORE_ID, SYMBOLIC, TAXID) "
//                + "VALUES (45482309001, N'PPCEDEID', N'[PPCEDEID|9606|(0,MOD:01214)]', N'', 1.0, N'FALSE', 9606.0) ");
//        jdbcTemplate.update( " INSERT INTO PRIDEPROT.PEP_MOD (PEPTIDE_ID, MOD_ID, POSITION) "
//                + "VALUES (45482309001, N'MOD:01214', 0.0) ");
//        jdbcTemplate.update( "INSERT INTO PRIDEPROT.PEPTIDE (PEPTIDE_ID, SEQUENCE, REPRESENTATION, DESCRIPTION, SCORE_ID, SYMBOLIC, TAXID) "
//                + "VALUES (45482309012, N'PPCEDEID', N'[PPCEDEID|9606|(8,MOD:01214)]', N'', 1.0, N'FALSE', 9606.0) ");
//        jdbcTemplate.update( " INSERT INTO PRIDEPROT.PEP_MOD (PEPTIDE_ID, MOD_ID, POSITION) "
//                + "VALUES (45482309012, N'MOD:01214', 9.0) ");
//        jdbcTemplate.update( "INSERT INTO PRIDEPROT.PEPTIDE (PEPTIDE_ID, SEQUENCE, REPRESENTATION, DESCRIPTION, SCORE_ID, SYMBOLIC, TAXID) "
//                + "VALUES (45482301001, N'PPCEDEID', N'[PPCEDEID|9606]', N'', 1.0, N'TRUE', 9606.0) ");

    }

    @After
    public void tearDown() throws Exception {

//        jdbcTemplate.update( "DELETE FROM PRIDEPROT.PEP_MOD WHERE PEPTIDE_ID='888888881' AND MOD_ID='MOD:01214' AND POSITION='0.0' ");
//        jdbcTemplate.update( "DELETE FROM PRIDEPROT.PEP_MOD WHERE PEPTIDE_ID='45482309000' AND MOD_ID='MOD:01214' AND POSITION='0.0' ");
//        jdbcTemplate.update( "DELETE FROM PRIDEPROT.PEP_MOD WHERE PEPTIDE_ID='45482309013' AND MOD_ID='MOD:01214' AND POSITION='13.0' ");
//        jdbcTemplate.update( "DELETE FROM PRIDEPROT.PEP_MOD WHERE PEPTIDE_ID='45482309001' AND MOD_ID='MOD:01214' AND POSITION='0.0' ");
//        jdbcTemplate.update( "DELETE FROM PRIDEPROT.PEP_MOD WHERE PEPTIDE_ID='45482309012' AND MOD_ID='MOD:01214' AND POSITION='9.0' ");
//
//        jdbcTemplate.update( "DELETE FROM PRIDEPROT.PEPTIDE WHERE PEPTIDE_ID='888888881' ");
//        jdbcTemplate.update( "DELETE FROM PRIDEPROT.PEPTIDE WHERE PEPTIDE_ID='45482309000' ");
//        jdbcTemplate.update( "DELETE FROM PRIDEPROT.PEPTIDE WHERE PEPTIDE_ID='45482309013' ");
//        jdbcTemplate.update( "DELETE FROM PRIDEPROT.PEPTIDE WHERE PEPTIDE_ID='45482309001' ");
//        jdbcTemplate.update( "DELETE FROM PRIDEPROT.PEPTIDE WHERE PEPTIDE_ID='45482309012' ");
//        jdbcTemplate.update( "DELETE FROM PRIDEPROT.PEPTIDE WHERE PEPTIDE_ID='32' ");
//        jdbcTemplate.update( "DELETE FROM PRIDEPROT.PEPTIDE WHERE PEPTIDE_ID='1' ");
//        jdbcTemplate.update( "DELETE FROM PRIDEPROT.PEPTIDE WHERE PEPTIDE_ID='45482301001' ");

    }


    @Test
    @DirtiesContext
    @Ignore("This test depends of the PRIDEPROT Oracle DB, run carefully")
    public void launchStep() throws Exception {

        //Testing a individual step
        JobExecution jobExecution = jobLauncherTestUtils.launchStep("symbolicPeptideFilterStep");
        Assert.assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
    }
}
