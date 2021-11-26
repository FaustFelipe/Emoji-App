package br.com.felipefaustini.domain.usecases.googlerepos

import br.com.felipefaustini.domain.models.Repos
import br.com.felipefaustini.domain.repository.EmojiRepository
import br.com.felipefaustini.domain.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GoogleReposUseCase @Inject constructor(
    private val repository: EmojiRepository
): IGoogleReposUseCase {

    override fun getGoogleRepos(page: Int, perPage: Int): Flow<Result<List<Repos>>> {
        return repository.getUserRepos("google", page, perPage)
    }

}