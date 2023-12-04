package eif.viko.lt.appsas.bean.programele.presentation

import androidx.compose.ui.tooling.data.Group
import eif.viko.lt.appsas.bean.programele.data.remote.group_posts.GroupDto

data class GroupPostState(
        val groups: List<GroupDto> = emptyList(),
        val isLoading: Boolean = false,
        var tokenas: String = "",
        val error: String = ""
)