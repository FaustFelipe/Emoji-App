package br.com.felipefaustini.data.models.mappers

import br.com.felipefaustini.data.models.response.UserResponse
import br.com.felipefaustini.domain.models.User

object UserMapper {

    fun map(userResponse: UserResponse?) = User(
         login = userResponse?.login ?: "",
         id  = userResponse?.id ?: -1,
         nodeId  = userResponse?.nodeId ?: "",
         avatarUrl  = userResponse?.avatarUrl ?: "",
         gravatarUrl  = userResponse?.gravatarUrl ?: "",
         url  = userResponse?.url ?: "",
         htmlUrl = userResponse?.htmlUrl ?: "",
         followersUrl = userResponse?.followersUrl ?: "",
         followingUrl = userResponse?.followingUrl ?: "",
         gistsUrl = userResponse?.gistsUrl ?: "",
         starredUrl = userResponse?.starredUrl ?: "",
         subscriptionsUrl = userResponse?.subscriptionsUrl ?:"",
         organizationsUrl = userResponse?.organizationsUrl ?: "",
         reposUrl = userResponse?.reposUrl ?: "",
         type = userResponse?.type ?: "",
         siteAdmin = userResponse?.siteAdmin ?: false,
         name = userResponse?.name ?: "",
         company = userResponse?.company ?: "",
         blog = userResponse?.blog ?: "",
         location = userResponse?.location ?: "",
         email = userResponse?.email ?: "",
         bio = userResponse?.bio ?: "",
         createdAt = userResponse?.createdAt,
         updatedAt = userResponse?.updatedAt
    )

}