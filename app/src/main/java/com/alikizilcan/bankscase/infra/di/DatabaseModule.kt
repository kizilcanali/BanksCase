package com.alikizilcan.bankscase.infra.di

import android.content.Context
import androidx.room.Room
import com.alikizilcan.bankscase.data.local.AppDatabase
import com.alikizilcan.bankscase.data.local.BankBranchesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object DatabaseModule {

    @ActivityRetainedScoped
    @Provides
    fun getDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "app-database").build()

    @ActivityRetainedScoped
    @Provides
    fun getDao(appDatabase: AppDatabase): BankBranchesDao {
        return appDatabase.bankBranchesDao()
    }
}