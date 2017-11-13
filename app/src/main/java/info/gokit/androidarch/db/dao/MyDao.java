package info.gokit.androidarch.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import info.gokit.androidarch.db.entity.User;

/**
 * Created by llitfkitfk on 11/13/17.
 */

@Dao
public interface MyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(User... users);

    @Insert
    void insertBothUsers(User user1, User user2);

    @Insert
    void insertUsersAndFriends(User user, List<User> friends);

    @Update
    void updateUsers(User... users);

    @Delete
    void deleteUsers(User... users);

    @Query("SELECT * FROM user")
    public User[] loadAllUsers();

    @Query("SELECT * FROM user WHERE age > :minAge")
    public User[] loadAllUsersOlderThan(int minAge);

    @Query("SELECT * FROM user WHERE age BETWEEN :minAge AND :maxAge")
    public User[] loadAllUsersBetweenAges(int minAge, int maxAge);

    @Query("SELECT * FROM user WHERE first_name LIKE :search " + "OR last_name LIKE :search")
    public List<User> findUserWithName(String search);

}
