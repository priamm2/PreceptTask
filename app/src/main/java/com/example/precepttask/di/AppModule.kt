package com.example.precepttask.di

import android.content.Context
import androidx.room.Room
import com.example.precepttask.db.AppDatabase
import com.example.precepttask.db.PreceptFactorDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return AppDatabase.getDatabase(appContext)
    }

    @Provides
    fun providePreceptFactorDao(appDatabase: AppDatabase): PreceptFactorDao {
        return appDatabase.preceptFactorDao()
    }
}