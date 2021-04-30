package com.jatinsinghroha.githubrepodetails.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jatinsinghroha.githubrepodetails.models.reposmodel.BranchItem
import com.jatinsinghroha.githubrepodetails.models.reposmodel.Repo

@Database(entities = [Repo::class, BranchItem::class], version = 7 ,exportSchema = false)
abstract class RepoDatabase : RoomDatabase() {

    abstract fun repoDao(): RepoDao

    abstract fun branchDao() : BranchDao

    companion object {

        private var repoDatabase: RepoDatabase? = null

        fun getInstance(context: Context): RepoDatabase {
            if (repoDatabase == null) {
                repoDatabase =
                        Room.databaseBuilder(context.applicationContext, RepoDatabase::class.java, "reposDatabase.db")
                                .allowMainThreadQueries()
                                .fallbackToDestructiveMigration()
                                .build()
            }
            return repoDatabase!!
        }
    }

}