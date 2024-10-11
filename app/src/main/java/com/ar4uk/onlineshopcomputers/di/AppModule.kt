package com.ar4uk.onlineshopcomputers.di

import com.ar4uk.onlineshopcomputers.core.Constants.BANNER
import com.ar4uk.onlineshopcomputers.data.repository.BaseRepositoryImpl
import com.ar4uk.onlineshopcomputers.domain.repository.BaseRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideFirebaseFirestore() = Firebase.firestore

    @Provides
    fun provideBannerRef(db: FirebaseFirestore) = db.collection(BANNER)

    @Provides
    fun provideBaseRepository(
        bannerRef: CollectionReference
    ): BaseRepository = BaseRepositoryImpl(bannerRef)
}