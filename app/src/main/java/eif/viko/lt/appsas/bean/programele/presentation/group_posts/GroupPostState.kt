package eif.viko.lt.appsas.bean.programele.presentation.group_posts

import eif.viko.lt.appsas.bean.programele.data.remote.group_posts.GroupDto

data class GroupPostState(
        val groups: List<GroupDto> = emptyList(),
        val isLoading: Boolean = false,
        var tokenas: String = "",
        val error: String = ""
)