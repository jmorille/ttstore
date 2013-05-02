package eu.ttbox.service.search.elasticsearch.mapping.phonetic.metaphone;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import eu.ttbox.service.search.elasticsearch.mapping.phonetic.AbstractEsPhoneticTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class EsPhoneticMetaphoneTest extends AbstractEsPhoneticTest {

	@Test
	 public void testOk() {
		 // Just to Start abstract tests
	 }

}
