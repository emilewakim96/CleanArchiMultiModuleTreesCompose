package com.example.data.data_source.local.helper

import com.example.data.data_source.local.entity.TreeRealm
import com.example.data.data_source.remote.mappers.mapToTree
import com.example.domain.models.Tree
import io.realm.Realm
import io.realm.kotlin.executeTransactionAwait
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


class RealmDBOperations @Inject constructor(
    private var realm: Realm
) {

    suspend fun insertTree(treeRealm: TreeRealm) {
        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            realmTransaction.insertOrUpdate(treeRealm)
        }
    }

    suspend fun getTrees(): List<Tree> {
        val trees = mutableListOf<Tree>()

        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            trees.addAll(realmTransaction
                .where(TreeRealm::class.java)
                .findAll()
                .map {
                    it.mapToTree()
                }
            )
        }
        return trees
    }
}
