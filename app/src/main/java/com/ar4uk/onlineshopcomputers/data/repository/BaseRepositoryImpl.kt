package com.ar4uk.onlineshopcomputers.data.repository

import com.ar4uk.onlineshopcomputers.core.Constants.URL
import com.ar4uk.onlineshopcomputers.domain.model.Banner
import com.ar4uk.onlineshopcomputers.domain.model.Response.Success
import com.ar4uk.onlineshopcomputers.domain.model.Response.Failure
import com.ar4uk.onlineshopcomputers.domain.repository.BaseRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.QueryDocumentSnapshot
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BaseRepositoryImpl @Inject constructor(
    private val bannerRef: CollectionReference
): BaseRepository {
    override fun getBanners() = callbackFlow {
        val snapshotListener = bannerRef.addSnapshotListener { bannersSnapshot, e ->
            val booksResponse = if (bannersSnapshot != null) {
                val books = bannersSnapshot.map { bannerSnapshot ->
                    bannerSnapshot.toBanner()
                }
                Success(books)
            } else {
                Failure(e)
            }
            trySend(booksResponse)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

//    override suspend fun addBook(book: Map<String, Any>) = try {
//        booksRef.add(book).await()
//        Success(true)
//    } catch (e: Exception) {
//        Failure(e)
//    }
//
//    override suspend fun getBookById(id: String) = try {
//        val book = booksRef.document(id).get().await().toObject(Book::class.java)?.apply {
//            this.id = id
//        }
//        Success(book)
//    } catch (e: Exception) {
//        Failure(e)
//    }
//
//    override suspend fun updateBook(id: String, book: MutableMap<String, Any>) = try {
//        booksRef.document(id).update(book).await()
//        Success(true)
//    } catch (e: Exception) {
//        Failure(e)
//    }
//
//    override suspend fun deleteBookById(id: String) = try {
//        booksRef.document(id).delete().await()
//        Success(true)
//    } catch (e: Exception) {
//        Failure(e)
//    }
}

fun QueryDocumentSnapshot.toBanner() = Banner(
    url = getString(URL)
).apply {
    id = getId()
}