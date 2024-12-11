package com.example.togedy_android.data.mapper.todomain

import com.example.togedy_android.data.remote.model.response.BoardDetailResponseDto
import com.example.togedy_android.data.remote.model.response.DetailCommentsDto
import com.example.togedy_android.data.remote.model.response.DetailRepliesDto
import com.example.togedy_android.domain.model.BoardDetail
import com.example.togedy_android.domain.model.DetailComments
import com.example.togedy_android.domain.model.DetailReplies

fun BoardDetailResponseDto.toDomain(): BoardDetail = BoardDetail(
    title = this.title,
    createdAt = this.createdAt,
    content = this.content,
    postImages = this.postImages,
    likeCount = this.likeCount,
    commentCount = this.commentCount,
    comments = this.comments.map { it.toDomain() },
    postLike = this.postLike
)

fun DetailCommentsDto.toDomain(): DetailComments = DetailComments(
    commentId = this.commentId,
    userProfileImg = this.userProfileImg,
    userName = this.userName,
    content = this.content,
    likeCount = this.likeCount,
    replies = this.replies.map { it.toDomain() },
    commentLike = this.commentLike
)

fun DetailRepliesDto.toDomain(): DetailReplies = DetailReplies(
    commentId = this.commentId,
    userProfileImg = this.userProfileImg,
    userName = this.userName,
    content = this.content,
    likeCount = this.likeCount,
    replies = this.replies,
    commentLike = this.commentLike
)
