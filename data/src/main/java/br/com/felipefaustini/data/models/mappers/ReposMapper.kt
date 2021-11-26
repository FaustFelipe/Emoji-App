package br.com.felipefaustini.data.models.mappers

import br.com.felipefaustini.data.models.response.ReposResponse
import br.com.felipefaustini.domain.models.Repos

object ReposMapper {

    fun map(reposReposResponse: ReposResponse?) = Repos(
        id = reposReposResponse?.id ?: -1L,
        nodeId = reposReposResponse?.nodeId ?: "",
        htmlUrl = reposReposResponse?.htmlUrl ?: ""
    )

}