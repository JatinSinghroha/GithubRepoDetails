package com.jatinsinghroha.githubrepodetails.room

import androidx.room.*
import com.jatinsinghroha.githubrepodetails.models.reposmodel.BranchItem

@Dao
interface BranchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg branchItems: BranchItem?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOne(branchItem: BranchItem?)

    @Query("SELECT COUNT(*) FROM BranchItem")
    fun count(): Int

    @Query("SELECT * FROM BranchItem")
    fun getAll(): List<BranchItem?>?

    @Query("SELECT * FROM BranchItem WHERE id =:id")
    fun getSingleRecord(id: Long?): BranchItem?

    @Query("SELECT * FROM BranchItem WHERE id =:id")
    fun getAllRecordWithID(id: Long?): List<BranchItem?>?

    @Delete
    fun delete(branchItem: BranchItem?)

    @Query("DELETE FROM BranchItem")
    fun clear()
}