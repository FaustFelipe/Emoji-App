package br.com.felipefaustini.data.di

import br.com.felipefaustini.data.BuildConfig
import br.com.felipefaustini.data.api.EmojiApi
import br.com.felipefaustini.data.utils.DateConverter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class APIModuleDI {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        return interceptor
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
        return client.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        val baseUrl = BuildConfig.BASE_URL

        val moshi = Moshi.Builder()
            .add(DateConverter())
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideEmojiApi(retrofit: Retrofit): EmojiApi {
        return retrofit.create(EmojiApi::class.java)
    }

}