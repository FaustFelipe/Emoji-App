package br.com.felipefaustini.domain.usecases.googlerepos

import br.com.felipefaustini.domain.models.Repos
import br.com.felipefaustini.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface IGoogleReposUseCase {
    fun getGoogleRepos(page: Int, perPage: Int): Flow<Result<List<Repos>>>
}