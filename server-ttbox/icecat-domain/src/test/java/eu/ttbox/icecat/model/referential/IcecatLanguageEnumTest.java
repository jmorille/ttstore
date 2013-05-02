package eu.ttbox.icecat.model.referential;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class IcecatLanguageEnumTest {

	@Test
	public void testGetByLangIdValid() {
		for (IcecatLanguageEnum e : IcecatLanguageEnum.values())  {
			Integer eid = e.getLangid();
			IcecatLanguageEnum re = IcecatLanguageEnum.getByLangId(eid);
			assertEquals(e, re);
 		}
 	}

	@Test
	public void testGetByLangIdUnValid() {
		int eid = IcecatLanguageEnum.values().length+1;
		IcecatLanguageEnum re = IcecatLanguageEnum.getByLangId(eid);
		assertNull(re);
		
 	}

}
