package eu.ttbox.service.user;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import eu.ttbox.model.user.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class UserServiceTest extends AbstractDbServiceTest {

	@Autowired
	UserService userService;

	Long userId = Long.valueOf(1);

	@Test
	public void testGetById() {
		User user = userService.getById(userId);
		Assert.assertNotNull(user);
		Assert.assertEquals(userId, user.getId());
		Assert.assertEquals("Morille", user.getLastName());
	}

	@Test
	public void testMerge() {
		User user = userService.getById(userId);
		Assert.assertNotNull(user);
		Assert.assertEquals(userId, user.getId());
		Assert.assertEquals("Morille", user.getLastName());
		// DO modif
		Integer version = user.getVersion();
		user.setLastName("Maurille");
		Assert.assertEquals(version, user.getVersion());
		userService.merge(user);
		// Assert.assertEquals(Long.valueOf(version.longValue() + 1),
		// user.getVersion());
		Assert.assertEquals(userId, user.getId());
		Assert.assertEquals("Maurille", user.getLastName());
		// Get It again
		user = userService.getById(userId);
		Assert.assertEquals(Long.valueOf(version.longValue() + 1), user.getVersion());
		Assert.assertEquals(userId, user.getId());
		Assert.assertEquals("Maurille", user.getLastName());
		// DO modif again
		version = user.getVersion();
		user.setLastName("Morille");
		Assert.assertEquals(version, user.getVersion());
		userService.merge(user);
		// Assert.assertEquals(Long.valueOf(version.longValue() + 1),
		// user.getVersion());
		Assert.assertEquals(userId, user.getId());
		Assert.assertEquals("Morille", user.getLastName());
		// Get It again
		user = userService.getById(userId);
		Assert.assertEquals(Long.valueOf(version.longValue() + 1), user.getVersion());
		Assert.assertEquals(userId, user.getId());
		Assert.assertEquals("Morille", user.getLastName());
	}

}
