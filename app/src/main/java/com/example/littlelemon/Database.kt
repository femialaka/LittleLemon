package com.example.littlelemon

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity(tableName = "menu_table")
data class MenuItem(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String
)

@Dao
interface MenuDao {

    @Query("SELECT * FROM menu_table")
    fun getMenuItems(): LiveData<List<MenuItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(menuItems: List<MenuItem>)

    @Query("DELETE FROM menu_table")
    fun deleteAll()

    @Query("SELECT (SELECT COUNT(*) FROM menu_table) == 0")
    fun isEmpty(): Boolean
}

@Database(entities = [MenuItem::class], version = 1, exportSchema = false)
abstract class MenuDatabase : RoomDatabase() {
    abstract fun menuDao(): MenuDao
}


