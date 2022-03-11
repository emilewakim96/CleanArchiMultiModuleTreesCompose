package com.example.cleanarchimultimoduletreescompose.presentation.event

import com.example.domain.entities.TreeEntity

sealed class TreeEvents: Event {
    data class OnDelete(val tree: TreeEntity): TreeEvents()
    data class OnAdd(val tree: TreeEntity): TreeEvents()
}
