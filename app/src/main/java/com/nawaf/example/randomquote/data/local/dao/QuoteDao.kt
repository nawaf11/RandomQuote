package com.nawaf.example.randomquote.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nawaf.example.randomquote.data.Quote

@Dao
interface QuoteDao {

    @Query("DELETE FROM Quote")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(quote: Quote)

    @Query("SELECT * FROM Quote LIMIT 1")
    fun observeSingleQuote(): LiveData<Quote?>

}