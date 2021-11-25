package br.com.felipefaustini.data.di

import br.com.felipefaustini.data.api.EmojiApi
import br.com.felipefaustini.data.repository.EmojiRepositoryImpl
import br.com.felipefaustini.domain.repository.EmojiRepository
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import br.com.felipefaustini.data.BuildConfig
import br.com.felipefaustini.data.utils.DateConverter
import com.squareup.moshi.Moshi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        return interceptor
    }

    single { provideHttpLoggingInterceptor() }

    fun provideOkhttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
        return client.build()
    }

    single { provideOkhttpClient(loggingInterceptor = get()) }

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

    single { provideRetrofit(client = get()) }

    fun provideEmojiApi(retrofit: Retrofit): EmojiApi {
        return retrofit.create(EmojiApi::class.java)
    }

    single { provideEmojiApi(retrofit = get()) }

}

val repositoryModule = module {

    fun provideRepositoryModule(emojiApi: EmojiApi): EmojiRepository {
        return EmojiRepositoryImpl(emojiApi, Dispatchers.IO)
    }

    single { provideRepositoryModule(emojiApi = get()) }

}
