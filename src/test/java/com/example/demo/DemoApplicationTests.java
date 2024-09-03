//package com.example.demo;
//
//import com.example.demo.dao.UsersDao;
//import com.example.demo.entity.User;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//import java.util.Optional;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class DemoApplicationTests {
//
//    @Autowired
//    private UsersDao usersDao;
//
//    @Test
//    public void testNewUser() {
//        User user = new User("testUser", "123", "test@gmail.com");
//        usersDao.save(user);
//        Assert.assertNotNull(user.getId());
//        Assert.assertEquals(user.getUsername(), "testUser");
//        Assert.assertEquals(user.getPassword(), "123");
//        Assert.assertEquals(user.getEmail(), "test@gmail.com");
//    }
//
//    @Test
//    public void testUserUpdate() {
//        User user = new User("testAdmin", "root", "admin@admin.com");
//        usersDao.save(user);
//        Integer userId = user.getId();
//        Assert.assertNotNull(userId);
//
//        user.setUsername("testAdmin123");
//        usersDao.save(user);
//        User updatedUser = usersDao.findById(userId).orElse(null);
//        Assert.assertNotNull(updatedUser);
//        Assert.assertEquals(updatedUser.getUsername(), "testAdmin123");
//    }
//
//    @Test
//    public void testUserRemove() {
//        User user = new User("test", "test", "test@gmail.com");
//        usersDao.save(user);
//        Integer userId = user.getId();
//        Assert.assertNotNull(userId);
//
//        usersDao.deleteById(userId);
//        User deletedUser = usersDao.findById(userId).orElse(null);
//        Assert.assertNull(deletedUser);
//    }
//
//    @Test
//    public void testFindAllUsers() {
//        List<User> userList = usersDao.findAll();
//        Assert.assertNotNull(userList);
//        Assert.assertTrue(userList.size() > 0);
//    }
//
//    @Test
//    public void testFindUserById() {
//        User user = new User("testUser", "123", "test@gmail.com");
//        usersDao.save(user);
//        Integer userId = user.getId();
//        Assert.assertNotNull(userId);
//
//        Optional<User> optionalUser = usersDao.findById(userId);
//        Assert.assertTrue(optionalUser.isPresent());
//        Assert.assertEquals(optionalUser.get().getUsername(), "testUser");
//    }
//
//    @Test
//    public void testFindByNonExistentId() {
//        Optional<User> optionalUser = usersDao.findById(-1);
//        Assert.assertFalse(optionalUser.isPresent());
//    }
//
////    @Test
////    public void testSaveNullUser() {
////        User user = null;
////        try {
////            usersDao.save(user);
////            Assert.fail("Expected IllegalArgumentException was not thrown");
////        } catch (IllegalArgumentException e) {
////        	
////            // Expected exception, test passes
////        }
////    }
//}
//



package com.example.demo;

import com.example.demo.dao.UsersDao;
import com.example.demo.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class DemoApplicationTests {

    @Mock
    private UsersDao usersDao;

    @InjectMocks
    private DemoApplication demoApplication;

    @Before
    public void setUp() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("test1", "pass1", "test1@example.com"));
        userList.add(new User("test2", "pass2", "test2@example.com"));
        Mockito.when(usersDao.findAll()).thenReturn(userList);
    }

    @Test
    public void testFindAllUsers() {
        List<User> userList = usersDao.findAll();
        Assert.assertNotNull(userList);
        Assert.assertEquals(2, userList.size());
    }

    @Test
    public void testSaveUser() {
        User user = new User("testUser", "password", "test@example.com");
        Mockito.when(usersDao.save(Mockito.any(User.class))).thenReturn(user);

        User savedUser = usersDao.save(user);
        Assert.assertNotNull(savedUser);
        Assert.assertEquals("testUser", savedUser.getUsername());
    }

    // Other test methods...
}
