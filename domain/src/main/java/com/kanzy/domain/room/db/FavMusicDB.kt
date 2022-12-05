package com.kanzy.domain.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kanzy.domain.room.dao.FavMusicsDao

//@Database(entities = [FavMusicsEntity::class], version = 1)
abstract class FavMusicDB : RoomDatabase() {
//    abstract fun favMusicDao(): FavMusicsDao
//    companion object {
//        private var instance: FavMusicDB? = null
//
//        fun getFavMusicsDatabase(context: Context): FavMusicDB? {
//
//            if (instance == null) {
//                instance = Room.databaseBuilder(
//                    context,
//                    FavMusicDB::class.java,
//                    "favMusics.db"
//                ).allowMainThreadQueries()
//                    .build()
//            }
//            return instance
//        }
//    }
}