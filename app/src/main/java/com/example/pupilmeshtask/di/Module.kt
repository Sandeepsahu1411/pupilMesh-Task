package com.example.pupilmeshtask.di

import android.content.Context
import androidx.room.Room
import com.example.pupilmeshtask.UserPreferenceManager
import com.example.pupilmeshtask.data_layer.api.ApiProvider.api
import com.example.pupilmeshtask.data_layer.api.ApiService
import com.example.pupilmeshtask.data_layer.room_db.AppDatabase
import com.example.pupilmeshtask.data_layer.room_db.dao.UserDao
import com.example.pupilmeshtask.doman_layer.Repo
import com.example.pupilmeshtask.doman_layer.RepoImpl
import com.example.pupilmeshtask.doman_layer.use_case.GetMangaListUseCase
import com.example.pupilmeshtask.doman_layer.use_case.LoginUseCase
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
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_db"
        ).build()
    }


    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    @Singleton
    fun provideUserPreferenceManager(
        @ApplicationContext context: Context
    ): UserPreferenceManager {
        return UserPreferenceManager(context)
    }

    @Provides
    @Singleton
    fun provideRepo(
        userDao: UserDao,
        preferences: UserPreferenceManager,
        api: ApiService
    ): Repo {
        return RepoImpl(userDao, preferences, api)
    }


    @Provides
    fun provideLoginUseCase(repo: Repo): LoginUseCase {
        return LoginUseCase(repo)
    }
    @Provides
    fun provideMangaListUseCase(repo: Repo): GetMangaListUseCase {
        return GetMangaListUseCase(repo)
    }
    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return api()
    }

}
