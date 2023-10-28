package com.arvo.expensemanager.di

import android.app.Application
import androidx.room.Room
import com.arvo.expensemanager.data.repository.BookRepositoryImpl
import com.arvo.expensemanager.data.repository.EntryRepositoryImpl
import com.arvo.expensemanager.data.source.EMDataBase
import com.arvo.expensemanager.domain.repository.BookRepository
import com.arvo.expensemanager.domain.repository.EntryRepository
import com.arvo.expensemanager.domain.usecase.BookUseCases
import com.arvo.expensemanager.domain.usecase.EntryUseCases
import com.arvo.expensemanager.domain.usecase.book.DeleteBook
import com.arvo.expensemanager.domain.usecase.book.GetBook
import com.arvo.expensemanager.domain.usecase.book.GetBooks
import com.arvo.expensemanager.domain.usecase.book.InsertBook
import com.arvo.expensemanager.domain.usecase.entry.DeleteEntry
import com.arvo.expensemanager.domain.usecase.entry.GetEntries
import com.arvo.expensemanager.domain.usecase.entry.GetEntriesByGroup
import com.arvo.expensemanager.domain.usecase.entry.GetEntry
import com.arvo.expensemanager.domain.usecase.entry.InsertEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideEMDataBase(app: Application): EMDataBase{
        return Room.databaseBuilder(
            app,
            EMDataBase::class.java,
            EMDataBase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providerBookRepository(db: EMDataBase): BookRepository{
        return BookRepositoryImpl(db.bookDao)
    }

    @Provides
    @Singleton
    fun providerEntryRepository(db: EMDataBase): EntryRepository{
        return EntryRepositoryImpl(db.entryDao)
    }

    @Provides
    @Singleton
    fun providerBookUseCases(repository: BookRepository): BookUseCases{
        return BookUseCases(
            getBooks = GetBooks(repository),
            getBook = GetBook(repository),
            insertBook = InsertBook(repository),
            deleteBook = DeleteBook(repository)
        )
    }

    @Provides
    @Singleton
    fun providerEntryUseCases(repository: EntryRepository): EntryUseCases{
        return EntryUseCases(
            getEntries = GetEntries(repository),
            getEntriesByGroup = GetEntriesByGroup(repository),
            getEntry = GetEntry(repository),
            insertEntry = InsertEntry(repository),
            deleteEntry = DeleteEntry(repository)
        )
    }
}