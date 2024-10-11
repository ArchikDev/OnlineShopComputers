package com.ar4uk.onlineshopcomputers.domain.repository

import com.ar4uk.onlineshopcomputers.domain.model.Banner
import com.ar4uk.onlineshopcomputers.domain.model.Response
import kotlinx.coroutines.flow.Flow

typealias Books = List<Banner>
typealias BooksResponse = Response<Books>
//typealias AddBookResponse = Response<Boolean>
//typealias GetBookResponse = Response<Book>
//typealias UpdateBookResponse = Response<Boolean>
//typealias DeleteBookResponse = Response<Boolean>

interface BaseRepository {
    fun getBanners(): Flow<BooksResponse>

//    suspend fun addBook(book: Map<String, Any>): AddBookResponse
//
//    suspend fun getBookById(id: String): GetBookResponse
//
//    suspend fun updateBook(id: String, book: MutableMap<String, Any>): UpdateBookResponse
//
//    suspend fun deleteBookById(id: String): DeleteBookResponse
}