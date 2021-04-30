package com.jatinsinghroha.githubrepodetails.room

import androidx.room.*
import com.jatinsinghroha.githubrepodetails.models.reposmodel.Repo

@Dao
interface RepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg repos: Repo?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOne(repo: Repo?)

    @Query("SELECT COUNT(*) FROM Repo")
    fun count(): Int

    @Query("SELECT * FROM Repo")
    fun getAll(): List<Repo?>?

    @Query("SELECT * FROM Repo WHERE id =:id")
    fun getSingleRecord(id: Long?): Repo?

    @Delete
    fun delete(repo: Repo?)

    @Query("DELETE FROM Repo")
    fun clear()
}