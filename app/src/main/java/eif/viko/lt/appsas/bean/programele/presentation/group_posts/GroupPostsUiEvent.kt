package eif.viko.lt.appsas.bean.programele.presentation.group_posts


sealed class GroupPostsUiEvent {
    data class JoinToGroup(val groupId: Int, val userId: Int): GroupPostsUiEvent()
    object LeaveGroup: GroupPostsUiEvent()
}
