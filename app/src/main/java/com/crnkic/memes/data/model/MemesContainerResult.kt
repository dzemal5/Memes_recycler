package com.crnkic.memes.data.model

import java.lang.Error

sealed class MemesContainerResult {
    class Success(val memes: List<Memes>) : MemesContainerResult()
    class Failure(val error: Error) : MemesContainerResult()
    object IsLoading : MemesContainerResult()
}
