package com.nawaf.example.randomquote.di

import com.nawaf.example.randomquote.data.local.dao.QuoteDao
import com.nawaf.example.randomquote.data.remote.QuoteService
import com.nawaf.example.randomquote.data.repository.QuoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

  @Singleton
  @Provides
  fun provideQuoteRepository(quoteService : QuoteService, quoteDao: QuoteDao) : QuoteRepository {
    return QuoteRepository(quoteService, quoteDao)
  }

  @Singleton
  @Provides
  fun provideIoDispatcher() = Dispatchers.IO


}
