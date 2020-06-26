package com.nawaf.example.randomquote.di

import android.app.Application
import com.nawaf.example.randomquote.data.local.AppDatabase
import com.nawaf.example.randomquote.data.local.dao.QuoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object PersistenceModule {

  @Singleton
  @Provides
  fun provideQuoteDao(db: AppDatabase) : QuoteDao = db.quoteDao()

  @Singleton
  @Provides
  fun provideDataBase(application: Application): AppDatabase {
    return AppDatabase.build(application)
  }

}
